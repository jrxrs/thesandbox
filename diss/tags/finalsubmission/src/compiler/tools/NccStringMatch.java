package compiler.tools;

/**
 * Contains the details of a String match.
 * 
 * @author Richard Hill
 */

public class NccStringMatch 
{
	private String theStringThatMatched;
	private int weight;
	
	/**
	 * Creates a new match.
	 * 
	 * @param theStringThatMatched	The String that mactched.
	 * @param weight				The weight of the match [(low) 0 - 3 (high)]
	 */
	public NccStringMatch(String theStringThatMatched, int weight)
	{
		this.theStringThatMatched = theStringThatMatched;
		this.weight = weight;
	}

	/**
	 * Gets the string that matched.
	 * 
	 * @return	The String that matched.
	 */
	public String getTheStringThatMatched()
	{
		return theStringThatMatched;
	}

	/**
	 * Sets the string that matched.
	 * 
	 * @param theStringThatMatched	The string that matched.
	 */
	public void setTheStringThatMatched(String theStringThatMatched)
	{
		this.theStringThatMatched = theStringThatMatched;
	}

	/**
	 * Gets how good that match was.
	 * 
	 * @return	How good that match was [(low) 0 - 3 (high)].
	 */
	public int getWeight()
	{
		return weight;
	}

	/**
	 * Sets how good that match was.
	 * 
	 * @param weight	How good that match was [(low) 0 - 3 (high)]. 
	 */
	public void setWeight(int weight)
	{
		this.weight = weight;
	}
}
