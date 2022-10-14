package oleg.fomin;

import static org.junit.Assert.*;

import org.junit.Test;

public class InputCSVRecordTest {

	@Test
	public void shouldParseGoodCSVString() {
		String input = "\"George\",\"Brown\",\"345 3rd Blvd., Apt. 200\",\"Seattle\",\"WA\",\"18\"";
		InputCSVRecord icr = InputCSVRecord.parseString(input);
		assertEquals("George", icr.getFirstName());
		assertEquals("Brown", icr.getLastName());
		assertEquals("345 3rd Blvd., Apt. 200", icr.getStreetAddress());
		assertEquals("Seattle", icr.getCity());
		assertEquals("WA", icr.getState());
		assertEquals("18", icr.getAge());
	}

	@Test(expected = IllegalStateException.class)
	public void shouldFailParseTooManyFields() {
		String input = "\"George\",\"Brown\",\"345 3rd Blvd., Apt. 200\",\"Seattle\",\"WA\",\"18\",\"Some more\"";
		InputCSVRecord icr = InputCSVRecord.parseString(input);
		assertEquals("George", icr.getFirstName());
		assertEquals("Brown", icr.getLastName());
		assertEquals("345 3rd Blvd., Apt. 200", icr.getStreetAddress());
		assertEquals("Seattle", icr.getCity());
		assertEquals("WA", icr.getState());
		assertEquals("18", icr.getAge());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailNotEnoughFields() {
	  String input = "\"Frank\",\"Jones\",\"234 2nd Ave.\"";
      InputCSVRecord.parseString(input);
	}  
}
