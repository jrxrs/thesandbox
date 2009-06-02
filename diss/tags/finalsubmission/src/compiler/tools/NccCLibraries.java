package compiler.tools;

import java.util.HashSet;

/**
 * The Standard C Library class.
 * 
 * @author Richard Hill
 */

public class NccCLibraries
{
	private HashSet<String> nccImplementedLibraries;
	
	/**
	 * The constructor for the C Library class.
	 */
	public NccCLibraries()
	{
		nccImplementedLibraries = new HashSet<String>();
		nccImplementedLibraries.add("assert.h");
		nccImplementedLibraries.add("complex.h");
		nccImplementedLibraries.add("ctype.h");
		nccImplementedLibraries.add("errno.h");
		nccImplementedLibraries.add("fenv.h");
		nccImplementedLibraries.add("float.h");
		nccImplementedLibraries.add("inttypes.h");
		nccImplementedLibraries.add("iso646.h");
		nccImplementedLibraries.add("limits.h");
		nccImplementedLibraries.add("locale.h");
		nccImplementedLibraries.add("malloc.h");
		nccImplementedLibraries.add("math.h");
		nccImplementedLibraries.add("setjmp.h");
		nccImplementedLibraries.add("signal.h");
		nccImplementedLibraries.add("stdarg.h");
		nccImplementedLibraries.add("stdbool.h");
		nccImplementedLibraries.add("stddef.h");
		nccImplementedLibraries.add("stdint.h");
		nccImplementedLibraries.add("stdio.h");
		nccImplementedLibraries.add("stdlib.h");
		nccImplementedLibraries.add("string.h");
		nccImplementedLibraries.add("tgmath.h");
		nccImplementedLibraries.add("time.h");
		nccImplementedLibraries.add("wchar.h");
		nccImplementedLibraries.add("wctype.h");
	}
	
	/**
	 * Returns the array list 
	 * 
	 * @param lib	The name of the required library.
	 */
	public void implementLibrary(String lib)
	{
		if(nccImplementedLibraries.contains(lib)){
			//...
		}
		else{
			System.out.println("Illegal library inclusion! NCC does not implement the " + lib + " library.");
			System.out.println("For help type: java ncc --help");
			System.exit(1);
		}
	}
}
