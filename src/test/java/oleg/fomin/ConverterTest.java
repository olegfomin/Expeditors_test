package oleg.fomin;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import java.util.Map;

public class ConverterTest {

	@Test
	public void shouldCountHouseholds() {
		List<InputCSVRecord> inputCSVRecordList = new ArrayList<>();
		inputCSVRecordList.add(InputCSVRecord.parseString("\"Dave\",\"Smith\",\"123 main st.\",\"seattle\",\"wa\",\"43\"")); 
		inputCSVRecordList.add(InputCSVRecord.parseString("\"Alice\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"45\""));
		inputCSVRecordList.add(InputCSVRecord.parseString("\"Bob\",\"Williams\",\"234 2nd Ave.\",\"Tacoma\",\"WA\",\"26\""));
		Converter conveter = new Converter();
		
		Map<Household, Converter.HouseHoldOrig2Count> hhMap = conveter.countHouseHolds(inputCSVRecordList);
		assertEquals(2, hhMap.size());
		
	}
	
	@Test
	public void shouldConvertIntoCSVHouseholdList() {
		List<InputCSVRecord> inputCSVRecordList = new ArrayList<>();
		inputCSVRecordList.add(InputCSVRecord.parseString("\"Dave\",\"Smith\",\"123 main st.\",\"seattle\",\"wa\",\"43\"")); 
		inputCSVRecordList.add(InputCSVRecord.parseString("\"Alice\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"45\""));
		inputCSVRecordList.add(InputCSVRecord.parseString("\"Bob\",\"Williams\",\"234 2nd Ave.\",\"Tacoma\",\"WA\",\"26\""));
		Converter converter = new Converter();
		
		Map<Household, Converter.HouseHoldOrig2Count> hhMap = converter.countHouseHolds(inputCSVRecordList);
		List<String> csvList = converter.convertIntoHHCSVList(hhMap);
		assertTrue(csvList.contains("\"123 main st.\",\"seattle\",\"wa\",\"2\""));
		assertTrue(csvList.contains("\"234 2nd Ave.\",\"Tacoma\",\"WA\",\"1\""));
	}
	
	@Test
	public void shouldConvertIntoPersonListOverAge() {
		List<InputCSVRecord> inputCSVRecordList = new ArrayList<>();
		inputCSVRecordList.add(InputCSVRecord.parseString("\"Dave\",\"Smith\",\"123 main st.\",\"seattle\",\"wa\",\"43\"")); 
		inputCSVRecordList.add(InputCSVRecord.parseString("\"Alice\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"45\""));
		inputCSVRecordList.add(InputCSVRecord.parseString("\"Bob\",\"Williams\",\"234 2nd Ave.\",\"Tacoma\",\"WA\",\"26\""));
		inputCSVRecordList.add(InputCSVRecord.parseString("\"Rob\",\"Klimt\",\"234 2nd Ave.\",\"Tacoma\",\"WA\",\"16\""));
		
		Converter converter = new Converter();
		List<String> peopleAsSCVList = converter.convertIntoPersonListOverAge(inputCSVRecordList);
		assertEquals(3, peopleAsSCVList.size());
		assertEquals("\"Smith\",\"Alice\",\"123 Main St.,Seattle,WA\",\"45\"", peopleAsSCVList.get(0));
		assertEquals("\"Smith\",\"Dave\",\"123 main st.,seattle,wa\",\"43\"", peopleAsSCVList.get(1));
		assertEquals("\"Williams\",\"Bob\",\"234 2nd Ave.,Tacoma,WA\",\"26\"", peopleAsSCVList.get(2));
		
		
		
	}
}
