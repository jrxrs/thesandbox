package compiler.tools;

public class CommandNotFoundException extends Exception
{
	/**
	 * The Eclipse generated serialVersionUID.
	 */
	private static final long serialVersionUID = 4459662145016103382L;
	
	public CommandNotFoundException()
	{
		super();
	}
	
	public CommandNotFoundException(String message)
	{
		super(message);
	}
    
	public CommandNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
    
	public CommandNotFoundException(Throwable cause)
	{
		super(cause);
	}
}
