package compiler.tools;

public class FunctionUndefinedException extends Exception
{

	/**
	 * The Eclipse generated serialVersionUID.
	 */
	private static final long serialVersionUID = 7927580452385873936L;
	
	public FunctionUndefinedException()
	{
		super();
	}
	
	public FunctionUndefinedException(String message)
	{
		super(message);
	}
    
	public FunctionUndefinedException(String message, Throwable cause)
	{
		super(message, cause);
	}
    
	public FunctionUndefinedException(Throwable cause)
	{
		super(cause);
	}
}
