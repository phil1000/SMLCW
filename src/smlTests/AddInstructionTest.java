package smlTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sml.AddInstruction;
import sml.Instruction;
import static org.mockito.Mockito.*;

public class AddInstructionTest {

//	Instruction instruction;
	AddInstruction instructionTest;
	int op1Test = 28;
	int op2Test = 10;
	int resultTest = 3;
	String labelTest = "labTest";
	String opcodeTest = "add";
	
	@Before
	public void setUp() throws Exception {
	
		instructionTest = new AddInstruction(labelTest,resultTest,op1Test,op2Test);
		
	}

	@After
	public void tearDown() throws Exception {
	
		instructionTest = null;
	
	}

	@Test
	public void testToString() {
		
//		String expectedOutput = instruction.label + ": " + instruction.opcode  + " " + instruction.op1 + " + " + instruction.op2 + " to " + instruction.result;
		String expectedOutput = labelTest + ": " + opcodeTest  + " " + op1Test + " + " + op2Test + " to " + resultTest;
		String actualOutput = instructionTest.toString();
		System.out.println(actualOutput);
		assertEquals(expectedOutput,actualOutput);
	}

	@Test
	public void testExecute() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddInstructionStringString() {

		instructionTest = new AddInstruction("labTest2", "bypassing2ndAddConstructor");
		String expectedOutput = "labTest2" + "bypassing2ndAddConstructor";
		String actualOutput = instructionTest.label + instructionTest.opcode;
		assertEquals(expectedOutput,actualOutput);

	}

	/**
	 * Test that constructor assigns member fields of Instruction and AddInstruction correctly 
	 */
	@Test
	public void testAddInstructionStringIntIntInt() {
		
		String expectedOutput = labelTest+op2Test+opcodeTest+resultTest+op1Test;
		String actualOutput = instructionTest.label+instructionTest.op2+instructionTest.opcode+instructionTest.result+instructionTest.op1;
		assertEquals(expectedOutput,actualOutput);

	}

	@Test
	public void testInstruction() {
		
		String expectedOutput = labelTest + opcodeTest;
		String actualOutput = instructionTest.label + instructionTest.opcode;
		assertEquals(expectedOutput,actualOutput);

	}

}
