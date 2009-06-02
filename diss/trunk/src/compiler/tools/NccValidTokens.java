package compiler.tools;

import java.util.HashSet;

/**
 * This static class contains a list of all of the strings and characters that
 * are valid in Standard C as parsed by the Novice C Compiler. 
 * 
 * @author Richard Hill
 */

public class NccValidTokens
{
	//Quick access to the list using a HashSet
	private HashSet<String> hs;
	
	/**
	 * Constructor. Basically adds all the acceptable string to the HasSet.
	 * and then add the ArrayList to the  
	 */
	public NccValidTokens()
	{
		hs = new HashSet<String>();
		init();
	}

	/**
	 * The isValid method determines whether or not a String is valid in the
	 * C Programming Language or not.
	 * 
	 * @param v		The String to check for validity.
	 * @return		A boolean - true if the string is valid and false otherwise.
	 */
	public boolean isValid(String v)
	{
		if(hs.contains(v)){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Initialise the HashSet with valid token. 
	 */
	private void init()
	{
		hs.add(";");hs.add(",");hs.add("=");hs.add("{");hs.add("}");hs.add("'");
		hs.add(":");hs.add("(");hs.add(")");hs.add("[");hs.add("]");hs.add("*");
		hs.add("+");hs.add("-");hs.add("/");hs.add("%");hs.add(".");hs.add("&");
		hs.add("~");hs.add("!");hs.add("<");hs.add(">");hs.add("^");hs.add("|");
		hs.add("?");hs.add("1");hs.add("2");hs.add("3");hs.add("4");hs.add("5");
		hs.add("6");hs.add("7");hs.add("8");hs.add("9");hs.add("0");hs.add("a");
		hs.add("b");hs.add("c");hs.add("d");hs.add("e");hs.add("f");hs.add("g");
		hs.add("h");hs.add("i");hs.add("j");hs.add("k");hs.add("l");hs.add("m");
		hs.add("n");hs.add("o");hs.add("p");hs.add("q");hs.add("r");hs.add("s");
		hs.add("t");hs.add("u");hs.add("v");hs.add("w");hs.add("x");hs.add("y");
		hs.add("z");hs.add("A");hs.add("B");hs.add("C");hs.add("D");hs.add("E");
		hs.add("F");hs.add("G");hs.add("H");hs.add("I");hs.add("J");hs.add("K");
		hs.add("L");hs.add("M");hs.add("N");hs.add("O");hs.add("P");hs.add("Q");
		hs.add("R");hs.add("S");hs.add("T");hs.add("U");hs.add("V");hs.add("W");
		hs.add("X");hs.add("Y");hs.add("Z");hs.add("_");hs.add("#");hs.add("\\");
		hs.add("\"");
	}
}
