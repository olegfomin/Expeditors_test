package oleg.fomin;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/* Prints the CSV file into the file or stdout
public class Writer {

	/**
	 * Writes the content of the list into the file specified if no file specified than the content is being written to the console
	 * @param fileName - file name where the output is being written if it is absent then write to the console 
	 * @param list
	 */
public class Writer {
  public void write(String fileName, List<String> list) {
	    boolean isFileNamePresent = (fileName != null && !fileName.equals(""));
	    BufferedWriter out=null;;
	    if(isFileNamePresent) { // If output file specified
	        try {
	            out = new BufferedWriter(new FileWriter(fileName, true));
                for(String elem : list) {
    	            out.write(elem+"\n");
                }
	            out.close();
             }
	 
	        // Catch block to handle the exceptions
	        catch (IOException e) {
	        	if(out != null) {
	        		try {
						out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	        	}
	        }
	    } else { // If no output file specified then writing to console
           for(String csvRecord : list) {
		     System.out.println(csvRecord); 
		   }
	    }
  }
}
