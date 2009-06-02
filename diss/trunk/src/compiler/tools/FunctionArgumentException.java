package compiler.tools;

public class FunctionArgumentException extends Exception
{

	/**
	 * The Eclipse generated serialVersionUID.
	 */
	private static final long serialVersionUID = 6790770711455361745L;
	
	public FunctionArgumentException()
	{
		super();
	}
	
	public FunctionArgumentException(String message)
	{
		super(message);
	}
    
	public FunctionArgumentException(String message, Throwable cause)
	{
		super(message, cause);
	}
    
	public FunctionArgumentException(Throwable cause)
	{
		super(cause);
	}

}
