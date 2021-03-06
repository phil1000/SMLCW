package smlTests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sml.OutInstruction;
import sml.Machine;

public class OutInstructionTest {
	
	OutInstruction instructionTest;
	int registerTest = 28;//its one register position, the destination for value
	int valueTest = 96;//its one value, at position registerTest
	String labelTest = "labTest";
	String opcodeTest = "out";

	@Before
	public void setUp() throws Exception {

		instructionTest = new OutInstruction(labelTest,registerTest);
	
	}

	@After
	public void tearDown() throws Exception {
	
		instructionTest = null;
		System.setOut(null);
		
	}

	@Test
	public void testToString() {
		
		String expectedOutput = labelTest + ": " + opcodeTest  + " register " + registerTest;
		String actualOutput = instructionTest.toString();
		assertEquals(expectedOutput,actualOutput);
	
	}

	/**
	 * wrote this testExecute() with help from StackOverFlow 
	 * http://stackoverflow.com/questions/1119385/junit-test-for-system-out-println 
	 */
	@Test
	public void testExecute() {
		
		Machine machineTest = new Machine();
		machineTest.execute();
		machineTest.getRegisters().setRegister(registerTest, valueTest);
		
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		instructionTest.execute(machineTest);
		String actualOutput = outContent.toString();
		String expectedOutput = "Register " + registerTest + " value is " + valueTest + "\n";
		
		assertEquals(expectedOutput,actualOutput);
		
	}

	@Test
	public void testOutInstructionStringString() {
		
		instructionTest = new OutInstruction("labTest2", "bypassing2ndAddConstructor");
		String expectedOutput = "labTest2" + "bypassing2ndAddConstructor";
		String actualOutput = instructionTest.label + instructionTest.opcode;
		assertEquals(expectedOutput,actualOutput);
	
	}

	/**
	 * arguments passed to constructor compared with 
	 * member fields of OutInstruction. 
	 * (Visibilities had to be temporarily changed).
	 */
	@Test
	public void testLinInstructionStringIntIntInt() {
		
		String expectedOutput = labelTest+opcodeTest+registerTest;
		String actualOutput = instructionTest.label+instructionTest.opcode+instructionTest.register;
		assertEquals(expectedOutput,actualOutput);

	}

	/**
	 * already tested in AddInstructionTest
	 */
	@Test
	public void testInstruction() {}

}
