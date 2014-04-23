package smlTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sml.Instruction;
import sml.Machine;
import sml.Translator;

public class TranslatorTest {

	Translator translator;
	Machine machine;
	String testFileName;
	String line;
	
	@Before
	public void setUp() throws Exception {
	
		machine = new Machine();
		
	}

	@After
	public void tearDown() throws Exception {
	
		machine = null;
		translator = null;
		testFileName = null;
	
	}

	/**
	 * the constructor
	 * (temporarily changed visibility of 'fileName' from private to public)
	 */
	@Test
	public void testTranslator() {
		
		testFileName = "testFileName.txt";
		translator = new Translator(testFileName);
		String actualOutput = translator.fileName;
		String expectedOutput = "src/testFileName.txt";
		assertEquals(expectedOutput,actualOutput);

	}

	/**
	 * This test is written to confirm readAndTranslate() has correct
	 * functionality with different files. (It seems that readAndTranslate() 
	 * will never return true, always false and that nothing is done with 
	 * this return type in machine.)
	 * 
	 * (Translator's 'ArrayList<Instruction> program' and 'Labels labels' temporarily made public
	 * for this test.)
	 */
	@Test
	public void testReadAndTranslate() {
		
		String testFileName2 = "1lineOfInstructionTest.txt";
		translator = new Translator(testFileName2); 
		assertFalse(translator.readAndTranslate(machine.getLabels(), machine.getProg()));		

		String testFileName3 = "3linesOfInstructionsTest.txt";
		translator = new Translator(testFileName3); 
		assertFalse(translator.readAndTranslate(machine.getLabels(), machine.getProg()));		

		testFileName3 = "3linesOfInstructionsTest.txt";
		translator = new Translator(testFileName3); 
		translator.readAndTranslate(machine.getLabels(), machine.getProg());
		String actualOutput = "";
		for (String labelTest : translator.labels.getLabels()) {
			
			actualOutput += labelTest;
			
		}	
		String expectedOutput = "fof1f2";
		assertEquals(expectedOutput, actualOutput);		

		
		String actualOutput2 = "";
		for (Instruction instructionTest : translator.program) {
			
			actualOutput2 += instructionTest.getLabel();
			
		}
		String expectedOutput2 = "fof1f2";
		assertEquals(expectedOutput2, actualOutput2);		

		String testFileName4 = "instructions.txt";
		translator = new Translator(testFileName4); 
		assertFalse(translator.readAndTranslate(machine.getLabels(), machine.getProg()));		
		
		String testFileName5 = "EmptyFileTest.txt";
		translator = new Translator(testFileName5); 
		assertFalse(translator.readAndTranslate(machine.getLabels(), machine.getProg()));
	
	}

	/**
	 * test for scan() and scanInt() checked to pass before checking
	 * getInstruction(String), as the latter method uses the former methods 
	 * ('String line' in Translator made temporarily public for this test)
	 */
	@Test
	public void testScan() {
		
		String testFileName = "testFileName";
		translator = new Translator(testFileName);
		translator.line = "fo 12 34 9";
		String expectedOutput = "fo";
		String actualOutput = translator.scan();
		assertEquals(expectedOutput,actualOutput);

		String expectedOutput2 = "12";
		String actualOutput2 = translator.scan();
		assertEquals(expectedOutput2,actualOutput2);

		String expectedOutput3 = "34";
		String actualOutput3 = translator.scan();
		assertEquals(expectedOutput3,actualOutput3);

		String expectedOutput4 = "9";
		String actualOutput4 = translator.scan();
		assertEquals(expectedOutput4,actualOutput4);
		
	}

	/**
	 * test for scan() and scanInt() checked to pass before checking
	 * getInstruction(String), as the latter method uses the former methods 
	 * ('String line' in Translator made temporarily public for this test)
	 */
	@Test
	public void testScanInt() {
		
		String testFileName = "testFileName";
		translator = new Translator(testFileName);
		translator.line = "fo lin 12 34 9";
		int expectedOutput = Integer.MAX_VALUE;
		int actualOutput = translator.scanInt();
		assertEquals(expectedOutput,actualOutput);

		int expectedOutput1 = Integer.MAX_VALUE;
		int actualOutput1 = translator.scanInt();
		assertEquals(expectedOutput1,actualOutput1);

		int expectedOutput2 = 12;
		int actualOutput2 = translator.scanInt();
		assertEquals(expectedOutput2,actualOutput2);

		int expectedOutput3 = 34;
		int actualOutput3 = translator.scanInt();
		assertEquals(expectedOutput3,actualOutput3);

		int expectedOutput4 = 9;
		int actualOutput4 = translator.scanInt();
		assertEquals(expectedOutput4,actualOutput4);

	}
	
	/**
	 * scan() is first called in readAndTranslate(Labels,ArrayList<>), returning the label (e.g. "fo"). Scan() 
	 * returns the first word in 'line' but then deletes it from 'line' too. As such, subsequent calls to scan() 
	 * return the next word along, string-by-string. Therefore the second scan() call, which is made in 
	 * getInstruction(String), returns the opcode (e.g. "lin"). This is used to generate 'newString' which
	 * should be "sml.LinInstruction" where scan() returns "lin" for e.g.
	 */
	@Test
	public void testGetInstruction() {
		
		String testFileName = "testFileName";
		translator = new Translator(testFileName);
		translator.line = "lin 34 9";//temporary change of visibility to 'line', private to public
		translator.getInstruction("test");
		String newString = "sml.LinInstruction";
		String expectedOutput = newString;
		String actualOutput = translator.forJUnitTestNewString;	
		assertEquals(expectedOutput, actualOutput);

		String labelTest = "foTest";
		String insTest = "lin";
		int registerTest = 30;
		int valueTest = 9; 
		translator.line = insTest + " " + registerTest + " " + valueTest;//temporary change of visibility to 'line', private to public
		String actualOutput2 = translator.getInstruction(labelTest).toString();
		String ToStringsOfInstructionAndLinInstruction = labelTest + ": " + insTest + " register " + registerTest + " value is " + valueTest;
		String expectedOutput2 = ToStringsOfInstructionAndLinInstruction;
		assertEquals(expectedOutput2, actualOutput2);
		
		labelTest = "foTest";
		insTest = "add";
		registerTest = 30;
		int value1Test = 9;
		int value2Test = 4;
		translator.line = insTest + " " + registerTest + " " + value1Test + " " + value2Test;//temporary change of visibility to 'line', private to public
		String actualOutput3 = translator.getInstruction(labelTest).toString();
		String ToStringsOfInstructionAndAddInstruction = labelTest + ": " + insTest +  " " + value1Test + " + " + value2Test + " to " + registerTest;
		String expectedOutput3 = ToStringsOfInstructionAndAddInstruction;
		assertEquals(expectedOutput3, actualOutput3);

		labelTest = "foTest";
		insTest = "bnz";
		registerTest = 30;
		String label2Test = "f5Test";
		translator.line = insTest + " " + registerTest + " " + label2Test;//temporary change of visibility to 'line', private to public
		String actualOutput4 = translator.getInstruction(labelTest).toString();
		String ToStringsOfInstructionAndBnzInstruction = labelTest + ": " + insTest +  " register " + registerTest + " label = " + label2Test;
		String expectedOutput4 = ToStringsOfInstructionAndBnzInstruction;
		assertEquals(expectedOutput4, actualOutput4);
		
	}

}
