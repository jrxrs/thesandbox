package compiler.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A test rig for testing components and methods of the compiler.tools package.
 * 
 * @author Richard Hill
 */

public class TestRig 
{
	/*
	 * Global Variables. 
	 */
	private NccSourceHandler nccsh;
	
	/**
	 * Test Rig main method.
	 * 
	 * @param args	Commandline arguments.
	 */
	public static void main(String args[])
	{
		new TestRig();
	}
	
	public TestRig()
	{
		//run();
		//runAgain();
		meth();
		//runAgainAgain();
	}

	private void meth()
	{
		for(int j = 0; j < 25; j++){
			System.out.println("j = " + j + " (j+1)%10) = " + (j+1)%10);
		}
	}

	public void run()
	{
		nccsh = new NccSourceHandler("testing.c");
		String liq = nccsh.getLineNumber(20);
		System.out.println(liq);
		char[] a = liq.toCharArray();
		for(int i = 0; i < a.length; i++){
			System.out.println((int) a[i]);
		}
	}
	
	private void runAgain() 
	{
		ArrayList<String> al = new ArrayList<String>();
		String one = "returnsAChar";
		String two = "functionA";
		String three = "returnAFloat";
		String four = "wontReturn";
		al.add(two);
		al.add(three);
		al.add(four);
		al.add(one);
		
		System.out.println(NccStringCompare.comp(al, "retunsachar"));
	}
	
	public void runAgainAgain()
	{
		File path = new File("StandardLibraries");
		File[] files = path.listFiles();
		for(int a = 0; a < files.length; a++){
			if(files[a].getName().endsWith(".h")){
				System.out.println("\t" + files[a].getName());
				try{
	                FileReader fileR = new FileReader(files[a]);
	                BufferedReader reader = new BufferedReader(fileR);
	            
	                String textInput = null;
	                while((textInput = reader.readLine()) != null){
	                	textInput = textInput.trim().toLowerCase();
	                	if(textInput.startsWith("#")){
	                		textInput = textInput.substring(1).trim();
	                		if(textInput.startsWith("include")){
	                			System.out.println("#" + textInput);
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
		}
	}
}
