package compiler.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * The GCC Parser extracts Error messages and warnings from the GCC output.
 * 
 * @author Richard Hill
 */

public class GccParser 
{
	private File f;
	//Maintain a list of all of the GCC Errors and Warnings generated. 
	private ArrayList<GccError> listOfErrors = new ArrayList<GccError>();
	
	/**
	 * The GccParser constructor.
	 * 
	 * @param f		The file object to parse.
	 */
	public GccParser(File f)
	{
		this.f = f;
		readFromFile();
	}
	
	/**
	 * This method starts off the parsing by opening and reading from the file.
	 */
	public void readFromFile()
	{
		try{
			FileReader fr = new FileReader(f);
			BufferedReader reader = new BufferedReader(fr);
			LineNumberReader lr = new LineNumberReader(reader);
			
			String input = lr.readLine();
			input = input.trim();
			GccError curErr;
			if(input.contains("In function")){
				curErr = parseInFunction(input);
				lr = getThisFunctionsErrors(lr, curErr);
			}
			else{
				//Error(s) are at top level
				curErr = new GccError(input.substring(0, input.indexOf(":")), "At Top Level");
				while(!(input.contains("In function"))){
					dealWithSingleLine(input, curErr);
					input = lr.readLine();
					//System.out.println(input);
					if(input == null){
						break; //break from while loop - finished parsing!
					}
				}
				//Should have read all the top level error by now.
				if(input != null){
					if(input.contains("In function")){
						//A new functions errors are not be processed
						GccError nexErr;
						nexErr = parseInFunction(input);
						lr = getThisFunctionsErrors(lr, nexErr);
					}
				}
			}
			
			lr.close();
			reader.close();
			fr.close();
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Generates a GccError.
	 * 
	 * @param input		The string to analyse.
	 * @return			The GccError object generated from the input string.
	 */
	private GccError parseInFunction(String input)
	{
		if(input.contains("In function")){
			//double check line passed.
		}
		else{
			System.out.println("ncc: Fatal Error! GCC Output not recognised!");
			System.exit(1);
		}
		String fName = input.substring(0, input.indexOf(":"));
		StringTokenizer st = new StringTokenizer(input, " ");
		String funName = "";
		while(st.hasMoreTokens()){
			funName = st.nextToken();
		}
		funName = funName.substring(1, funName.length()-2);
		return new GccError(fName, funName);
	}
	
	/**
	 * Gets the errors for a particular function.
	 * 
	 * @param lr	The LineNumberReader we're working with.
	 * @return		The LineNumberReader we're working with.
	 */
	private LineNumberReader getThisFunctionsErrors(LineNumberReader lr, GccError curErr)
	{
		try{
			String input = lr.readLine();
			//System.out.println(input);
			while(!(input.contains("In function"))){
				/*
				 * While in this loop input does not declare errors for a new function.
				 * Add line of error message until loop condition fails.
				 */
				dealWithSingleLine(input, curErr);
				input = lr.readLine();
				//System.out.println(input);
				if(input == null){
					break; //break from while loop
				}
			}
			if(input != null){
				if(input.contains("In function")){
					//A new functions errors are not be processed
					GccError nexErr;
					nexErr = parseInFunction(input);
					lr = getThisFunctionsErrors(lr, nexErr);
				}
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		return lr;
	}
	
	/**
	 * Deals with a single line of gcc error.
	 * 
	 * @param input		The line to deal with.
	 * @param curErr	High level error details.
	 */
	public void dealWithSingleLine(String input, GccError curErr)
	{
		GccError err;
		String t = input;
		String lineNo = "";
		try{
			t = input.substring(input.indexOf(":")+1);
			lineNo = t.substring(0, t.indexOf(":"));
		}
		catch(StringIndexOutOfBoundsException sioobe){
			//possible linker error! Can't do anything about this - just ignore it!
			if(t.contains("undefined reference to `main'")){
				//the user has omitted a main function
				listOfErrors.add(new GccError("*.c", 0, "undefined reference to `main'", 111));
			}
		}
		try{
			err = new GccError(curErr.getFileName(), curErr.getFunctionName(), Integer.parseInt(lineNo));
		}
		catch(NumberFormatException nfe){
			err = new GccError(curErr.getFileName(), curErr.getFunctionName());
		}
		try{
			err.addText(t.substring(t.indexOf(":")+2));
		}
		catch(StringIndexOutOfBoundsException sioobe){
			err.addText(t);
		}
		setErrorNumber(err);
		listOfErrors.add(err);
	}
	
	/**
	 * Extracts and sets the correct GCC Error number.
	 */
	public void setErrorNumber(GccError err)
	{
		/*
		 * GCC handles syntax errors so poorly by default that we have to 
		 * declare a single errorNumber for all syntax errors. The number
		 * chosen for this was 999 and this means that all error messages
		 * that GCC output that contain the text "syntax error" have to be
		 * given the error number 999. It also destroys the...
		 * There are two other generic error numbers to be aware of, the first
		 * is 0 which is set on GCC errors when they are not handled by NCC.
		 * The second is -1 which is set on GCC errors that are associated with
		 * errors NCC handles but which do not mean anything on their own. An
		 * error with an error number of -1 will be discarded at print stage. 
		 */
		if(err.getMessage().contains("syntax error")){
			err.setErrorNumber(999);
		}
		//Error Number 121 is a specific case of Error Number 101 so must be caught before the generic version (101)
		else if(err.getMessage().equals("error: too many arguments to function `getchar'")){
			err.setErrorNumber(121);
		}
		//Error Number 122 can also be a specific case of Error Number 101 so must be caught before the generic version (101)
		else if( (err.getMessage().equals("error: too few arguments to function `putchar'")) || (err.getMessage().equals("error: too many arguments to function `putchar'")) ){
			err.setErrorNumber(122);
		}
		else if( (err.getMessage().startsWith("error")) && 
				( (err.getMessage().contains("too")) && (err.getMessage().contains("arguments to function")) ) ){
			//if 101 stops working for no apparent reason it's because I swapped the second && (originally was an ||)
			err.setErrorNumber(101);
		}
		//102 - Print Regardless
		//103 - Print Regardless
		else if( (err.getMessage().contains("implicit declaration of function")) 
				|| (err.getMessage().contains("previous implicit declaration of")) 
				|| (err.getMessage().contains("error: conflicting types for '"))
				|| (err.getMessage().contains("note: an argument type that has a default promotion can't match an empty parameter name list declaration")) ){
			err.setErrorNumber(104);
		}
		//105 - Syntax Error = 999
		//106 - Syntax Error = 999
		//107 - Syntax Error = 999
		//108 - Syntax Error = 999
		else if(err.getMessage().contains("redeclared as different kind of symbol")){
			err.setErrorNumber(109);
		}
		else if( (err.getMessage().contains("redefinition of")) 
				|| err.getMessage().contains(("error: redeclaration of")) ){
			err.setErrorNumber(110);
		}
		else if( (err.getMessage().contains("previous definition of")) 
				&& (err.getMessage().contains("was here")) ){
			//Related to 109, 110 & 126, but we can discard it
			err.setErrorNumber(-1);
		}
		else if(err.getMessage().contains("undefined reference to `main'")){
			err.setErrorNumber(111);
		}
		//112 - Print Regardless
		//113 - Print Regardless
		else if( (err.getMessage().contains("stray")) && (err.getMessage().contains("in program")) ){
			err.setErrorNumber(114);
		}
		//115 - Print Regardless (NCC Warning)
		//116 - Print Regardless (NCC Warning)
		//117 - Print Regardless (NCC Warning)
		//118 - Print Regardless (NCC Warning)
		//119 - Print Regardless (NCC Warning)
		//120 - Print Regardless (NCC Warning)
		//121 - See first else if above
		else if(err.getMessage().contains("passing arg 1 of `putchar' makes")){
			err.setErrorNumber(122); //Also see second else if above
		}
		//122 - Print Regardless
		else if(err.getMessage().contains("warning: unknown conversion type character")){
			err.setErrorNumber(123);
		}
		else if( (err.getMessage().startsWith("warning")) && 
				( (err.getMessage().contains("too")) && (err.getMessage().contains("arguments for format")) ) ){
			err.setErrorNumber(123);
		}
		else if( ((err.getMessage().startsWith("warning"))) && (err.getMessage().contains("format, different type arg")) ){
			err.setErrorNumber(123);
		}
		else if(err.getMessage().contains("warning: format argument is not a pointer")){
			err.setErrorNumber(123);
		}
		//124 - scanf and printf errors are not differentiated by GCC so all have to be lumped together
		else if( (err.getMessage().contains("undeclared (first use in this function)")) 
				|| (err.getMessage().contains("error: (Each undeclared identifier is reported only once")) 
				|| (err.getMessage().contains("error: for each function it appears in.)")) ){
			err.setErrorNumber(125);
		}
		//126 - caught by error code 104 but differentiated
		//else if(err.getMessage().contains("")){
		//	err.setErrorNumber();
		//}
		else{
			//ignore this message
			err.setErrorNumber(0);
		}
	}
	
	/**
	 * Returns the list of GCC Error messages and Warnings generated.
	 * 
	 * @return	The list of GCC Error messages and Warnings generated.
	 */
	public ArrayList<GccError> getGccErrors()
	{
		return listOfErrors;
	}
}
