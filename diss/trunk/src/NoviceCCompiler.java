import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

import compiler.tools.CommandLineOptions;
import compiler.tools.NccCLibraries;
import compiler.tools.NccError;
import compiler.tools.NccFunction;
import compiler.tools.NccPostProcessorJob;
import compiler.tools.NccPostProcessor;
import compiler.tools.NccSourceHandler;
import compiler.tools.NccSymbolTable;
import compiler.tools.NccValidTokens;

import org.antlr.runtime.*;

public class NoviceCCompiler 
{
	private CommandLineOptions clo;
	/*
	 * Notes: (1)	The treatment of functions and Symbol Tables in the 
	 * lexANDParse method is different because the list of functions (and types
	 * for that matter) could be populated with functions (or types) from 
	 * #included files. Symbol tables on the other hand don't have to be added, 
	 * they can just be set.
	 */
	private ArrayList<NccFunction> functions = new ArrayList<NccFunction>();
	private ArrayList<NccSymbolTable> listSymTabs = new ArrayList<NccSymbolTable>(); 
	private ArrayList<NccError> nccErrors = new ArrayList<NccError>();
	private HashSet<String> passingTypes = new HashSet<String>();
	private PrintStream currentOut = System.out;
	private PrintStream currentErr = System.err;
	private File errFile;
	private NccValidTokens vt = new NccValidTokens();
	
	/**
	 * The constructor for the Antlr Implementation of the Novice C Compiler
	 * 
	 * @param f			The filename to parse.
	 * @param clo		The set of command line options. 
	 */
	public NoviceCCompiler(CommandLineOptions clo)
	{
		//Begin the Lexer and Parser
		this.clo = clo;
		loadStandardCFunctions();
		preprocess();
    	lexANDParse(clo.fName, true);
    	//Once the parsing is complete tidy up the errFile
    	tidyErrFile();
    	parseTheErrFile();
    	if(clo.scopeDetail){
    		printScopeDetails();
    	}
    	/*
    	 * Finally, if debugging and you need to print the list of NCC errors
    	 * generated here then if might to order them first, they will be 
    	 * ordered again later in the AllErrorPrinter class but ordering them
    	 * here might help clear things up. After they are ordered they can be
    	 * printed using the local method.
    	 */ 
    	//nccErrors = orderNccErrors(nccErrors);
    	//printNccErrors();
	}
	
	/**
	 * Return the list of NCC generated errors.
	 * 
	 * @return	The list of NccErrors.
	 */
	public ArrayList<NccError> getNccErrors()
	{
		return nccErrors;
	}
	
	/**
	 * Load the Standard C functions if they're included.
	 */
	public void loadStandardCFunctions()
	{
		NccCLibraries cl = new NccCLibraries();
		try{
			File scanFile = new File(clo.fName);
            FileReader fileR = new FileReader(scanFile);
            BufferedReader reader = new BufferedReader(fileR);
            
            String textInput = null;
            int lineNo = 0;
            while((textInput = reader.readLine()) != null){
            	lineNo++;
            	textInput = textInput.trim();
            	if(textInput.startsWith("#")){
            		textInput = textInput.substring(1).trim();
            		if(textInput.startsWith("include")){
            			char[] charArray = textInput.toCharArray();
            			for(int x = 0; x < charArray.length; x++){
            				if(Character.toString(charArray[x]).equals("<")){
            					//System.out.println(textInput);
            					int q = textInput.length()-1;
            					String funs = textInput.substring(x+1, q);
            					if(!funs.endsWith(".h")){
            						System.out.println("Warning: Line " + lineNo + ": the #include for <" + funs + "> does not end .h");
            						funs = funs.concat(".h");
            					}
            					//System.out.println("Line:" + lineNo + ": Including definitions from: " + funs);
            					//http://www.opengroup.org/onlinepubs/009695399/basedefs/
            					cl.implementLibrary(funs); //Can cause the program to exit here after printing an error message
            					
            					populateLists("StandardCLibraries/" + funs);
            				}
            				else if(Character.toString(charArray[x]).equals("\"")){
            					int q = textInput.length()-1;
            					String funs = textInput.substring(x+1, q);
            					x = charArray.length; //Break from the loop - avoids problem with second " char
            					System.out.println("Error: Line " + lineNo + ": Illegal library inclusion!");
            					System.out.println("DIAGNOSIS: Unfortunately NCC does not support" +
            									   " user defined header files like #include \"" + funs + "\"");
            					System.out.println("For help type: java ncc --help");
            					System.exit(1);
            				}
            			}
            		}
            	}
            }
            reader.close();
            fileR.close();
        }
        catch(IOException e){
            System.out.println("IOException reading from file: " + e.getMessage());
        }
	}

