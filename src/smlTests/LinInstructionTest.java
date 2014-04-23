package smlTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sml.LinInstruction;
import sml.Machine;

public class LinInstructionTest {
	
	LinInstruction instructionTest;
	int registerTest = 28;//its one register position, the destination for value
	int valueTest = 96;//its one value to be set to position registerTest
	String labelTest = "labTest";
	String opcodeTest = "lin";

	@Before
	public void setUp() throws Exception {

		instructionTest = new LinInstruction(labelTest,registerTest,valueTest);
	
	}

	@After
	public void tearDown() throws Exception {
	
		instructionTest = null;
		
	}

	@Test
	public void testToString() {
		
		String expectedOutput = labelTest + ": " + opcodeTest  + " register " + registerTest + " value is " + valueTest;
		String actualOutput = instructionTest.toString();
		assertEquals(expectedOutput,actualOutput);
	
	}

	@Test
	public void testExecute() {

		Machine machineTest = new Machine();
		machineTest.execute();
		instructionTest.execute(machineTest);
		
		int actualOutput = machineTest.getRegisters().getRegister(registerTest);
		int expectedOutput = valueTest;
		
		assertEquals(expectedOutput,actualOutput);
		
	}

	@Test
	public void testLinInstructionStringString() {
		
		instructionTest = new LinInstruction("labTest2", "bypassing2ndAddConstructor");
		String expectedOutput = "labTest2" + "bypassing2ndAddConstructor";
		String actualOutput = instructionTest.label + instructionTest.opcode;
		assertEquals(expectedOutput,actualOutput);
	
	}

	/**
	 * the arguments passed to the constructor compared directly with 
	 * the member fields of the LinInstruction object. 
	 * (Concatenated in random order for no particular reason). 
	 * (Visibilities had to be temporarily changed).
	 * 
	 */
	@Test
	public void testLinInstructionStringIntIntInt() {
		
		String expectedOutput = labelTest+valueTest+opcodeTest+registerTest;
		String actualOutput = instructionTest.label+instructionTest.value+instructionTest.opcode+instructionTest.register;
		assertEquals(expectedOutput,actualOutput);

	}
	
	/**
	 * already tested in AddInstructionTest
	 */
	@Test
	public void testInstruction() {}

}
