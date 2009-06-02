package compiler.tools;

/**
 * This class stores the details of a GCC Error.
 * 
 * @author Richard Hill
 */

public class GccError extends GeneralError
{
	private String functionName;
	private String fileName;
	
	/**
	 * Creates a new empty GccError object.
	 */
	public GccError()
	{
		super();
		fileName = "";
		functionName = "";
	}
	
	/**
	 * Creates a new GccError initialised with the values passed.
	 * 
	 * @param fileName	The name of the source file GCC compiled.
	 * @param line		The source file line no. to which this error relates.
	 */
	public GccError(String fileName, int line)
	{
		super(line);
		this.fileName = fileName;
		functionName = "";
	}
	
	/**
	 * Creates a new GccError initialised with the values passed.
	 * 
	 * @param message	The actual error message.
	 */
	public GccError(String message)
	{
		super(message);
		fileName = "";
		functionName = "";
	}
	
	/**
	 * Creates a new GccError initialised with the values passed.
	 * 
	 * @param fileName	The name of the source file GCC compiled.
	 * @param line		The source file line no. to which this error relates.
	 * @param message	The actual error message.
	 */
	public GccError(String fileName, int line, String message)
	{
		super(line, message);
		this.fileName = fileName;
		functionName = "";
	}
	
	/**
	 * Creates a new GccError initialised with the values passed.
	 * 
	 * @param fileName		The name of the source file GCC compiled.
	 * @param functionName	The name of the function that GCC was parsing when
	 * 						it reported the error. 
	 */
	public GccError(String fileName, String functionName)
	{
		super();
		this.fileName = fileName;
		this.functionName = functionName;
	}
	
	/**
	 * Creates a new GccError initialised with the values passed.
	 * 
	 * @param fileName		The name of the source file GCC compiled.
	 * @param functionName	The name of the function that GCC was parsing when
	 * 						it reported the error. 
	 * @param line			The source file line no. to which this error 
	 * 						relates.
	 */
	public GccError(String fileName, String functionName, int line)
	{
		super(line);
		this.fileName = fileName;
		this.functionName = functionName;
	}
	
	/**
	 * Creates a new GccError initialised with the values passed.
	 * 
	 * @param fileName		The name of the source file GCC compiled.
	 * @param line			The source file line no. to which this error 
	 * 						relates.
	 * @param message		The actual error message.
	 * @param errorNumber	The unique error number associated with this GCC
	 * 						error (if there is one).
	 */
	public GccError(String fileName, int line, String message, int errorNumber)
	{
		super(line, message, errorNumber);
		this.fileName = fileName;
		functionName = "";
	}
	
	/**
	 * Creates a new GccError initialised with the values passed.
	 * 
	 * @param fileName		The name of the source file GCC compiled.
	 * @param line			The source file line no. to which this error 
	 * 						relates.
	 * @param message		The actual error message.
	 * @param errorNumber	The unique error number associated with this GCC
	 * 						error (if there is one).
	 * @param functionName	The name of the function that GCC was parsing when
	 * 						it reported the error. 
	 */
	public GccError(String fileName, int line, String message, int errorNumber, String functionName)
	{
		super(line, message, errorNumber);
		this.fileName = fileName;
		this.functionName = functionName;
	}

	/**
	 * Gets the name of the function GCC was parsing when it reported the 
	 * error.
	 * 
	 * @return 	The name of the function GCC was parsing when it reported the 
	 * 			error.
	 */
	public String getFunctionName() 
	{
		return functionName;
	}

	/**
	 * Sets the name of the function GCC was parsing when it reported the 
	 * error.
	 * 
	 * @param functionName 	The name of the function GCC was parsing when
	 * 						it reported the error.
	 */
	public void setFunctionName(String functionName)
	{
		this.functionName = functionName;
	}

	/**
	 * Gets the name of the file to which this error/warning relates.
	 * 
	 * @return	The name of the file to which this error/warning relates.
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the name of the file to which this error/warning relates.
	 * 
	 * @param fileName	The name of the file to which this error/warning 
	 * 					relates.
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
}
