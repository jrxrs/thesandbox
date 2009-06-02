package compiler.tools;

import java.util.ArrayList;

/**
 * Contains the details of a job required to run after a file has been parsed.
 * 
 * @author Richard Hill
 */

public class NccPostProcessorJob
{
	private String jobType;
	private int beginLine;
	private ArrayList<String> argTokenList; 
	
	/**
	 * Constructor.
	 * 
	 * @param jobType		The type of job to be performed. 
	 * @param beginLine		Indicates the line number on which the jobs 
	 * 						processing must begin.
	 */
	public NccPostProcessorJob(String jobType, int beginLine)
	{
		this.jobType = jobType;
		this.beginLine = beginLine;
		argTokenList = new ArrayList<String>();
	}

	/**
	 * Gets the type of job that this object represents.
	 * 
	 * @return	The type of job that this object represents.
	 */
	public String getJobType()
	{
		return jobType;
	}

	/**
	 * Sets the type of job that this object represents.
	 * 
	 * @param jobType	The type of job that this object represents.
	 */
	public void setJobType(String jobType)
	{
		this.jobType = jobType;
	}

	/**
	 * Gets the line number on which the jobs processing must begin.
	 * 
	 * @return	The line number on which the jobs processing must begin.
	 */
	public int getBeginLine()
	{
		return beginLine;
	}

	/**
	 * Sets the line number on which the jobs processing must begin.
	 * 
	 * @param beginLine		The line number on which the jobs processing must 
	 * 						begin.
	 */
	public void setBeginLine(int beginLine)
	{
		this.beginLine = beginLine;
	}
	
	/**
	 * Add an argument type to the ordered ArrayList of arguments.
	 * 
	 * @param nt	The NccToken associated with the function call to either
	 * 				printf or scanf. 
	 */
	public void addArgToken(String nt)
	{
		argTokenList.add(nt);
	}

	/**
	 * Gets the list of argument types for this function call. 
	 * 
	 * @return	The ArrayList of String of types.
	 */
	public ArrayList<String> getArgTokenList()
	{
		return argTokenList;
	}

	/**
	 * Sets the list of argument types for this function call.
	 * 
	 * @param argTokenList	The ArrayList of String of types.
	 */
	public void setArgTokenList(ArrayList<String> argTokenList)
	{
		this.argTokenList = argTokenList;
	}
}
