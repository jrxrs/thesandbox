package compiler.tools;

import java.util.ArrayList;

/**
 * This class stores information on each function that is declared in the C
 * program.
 * 
 * @author Richard Hill
 */

public class NccFunction 
{
	private String name;
	private boolean hashIncluded = false;
	private String type;
	private int line;
	private int charPos;
	private int noArgs;
	private ArrayList<NccFunctionArg> args;
	
	/**
	 * Constructs a new NccFunction that is #included by the user.
	 * 
	 * @param name			The name of the function.
	 * @param hashIncluded	True because the function is #included
	 */
	public NccFunction(String name, boolean hashIncluded)
	{
		this.name = name;
		this.hashIncluded = true; //always true!!!
		type = "#included";
		line = 0;
		charPos = 0;
		noArgs = -1;
		args = new ArrayList<NccFunctionArg>();
	}
	
	/**
	 * Constructs a new Function with the assigned values.
	 */
	public NccFunction()
	{
		name = null;
		type = null;
		line = -10;
		charPos = -10;
		noArgs = 0;
		args = new ArrayList<NccFunctionArg>();
	}
	
	/**
	 * Constructs a new Function with the assigned values.
	 * 
	 * @param name		The name of the function.
	 * @param type		The type of the function.
	 * @param line		The line number this function was declared on.
	 * @param charPos	The character position in the line this function is
	 * 					declared on.
	 */
	public NccFunction(String name, String type, int line, int charPos)
	{
		this.name = name;
		this.type = type;
		this.line = line;
		this.charPos = charPos;
		noArgs = 0;
		args = new ArrayList<NccFunctionArg>();
	}
	
	/**
	 * Constructs a new Function with the assigned values.
	 * 
	 * @param name		The name of the function.
	 * @param type		The type of the function.
	 * @param line		The line number this function was declared on.
	 * @param charPos	The character position in the line this function is
	 * 					declared on.
	 * @param noArgs	The number of arguments the function accepts.
	 */
	public NccFunction(String name, String type, int line, int charPos, int noArgs)
	{
		this.name = name;
		this.type = type;
		this.line = line;
		this.charPos= charPos;
		this.noArgs = noArgs;
		args = new ArrayList<NccFunctionArg>();
	}
	
	/**
	 * Constructs a new Function with the assigned values.
	 * 
	 * @param name		The name of the function.
	 * @param type		The type of the function.
	 * @param line		The line number this function was declared on.
	 * @param charPos	The character position in the line this function is
	 * 					declared on.
	 * @param noArgs	The number of arguments the function accepts.
	 * @param args		The ordered list of arguments.
	 */
	public NccFunction(String name, String type, int line, int charPos, int noArgs, ArrayList<NccFunctionArg> args)
	{
		this.name = name;
		this.type = type;
		this.line = line;
		this.charPos = charPos;
		this.noArgs = noArgs;
		this.args = args;
	}
	
	/**
	 * Adds the parameter argX of type NccFunctionArg to the list of arguments
	 * of this function.
	 * 
	 * @param argX	The argument object to add to the ordered list of arguments.
	 */
	public void addArg(NccFunctionArg argX)
	{
		args.add(argX);
	}

	/**
	 * Gets the name of this function.
	 * 
	 * @return	The name of this function.
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * Sets the name of this function.
	 * 
	 * @param name	The name of this function.
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * Gets the type of this function. 
	 * 
	 * @return	The type of this function.
	 */
	public String getType() 
	{
		return type;
	}

	/**
	 * Sets the type of this function.
	 * 
	 * @param type	The type to set this function to.
	 */
	public void setType(String type) 
	{
		this.type = type;
	}

	/**
	 * Gets the line this function was declared on.
	 * 
	 * @return	The line number this function was declared on.
	 */
	public int getLine() 
	{
		return line;
	}

	/**
	 * Sets the line number this function was declared on.
	 * 
	 * @param line	The line to this function is declared on.
	 */
	public void setLine(int line) 
	{
		this.line = line;
	}
	
	/**
	 * Gets the number of arguments this function accepts.
	 * 
	 * @return	The number of arguments this function accepts. 
	 */
	public int getNoArgs() 
	{
		return noArgs;
	}

	/**
	 * Sets the number of arguments this function accepts.
	 * 
	 * @param noArgs	The number of arguments this function accepts.
	 */
	public void setNoArgs(int noArgs) 
	{
		this.noArgs = noArgs;
	}
	
	/**
	 * Gets the character position in the line this function was declared on.
	 * 
	 * @return	The charPos in the line this function was declared on.
	 */
	public int getCharPos()
	{
		return charPos;
	}

	/**
	 * Sets the character position in the line this function was declared on. 
	 * 
	 * @param charPos	The value to set the charPos to.
	 */
	public void setCharPos(int charPos)
	{
		this.charPos = charPos;
	}
	
	/**
	 * Gets the ArrayList which stores the list of arguments that this function
	 * was declared with.
	 * 
	 * @return	The ArrayList storing the list of arguments for this function.
	 */
	public ArrayList<NccFunctionArg> getArgsArrayList()
	{
		return args;
	}
	
	/**
	 * This method prints the details of a function.
	 */
	public void printFunctionDetails()
	{
		System.out.println();
		System.out.println("FUNCTION:");
		System.out.println("Function: '" + name + "' of Type: " + type + " on Line: " + line + " taking " + noArgs + " argument(s):");
		for(int i = 0; i < args.size(); i++){
			NccFunctionArg a = args.get(i);
			System.out.println("Argument " + i + ": Name: " + a.getArgName() + " Type: " + a.getType());
		}
	}

	/**
	 * Returns whether or not the function is #included.
	 * 
	 * @return	Whether the function was #include
	 */
	public boolean isHashIncluded()
	{
		return hashIncluded;
	}
}
