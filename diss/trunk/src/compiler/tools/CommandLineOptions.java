package compiler.tools;

/**
 * This class is a transparent workhorse allowing the options the user invoked
 * NCC with from the command line to be set and read at will.
 * 
 * Member: fName		The name (possibly including the path) of the source 
 * 						file.
 * Member: oDest		The destination of the GCC output file. If oDest is 
 * 						a.out then it means that the GCC should not be invoked
 * 						with the -o flag so basically we're just outputting to
 * 						the classic a.out.
 * Member: dFlag		The value of the dFlag - If set on the command line
 * 						then the dFlag should be false so no diagnostics 
 * 						will be printed, if not set of the command line 
 * 						then if should be true as is default.
 * Member: forceGcc		Sets whether or not the output from GCC will be
 * 						printed verbatim or not (i.e. without filtering)
 * 						after the NCC and GCC errors are combined.
 * Member: forceNcc		Sets whether or not the output from NCC will be
 * 						printed verbatim or not (i.e. without filtering)
 * 						after the NCC and GCC errors are combined.
 * Member: scopeDetail	Sets whether or not the details of every scope in
 * 						the whole program is printed or not. Helpful for
 * 						understanding variable declarations and scope.  
 * Member: verbose		Forces the separate compilers to print their lists
 * 						of errors verbatim in defined lists. No error 
 * 						combination takes place at any stage. 
 * 
 * @author Richard Hill
 */

public class CommandLineOptions
{
	public String fName, oDest = "a.out";
	public boolean dFlag, forceGcc, forceNcc, scopeDetail, verbose;
	//public String baseLoader;
	public ClassLoader cl;
	
	/**
	 * Constructor for new command line options set. This initialises all of
	 * the boolean command line options with their default values which are:
	 * 					Default Value	Means
	 * dFlag			true
	 * forceGcc			false
	 * forceNcc			false
	 * scopeDetail		false
	 * verbose			false
	 */
	public CommandLineOptions(String fName, String oDest, ClassLoader cl)
	{
		this.fName = fName;
		this.oDest = oDest;
		this.cl = cl;
		dFlag =	true;
		forceGcc = false;
		forceNcc = false;
		scopeDetail = false;
		verbose = false;
	}
}
