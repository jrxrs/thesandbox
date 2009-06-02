package compiler.tools;

/**
 * This class contains the details of all the token identified by the compiler.
 * 
 * @author Richard Hill
 */
public class NccToken 
{
	private String id;
	private int declLine;
	private String tokenType;
	private String value;
	private boolean param = false;
	/*
	 * Note no variable can be initialised (i.e. using a constructor) as an 
	 * array. In order to set the variable as an array the setArray method 
	 * must be called which takes care of assigning all the necessary values. 
	 */
	private boolean isArray = false; 
	private int arraySize = -1; 
	
	/**
	 * Constructs a new Token object.
	 */
	public NccToken()
	{
		id = null;
		declLine = -1;
		tokenType = null;
		value = null;
	}
	
	/**
	 * Constructs a new Token object.
	 * 
	 * @param id	The id of the new token.
	 */
	public NccToken(String id)
	{
		this.id = id;
		declLine = -1;
		tokenType = null;
		value = null;
	}
	
	/**
	 * Constructs a new Token object.
	 * 
	 * @param id		The id of the new token.
	 * @param declLine	The line the new Token is declared on.
	 */
	public NccToken(String id, int declLine)
	{
		this.id = id;
		this.declLine = declLine;
		tokenType = null;
		value = null;
	}
	
	/**
	 * Constructs a new Token object.
	 * 
	 * @param id		The id of the new token.
	 * @param declLine	The line the new Token is declared on.
	 * @param tokenType	The type of the new Token.
	 */
	public NccToken(String id, int declLine, String tokenType)
	{
		this.id = id;
		this.declLine = declLine;
		this.tokenType = tokenType;
		value = null;
	}
	
	/**
	 * Constructs a new Token object.
	 * 
	 * @param id		The id of the new token.
	 * @param declLine	The line the new Token is declared on.
	 * @param tokenType	The type of the new Token.
	 * @param param		A boolean value for param.
	 */
	public NccToken(String id, int declLine, String tokenType, boolean param)
	{
		this.id = id;
		this.declLine = declLine;
		this.tokenType = tokenType;
		value = null;
		this.param = param;
	}

	/**
	 * Gets the id of this token.
	 * 
	 * @return id	The id of this Token object.
	 */
	public String getId() 
	{
		return id;
	}

	/**
	 * Sets the id of this Token object.
	 * 
	 * @param id	The id to set this token to.
	 */
	public void setId(String id) 
	{
		this.id = id;
	}

	/**
	 * Gets the declLine of this Token.
	 * 
	 * @return declLine		The line this Token was declared on.
	 */
	public int getDeclLine() 
	{
		return declLine;
	}

	/**
	 * Sets the line this Token was declared on.
	 * 
	 * @param declLine
	 */
	public void setDeclLine(int declLine) 
	{
		this.declLine = declLine;
	}

	/**
	 * Gets the type of this Token.
	 * 
	 * @return tokenType	The type of this token.
	 */
	public String getTokenType() 
	{
		return tokenType;
	}

	/**
	 * Sets the type of this Token.
	 * 
	 * @param tokenType		The type of this Token.
	 */
	public void setTokenType(String tokenType) 
	{
		this.tokenType = tokenType;
	}

	/**
	 * Gets the value of this Token.
	 * 
	 * @return value	The value of this Token.	
	 */ 
	public String getValue() 
	{
		return value;
	}

	/**
	 * Sets the value of this token.
	 * 
	 * @param value		The value to be assigned to this Token.
	 */
	public void setValue(String value) 
	{
		this.value = value;
	}
	
	/**
	 * Returns whether the token is a param token or not?
	 * 
	 * @return	True is the token is a param token, false otherwise.
	 */
	public boolean isParam()
	{
		return param;
	}
	
	/**
	 * Alters the variable to set it up as an array. Because of the 
	 * relationship pointers and arrays this method will also be invoked when
	 * a pointer is declared.
	 * 
	 * @param arraySize		The size of the array (i.e. max number of elements)
	 */
	public void setArray(int arraySize)
	{
		isArray = true;
		this.arraySize = arraySize;
	}

	/**
	 * Returns whether or not the variable is an array or not.
	 * 
	 * @return	A boolean, true if the variable is an array, otherwise false.
	 */
	public boolean isArray()
	{
		return isArray;
	}

	/**
	 * Gets the size of the array, i.e the maximum number of elements this 
	 * array can hold. 
	 * 
	 * @return	The size of the array, a integer >= 0 if the variable
	 * 			is indeed an array, and -1 if the variable is not an array.
	 */
	public int getArraySize()
	{
		return arraySize;
	}
}
