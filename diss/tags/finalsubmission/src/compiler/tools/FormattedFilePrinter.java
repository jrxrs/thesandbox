package compiler.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Prints formatted files for the command line.
 * 
 * @author Richard Hill
 */

public class FormattedFilePrinter 
{
	public static void print(ClassLoader cl, String fileName)
	{
		try{
			InputStream is = cl.getResourceAsStream(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        
            String text = null;
            while((text = reader.readLine()) != null){
            	if(text.startsWith("##", 0)){
            		//Ignore the line of text
            	}
            	else{
            		System.out.println(text);
            	}
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
	}
}
