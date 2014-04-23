package smlTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sml.AddInstruction;
import sml.DivInstruction;
import sml.Machine;
import static org.mockito.Mockito.*;

/**
 * tests for DivInstructionTest were virtually copied and pasted from my AddInstructionTest
 * 
 * @author Shahin
 *
 */
public class DivInstructionTest {
	
	DivInstruction instructionTest;
	int op1Test = 28; //register position of operand 1 
	int op2Test = 10; //register position of operand 2
	int op1TestValue = 20; // value of operand 1
	int op2TestValue = 5; // value of operand 2
	int resultTest = 3; //register position of result
	String labelTest = "labTest";
	String opcodeTest = "div";


	@Before
	public void setUp() throws Exception {
	
		instructionTest = new DivInstruction(labelTest,resultTest,op1Test,op2Test);

	}

	@After
	public void tearDown() throws Exception {
	
		instructionTest = null;
		
	}

	@Test
	public void testToString() {

		String expectedOutput = labelTest + ": " + opcodeTest  + " " + op1Test + " / " + op2Test + " to " + resultTest;
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
		int expectedOutput = op1TestValue/op2TestValue;
		
		assertEquals(expectedOutput,actualOutput);
		
	}

	@Test
	public void testDivInstructionStringString() {

		instructionTest = new DivInstruction("labTest2", "bypassing2ndAddConstructor");
		String expectedOutput = "labTest2" + "bypassing2ndAddConstructor";
		String actualOutput = instructionTest.label + instructionTest.opcode;
		assertEquals(expectedOutput,actualOutput);
		
	}

	@Test
	public void testDivInstructionStringIntIntInt() {

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
