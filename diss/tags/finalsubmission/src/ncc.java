import java.util.ArrayList;

import compiler.tools.CommandLineOptions;
import compiler.tools.FormattedFilePrinter;

import ml.options.*;
//It is necessary to import the static Options enums etc.
import static ml.options.Options.*;

public class ncc 
{
	/**
	 * Main method for the ncc (Novice C Compiler) application.
	 * 
	 * @param args	The arguments input at the command line. 
	 */
    public static void main(String args[]) 
    {
    	new ncc(args);
    }
    
    /**
     * Constructor.
     */
    public ncc(String args[])
    {
    	processArgs(args);
    }
    
    /**
     * Processes the command line args and call the correct methods.
     * 
     * @param args	The arguments input at the command line.
     */
    public void processArgs(String args[])
    {
    	ClassLoader cl = this.getClass().getClassLoader();

    	//System.out.println(baseLoader);
    	/*
    	 * ncc command line options:
    	 * (1)	java ncc --help
    	 * (2)	java ncc [-o <output>] [-d] [-g] [-n] [-s] <input>
    	 * (3)  java ncc [-o <output>] [-d] [-s] [-v]
    	 * (4)  java ncc -p <xxx>
    	 * 
    	 * [...] - Denotes optional.
    	 */

    	Options opt = new Options(args, 1);

    	opt.addSet("help", 0, 0).addOption("-help", Multiplicity.ONCE);
    	opt.addSet("norm").addOption("o", Separator.BLANK, Multiplicity.ZERO_OR_ONE)
    					  .addOption("d", Multiplicity.ZERO_OR_ONE)
    					  .addOption("g", Multiplicity.ZERO_OR_ONE)
    					  .addOption("n", Multiplicity.ZERO_OR_ONE)
    					  .addOption("s", Multiplicity.ZERO_OR_ONE);
    	opt.addSet("normPlus").addOption("o", Separator.BLANK, Multiplicity.ZERO_OR_ONE)
    						  .addOption("d", Multiplicity.ZERO_OR_ONE)
    						  .addOption("s", Multiplicity.ZERO_OR_ONE)
    						  .addOption("v", Multiplicity.ZERO_OR_ONE);    	
    	opt.addSet("print", 0, 0).addOption("p", Separator.BLANK, Multiplicity.ONCE);
 	
    	/*
    	 * Get the option set parsed from the command  line, (false, false)
    	 * so data args can be entered in any order.
    	 */
    	OptionSet set = opt.getMatchingSet(false, false);

    	if(set == null){
    		System.out.println("ncc: illegal argument combination or no input file found");
    		System.out.println("To show help type: java ncc --help");
    		System.exit(1);
    	}

    	// Processing Cases

    	if(set.getSetName().equals("help")) {
    		//System.out.println("printing help");
    		FormattedFilePrinter.print(cl, "ErrorCodes/ncc.help");
    		System.exit(1);
    	}
    	else if(set.getSetName().equals("norm")) {
    		//System.out.println("norm");
    		/*
			 * Under normal circumstances the application can only have one
			 * input file, need to check the input file has a .c extension.
			 * 
			 * Note for flags: All flags are off by default and switched on 
			 * when their switches appear on the command line except for -d
			 * which is turned off when its switch is on. 
			 */
    		ArrayList<String> inputAL = set.getData();
    		String inputFile = inputAL.get(0);
    		String oDest = "a.out";

    		//Check for input file extension
    		if(inputFile.endsWith(".c")){
    			//Good - continue execution
    		}
    		else{
    			System.out.println("File: " + inputFile + " is not a valid .c source file");
    			System.exit(1);
    		}
    		//Check for output option and destination file
    		if(set.isSet("o")) {
    			//System.out.println("o is set");
    			oDest = set.getOption("o").getResultValue(0);
    		}
    		else{
    			//-o flag is not set, leave oDest = "a.out"
    		}
    		//Create the CommandLineOptions data structure and deal with flags.
    		CommandLineOptions clo = new CommandLineOptions(inputFile, oDest, cl);
    		if(set.isSet("d")){
    			//System.out.println("d flag is set");
    			clo.dFlag = false;
    		}
    		if(set.isSet("g")){
    			//System.out.println("g flag is set");
    			clo.forceGcc = true;
    		}
    		if(set.isSet("n")){
    			//System.out.println("n flag is set");
    			clo.forceNcc = true;
    		}
    		if(set.isSet("s")){
    			//System.out.println("s flag is set");
    			clo.scopeDetail = true;
    		}
    		
    		new Driver(clo);
    	}
    	else if(set.getSetName().equals("normPlus")){
    		//System.out.println("normPlus");
    		/*
			 * Under normal circumstances the application can only have one
			 * input file, need to check the input file has a .c extension.
			 * 
			 * Note for flags: All flags are off by default and switched on 
			 * when their switches appear on the command line except for -d
			 * which is turned off when its switch is on.  
			 */
    		ArrayList<String> inputAL = set.getData();
    		String inputFile = inputAL.get(0);
    		String oDest = "a.out";

    		//Check for input file extension
    		if(inputFile.endsWith(".c")){
    			//Good - continue execution
    		}
    		else{
    			System.out.println("File: " + inputFile + " is not a valid .c source file");
    			System.exit(1);
    		}
    		//Check for output option and destination file
    		if(set.isSet("o")) {
    			//System.out.println("o is set");
    			oDest = set.getOption("o").getResultValue(0);
    		}
    		else{
    			//-o flag is not set, leave oDest = "a.out"
    		}
    		//Create the CommandLineOptions data structure and deal with flags.
    		CommandLineOptions clo = new CommandLineOptions(inputFile, oDest, cl);
    		if(set.isSet("d")){
    			//System.out.println("d flag is set");
    			clo.dFlag = false;
    		}
    		if(set.isSet("s")){
    			//System.out.println("s flag is set");
    			clo.scopeDetail = true;
    		}
    		if(set.isSet("v")){
    			//System.out.println("v flag is set");
    			clo.verbose = true;
    		}
    		
    		new Driver(clo);
    	}
    	else if(set.getSetName().equals("print")){
    		//System.out.println("printing error code");
    		if(set.isSet("p")) {
    			String arg = set.getOption("p").getResultValue(0);
    			//System.out.println("p is set");
    			//System.out.println(arg);
    			try{
	    			if( (arg.length() == 3) 
	    					&& ( ( (Integer.valueOf(arg) > 100) && (Integer.valueOf(arg) < 126) ) 
	    							|| ( (Integer.valueOf(arg) > 200) && (Integer.valueOf(arg) < 203) ) ) ){
	    				FormattedFilePrinter.print(cl, "ErrorCodes/" + arg + ".nec");
	    			}
	    			else if(arg.equals("000")){
	    				FormattedFilePrinter.print(cl, "ErrorCodes/list.nec");
	    			}
	    			else{
	    				System.out.println("Invalid Error Code! Type 'java ncc -p 000' for list of codes");
	    			}
    			}
    			catch(NumberFormatException e){
    				if(arg.equals("lib")){
        				FormattedFilePrinter.print(cl, "ErrorCodes/libraries.nec");
        			}
    				else{
    					System.out.println("ncc: no valid argument for -p flag");
    		    		System.out.println("To show help type: java ncc --help");
    				}
    			}
    		}
    		else{
    			System.out.println("Need to work out why I'm here!");
    		}
    	}
    	else{
    		System.out.println("Error shouldn't be here");
    	}
    }
}
