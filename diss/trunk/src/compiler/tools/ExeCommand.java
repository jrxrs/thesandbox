package compiler.tools;

import java.lang.IllegalArgumentException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExeCommand 
{
	//Instance Variables
	private String command;
	private String stream;
	
	/**
	 * Default Constructor for ExeCommand
	 * 
	 * @param command						The command to execute.
	 * @param stream						Which input to get the commands 
	 * 										output from, either out or err.
	 * @throws IllegalArgumentException		Thrown if the stream is not 
	 * 										err or out
	 */
	public ExeCommand(String command, String stream) throws IllegalArgumentException
	{
		this.command = command;
		if( (stream.equals("out")) || (stream.equals("err")) ){
			this.stream = stream;
		}
		else{
			throw new IllegalArgumentException("1:Bad Stream Arg! Not out or err");
		}
	}
	
	/**
	 * Executes the command that this object holds capturing the commands
	 * output from the input stream held by its stream variable.
	 * 
	 * @param writeTo						The name of the file to write the 
	 * 										commands output to.
	 * @return								The File object that contains the
	 * 										output created by running this
	 * 										command.
	 * @throws IllegalArgumentException		Thrown if the stream is not 
	 * 										err or out
	 * @throws CommandNotFoundException		Thrown if the command cannot be 
	 * 										found or executed in the environment.
	 */
	public File go(String writeTo) throws IllegalArgumentException, CommandNotFoundException
	{
		char[] buf = new char[102400];
		int c = 0, i = 0;
		
		Runtime r = Runtime.getRuntime();
		Process p = null;
		try {
			p = r.exec(command);
		}
		catch(IOException e){
			throw new CommandNotFoundException("Command not found!");
		}
		try{
			BufferedInputStream in;
			if(stream.equals("err")){
				in = new BufferedInputStream(p.getErrorStream());  
			}
			else if(stream.equals("out")){
				in = new BufferedInputStream(p.getInputStream());
			}
			else{
				throw new IllegalArgumentException("2:Bad Stream Arg! Not in or err");
			}
			for(i = 0; (c != -1) && (i < 102400); i++){
				c = in.read();
				buf[i] = (char) c;
			}
			try{
				FileWriter writer = new FileWriter(writeTo);
				writer.write(buf, 0, i-1);
				writer.close();
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
		} 
		catch ( IOException e ) {
			System.out.println("IOException: " + e.getMessage());
		}
		
		return new File(writeTo);
	}
}
