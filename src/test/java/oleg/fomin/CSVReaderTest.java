package oleg.fomin;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class CSVReaderTest {

    @Test
    public void shouldReadInnerCSV()
    {
    	CSVReader csv2csv= new CSVReader();
    	List<InputCSVRecord> csvList = csv2csv.readCSVFile("");
    	assertEquals(10, csvList.size());
    }

}