	/**
	 * Populate the list of included typedefs and functions. 
	 * 
	 * @param s	The file to populate from.
	 */
	public void populateLists(String s)
	{
		try{
			InputStream is = clo.cl.getResourceAsStream(s);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			String textInput = null;
            while((textInput = reader.readLine()) != null){
            	textInput = textInput.trim();
            	if(textInput.startsWith("typedef:")){
            		textInput = textInput.substring(8).trim();
            		passingTypes.add(textInput);
            	}
            	else if(textInput.startsWith("function:")){
            		textInput = textInput.substring(9).trim();
            		functions.add(new NccFunction(textInput, true));
            	}
            	else{
            		//The line has no meaning!
            	}
            }
			
			reader.close();
		}
		catch(IOException e){
			System.out.println("IOException reading from file: " + e.getMessage());
		}
	}

	/**
	 * Preprocess the input ready for the full parse.
	 */
	public void preprocess()
	{
		disableOutStream();
		lexANDParse(clo.fName, false);
		//Once the first run is done we can restore the normal streams
		enableOutStream();
	}

	/**
     * Starts the lexer and parser.
     * 
     * @param fName	The file name to read from.
     * @param p		A boolean, false for 1st parse, true for 2nd.
     */
    public void lexANDParse(String fName, boolean p)
    {
    	try{
	    	CLexer lex = new CLexer(new ANTLRFileStream(fName));
	        CommonTokenStream tokens = new CommonTokenStream(lex);
	        CParser g = new CParser(tokens);
	
	        try{
	        	//Pass the Command Line Options
	        	g.setCommandLineOptions(clo);
	        	//Set source printing - default now true
	        	g.setupSourcePrinting(true);
	        	//Set the preparsed variable
	        	g.setPreparsed(p);
	        	if(p){
	        		//If the file has been parsed before then specify all the functions
	        		g.setCompleteListOfFunctions(functions);
	        		//If the file has been parsed before then set the list of symbol tables (scopes)
	        		g.setEntireListOfSymbols(listSymTabs);
	        		//If the file has been parsed before then specify the list of available types
	        		Iterator<String> iter = passingTypes.iterator();
	        		while(iter.hasNext()){
	        			String temp = iter.next();
	        			g.addPassingTypes(temp);
	        		}
	        	}
	        	disableErrStream();
	        	//Do the actual parsing
	            g.translation_unit();
	            enableErrStream();
	            if(p){
		            //Get the list of NccError generated
		            nccErrors = g.getNccErrors();
		            
		            //Get the list of post processor jobs
		            ArrayList<NccPostProcessorJob> nppjList = g.getListOfJobs();
		            NccPostProcessor npp = new NccPostProcessor(clo);
		            for(int i = 0; i < nppjList.size(); i++){
		            	NccError e = npp.processJob(nppjList.get(i));
		            	if(e != null){
		            		nccErrors.add(e);
		            	}
		            	else{
		            		//There was no error! 
		            	}
		            }
		            
		            //Print the list of functions
		            //g.printGeneratedFunctions();
	            }
	            else{
		            //Get the final list of function
	            	addToListOfFunctions(g.getFunctions());
	            	
	            	//Get the final list of symbol tables (scopes)
	            	listSymTabs = g.getEntireListOfSymbols();
	            }
	            //check main is a function.
	            checkMainPresent();
	        } 
	        catch(RecognitionException e) {
	            e.printStackTrace();
	        }
    	}
    	catch(IOException e){
    		System.out.println("ANTLRFileStream threw IOException: Error opening input file");
    	}
    }
    
    /**
     * Checks that main is a declared function, outputs an appropriate error
     * message if it is not. 
     */
    public void checkMainPresent()
    {
    	boolean mainPresent = false;
    	for(int m = 0; m < functions.size(); m++){
    		NccFunction f = functions.get(m);
    		if(f.getName().equals("main")){
    			mainPresent = true;
    		}
    	}
    	if(!mainPresent){
    		//print error message
    		NccError mainErr = new NccError(0, 111); 
    		mainErr.addTextln("Error: " + clo.fName + " does not contain a 'main' function!");
    		mainErr.addText("See NCC Error Code 111 for more details.");
    		nccErrors.add(mainErr);
    	}
    }
    
    /**
     * Add each function found to the list of functions.
     */
    public void addToListOfFunctions(ArrayList<NccFunction> q)
    {
    	//System.out.println("q size = " + q.size());
    	for(int index = 0; index < q.size(); index++){
    		functions.add(q.get(index));
    	}
    }
	
