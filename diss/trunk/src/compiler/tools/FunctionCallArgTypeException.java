package compiler.tools;

public class FunctionCallArgTypeException extends Exception 
{
	/**
	 * The Eclipse generated serialVersionUID.
	 */
	private static final long serialVersionUID = 9174703521880781501L;

	public FunctionCallArgTypeException()
	{
		super();
	}
	
	public FunctionCallArgTypeException(String message)
	{
		super(message);
	}
    
	public FunctionCallArgTypeException(String message, Throwable cause)
	{
		super(message, cause);
	}
    
	public FunctionCallArgTypeException(Throwable cause)
	{
		super(cause);
	}
}
