package compiler.tools;

/**
 * This class stores the details of a General Error (GCC or NCC Error).
 * 
 * @author Richard Hill
 */

public class GeneralError
{
	protected int line;
	protected String message;
	protected int errorNumber;
	
	/**
	 * Creates a new empty GeneralError object.
	 */
	public GeneralError()
	{
		line = -10;
		message = "";
		errorNumber = 0;
	}
	
	/**
	 * Creates a new GeneralError initialised with the values passed.
	 * 
	 * @param line		The source file line no. to which this error relates.
	 */
	public GeneralError(int line)
	{
		this.line = line;
		message = "";
		errorNumber = 0;
	}
	
	/**
	 * Creates a new GeneralError initialised with the values passed.
	 * 
	 * @param message	The actual error message.
	 */
	public GeneralError(String message)
	{
		line = -10;
		this.message = message;
		errorNumber = 0;
	}
	
	/**
	 * Creates a new GeneralError initialised with the values passed.
	 * 
	 * @param line		The source file line no. to which this error relates.
	 * @param message	The actual error message.
	 */
	public GeneralError(int line, String message)
	{
		this.line = line;
		this.message = message;
		errorNumber = 0;
	}
	
	/**
	 * Creates a new GeneralError initialised with the values passed.
	 * 
	 * @param line			The source file line no. to which this error relates.
	 * @param errorNumber	The error number of this error. 
	 */
	public GeneralError(int line, int errorNumber)
	{
		this.line = line;
		message = "";
		this.errorNumber = errorNumber;
	}
	
	/**
	 * Creates a new GeneralError initialised with the values passed.
	 * 
	 * @param line			The source file line no. to which this error 
	 * 						relates.
	 * @param message		The actual error message.
	 * @param errorNumber	The error number of this error. 
	 */
	public GeneralError(int line, String message, int errorNumber)
	{
		this.line = line;
		this.message = message;
		this.errorNumber = errorNumber;
	}
	
	/**
	 * Gets the error message to be printed.
	 * 
	 * @return	The error message to be printed.
	 */
	public String getMessage() 
	{
		return message;
	}

	/**
	 * Sets the error message to be printed.
	 * 
	 * @param message	The error message to be printed.
	 */
	public void setMessage(String message) 
	{
		this.message = message;
	}

	/**
	 * Gets the line number this error messages relates to.
	 * 
	 * @return	The line number this error messages relates to.
	 */
	public int getLine() 
	{
		return line;
	}

	/**
	 * Sets the line number this error message relates to. 
	 * 
	 * @param line	The line number this error messages relates to.
	 */
	public void setLine(int line) 
	{
		this.line = line;
	}
	
	/**
	 * Appends a line of text to the message for this error.
	 * 
	 * @param app	The text to add to this line.
	 */
	public void addText(String app)
	{
		message = message.concat(app);
	}
	
	/**
	 * Appends a line of text to the message for this error and adds a
	 * new line character after the line of text.
	 * 
	 * @param app	The text to add to this line.
	 */
	public void addTextln(String app)
	{
		message = message.concat(app).concat("\n");
	}

	/**
	 * Gets the error number.
	 * 
	 * @return	The error number of this error.
	 */
	public int getErrorNumber()
	{
		return errorNumber;
	}

	/**
	 * Sets the error number.
	 * 
	 * @param errorNumber	The error number of this error.
	 */
	public void setErrorNumber(int errorNumber)
	{
		this.errorNumber = errorNumber;
	}
}
