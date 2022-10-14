package oleg.fomin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Reads a specified file and tries to assemble the CSV record */
public class CSVReader {
	private static final String COMMENT_LINE_INDICATOR = "#"; // if line starts with this character it means it is a comment 


	private File getInnerFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return new File(classLoader.getResource("test.txt").getFile()); 
	}
	
	private BufferedReader getBufferedReader(String fileName) throws IOException {
		BufferedReader reader = null;
		if(fileName==null || fileName.trim().equals("")) {
			InputStream in = getClass().getResourceAsStream("/test.txt");
		    reader = new BufferedReader(new InputStreamReader(in));
		} else {
			InputStream inputStream = new FileInputStream(fileName);
		    reader = new BufferedReader(new InputStreamReader(inputStream));
		}
		return reader;
	}
	
	/** Reads the CSV file provided as an argument if empty string or null provided then the information read from 
	 * inside jar file itself
	 * @param file2Read - CSV file to be read (null or empty are Ok)
	 * @return
	 */
	public List<InputCSVRecord> readCSVFile(String file2Read) {
		BufferedReader reader = null;
		List<InputCSVRecord> csvRecordList = new ArrayList<>();
		try {
		    try {
			    reader = getBufferedReader(file2Read);
			    while(reader.ready()) {
			        String line = reader.readLine();
			        if(!line.startsWith(COMMENT_LINE_INDICATOR)) {
				        InputCSVRecord record = InputCSVRecord.parseString(line);
				        csvRecordList.add(record);
			        }    
		        }
	 			
			} catch (IOException e) {
				throw new IllegalArgumentException("The file '"+file2Read+"' cannot be read", e);
			}
		    
		}     
		finally {
		    if(reader != null) {
		        try {
		        	reader.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
		return csvRecordList;
	}
	
	

}
