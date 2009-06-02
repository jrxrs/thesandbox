package compiler.tools;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * This class performs the post processing jobs collected during normal parsing. 
 * 
 * @author Richard Hill
 */

public class NccPostProcessor
{
	private CommandLineOptions clo;
	private File readFrom;
	private NccSourceHandler sh;
	private enum Types {DO, DOWHILE, ELSE, FOR, IF, SWITCH, WHILE, GETCHAR, PUTCHAR, PRINTF, SCANF, NOVALUE};
	//Valid printf end formatters
	private HashSet<String> validPrintfEnds = new HashSet<String>();
	//Valid printf middle chars
	private HashSet<String> validPrintfMids = new HashSet<String>();
	//Valid scanf end formatters
	private HashSet<String> validScanfEnds = new HashSet<String>();
	//Valid scanf middle chars
	private HashSet<String> validScanfMids = new HashSet<String>();
	
	/**
	 * Constructor.
	 * 
	 * @param clo	The command line options data structure. 
	 */
	public NccPostProcessor(CommandLineOptions clo)
	{
		this.clo = clo;
		readFrom = new File(clo.fName);		
		sh = new NccSourceHandler(clo.fName);
		initValidPrintfEnds();
		initValidPrintfMids();
		initValidScanfEnds();
		initValidScanfMids();
	}
	
	/**
	 * This method looks at the job and decides how to process it.
	 * 
	 * @return	The NccError generated if there is one, null otherwise.
	 */
	public NccError processJob(NccPostProcessorJob j)
	{
		switch(toType(j.getJobType())){
		case DO     : return matchDo(j.getBeginLine());
		case DOWHILE: return null; //see below
		case ELSE   : return matchElse(j.getBeginLine());
		case FOR    : return matchFor(j.getBeginLine());
		case IF     : return matchIf(j.getBeginLine());
		case SWITCH : return matchSwitch(j.getBeginLine());
		case WHILE  : return matchWhile(j.getBeginLine());
		case GETCHAR: return checkGetchar(j);
		case PUTCHAR: return checkPutchar(j);
		case PRINTF : return checkPrintf(j);
		case SCANF  : return checkScanf(j);
		default     : return null;
		}
	}
	
	/**
	 * NCC Error NCC Error Number(s): 115 (Print Regardless)
	 * 
	 * Matches a 'do'. e.g.
	 * 
	 * 	//DO-WHILE
	 * 	do{
	 * 		y++;
	 * 	}while(x-- > 0);
	 * 
	 * @param beginLine		The line the matching process begins on.
	 * @return				The NccError generated if there is one, null 
	 * 						otherwise.
	 */
	private NccError matchDo(int beginLine)
	{
		NccError err = null;
		//System.err.println("Matching do");
		try{
			FileReader fr = new FileReader(readFrom);
			LineNumberReader lnr = new LineNumberReader(fr);
			
			//Stop the file one line before the line we're interested in 
			while(lnr.getLineNumber() < beginLine - 1){
				//System.err.println(lnr.readLine());
				lnr.readLine();
			}
			
			//Now read character by character
			int i;
			int counter = 0;
			while( (i = lnr.read()) != -1){ //While we're not at the end of the file
				counter++;
				if(i == (int) 'd'){
					counter--;
					//System.err.println("Matched a 'd' in 'do'");
					//Matched a 'd', move on to find an 'o'
					i = lnr.read();
					if(i == (int) 'o'){
						//System.err.println("Matched an 'o' in 'do'");
						i = lnr.read();
						/*
						 * Most of the time this should be a '{', on the off
						 * chance that it is not advance the input through
						 * whitespace and comments.
						 */
						boolean keepMoving = true;
						while(keepMoving){
							if(Character.isWhitespace((char)i)){
								i = lnr.read(); //Advance
							}
							else if(i == (int) '/'){
								//parsing comment
								i = matchComment(lnr);
							}
							else if(i == (int) '{'){
								//Correct usage of { ensured.
								//System.err.println("Complete & Correct!");
								return null;
							}
							else{
								keepMoving = false;
							}
						}
						//If we reach here then { was not used correctly so a warning is required
						if(clo.dFlag){
							err = new NccError(beginLine, 115);
							err.addTextln("Warning: Line " + beginLine + ": Poor block conversion after 'do'.");
							err.addTextln("\t" + sh.getLineNumber(beginLine));
							err.addTextln("\t" + sh.generateWhiteSpace(counter, beginLine) + "^");
							err.addTextln("DIAGNOSIS: The statement(s) in the 'do' section of the " +
										  "do-while loop beginning on line " + beginLine + " are not enclosed by " +
										  "curly brackets, this may result in code ambiguity.");
							err.addText("See NCC Error Code 115 for more details");
						}
						break; //Matched entire 'do{' so break from while and return
					}
				}
			}
			
			lnr.close();
			fr.close();
		}
		catch(IOException e){
			System.out.println("IOException: " + e.getMessage());
			e.printStackTrace();
		}
		return err;
	}
	
	/*
	 * This method would match the while segment of a 'do-while'. e.g.
	 * 
	 * 	//DO-WHILE
	 * 	do{
	 * 		y++;
	 * 	}while(x-- > 0);
	 * 
	 * But as there is not block convention to encourage the correct use of and
	 * because any syntax errors should have been reported by the parser we 
	 * don't need to perform any checks so we just return null here. 
	 * 
	 * @param beginLine		The line the matching process begins on.
	 * @return				The NccError generated if there is one, null 
	 * 						otherwise.
	 *
	 * private NccError mactchDoWhile(int beginLine) 
	 * {
	 * 	   return null;
	 * }
	 */

