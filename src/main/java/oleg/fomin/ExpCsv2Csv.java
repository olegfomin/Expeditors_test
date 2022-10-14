package oleg.fomin;

import java.util.List;
import java.util.Map;

/**
 * This class contains a main method that is responsible for getting the params from command line call that supports the arguments below. 
 * The arguments must be followed with the double minus sign like that "--".
 * --input_file - it must be followed by space or spaces and then it should contain 
      the path to the input CSV file where the input values will be read from (default value is "<jarfileRoot>/test.csv" filled with the original task data given to me on Oct-12-2022). 
 *    The software purposefully skips all the strings which have '#' at the very beginning so that some explanations can be put. The
 *    header of the file will look like: #"FirstName","LastName","Street Address","City","State","Age". 
 *    This header will not participate in the calculations so if you do not like it go ahead and delete it 
 * --output_file - it must be followed by space or spaces and then it should contain 
 *                 the path to the output file where the resulting .CSV file will be put. By default it will print everything into stdout (terminal)
 *                                                         
 *   This file written by Oleg Fomin (oleg.fomin@16x32.com) as a part of an assessment exercise for the "Expeditors". 
 *   Anyone is allowed using this file as they wish.
 *   If you want to find out more about author please visit 
 *     * http://www.roboticseattle.com/http://www.roboticseattle.com/main.html#about-acc-my-view
 *     * https://github.com/olegfomin/roboticseattle
 *     * http://www.16x32.com                     
 */

public class ExpCsv2Csv {
	private CSVReader csvReader = new CSVReader();
	private Converter converter = new Converter();
	private Writer    writer    = new Writer();    
	
	private CommandLineArguments parseCommandLineArgs(String[] commandLineString) {
		return CommandLineArguments.parse(commandLineString);
	}
	
	
    public static void main( String[] args )
    {
    	ExpCsv2Csv expCsv2Csv = new ExpCsv2Csv();
    	CommandLineArguments commandLineArgs = expCsv2Csv.parseCommandLineArgs(args);
    	if(commandLineArgs.wasHelpUsed()) System.exit(0);
    	List<InputCSVRecord> csvRecordList = expCsv2Csv.csvReader.readCSVFile(commandLineArgs.getInputFilePath());
    	Map<Household, Converter.HouseHoldOrig2Count> household2TupleMap = expCsv2Csv.converter.countHouseHolds(csvRecordList);
    	List<String> householdAndCountCsvList =  expCsv2Csv.converter.convertIntoHHCSVList(household2TupleMap);
    	expCsv2Csv.writer.write(commandLineArgs.getOutputFilePath(), householdAndCountCsvList); // Here the first part of the task is accomplished
        List<String> personCsvList = expCsv2Csv.converter.convertIntoPersonListOverAge(csvRecordList);
        expCsv2Csv.writer.write(commandLineArgs.getOutputFilePath(),personCsvList); 
    }
}
