package oleg.fomin;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class CommandLineArgumentsTest {

	@Test
	public void shouldParseAllInputArguments () {
		String[] args = {"--input_file", "~/File2Load.csv", "--output_file", "./output.csv", "--include_headers"};
		CommandLineArguments cla = CommandLineArguments.parse(args);
		
		assertEquals(cla.areHeadersIncluded(), true);
		assertEquals(cla.getInputFilePath(), "~/File2Load.csv");
		assertEquals(cla.getOutputFilePath(), "./output.csv");
	}
	
	@Test
	public void shouldParseOnlyOneInputArgument () {
		String[] args = {"--output_file", "./theLonely.csv"};
		CommandLineArguments cla = CommandLineArguments.parse(args);
		
		assertEquals(cla.areHeadersIncluded(), false);
		assertEquals(cla.getInputFilePath(), "");
		assertEquals(cla.getOutputFilePath(), "./theLonely.csv");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailMalformedInputString () {
		String[] args = {"--output_file", "--", "--input_file"};
		CommandLineArguments cla = CommandLineArguments.parse(args);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldFailIfUnknownArgument () {
		String[] args = {"--unknown_argument", "someVelue"};
		CommandLineArguments cla = CommandLineArguments.parse(args);
	}
	
    @Test
    public void shouldPrintHelp() {
        PrintStream previousConsole = System.out;
        
        // Set the standard output to use newConsole.
        ByteArrayOutputStream newConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newConsole));
        
		String[] args = {"--help"};
		CommandLineArguments cla = CommandLineArguments.parse(args);
		
		assertTrue(newConsole.toString().startsWith("java"));
 
        // Restore back the standard console output.
        System.setOut(previousConsole);

    }
	

	
}
