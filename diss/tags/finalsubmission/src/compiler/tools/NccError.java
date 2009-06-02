package compiler.tools;

/**
 * This class stores the details of an NCC Error.
 * 
 * @author Richard Hill
 */

public class NccError extends GeneralError
{
	/**
	 * Creates a new empty NccError object.
	 */
	public NccError()
	{
		super();
	}
	
	/**
	 * Creates a new NccError initialised with the values passed.
	 * 
	 * @param line		The source file line no. to which this error relates.
	 */
	public NccError(int line)
	{
		super(line);
	}
	
	/**
	 * Creates a new NccError initialised with the values passed.
	 * 
	 * @param message	The actual error message.
	 */
	public NccError(String message)
	{
		super(message);
	}
	
	/**
	 * Creates a new NccError initialised with the values passed.
	 * 
	 * @param line		The source file line no. to which this error relates.
	 * @param message	The actual error message.
	 */
	public NccError(int line, String message)
	{
		super(line, message);
	}
	
	/**
	 * Creates a new NccError initialised with the values passed.
	 * 
	 * @param line			The source file line no. to which this error relates.
	 * @param errorNumber	The error number, used to tell whether or not to
	 * 						print this message - decisions made off the back 
	 * 						of the GCC output.
	 */
	public NccError(int line, int errorNumber)
	{
		super(line, errorNumber);
	}
	
	/**
	 * Creates a new NccError initialised with the values passed.
	 * 
	 * @param line			The source file line no. to which this error 
	 * 						relates.
	 * @param message		The actual error message.
	 * @param errorNumber	The error number, used to tell whether or not to
	 * 						print this message - decisions made off the back 
	 * 						of the GCC output. 
	 */
	public NccError(int line, String message, int errorNumber)
	{
		super(line, message, errorNumber);
	}
}
