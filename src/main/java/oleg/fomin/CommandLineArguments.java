package oleg.fomin;

/* This call parses the arguments that are coming from operating system main(String[] args) 
 * There are five of them
 * --input_file - it must be followed by space or spaces and then it should contain 
      the path to the input CSV file where the input values will be read from (default value is "<jarfileRoot>/test.csv" filled with the original task data given to me on Oct-12-2022). 
 *    The software purposefully skips all the strings which have '#' at the very beginning so that some explanations can be put. The
 *    header of the file will look like: #"FirstName","LastName","Street Address","City","State","Age". 
 *    This header will not participate in the calculations so if you do not like it go ahead and delete it 
 * --output_file - it must be followed by space or spaces and then it should contain 
 *                 the path to the output file where the resulting .CSV file will be put. By default it will print everything into stdout (terminal)
 * --include_headers - this is a unary argument that disregards any parameters supplied but if it is present then the output file will contain a comment lines that
 *                      starts with #. The first line will contain: #"Household address", "number of occupants" and the when this section 
 *                      is filled with data then another header will appear that looks as follows: 
 *                      #"First Name", "Last Name", "Address", "Age" and then the appropriate csv records will follow.
 *                      By default if this flag is NOT present the headers will NOT be included.
 *  --help - prints short memo how the application supposed to be used
 *  --usage - prints short memo how the application supposed to be used                    
*/
public class CommandLineArguments {
	private String inputFilePath="";
	private String outputFilePath="";
	private boolean areHeadersIncluded=false;
	
	public static CommandLineArguments parse(String[] commandLineArgs) {
		CommandLineArguments output = new CommandLineArguments();
		for(int i=0; i < commandLineArgs.length; i++) {
			String clInput = commandLineArgs[i];
			switch(clInput) {
			  case "--input_file": 
				  if(i > commandLineArgs.length-2 || commandLineArgs[i+1].startsWith("--")) {
					  throw new IllegalArgumentException("The argument '"+clInput+"' is not supplied with any data");
				  };
				  output.inputFilePath = commandLineArgs[i+1];
				  break;
			  case "--output_file":
				  if(i > commandLineArgs.length-2 || commandLineArgs[i+1].startsWith("--")) {
					  throw new IllegalArgumentException("The argument '"+clInput+"' is not supplied with any data");
				  };
				  output.outputFilePath = commandLineArgs[i+1];
				  break;
			  case "--include_headers": output.areHeadersIncluded=true; break; 
			  case "--help": 
			  case "--usage": printUsage(); break;	  
			  default: if(clInput.trim().startsWith("--")) throw new IllegalArgumentException("The argument '"+clInput+"' is unknown");	  
			}
		}
		return output;
	}
	
	protected static void printUsage() {
		System.out.println("java -jar ./Expeditors_test-1.0-SNAPSHOT.jar --input_file <path_to_input_csv_file> --output_file <path_to_output_csv_file> --include_headers \n"+
	                       "All the parameters are optional and in this case the default values will take place of the argument missing" );
	}

	public String getInputFilePath() {
		return inputFilePath;
	}

	public String getOutputFilePath() {
		return outputFilePath;
	}

	public boolean areHeadersIncluded() {
		return areHeadersIncluded;
	}
  	
}
