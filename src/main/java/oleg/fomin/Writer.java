package oleg.fomin;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/* Prints the CSV file into the file of stdout if the filename is absent */
public class Writer {

	/**
	 * Writes the content of the list into the file specified if no file specified than the content is being written to the console
	 * @param fileName - file name where the output is being written if it is absent then write to the console 
	 * @param list
	 */
  public void write(String fileName, List<String> list) {
	    boolean isFileNamePresent = (fileName != null && !fileName.equals(""));
	    
	    if(isFileNamePresent) { // If output file specified
		    FileOutputStream outputStream=null;
			try {
		
				try {
					outputStream = new FileOutputStream(fileName);
				} catch (FileNotFoundException e1) {
					throw new IllegalStateException("Cannot open file '"+fileName+"'",e1);
				}
			    for(String elem : list) {
			    	byte[] strToBytes = elem.getBytes();	
				    try {
						outputStream.write(strToBytes);
					} catch (IOException e) {
						throw new IllegalStateException("Cannot write into '"+fileName+"'",e);
					}
			    }
			    
			} finally {
				if(outputStream != null) {
			      try {
					outputStream.close();
	    		  } catch (IOException e) {
					e.printStackTrace();
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