	/**
	 * Tidy the errFile once it has been filled. This method removes
	 * information we don't need anymore! 
	 */
	public void tidyErrFile()
	{
		try{
			File temp = new File("working.temp");
			temp.deleteOnExit();
			
			FileReader fr = new FileReader(errFile);
			BufferedReader reader = new BufferedReader(fr);
			FileWriter fw = new FileWriter(temp);
			BufferedWriter writer = new BufferedWriter(fw);
			
			String input;
			while( (input = reader.readLine()) != null ){
				if( (input.startsWith("BR.")) || (input.contains("mismatched")) ){
					//ignore the line, we don't need it or have already reported it!
				}
				else{
					writer.write(input);
					writer.newLine();
				}
			}
			
			writer.close();
			fw.close();
			reader.close();
			fr.close();
			
			//make the global errFile point to the tidy version - temp
			errFile = temp;
		}
		catch(IOException e){
			System.out.println("HERE: " + e.getMessage());
		}
	}
	
	/**
	 * Parses the errFile, creating a NccError objects where required. 
	 */
	public void parseTheErrFile()
	{
		//for the time being just print the file to screen...
		try{			
			FileReader fr = new FileReader(errFile);
			BufferedReader reader = new BufferedReader(fr);
			
			String input;
			while( (input = reader.readLine()) != null ){
				nccErrors.add(antlrErrorParse(input));
				//System.err.println(input);
			}
			
			reader.close();
			fr.close();
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * NCC Error Number(s): 112 (Print Regardless) & 114
	 * 
	 * Parses the antlr errors so they can be out put in corrrect order.
	 * 
	 * @param input		The antlr error line text to parse and create an 
	 * 					NccError from.
	 * @return			The NccError created.
	 */
	public NccError antlrErrorParse(String input)
	{
		NccError err = new NccError();
		err.setErrorNumber(112);
		int charPos = 0;
		if(input.startsWith("line")){
			String t = input.substring(0, input.indexOf(":"));
			StringTokenizer st = new StringTokenizer(t, " ");
			while(st.hasMoreTokens()){
				t = st.nextToken();
			}
			String cp = input.substring(input.indexOf(":")+1);
			st = new StringTokenizer(cp, " ");
			if(st.hasMoreTokens()){
				cp = st.nextToken();
			}
			//Pick out the String that caused the No Viable Alt Exception
			StringTokenizer dash = new StringTokenizer(input, "'");
			String nvaec = "";
			while(dash.hasMoreTokens()){
				nvaec = dash.nextToken();
			}
			try{
				err.setLine(Integer.parseInt(t));
				charPos = Integer.parseInt(cp);
			}
			catch(NumberFormatException nfe){
				
			}
			String mess = "";
			while(st.hasMoreTokens()){
				mess = mess.concat(" ").concat(st.nextToken());
			}
			mess = mess.trim();
			err.addTextln("Error: Line " + err.getLine() + ": " + mess + ":");
			NccSourceHandler sh = new NccSourceHandler(clo.fName);
			if( (mess.contains("at character")) && (nvaec.length() == 1) ){
				/*
				 * The No Viable Alternative Exception Error is the result of
				 * a stray character.
				 */
				if(clo.dFlag){
					if(vt.isValid(nvaec)){
						//The character is valid, all we can offer is a generic error message.
						err.addTextln("\t" + sh.getLineNumber(err.getLine()));
						err.addTextln("\t" + sh.generateWhiteSpace(charPos, err.getLine()) + "^");
						err.addText("See NCC Error Code 112 for more details.");
						err.setErrorNumber(112);
					}
					else{
						//The character is not valid we can offer a good message
						err.addTextln("\t" + sh.getLineNumber(err.getLine()));
						err.addTextln("\t" + sh.generateWhiteSpace(charPos, err.getLine()) + "^");
						err.addTextln("DIAGNOSIS: '" + nvaec + "' is not a valid character in" +
									  " a C source file, please delete it!");
						err.addText("See NCC Error Code 114 for more details.");
						err.setErrorNumber(114);
					}
				}
				else{
					//Set the error number for a generic message (112)
					err.setErrorNumber(112);
				}
			}
			else{
				if(clo.dFlag){
					/*
					 * Usually in this case the Token that there was no viable
					 * alternative for was some kind of work or combination of
					 * valid symbols used in an unexpected way that the compiler
					 * could not recover from. Therefore a generic error is all
					 * that can be offered to the user.
					 */
					err.addTextln("\t" + sh.getLineNumber(err.getLine()));
					err.addTextln("\t" + sh.generateWhiteSpace(charPos, err.getLine()) + "^");
					err.addText("See NCC Error Code 112 for more details.");
				}
				err.setErrorNumber(112);
			}
		}
		else{
			System.err.println(input + " didn't start with 'line'!");
		}
		return err;
	}
	
	/**
	 * Disables the out stream.
	 */
	private void disableOutStream()
	{
		//Store current out print stream
		currentOut = System.out;
		
		//Create a new print stream we can ignore for now.
		try{
			File tmpFile = new File("Out.temp");
			PrintStream tempOut = new PrintStream(tmpFile);
			//No need for this file to hang around...
			tmpFile.deleteOnExit();
			//Assign the System.out stream and System.err stream to the temp stream
			System.setOut(tempOut);
			//System.out.println("This should be the first line of my file!");
		} 
		catch(FileNotFoundException e) {
			currentOut.println("File Error: Creating Out.temp PrintStream");
		}
	}
	
	/**
	 * Enables the out streams.
	 */
	private void enableOutStream()
	{
		System.setOut(currentOut);
		//System.out.println("This should be back on std.out!");
	}
	
	/**
	 * Disables the err stream.
	 */
	private void disableErrStream()
	{
		//Store current err print stream
		currentErr = System.err;
		
		//Create a new print stream we can ignore for now.
		try{
			errFile = new File("Err.out");
			PrintStream tempErr = new PrintStream(errFile);
			//No need for this file to hang around...
			errFile.deleteOnExit();
			//Assign the System.err stream to the err file stream
			System.setErr(tempErr);
			//System.out.println("This should be the first line of my file!");
		} 
		catch(FileNotFoundException e) {
			System.out.println("File Error: Creating err.out PrintStream");
		}
	}
	
	/**
	 * Enables the err stream.
	 */
	private void enableErrStream()
	{
		System.setErr(currentErr);
		//System.err.println("This should be back on std.err!");
	}
	
	/**
	 * Put the list of NccErrors in the correct order so they print in Line
	 * number order.
	 */
	public ArrayList<NccError> orderNccErrors(ArrayList<NccError> l)
	{
		//put the current list in an array
		NccError[] temp = new NccError[l.size()];
		
		//fill the temp array
		for(int i = 0; i < l.size(); i++){
			temp[i] = l.get(i);
		}
		
		bubbleSort(temp);
		
		ArrayList<NccError> retArr = new ArrayList<NccError>();
		//fill the ArrayList to return 
		for(int i = 0; i < temp.length; i++){
			retArr.add(temp[i]);
		}
		
		return retArr;
	}
	
	/**
	 * This is the bubble sort algorithm.
	 */
	public void bubbleSort(NccError[] sorta)
	{
		NccError temp;
		int outX;
		int inX;
		//outer loop from top to bottom
		for(outX = sorta.length -1; outX > 0; outX--){
			//inner loop from bottom to current outX
			for(inX = 1; inX <= outX; inX++){
				// if adjacent items are out of order, swap them!
				if(sorta[inX -1].getLine() > sorta[inX].getLine()){
					temp = sorta[inX -1];
					sorta[inX -1] = sorta[inX];
					sorta[inX] = temp;
				}
			}
		}
	}
	
	/**
     * Prints the list of errors generated by NCC.
     */
	public void printNccErrors()
	{
        for(int a = 0; a < nccErrors.size(); a++){
        	System.out.print(nccErrors.get(a).getMessage());
        }
	}
	
	/**
	 * Print the scope details.
	 */
	public void printScopeDetails()
	{
		System.out.println("\nScope Details: ");
		NccSymbolTable[] temp = new NccSymbolTable[listSymTabs.size()];
		//Fill temp and bubble sort it
		for(int t = 0; t < listSymTabs.size(); t++){
			temp[t] = listSymTabs.get(t);
		}
		listSymTabs = bubbleSortSymbols(temp);
		int counter = 1;
		for(int i = 0; i < listSymTabs.size(); i++){
			NccSymbolTable tab = listSymTabs.get(i);
			if(tab.size() != 0){
				System.out.println("Scope " + counter + ": Variables visible from Line " + tab.getStartLine() + " to Line " + tab.getEndLine() + ":");
				System.out.println("Variable                      Array/Pointer       Type         Declared on Line");
				tab.printSymbolsDetails();
				counter++;
			}
		}
		System.out.println("");
	}
	
	/**
	 * This is the bubble sort algorithm.
	 */
	public ArrayList<NccSymbolTable> bubbleSortSymbols(NccSymbolTable[] sorta)
	{
		NccSymbolTable temp;
		int outX;
		int inX;
		//outer loop from top to bottom
		for(outX = sorta.length -1; outX > 0; outX--){
			//inner loop from bottom to current outX
			for(inX = 1; inX <= outX; inX++){
				// if adjacent items are out of order, swap them!
				if(sorta[inX -1].getStartLine() > sorta[inX].getStartLine()){
					temp = sorta[inX -1];
					sorta[inX -1] = sorta[inX];
					sorta[inX] = temp;
				}
			}
		}
		ArrayList<NccSymbolTable> retArr = new ArrayList<NccSymbolTable>();
		for(int i = 0; i < sorta.length; i++){
			retArr.add(sorta[i]);
		}
		
		return retArr;
	}
}