	/**
	 * NCC Error NCC Error Number(s): 116 (Print Regardless)
	 *
	 * Matches an 'else'.
	 * 
	 * @param beginLine		The line the matching process begins on.
	 * @return				The NccError generated if there is one, null 
	 * 						otherwise.
	 */
	private NccError matchElse(int beginLine)
	{
		NccError err = null;
		//System.err.println("Matching else");
		try{
			FileReader fr = new FileReader(readFrom);
			LineNumberReader lnr = new LineNumberReader(fr);
			
			//Stop the file one line before the line we're interested in 
			while(lnr.getLineNumber() < beginLine - 1){
				//System.err.println(lnr.readLine());
				lnr.readLine();
			}
			
			//Now read character by character
			int i;
			int counter = 0;
			while( (i = lnr.read()) != -1){ //While we're not at the end of the file
				counter++;
				if(i == (int) 'e'){
					counter--;
					//System.err.println("Matched an 'e' in 'else'");
					//Matched an 'e', move on to find an 'l'
					i = lnr.read();
					if(i == (int) 'l'){
						//System.err.println("Matched an 'l' in 'else'");
						//Matched a 'l', move on to find an 's'
						i = lnr.read();
						if(i == (int) 's'){
							//System.err.println("Matched an 's' in 'else'");
							//Matched a 's', move on to find an 'e'
							i = lnr.read();
							if(i == (int) 'e'){
								//System.err.println("Matched an 'e' in 'else'");
								i = lnr.read();
								/*
								 * Most of the time this should be a '{', on the off
								 * chance that it is not advance the input through
								 * whitespace and comments.
								 */
								boolean keepMoving = true;
								while(keepMoving){
									if(Character.isWhitespace((char)i)){
										i = lnr.read(); //Advance
									}
									else if(i == (int) '/'){
										//parsing comment
										i = matchComment(lnr);
									}
									else if(i == (int) '{'){
										//Correct usage of { ensured.
										//System.err.println("Complete & Correct!");
										return null;
									}
									else{
										keepMoving = false;
									}
								}
								//If we reach here then { was not used correctly so a warning is required
								if(clo.dFlag){
									err = new NccError(beginLine, 116);
									err.addTextln("Warning: Line " + beginLine + ": Poor block conversion after 'else'.");
									err.addTextln("\t" + sh.getLineNumber(beginLine));
									err.addTextln("\t" + sh.generateWhiteSpace(counter, beginLine) + "^");
									err.addTextln("DIAGNOSIS: The statement(s) in the 'else' section of the " +
												  "if-else control statement beginning on line " + beginLine + " are not enclosed by " +
												  "curly brackets, this may result in code ambiguity.");
									err.addText("See NCC Error Code 116 for more details");
								}
								break; //Matched entire 'else{' so break from while and return
							}
						}
					}
				}
			}
			
			lnr.close();
			fr.close();
		}
		catch(IOException e){
			System.out.println("IOException: " + e.getMessage());
			e.printStackTrace();
		}
		return err;
	}

	/**
	 * NCC Error NCC Error Number(s): 117 (Print Regardless)
	 * 
	 * Matches a 'for'.
	 * 
	 * @param beginLine		The line the matching process begins on.
	 * @return				The NccError generated if there is one, null 
	 * 						otherwise.
	 */
	private NccError matchFor(int beginLine)
	{
		NccError err = null;
		//System.err.println("Matching for");
		try{
			FileReader fr = new FileReader(readFrom);
			LineNumberReader lnr = new LineNumberReader(fr);
			
			//Stop the file one line before the line we're interested in 
			while(lnr.getLineNumber() < beginLine - 1){
				//System.err.println(lnr.readLine());
				lnr.readLine();
			}
			
			//Now read character by character
			int i;
			int counter = 0;
			while( (i = lnr.read()) != -1){ //While we're not at the end of the file
				counter++;
				if(i == (int) 'f'){
					counter--;
					//System.err.println("Matched an 'f' in 'for'");
					//Matched an 'f', move on to find an 'o'
					i = lnr.read();
					if(i == (int) 'o'){
						//System.err.println("Matched an 'o' in 'for'");
						//Matched an 'o', move on to find an 'r'
						i = lnr.read();
						if(i == (int) 'r'){
							//System.err.println("Matched an 'r' in 'for'");
							i = lnr.read();
							/*
							 * Most of the time this should be a '(', on the off
							 * chance that it is not advance the input through
							 * whitespace and comments.
							 */
							boolean keepMoving = true;
							while(keepMoving){
								if(Character.isWhitespace((char)i)){
									i = lnr.read(); //Advance
								}
								else if(i == (int) '/'){
									//parsing comment
									i = matchComment(lnr);
								}
								else if(i == (int) '('){
									i = matchBrackets(lnr);
								}
								else{
									keepMoving = false;
								}
							}
							/*
							 * Most of the time this should be a '{', on the off
							 * chance that it is not advance the input through
							 * whitespace and comments.
							 */
							keepMoving = true;
							while(keepMoving){
								if(Character.isWhitespace((char)i)){
									i = lnr.read(); //Advance
								}
								else if(i == (int) '/'){
									//parsing comment
									i = matchComment(lnr);
								}
								else if(i == (int) '{'){
									//Correct usage of { ensured.
									//System.err.println("Complete & Correct!");
									return null;
								}
								else{
									keepMoving = false;
								}
							}
							//If we reach here then { was not used correctly so a warning is required
							if(clo.dFlag){
								err = new NccError(beginLine, 117);
								err.addTextln("Warning: Line " + beginLine + ":  Poor block conversion after 'for'.");
								err.addTextln("\t" + sh.getLineNumber(beginLine));
								err.addTextln("\t" + sh.generateWhiteSpace(counter, beginLine) + "^");
								err.addTextln("DIAGNOSIS: The statement(s) in the 'for' section of the " +
											  "for control statement beginning on line " + beginLine + " are not enclosed by " +
											  "curly brackets, this may result in code ambiguity.");
								err.addText("See NCC Error Code 117 for more details");
							}
							break; //Matched entire 'for{' so break from while and return
						}
					}
				}
			}
			
			lnr.close();
			fr.close();
		}
		catch(IOException e){
			System.out.println("IOException: " + e.getMessage());
			e.printStackTrace();
		}
		return err;
	}

