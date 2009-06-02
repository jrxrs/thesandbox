package compiler.tools;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * The Symbol Table class for the NCC Complier.
 * 
 * @author jrxrs
 */

public class NccSymbolTable 
{
	//The actual hash table data structure
	private Hashtable<String, NccToken> table;
	//Information about this symbol table (it's scope)
	private int startLine = -1;
	private int endLine = -1;

	/**
	 * Constructs a new NccSymbolTable.
	 */
	public NccSymbolTable()
	{
		table = new Hashtable<String, NccToken>();
	}
	
	/**
	 * Stores a token in the Symbol Table.
	 * 
	 * @param identifier	The unique identifier to hash on.  
	 * @param t				The Token to store.
	 */
	public void put(String identifier, NccToken t)
	{
		table.put(identifier, t);
	}
	
	/**
	 * Gets a Token from the Symbol Table.
	 * 
	 * @param 	identifier	The unique id for this Token.
	 * @return	t			The Token this identifier maps too.
	 */
	public NccToken get(String identifier)
	{
		if(table.containsKey(identifier)){
			return table.get(identifier);
		}
		else{
			return null;
		}
	}
	
	/**
	 * Searches the hash table data structure to see if a token with the same
	 * name of the identifier passed is present. If it is return true otherwise
	 * return false. 
	 * 
	 * @param  identifier	The identifier to look for.
	 * @return 				A boolean, true if the value is held in the symbol
	 * 						table, false otherwise.
	 */
	public boolean contains(String identifier)
	{
		return table.containsKey(identifier);
	}
	
	/**
	 * Returns the size of the the symbol table (number of entries).
	 */
	public int size()
	{
		return table.size();
	}
	
	/**
	 * Used for printing formatted tables of symbols under the -s flag.
	 * Prints the details of the Symbols in this table.
	 */
	public void printSymbolsDetails()
	{
		Enumeration<NccToken> enu = table.elements();
		NccToken t;
		while(enu.hasMoreElements()){
			t = enu.nextElement();
			String space = " ";
			String ws = "";
			int x = 28;
			while(x - t.getId().length() > 0){
				ws = ws.concat(space);
				x--;
			}
			String ws2 = "";
			int x2 = 13;
			while(x2 - t.getTokenType().length() > 0){
				ws2 = ws2.concat(space);
				x2--;
			}
			if(t.isArray()){
				System.out.println("  " + t.getId() + ws + "TRUE                " + t.getTokenType() + ws2 + t.getDeclLine());
			}
			else{
				System.out.println("  " + t.getId() + ws + "FALSE               " + t.getTokenType() + ws2 + t.getDeclLine());
			}
		}
	}
	
	/**
	 * Gets the line number this scope began on (symbol table initialisation)
	 * 
	 * @return	The line number this scope began on (symbol table initialisation)
	 */
	public int getStartLine()
	{
		return startLine;
	}

	/**
	 * Sets the line number this scope began on (symbol table initialisation)
	 * 
	 * @param startLine		The line number this scope began on (symbol table
	 * 						initialisation)
	 */
	public void setStartLine(int startLine)
	{
		this.startLine = startLine;
	}

	/**
	 * Gets the line number this scope ended on (symbol table storage)
	 * 
	 * @return	The line number this scope ended on (symbol table storage)
	 */
	public int getEndLine()
	{
		return endLine;
	}

	/**
	 * Sets the line number this scope ended on (symbol table storage)
	 * 
	 * @param endLine	The line number this scope ended on (symbol table 
	 * 					storage)
	 */
	public void setEndLine(int endLine)
	{
		this.endLine = endLine;
	}
	
	/**
	 * Gets the complete list of symbols stored in this symbol table.
	 * 
	 * @return	The ArrayList containing the complete list of symbols stored in
	 * 			this symbol table.
	 */
	public ArrayList<String> returnAllSymbols()
	{
		ArrayList<String> retArr = new ArrayList<String>();
		Enumeration<NccToken> enu = table.elements();
		NccToken t;
		while(enu.hasMoreElements()){
			t = enu.nextElement();
			retArr.add(t.getId());
		}
		
		return retArr;
	}

	/**
	 * Used for debugging NOT for printing symbols under the -s flag.
	 * Prints the details of the Symbols in this table.
	 */
	public void printSymbols()
	{
		Enumeration<NccToken> enu = table.elements();
		NccToken t;
		System.out.println("Printing new Symbol Table (Starts Line: " + startLine + ", Ends Line: " + endLine + "):");
		while(enu.hasMoreElements()){
			t = enu.nextElement();
			if(t.isArray()){
				System.out.println("Token: " + t.getId() + " is an array or pointer of type: " + t.getTokenType() + " and size: " + t.getArraySize() + " declared on line: " + t.getDeclLine());
			}
			else{
				System.out.println("Token: " + t.getId() + " of type: " + t.getTokenType() + " declared on line: " + t.getDeclLine() + " with value: " + t.getValue());
			}
		}
	}
}
