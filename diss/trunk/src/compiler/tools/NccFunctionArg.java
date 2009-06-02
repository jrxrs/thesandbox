package compiler.tools;

/**
 * This class stores the details of an argument to a function.
 * 
 * @author Richard Hill
 */

public class NccFunctionArg 
{
	private String type;
	private String argName;
	
	
	/**
	 * NccFunctionArg constructor.
	 * 
	 * @param type		The type of this argument.
	 * @param argName	The name of this argument.
	 */
	public NccFunctionArg(String type, String argName)
	{
		this.type = type;
		this.argName = argName;
	}

	/**
	 * Returns the type of this argument.
	 * 
	 * @return	The type of this argument
	 */
	public String getType() 
	{
		return type;
	}

	/**
	 * Sets the type of this argument.
	 * 
	 * @param type	The type to set this argument to.
	 */
	public void setType(String type) 
	{
		this.type = type;
	}

	/**
	 * Get the name of this argument.
	 * 
	 * @return	The name of this argument.
	 */
	public String getArgName() 
	{
		return argName;
	}

	/**
	 * Sets the name of this argument.
	 * 
	 * @param argName	The name of this argument.
	 */
	public void setArgName(String argName) 
	{
		this.argName = argName;
	}
	
	
}