	/**
	 * NCC Error NCC Error Number(s): 118 (Print Regardless)
	 * 
	 * Matches an 'if'.
	 * 
	 * @param beginLine		The line the matching process begins on.
	 * @return				The NccError generated if there is one, null 
	 * 						otherwise.
	 */
	private NccError matchIf(int beginLine)
	{
		NccError err = null;
		//System.err.println("Matching if");
		try{
			FileReader fr = new FileReader(readFrom);
			LineNumberReader lnr = new LineNumberReader(fr);
			
			//Stop the file one line before the line we're interested in 
			while(lnr.getLineNumber() < beginLine - 1){
				//System.err.println(lnr.readLine());
				lnr.readLine();
			}
			
			//Now read character by character
			int i;
			int counter = 0;
			while( (i = lnr.read()) != -1){ //While we're not at the end of the file
				counter++;
				if(i == (int) 'i'){
					counter--;
					//System.err.println("Matched an 'i' in 'if'");
					//Matched an 'i', move on to find an 'f'
					i = lnr.read();
					if(i == (int) 'f'){
						//System.err.println("Matched an 'f' in 'if'");
						i = lnr.read();
						/*
						 * Most of the time this should be a '(', on the off
						 * chance that it is not advance the input through
						 * whitespace and comments.
						 */
						boolean keepMoving = true;
						while(keepMoving){
							if(Character.isWhitespace((char)i)){
								i = lnr.read(); //Advance
							}
							else if(i == (int) '/'){
								//parsing comment
								i = matchComment(lnr);
							}
							else if(i == (int) '('){
								i = matchBrackets(lnr);
							}
							else{
								keepMoving = false;
							}
						}
						/*
						 * Most of the time this should be a '{', on the off
						 * chance that it is not advance the input through
						 * whitespace and comments.
						 */
						keepMoving = true;
						while(keepMoving){
							if(Character.isWhitespace((char)i)){
								i = lnr.read(); //Advance
							}
							else if(i == (int) '/'){
								//parsing comment
								i = matchComment(lnr);
							}
							else if(i == (int) '{'){
								//Correct usage of { ensured.
								//System.err.println("Complete & Correct!");
								return null;
							}
							else{
								keepMoving = false;
							}
						}
						//If we reach here then { was not used correctly so a warning is required
						if(clo.dFlag){
							err = new NccError(beginLine, 118);
							err.addTextln("Warning: Line " + beginLine + ": Poor block conversion after 'if'.");
							err.addTextln("\t" + sh.getLineNumber(beginLine));
							err.addTextln("\t" + sh.generateWhiteSpace(counter, beginLine) + "^");
							err.addTextln("DIAGNOSIS: The statement(s) in the 'if' section of the " +
										  "if control statement beginning on line " + beginLine + " are not enclosed by " +
										  "curly brackets, this may result in code ambiguity.");
							err.addText("See NCC Error Code 118 for more details");
						}
						break; //Matched entire 'if{' so break from while and return
					}
				}
			}
			
			lnr.close();
			fr.close();
		}
		catch(IOException e){
			System.out.println("IOException: " + e.getMessage());
			e.printStackTrace();
		}
		return err;
	}

	/**
	 * NCC Error NCC Error Number(s): 119 (Print Regardless)
	 * 
	 * Matches a 'switch'.
	 * 
	 * @param beginLine		The line the matching process begins on.
	 * @return				The NccError generated if there is one, null 
	 * 						otherwise.
	 */
	private NccError matchSwitch(int beginLine)
	{
		NccError err = null;
		//System.err.println("Matching switch");
		try{
			FileReader fr = new FileReader(readFrom);
			LineNumberReader lnr = new LineNumberReader(fr);
			
			//Stop the file one line before the line we're interested in 
			while(lnr.getLineNumber() < beginLine - 1){
				//System.err.println(lnr.readLine());
				lnr.readLine();
			}
			
			//Now read character by character
			int i;
			int counter = 0;
			while( (i = lnr.read()) != -1){ //While we're not at the end of the file
				counter++;
				if(i == (int) 's'){
					counter--;
					//System.err.println("Matched an 's' in 'switch'");
					//Matched an 's', move on to find a 'w'
					i = lnr.read();
					if(i == (int) 'w'){
						//System.err.println("Matched a 'w' in 'switch'");
						//Matched a 'w', move on to find an 'i'
						i = lnr.read();
						if(i == (int) 'i'){
							//System.err.println("Matched an 'i' in 'switch'");
							//Matched an 'i', move on to find a 't'
							i = lnr.read();
							if(i == (int) 't'){
								//System.err.println("Matched a 't' in 'switch'");
								//Matched an 't', move on to find a 'c'
								i = lnr.read();
								if(i == (int) 'c'){
									//System.err.println("Matched a 'c' in 'switch'");
									//Matched a 'c', move on to find an 'h'
									i = lnr.read();
									if(i == (int) 'h'){
										//System.err.println("Matched an 'h' in 'switch'");
										i = lnr.read();
										/*
										 * Most of the time this should be a '(', on the off
										 * chance that it is not advance the input through
										 * whitespace and comments.
										 */
										boolean keepMoving = true;
										while(keepMoving){
											if(Character.isWhitespace((char)i)){
												i = lnr.read(); //Advance
											}
											else if(i == (int) '/'){
												//parsing comment
												i = matchComment(lnr);
											}
											else if(i == (int) '('){
												i = matchBrackets(lnr);
											}
											else{
												keepMoving = false;
											}
										}
										/*
										 * Most of the time this should be a '{', on the off
										 * chance that it is not advance the input through
										 * whitespace and comments.
										 */
										keepMoving = true;
										while(keepMoving){
											if(Character.isWhitespace((char)i)){
												i = lnr.read(); //Advance
											}
											else if(i == (int) '/'){
												//parsing comment
												i = matchComment(lnr);
											}
											else if(i == (int) '{'){
												//Correct usage of { ensured.
												//System.err.println("Complete & Correct!");
												return null;
											}
											else{
												keepMoving = false;
											}
										}
										//If we reach here then { was not used correctly so a warning is required
										if(clo.dFlag){
											err = new NccError(beginLine, 119);
											err.addTextln("Warning: Line " + beginLine + ":  Poor block conversion after 'switch'.");
											err.addTextln("\t" + sh.getLineNumber(beginLine));
											err.addTextln("\t" + sh.generateWhiteSpace(counter, beginLine) + "^");
											err.addTextln("DIAGNOSIS: The case statement(s) in the options section of the " +
														  "'switch' control statement beginning on line " + beginLine + " are not enclosed by " +
														  "curly brackets, this may result in code ambiguity.");
											err.addText("See NCC Error Code 119 for more details");
										}
										break; //Matched entire 'switch{' so break from while and return
									}
								}
							}
						}
					}
				}
			}
			
			lnr.close();
			fr.close();
		}
		catch(IOException e){
			System.out.println("IOException: " + e.getMessage());
			e.printStackTrace();
		}
		return err;
	}

