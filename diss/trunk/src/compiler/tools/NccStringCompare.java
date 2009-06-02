package compiler.tools;

import java.util.ArrayList;

/**
 * A Static class used to compare Strings.
 * 
 * @author Richard Hill
 */

public class NccStringCompare 
{
	private ArrayList<String> history; 
	
	/**
	 * Creates an NccStringCompare object if a history is required.
	 */
	public NccStringCompare()
	{
		history = new ArrayList<String>();
	}
	
	/**
	 * The String matching process for variable and functions.
	 * 
	 * This matching process it a little crude - it works by switching
	 * all the chars in the target strings to lower case and then dividing
	 * the second string into a number of portions dependent on its size.
	 * Each portion is then searched for in the first string
	 * 
	 * @param listToCompare		The list of identifiers (variables or functions)
	 * 							to match against.
	 * @param two				The identifier that could not be located in the
	 * 							symbol table.
	 * @return					The closest matching string or "NO MATCH" if 
	 * 							everything failed.
	 */
	public static String comp(ArrayList<String> listToCompare, String two)
	{
		NccStringMatch match = new NccStringMatch("NO MATCH", 0);
		two = two.toLowerCase();
		int len = two.length();
		
		if(len < 3){
			//String too small just return no match
		}
		else if( (len == 3) || (len == 4) ){
			//match the whole string
			for(int a = 0; a < listToCompare.size(); a++){
				if(listToCompare.get(a).toLowerCase().contains(two)){
					NccStringMatch nsm = new NccStringMatch(listToCompare.get(a), 1);
					if(nsm.getWeight() > match.getWeight()){
						match = nsm;
					}
				}
			}
		}
		else if( (len > 4) && (len <= 10) ){
			//split the string in 2 and match
			int temp = Math.round(len / 2);
			String part1 = two.substring(0, temp);
			String part2 = two.substring(temp, len);
			/*if((len % 2) == 1){
				int temp = Math.round(len / 2);
				part1 = two.substring(0, temp);
				part2 = two.substring(temp, len);
			}
			else{
				int temp = len / 2;
				part1 = two.substring(0, temp);
				part2 = two.substring(temp, len);
			}*/
			for(int a = 0; a < listToCompare.size(); a++){
				NccStringMatch nsm = new NccStringMatch(listToCompare.get(a), 0);
				if(listToCompare.get(a).toLowerCase().contains(part1)){
					nsm.setWeight(nsm.getWeight()+1);
				}
				if(listToCompare.get(a).toLowerCase().contains(part2)){
					nsm.setWeight(nsm.getWeight()+1);
				}
				if(nsm.getWeight() > match.getWeight()){
					match = nsm;
				}
			}
		}
		else{
			//split the string into 3 parts and match
			int temp = Math.round(len / 3);
			String part1 = two.substring(0, temp);
			String part2 = two.substring(temp, 2 * temp);
			String part3 = two.substring(2 * temp, len);
			
			for(int a = 0; a < listToCompare.size(); a++){
				NccStringMatch nsm = new NccStringMatch(listToCompare.get(a), 0);
				if(listToCompare.get(a).toLowerCase().contains(part1)){
					nsm.setWeight(nsm.getWeight()+1);
				}
				if(listToCompare.get(a).toLowerCase().contains(part2)){
					nsm.setWeight(nsm.getWeight()+1);
				}
				if(listToCompare.get(a).toLowerCase().contains(part3)){
					nsm.setWeight(nsm.getWeight()+1);
				}
				if(nsm.getWeight() > match.getWeight()){
					match = nsm;
				}
			}
		}
		return match.getTheStringThatMatched();
	}
	
	/**
	 * Adds a string to History.
	 * 
	 * @param toAdd		The String to add to history.
	 */
	public void addToHistory(String toAdd)
	{
		history.add(toAdd);
	}
}
