package compiler.tools;

public class VariableUndeclaredException extends Exception
{

	/**
	 * The Eclipse generated serialVersionUID.
	 */
	private static final long serialVersionUID = 3271918587440311977L;
	
	public VariableUndeclaredException()
	{
		super();
	}
	
	public VariableUndeclaredException(String message)
	{
		super(message);
	}
    
	public VariableUndeclaredException(String message, Throwable cause)
	{
		super(message, cause);
	}
    
	public VariableUndeclaredException(Throwable cause)
	{
		super(cause);
	}
}