	/**
	 * NCC Error NCC Error Number(s): 120 (Print Regardless)
	 * 
	 * Matches a 'while'.
	 * 
	 * @param beginLine		The line the matching process begins on.
	 * @return				The NccError generated if there is one, null 
	 * 						otherwise.
	 */
	private NccError matchWhile(int beginLine)
	{
		NccError err = null;
		//System.err.println("Matching while");
		try{
			FileReader fr = new FileReader(readFrom);
			LineNumberReader lnr = new LineNumberReader(fr);
			
			//Stop the file one line before the line we're interested in 
			while(lnr.getLineNumber() < beginLine - 1){
				//System.err.println(lnr.readLine());
				lnr.readLine();
			}
			
			//Now read character by character
			int i;
			int counter = 0;
			while( (i = lnr.read()) != -1){ //While we're not at the end of the file
				counter++;
				if(i == (int) 'w'){
					counter--;
					//System.err.println("Matched a 'w' in 'while'");
					//Matched an 'w', move on to find an 'h'
					i = lnr.read();
					if(i == (int) 'h'){
						//System.err.println("Matched an 'h' in 'while'");
						//Matched an 'h', move on to find an 'i'
						i = lnr.read();
						if(i == (int) 'i'){
							//System.err.println("Matched an 'i' in 'while'");
							//Matched an 'i', move on to find an 'l'
							i = lnr.read();
							if(i == (int) 'l'){
								//System.err.println("Matched an 'l' in 'while'");
								//Matched an 'l', move on to find an 'e'
								i = lnr.read();
								if(i == (int) 'e'){
									//System.err.println("Matched an 'e' in 'while'");
									i = lnr.read();
									/*
									 * Most of the time this should be a '(', on the off
									 * chance that it is not advance the input through
									 * whitespace and comments.
									 */
									boolean keepMoving = true;
									while(keepMoving){
										if(Character.isWhitespace((char)i)){
											i = lnr.read(); //Advance
										}
										else if(i == (int) '/'){
											//parsing comment
											i = matchComment(lnr);
										}
										else if(i == (int) '('){
											i = matchBrackets(lnr);
										}
										else{
											keepMoving = false;
										}
									}
									/*
									 * Most of the time this should be a '{', on the off
									 * chance that it is not advance the input through
									 * whitespace and comments.
									 */
									keepMoving = true;
									while(keepMoving){
										if(Character.isWhitespace((char)i)){
											i = lnr.read(); //Advance
										}
										else if(i == (int) '/'){
											//parsing comment
											i = matchComment(lnr);
										}
										else if(i == (int) '{'){
											//Correct usage of { ensured.
											//System.err.println("Complete & Correct!");
											return null;
										}
										else{
											keepMoving = false;
										}
									}
									//If we reach here then { was not used correctly so a warning is required
									if(clo.dFlag){
										err = new NccError(beginLine, 120);
										err.addTextln("Warning: Line " + beginLine + ":  Poor block conversion after 'while'.");
										err.addTextln("\t" + sh.getLineNumber(beginLine));
										err.addTextln("\t" + sh.generateWhiteSpace(counter, beginLine) + "^");
										err.addTextln("DIAGNOSIS: The statement(s) in the 'while' section of the " +
													  "while control statement beginning on line " + beginLine + " are not enclosed by " +
													  "curly brackets, this may result in code ambiguity.");
										err.addText("See NCC Error Code 120 for more details");
									}
									break; //Matched entire 'while{' so break from while and return
								}
							}
						}
					}
				}
			}
			
			lnr.close();
			fr.close();
		}
		catch(IOException e){
			System.out.println("IOException: " + e.getMessage());
			e.printStackTrace();
		}
		return err;
	}
	
	/*
	 * Note: There is not point matching normal brackets and reporting errors 
	 * about their usage in this class as they will have already be matched and
	 * parsed correctly by the compiler(s) and thus any errors with them will 
	 * already have been reported.
	 */
	
	/**
	 * Matches brackets and returns when complete. This method is 
	 * recursive in nature, with each new opening bracket that is found a
	 * new invocation of matchBrackets is started. When a closing bracket
	 * is found a match has occurred and the method returns.
	 * 
	 * This method essentially reads one character at a time simply looking for
	 * a match.
	 * 
	 * @param lnr		The LineNumberReader we're working with.
	 * @return			The last integer lnr read.
	 */
	private int matchBrackets(LineNumberReader lnr)
	{
		/*
		 * When we're here we have read the first '('.
		 */
		try {
			int i = lnr.read();
			while(i != -1){
				if(i == (int) '('){
					//New bracket block opened
					i = matchBrackets(lnr);
				}
				else if(i == (int) ')'){
					//Existing bracket block closed
					return lnr.read();
				}
				else{
					//Otherwise skip the contents of the brackets
					i = lnr.read();
				}
			}
		}
		catch(IOException e){
			System.out.println("IOException: " + e.getMessage());
			e.printStackTrace();
		}
		return (int) '@';
	}
	
