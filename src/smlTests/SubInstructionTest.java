package smlTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sml.Machine;
import sml.MulInstruction;
import sml.SubInstruction;

public class SubInstructionTest {
	
	SubInstruction instructionTest;
	int op1Test = 28;
	int op2Test = 10;
	int resultTest = 3; 
	String labelTest = "labTest";
	String opcodeTest = "sub";
	int op1TestValue = 96;
	int op2TestValue = 69;
	
	@Before
	public void setUp() throws Exception {

		instructionTest = new SubInstruction(labelTest,resultTest,op1Test,op2Test);
	
	}

	@After
	public void tearDown() throws Exception {
	
		instructionTest = null;
		
	}

	@Test
	public void testToString() {
		
		String expectedOutput = labelTest + ": " + opcodeTest  + " " + op1Test + " - " + op2Test + " to " + resultTest;
		String actualOutput = instructionTest.toString();
		assertEquals(expectedOutput,actualOutput);
	
	}

	@Test
	public void testExecute() {

		Machine machineTest = new Machine();
		machineTest.execute();
		machineTest.getRegisters().setRegister(op1Test,op1TestValue);
		machineTest.getRegisters().setRegister(op2Test,op2TestValue);

		instructionTest.execute(machineTest);
		
		int actualOutput = machineTest.getRegisters().getRegister(resultTest);
		int expectedOutput = op1TestValue-op2TestValue;
		
		assertEquals(expectedOutput,actualOutput);
		
	}

	@Test
	public void testSubInstructionStringString() {
		
		instructionTest = new SubInstruction("labTest2", "bypassing2ndAddConstructor");
		String expectedOutput = "labTest2" + "bypassing2ndAddConstructor";
		String actualOutput = instructionTest.label + instructionTest.opcode;
		assertEquals(expectedOutput,actualOutput);
	
	}

	@Test
	public void testSubInstructionStringIntIntInt() {
		
		String expectedOutput = labelTest+op2Test+opcodeTest+resultTest+op1Test;
		String actualOutput = instructionTest.label+instructionTest.op2+instructionTest.opcode+instructionTest.result+instructionTest.op1;
		assertEquals(expectedOutput,actualOutput);

	}
	
	/**
	 * already tested in AddInstructionTest
	 */
	@Test
	public void testInstruction() {}

}
