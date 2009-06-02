package compiler.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This file handles the source file that is being compiler by NCC.
 * 
 * @author Richard Hill
 */

public class NccSourceHandler 
{
	private String fileName;
	private File file;
	private int noOfLines;
	
	/**
	 * Creates a Source File Handler for the specified source file.
	 * 
	 * @param fileName	The name of the source file.
	 */
	public NccSourceHandler(String fileName)
	{
		this.fileName = fileName;
		file = new File(fileName);
		noOfLines = calcNoOfLines(file);
	}

	/**
	 * Returns the text at the specified line.
	 * 
	 * @param line	The line number to return.
	 * @return 		The line of text and the line number specified.
	 */
	public String getLineNumber(int line)
	{
		String lineToFetch = "NccSourceHandler Error: Line Not Available";
		
		if(line > noOfLines){
			return lineToFetch;
		}
		else if(line == 0){
			return null;
		}
		else{
			try{
				FileReader fileR = new FileReader(file);
				BufferedReader reader = new BufferedReader(fileR);
	        
				while(line > 0){
					lineToFetch = reader.readLine();
					line--;
				}
	        
				reader.close();
				fileR.close();
			}
			catch(IOException e){
				System.out.println("NccSourceHandler: Error Reading from file: " + fileName);
			}
	        
			return lineToFetch.trim();
		}
	}
	
	private int calcNoOfLines(File file2) 
	{
		int lines = 0;
		
		try{
			FileReader fileR = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileR);
        
			while(reader.readLine() != null){
				lines++;
			}
        
			reader.close();
			fileR.close();
		}
		catch(IOException e){
			System.out.println("NccSourceHandler: Error Reading from file: " + fileName);
		}
		
		return lines;
	}
	
	/**
	 * Generates whitespace for source printing.
	 * 
	 * @param x		The number of spaces required.
	 * @param line	The line number to print.
	 * @return		The String consisting of x ' ' characters.
	 */
	public String generateWhiteSpace(int x, int line)
	{
		String liq = getLineNumberInternal(line);
		char[] a = liq.toCharArray();
		for(int i = 0; i < a.length; i++){
			if( ((int) a[i] == 9) || ((int) a[i] == 32) ){
				x--;
			}
			else if( ((int) a[i] != 9) && ((int) a[i] != 32) ){
				break;
			}
		}
		
		String whitespace = "";
		while(x > 0){
			whitespace = whitespace.concat(" ");
			x--;
		}
		
		return whitespace;
	}

	/**
	 * Returns the text at the specified line.
	 * 
	 * @param line	The line number to return.
	 * @return 		The line of text and the line number specified
	 */
	private String getLineNumberInternal(int line)
	{
		String lineToFetch = "NccSourceHandler Error: Line Not Available";
		
		if(line > noOfLines){
			return lineToFetch;
		}
		else{
			try{
				FileReader fileR = new FileReader(file);
				BufferedReader reader = new BufferedReader(fileR);
	        
				while(line > 0){
					lineToFetch = reader.readLine();
					line--;
				}
	        
				reader.close();
				fileR.close();
			}
			catch(IOException e){
				System.out.println("NccSourceHandler: Error Reading from file: " + fileName);
			}
	        
			return lineToFetch;
		}
	}
}