	/*
	 * In C multi-line comments are defined as "/* this is a comment */
	/**
	 * Matches multi-line comments and returns true when complete.
	 * 
	 * @param lnr		The LineNumberReader we're working with.
	 * @return			The last integer lnr read.
	 */
	private int matchComment(LineNumberReader lnr)
	{
		/*
		 * When we're here we have read the first / so we're either looking at
		 * a single line comment i.e. another / and skip until the end of the
		 * line or we're looking for a * and skip until we find a * followed by
		 * a /. If we find anything else then we return false. 
		 */
		try {
			int i;
			while((i = lnr.read()) != -1){
				if(i == (int) '/'){
					//Single line comment matching
					while((i = lnr.read()) != -1){
						if(i == (int) '\n'){
							return lnr.read();
						}
					}
				}
				else if(i == (int) '*'){
					//Multi-line comment
					while((i = lnr.read()) != -1){
						if(i == (int) '*'){
							i = lnr.read();
							if(i == (int) '/'){
								return lnr.read();
							}
						}
					}
				}
				else{
					return i;
				}
			}
		}
		catch(IOException e){
			System.out.println("IOException: " + e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Converts a String to a Types value for processing decision making.
	 * 
	 * @param str	The String to return the value of.
	 * @return		The value of the String in the Types enum.
	 */
	public static Types toType(String str)
    {
		try{
			return Types.valueOf(str);
		}
		catch(Exception ex){
			return Types.NOVALUE;
		}
    }
	
	/**
	 * NCC Error Number(s): 121
	 * 
	 * Analyses the line getchar() is made on to make sure it complies. If it 
	 * is not a correct call then an Error Message will be created and returned
	 * by the method.   
	 * 
	 * @param j		The NccPostProcessorJob to analyse. This object should in 
	 * 				fact be a NccPostProcessorAnalysisJob object as it will be
	 * 				cast to this type of object first job in the method.
	 * @return		The NccError generated if there is one, null 
	 * 				otherwise.
	 */
	private NccError checkGetchar(NccPostProcessorJob aj)
	{
		if(aj.getArgTokenList().size() != 0){
			NccError e = new NccError(aj.getBeginLine(), 121);
			e.addTextln("Error: Line " + e.getLine() + ": Function 'getchar' has been called with arguments but should not have been.");
			if(clo.dFlag){
				String calc = sh.getLineNumber(e.getLine());
				e.addTextln("\t" + calc);
				e.addTextln("\t" + sh.generateWhiteSpace(calc.indexOf("getchar("), e.getLine()) + "^");
				e.addTextln("DIAGNOSIS: To fix the error remove the argument in the call to 'getchar'");
				e.addText("See NCC Error Code 121 for more details.");
			}
			return e;
		}
		return null;
	}

	/**
	 * NCC Error Number(s): 122
	 * 
	 * Analyses the line putchar() is made on to make sure it complies. If it 
	 * is not a correct call then an Error Message will be created and returned
	 * by the method.   
	 * 
	 * @param j		The NccPostProcessorJob to analyse. This object should in 
	 * 				fact be a NccPostProcessorAnalysisJob object as it will be
	 * 				cast to this type of object first job in the method.
	 * @return		The NccError generated if there is one, null 
	 * 				otherwise.
	 */
	private NccError checkPutchar(NccPostProcessorJob aj)
	{
		if(aj.getArgTokenList().size() != 1){
			NccError e = new NccError(aj.getBeginLine(), 122);
			if(aj.getArgTokenList().size() == 0){
				e.addTextln("Error: Line " + e.getLine() + ": Function 'putchar' has been called with too few arguments.");
			}
			else{
				e.addTextln("Error: Line " + e.getLine() + ": Function 'putchar' has been called with too many arguments.");
			}
			if(clo.dFlag){
				String calc = sh.getLineNumber(e.getLine());
				e.addTextln("\t" + calc);
				e.addTextln("\t" + sh.generateWhiteSpace(calc.indexOf("putchar("), e.getLine()) + "^");
				e.addTextln("DIAGNOSIS: To fix the error ensure only one char (or int) argument is passed to 'putchar'");
				e.addText("See NCC Error Code 122 for more details.");
			}
			return e;
		}
		else{
			//Number of args to putchar is 1, check it is a char or and int
			if( (aj.getArgTokenList().get(0).equals("char")) || (aj.getArgTokenList().get(0).equals("int")) ){
				//No error
				return null;
			}
			else{
				NccError e = new NccError(aj.getBeginLine(), 122);
				e.addTextln("Error: Line " + e.getLine() + ": The argument passed to 'putchar' was not a valid char or int. 'putchar' was in fact passed a " + aj.getArgTokenList().get(0) + ".");
				if(clo.dFlag){
					String calc = sh.getLineNumber(e.getLine());
					e.addTextln("\t" + calc);
					e.addTextln("\t" + sh.generateWhiteSpace(calc.indexOf("putchar("), e.getLine()) + "^");
					e.addTextln("DIAGNOSIS: To fix the error ensure the value or variable argument passed to 'putchar' is a char or an int.");
					e.addText("See NCC Error Code 122 for more details.");
				}
				return e;
			}
		}
	}

	/*
	 * Notes: The valid string format characters for printf:
	 * Character	Argument Type: Printed as
	 *    d,i		int; decimal number
	 *     o		int; octal number
	 *    x,X		int; hexadecimal number
	 *     u		int; unsigned decimal number
	 *     c		int; single char
	 *     s		char *; prints a string "", terminated by '\0'
	 *     f		double; [-]m.dddddd
	 *    e,E		double; [-]m.dddddde+-xx or [-]m.ddddddE+-xx 
	 *    g,G		double; prints %f or %[e,E] as appropriate
	 *     p		void *; pointer
	 *     %		print a & character
	 * 
	 * Other formatting considerations include spatial specifications. e.g.
	 * 
	 * :%s:			:hello, world:
	 * :%10s:		:hello, world:
	 * :%.10s:		:hello, wor:
	 * :%-10s:		:hello, world:
	 * :%.15s:		:hello, world:
	 * :%-15s:		:hello, world   :
	 * :%15.10s:	:     hello, wor:
	 * :%-15.10s:	:hello, wor     :
	 *  
	 * Therefore between the % and the valid letters from the table above
	 * e.g. 'd', the valid characters are: 	0-9
	 * 										.*, .#, -, +, ' '(space), ', I
	 * 										hh, h, l, ll, j, z, L,
	 * 										
	 * 
	 * Table taken from:
	 * Kernighan B. W. & Ritchie D. M, The C Programming Language, Second 
	 * Edition. Prentice Hall: New Jersey. p. 154. 
	 */
	
	/**
	 * NCC Error Number(s): 123
	 * 
	 * Analyses the line printf(...) is made on to make sure it complies. If it 
	 * is not a correct call then an Error Message will be created and returned
	 * by the method.
	 * 
	 * Note: "char aorp" is equiv. to "string" for type of arg 
	 * 
	 * @param j		The NccPostProcessorJob to analyse. This object should in 
	 * 				fact be a NccPostProcessorAnalysisJob object as it will be
	 * 				cast to this type of object first job in the method.
	 * @return		The NccError generated if there is one, null 
	 * 				otherwise.
	 */
	private NccError checkPrintf(NccPostProcessorJob aj)
	{
		NccError e = new NccError();
		ArrayList<String> formatOrder = new ArrayList<String>();
		/*
		 * See tables above for valid formatting characters in printf.
		 */
		//Get the format string
		String format = sh.getLineNumber(aj.getBeginLine());
		format = format.substring(format.indexOf('"', 0)+1, format.indexOf('"', format.indexOf('"', 0)+1));
		char[] cArray = format.toCharArray();
		int i = 0;
		//Consume characters until a '%' is found 
		while(i < cArray.length){
			if(cArray[i] == '%'){
				i++;
				while(!validPrintfEnds.contains(Character.toString(cArray[i]))){
					if(validPrintfMids.contains(Character.toString(cArray[i]))){
						i++;
					}
					else{
						//The character was not recognised as a valid printf formatter (conversion character)
						e.setLine(aj.getBeginLine());
						e.setErrorNumber(123);
						e.addTextln("Warning: Line: " + e.getLine() + ": '" + cArray[i] + "' is not a valid printf string conversion character.");
						if(clo.dFlag){
							String calc = sh.getLineNumber(e.getLine());
							e.addTextln("\t" + calc);
							e.addTextln("\t" + sh.generateWhiteSpace(calc.indexOf("printf(")+9+i, e.getLine()) + "^");
						}
						i++;
						break; //Valid conversion char not found move on to next '%' 
					}
				}
				if(validPrintfEnds.contains(Character.toString(cArray[i]))){
					//If the character is a valid end char find out what it is
					switch((int) cArray[i]){
					case ((int)'d') : formatOrder.add("int"); break;
					case ((int)'i') : formatOrder.add("int"); break;
					case ((int)'o') : formatOrder.add("octal"); break;
					case ((int)'x') : formatOrder.add("hex"); break;
					case ((int)'X') : formatOrder.add("hex"); break;
					case ((int)'u') : formatOrder.add("int"); break;
					case ((int)'c') : formatOrder.add("char"); break;
					case ((int)'s') : formatOrder.add("string"); break;
					case ((int)'f') : formatOrder.add("float"); break;
					case ((int)'e') : formatOrder.add("float"); break;
					case ((int)'E') : formatOrder.add("float"); break;
					case ((int)'g') : formatOrder.add("float"); break;
					case ((int)'G') : formatOrder.add("float"); break;
					case ((int)'p') : formatOrder.add("pointer"); break;
					case ((int)'%') : formatOrder.add("%"); break;
					default 		: break;
					}
					i++;
				}
			}
			else{
				//Consume
				i++;
			}
		}
		
		/*
		 * Compare the lists: conversion char vs. arguments, remember that the
		 * size of the aj.getArgTokenList() ArrayList should be be one greater 
		 * than the number of conversion chars because the initial String 
		 * containing the formatting is also included in that count.
		 */
		if(formatOrder.size() == aj.getArgTokenList().size()-1){
			/*
			 * The lists are the same size, check the types etc.
			 * 
			 * Begin by discarding the fist element of the list of args and
			 * exchanging any args of type "char aorp" to type "string".  
			 */
			ArrayList<String> argsList = new ArrayList<String>();
			for(int j = 1; j < aj.getArgTokenList().size(); j++){
				String t;
				if( ((t = aj.getArgTokenList().get(j)).equals("char aorp")) || (t.equals("int aorp")) ){
					argsList.add("string");
				}
				else{
					argsList.add(aj.getArgTokenList().get(j));
				}
			}
			for(int j = 0; j < argsList.size(); j++){
				if(argsList.get(j).equals(formatOrder.get(j))){
					//No Error
				}
				else{
					//Error
					e.setLine(aj.getBeginLine());
					e.setErrorNumber(123);
					e.addTextln("Warning: Line: " + e.getLine() + ": 'printf' conversion character/argument type mismatch.");
					if(clo.dFlag){
						String stndrdth = "th";
						switch((j+2)%10){
						case 0 : stndrdth = "th"; break;
						case 1 : stndrdth = "st"; break;
						case 2 : stndrdth = "nd"; break;
						case 3 : stndrdth = "rd"; break;
						case 4 : stndrdth = "th"; break;
						case 5 : stndrdth = "th"; break;
						case 6 : stndrdth = "th"; break;
						case 7 : stndrdth = "th"; break;
						case 8 : stndrdth = "th"; break;
						case 9 : stndrdth = "th"; break;
						}
						String calc = sh.getLineNumber(e.getLine());
						e.addTextln("\t" + calc);
						e.addTextln("\t" + sh.generateWhiteSpace(calc.indexOf("printf("), e.getLine()) + "^");
						e.addTextln("DIAGNOSIS: The " + (j+2) + stndrdth + " conversion character/argument pair are not the same type.");
						e.addTextln("Format string expected '" + formatOrder.get(j) + "' but '" + argsList.get(j) + "' was found in the arguments!");
					}
				}
			}
		}
		else{
			//The lists are not the same size
			if(formatOrder.size() < aj.getArgTokenList().size()-1){
				//More arguments than conversion characters.
				e.setLine(aj.getBeginLine());
				e.setErrorNumber(123);
				e.addTextln("Warning: Line: " + e.getLine() + ": 'printf' called with too many arguments.");
				if(clo.dFlag){
					String calc = sh.getLineNumber(e.getLine());
					e.addTextln("\t" + calc);
					e.addTextln("\t" + sh.generateWhiteSpace(calc.indexOf("printf("), e.getLine()) + "^");
					e.addTextln("DIAGNOSIS: The formatted string in printf contains " + formatOrder.size() + " conversion characters " + 
								"however it was passed " + (aj.getArgTokenList().size()-1) + " valid arguments.");
					e.addTextln("To fix the error include an extra conversion character(s) in the format string or remove the appropriate argument(s).");
				}
			}
			else{
				//More conversion characters than arguments.
				e.setLine(aj.getBeginLine());
				e.setErrorNumber(123);
				e.addTextln("Warning: Line: " + e.getLine() + ": The 'printf' format string includes too many conversion characters for the number of arguments passed.");
				if(clo.dFlag){
					String calc = sh.getLineNumber(e.getLine());
					e.addTextln("\t" + calc);
					e.addTextln("\t" + sh.generateWhiteSpace(calc.indexOf("printf("), e.getLine()) + "^");
					e.addTextln("DIAGNOSIS: The formatted string in printf contains " + formatOrder.size() + " conversion characters " + 
								"however it was only passed " + (aj.getArgTokenList().size()-1) + " valid arguments.");
					e.addTextln("To fix the error remove the extra conversion character(s) in the format string or include another argument(s).");
				}
			}
		}
		if(clo.dFlag){
			//Set the Error number for the error (if one was created).
			e.addText("See NCC Error Code 123 for more details.");
		}
		//Return the error if there was one 
		if(e.getErrorNumber() == 0){
			return null;
		}
		else{
			return e;
		}
	}

	/*
	 * Notes: The valid string format characters for scanf:
	 * Character	Argument Type: Printed as
	 *     d		int *; decimal integer
	 *     i		int *; integer, hexadecimal, octal
	 *     o		int *; octal integer
	 *     u		unsigned int*; unsigned decimal integer
	 *     x		int *; hexadecimal integer
	 *     c		char *; characters
	 *     s		char *; character string
	 *   e,f,g		float *; floating point number
	 *     %		literal %; no assignment is made
	 * 
	 * Table taken from:
	 * Kernighan B. W. & Ritchie D. M, The C Programming Language, Second 
	 * Edition. Prentice Hall: New Jersey. p. 158. 
	 */
	
	/**
	 * NCC Error Number(s): 124 (Set as 123 for GCC parsing reasons)
	 * 
	 * Analyses the line scanf(...) is made on to make sure it complies. If it 
	 * is not a correct call then an Error Message will be created and returned
	 * by the method.   
	 * 
	 * @param j		The NccPostProcessorJob to analyse. This object should in 
	 * 				fact be a NccPostProcessorAnalysisJob object as it will be
	 * 				cast to this type of object first job in the method.
	 * @return		The NccError generated if there is one, null 
	 * 				otherwise.
	 */
	private NccError checkScanf(NccPostProcessorJob aj)
	{
		NccError e = new NccError();
		ArrayList<String> formatOrder = new ArrayList<String>();
		/*
		 * See tables above for valid formatting characters in scanf.
		 */
		//Get the format string
		String format = sh.getLineNumber(aj.getBeginLine());
		format = format.substring(format.indexOf('"', 0)+1, format.indexOf('"', format.indexOf('"', 0)+1));
		char[] cArray = format.toCharArray();
		int i = 0;
		//Consume characters until a '%' is found 
		while(i < cArray.length){
			if(cArray[i] == '%'){
				i++;
				while(!validScanfEnds.contains(Character.toString(cArray[i]))){
					if(validScanfMids.contains(Character.toString(cArray[i]))){
						i++;
					}
					else{
						//The character was not recognised as a valid scanf conversion character
						e.setLine(aj.getBeginLine());
						e.setErrorNumber(123);
						e.addTextln("Warning: Line: " + e.getLine() + ": '" + cArray[i] + "' is not a valid scanf string conversion character.");
						if(clo.dFlag){
							String calc = sh.getLineNumber(e.getLine());
							e.addTextln("\t" + calc);
							e.addTextln("\t" + sh.generateWhiteSpace(calc.indexOf("scanf(")+9+i, e.getLine()) + "^");
						}
						i++;
						break; //Valid conversion char not found move on to next '%' 
					}
				}
				if(validScanfEnds.contains(Character.toString(cArray[i]))){
					//If the character is a valid end char find out what it is
					switch((int) cArray[i]){
					case ((int)'d') : formatOrder.add("int"); break;
					case ((int)'i') : formatOrder.add("int"); break;
					case ((int)'o') : formatOrder.add("octal"); break;
					case ((int)'u') : formatOrder.add("int"); break;
					case ((int)'x') : formatOrder.add("hex"); break;
					case ((int)'c') : formatOrder.add("char"); break;
					case ((int)'s') : formatOrder.add("string"); break;
					case ((int)'e') : formatOrder.add("float"); break;
					case ((int)'f') : formatOrder.add("float"); break;
					case ((int)'g') : formatOrder.add("float"); break;
					case ((int)'%') : formatOrder.add("%"); break;
					default 		: break;
					}
					i++;
				}
			}
			else{
				//Consume
				i++;
			}
		}
		
		/*
		 * Compare the lists: conversion char vs. arguments, remember that the
		 * size of the aj.getArgTokenList() ArrayList should be be one greater 
		 * than the number of conversion chars because the initial String 
		 * containing the formatting is also included in that count.
		 */
		if(formatOrder.size() == aj.getArgTokenList().size()-1){
			/*
			 * The lists are the same size, check the types etc.
			 * 
			 * Begin by discarding the fist element of the list of args and
			 * exchanging any args of type "char aorp" to type "string".  
			 */
			ArrayList<String> argsList = new ArrayList<String>();
			for(int j = 1; j < aj.getArgTokenList().size(); j++){
				/*
				 * The second conversion that takes place in the printf method 
				 * above has been removed from the line below. There is a 
				 * possibility this will casue typing errors...not sure?
				 */
				if( (aj.getArgTokenList().get(j).equals("char aorp"))){
					argsList.add("string");
				}
				else{
					argsList.add(aj.getArgTokenList().get(j));
				}
			}
			for(int j = 0; j < argsList.size(); j++){
				if(argsList.get(j).equals(formatOrder.get(j))){
					//No type errors
				}
				else{
					//Error
					e.setLine(aj.getBeginLine());
					e.setErrorNumber(123);
					e.addTextln("Warning: Line: " + e.getLine() + ": 'scanf' conversion character/argument type mismatch.");
					if(clo.dFlag){
						String stndrdth = "th";
						switch((j+2)%10){
						case 0 : stndrdth = "th"; break;
						case 1 : stndrdth = "st"; break;
						case 2 : stndrdth = "nd"; break;
						case 3 : stndrdth = "rd"; break;
						case 4 : stndrdth = "th"; break;
						case 5 : stndrdth = "th"; break;
						case 6 : stndrdth = "th"; break;
						case 7 : stndrdth = "th"; break;
						case 8 : stndrdth = "th"; break;
						case 9 : stndrdth = "th"; break;
						}
						String calc = sh.getLineNumber(e.getLine());
						e.addTextln("\t" + calc);
						e.addTextln("\t" + sh.generateWhiteSpace(calc.indexOf("printf("), e.getLine()) + "^");
						e.addTextln("DIAGNOSIS: The " + (j+2) + stndrdth + " conversion character/argument pair are not the same type.");
						e.addTextln("Format string expected '" + formatOrder.get(j) + "' but '" + argsList.get(j) + "' was found in the arguments!");
					}
				}
			}
			/*
			 * Check that the arguments passed are all addresses in
			 * momory (pointers).
			 * 
			 * Begin by breaking up the call in to blocks ot arguments
			 * and storing the args in an ordered ArrayList.
			 */
			String working = sh.getLineNumber(aj.getBeginLine());
			StringTokenizer st = new StringTokenizer(working, ",");
			//Discard the first argument (formatted string)
			if(st.hasMoreTokens()){
				st.nextToken();
			}
			//Store the rest of the tokens in an ArrayList
			ArrayList<String> checker = new ArrayList<String>();
			while(st.hasMoreTokens()){
				checker.add(st.nextToken().trim());
			}
			/*
			 * Now check the types of the arguments against the string
			 * representing it to ensure it is an address in memory. If
			 * the type is "string" then this is already a pointer or
			 * array so there is no need to check these.
			 */
			for(int y = 0; y < argsList.size(); y++){
				if(argsList.get(y).equals("string")){
					//Check the argument string to ensure it doesn't start with &
					if(checker.get(y).startsWith("&")){
						//Warning: Variable begins with & so is an address to a pointer which is probably non-sence!
						e.setLine(aj.getBeginLine());
						e.setErrorNumber(123);
						String stndrdth = "th";
						switch((y+2)%10){
						case 0 : stndrdth = "th"; break;
						case 1 : stndrdth = "st"; break;
						case 2 : stndrdth = "nd"; break;
						case 3 : stndrdth = "rd"; break;
						case 4 : stndrdth = "th"; break;
						case 5 : stndrdth = "th"; break;
						case 6 : stndrdth = "th"; break;
						case 7 : stndrdth = "th"; break;
						case 8 : stndrdth = "th"; break;
						case 9 : stndrdth = "th"; break;
						}
						e.addTextln("Warning: Line: " + e.getLine() + ": The " + (y+2) + stndrdth + " argument to 'scanf' is already an address in memory (pointer).");
						if(clo.dFlag){
							String calc = sh.getLineNumber(e.getLine());
							e.addTextln("\t" + calc);
							e.addTextln("\t" + sh.generateWhiteSpace(calc.indexOf(checker.get(y))+1, e.getLine()) + "^");
							e.addTextln("DIAGNOSIS: Argument '" + checker.get(y) + "' is already an address in memory (pointer/array), therefore '&' probably doesn't need to be used in order to assign a value to it.");
							e.addTextln("The warning can therefore be removed by deleting the & character.");
						}
					}
				}
				else{
					if(checker.get(y).startsWith("&")){
						//Variable begins with &... so is address
					}
					else{
						//Error...
						e.setLine(aj.getBeginLine());
						e.setErrorNumber(123);
						String stndrdth = "th";
						switch((y+2)%10){
						case 0 : stndrdth = "th"; break;
						case 1 : stndrdth = "st"; break;
						case 2 : stndrdth = "nd"; break;
						case 3 : stndrdth = "rd"; break;
						case 4 : stndrdth = "th"; break;
						case 5 : stndrdth = "th"; break;
						case 6 : stndrdth = "th"; break;
						case 7 : stndrdth = "th"; break;
						case 8 : stndrdth = "th"; break;
						case 9 : stndrdth = "th"; break;
						}
						e.addTextln("Error: Line: " + e.getLine() + ": The " + (y+2) + stndrdth + " argument to 'scanf' is not an address in memory (pointer).");
						if(clo.dFlag){
							String calc = sh.getLineNumber(e.getLine());
							e.addTextln("\t" + calc);
							e.addTextln("\t" + sh.generateWhiteSpace(calc.indexOf(checker.get(y))+1, e.getLine()) + "^");
							e.addTextln("DIAGNOSIS: Argument '" + checker.get(y) + "' is not an address in memory.");
							e.addTextln("If '" + checker.get(y) + "' is a variable insert a '&' character in order to point to its' address in memory.");
						}
					}
				}
			}
		}
		else{
			//The lists are not the same size
			if(formatOrder.size() < aj.getArgTokenList().size()-1){
				//More arguments than conversion characters.
				e.setLine(aj.getBeginLine());
				e.setErrorNumber(123);
				e.addTextln("Warning: Line: " + e.getLine() + ": 'scanf' called with too many arguments.");
				if(clo.dFlag){
					String calc = sh.getLineNumber(e.getLine());
					e.addTextln("\t" + calc);
					e.addTextln("\t" + sh.generateWhiteSpace(calc.indexOf("scanf("), e.getLine()) + "^");
					e.addTextln("DIAGNOSIS: The formatted string in scanf contains " + formatOrder.size() + " conversion characters " + 
								"however it was passed " + (aj.getArgTokenList().size()-1) + " valid arguments.");
					e.addTextln("To fix the error include an extra conversion character(s) in the format string or remove the appropriate argument(s).");
				}
			}
			else{
				//More conversion characters than arguments.
				e.setLine(aj.getBeginLine());
				e.setErrorNumber(123);
				e.addTextln("Warning: Line: " + e.getLine() + ": The 'scanf' format string includes too many conversion characters for the number of arguments passed.");
				if(clo.dFlag){
					String calc = sh.getLineNumber(e.getLine());
					e.addTextln("\t" + calc);
					e.addTextln("\t" + sh.generateWhiteSpace(calc.indexOf("scanf("), e.getLine()) + "^");
					e.addTextln("DIAGNOSIS: The formatted string in scanf contains " + formatOrder.size() + " conversion characters " + 
								"however it was only passed " + (aj.getArgTokenList().size()-1) + " valid arguments.");
					e.addTextln("To fix the error remove the extra conversion character(s) in the format string or include another argument(s).");
				}
			}
		}
		if(clo.dFlag){
			//Set the Error number for the error (if one was created).
			e.addText("See NCC Error Code 124 for more details.");
		}
		//Return the error if there was one 
		if(e.getErrorNumber() == 0){
			return null;
		}
		else{
			return e;
		}
	}
	
	/**
	 * Initialises the list of Valid Printf Ends.
	 */
	private void initValidPrintfEnds()
	{
		validPrintfEnds.add("d");
		validPrintfEnds.add("i");
		validPrintfEnds.add("o");
		validPrintfEnds.add("x");
		validPrintfEnds.add("X");
		validPrintfEnds.add("u");
		validPrintfEnds.add("c");
		validPrintfEnds.add("s");
		validPrintfEnds.add("f");
		validPrintfEnds.add("e");
		validPrintfEnds.add("E");
		validPrintfEnds.add("g");
		validPrintfEnds.add("G");
		validPrintfEnds.add("p");
		validPrintfEnds.add("%");
	}
	
	/**
	 * Initialises the list of Valid Printf Mids.
	 */
	private void initValidPrintfMids()
	{
		validPrintfMids.add("0");
		validPrintfMids.add("1");
		validPrintfMids.add("2");
		validPrintfMids.add("3");
		validPrintfMids.add("4");
		validPrintfMids.add("5");
		validPrintfMids.add("6");
		validPrintfMids.add("7");
		validPrintfMids.add("8");
		validPrintfMids.add("9");
		validPrintfMids.add(".");
		validPrintfMids.add("-");
		validPrintfMids.add("+");
		validPrintfMids.add("*");
		validPrintfMids.add("#");
		validPrintfMids.add(" ");
		validPrintfMids.add("'");
		validPrintfMids.add("I");
		validPrintfMids.add("h");
		validPrintfMids.add("l");
		validPrintfMids.add("j");
		validPrintfMids.add("z");
		validPrintfMids.add("L");
	}
	
	/**
	 * Initialises the list of Valid Scanf Ends.
	 */
	private void initValidScanfEnds()
	{
		validScanfEnds.add("d");
		validScanfEnds.add("i");
		validScanfEnds.add("o");
		validScanfEnds.add("u");
		validScanfEnds.add("x");
		validScanfEnds.add("c");
		validScanfEnds.add("s");
		validScanfEnds.add("e");
		validScanfEnds.add("f");
		validScanfEnds.add("g");
		validScanfEnds.add("%");
	}
	
	private void initValidScanfMids()
	{
		validPrintfMids.add("l");
	}
}
