package smlTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sml.BnzInstruction;
import sml.Machine;

public class BnzInstructionTest {

	BnzInstruction instructionTest;
	int registerTest = 28;//its one register position, the destination for value
	int valueTest = 96;//its one value, at position registerTest, which is not 0, therefore should branch.
	int value0Test = 0; // alternative value 0, therefore should not branch.
	String labelTest = "labTest";
	String label2Test = "lab2Test";//the instruction line to which the program should branch to
	String opcodeTest = "bnz";

	@Before
	public void setUp() throws Exception {

		instructionTest = new BnzInstruction(labelTest,registerTest,label2Test);
	
	}

	@After
	public void tearDown() throws Exception {
	
		instructionTest = null;
		
	}

	@Test
	public void testToString() {
		
		String expectedOutput = labelTest + ": " + opcodeTest  + " register " + registerTest + " label = " + label2Test;
		String actualOutput = instructionTest.toString();
		assertEquals(expectedOutput,actualOutput);
		
	}

	@Test
	public void testExecute() {

		Machine machineTest = new Machine();
		machineTest.execute();
		machineTest.getRegisters().setRegister(registerTest, valueTest);
		machineTest.getLabels().addLabel(label2Test);//label is added to otherwise empty Labels 
		int randomPcValue = 4;
		machineTest.setPc(randomPcValue);
		instructionTest.execute(machineTest);
		int expectedOutput = 0;//pc at which the label2Test is explicitly added above 
		int actualOutput = machineTest.getPc();
		assertEquals(expectedOutput,actualOutput);
		
		randomPcValue = 4;
		machineTest.setPc(randomPcValue);
		machineTest.getRegisters().setRegister(registerTest, value0Test);//now the value is 0, so should not branch
		instructionTest.execute(machineTest);
		int expectedOutput2 = 4;
		int actualOutput2 = machineTest.getPc();
		assertEquals(expectedOutput2,actualOutput2);

	}

	@Test
	public void testBnzInstructionStringString() {
		
		instructionTest = new BnzInstruction("labTest2", "bypassing2ndAddConstructor");
		String expectedOutput = "labTest2" + "bypassing2ndAddConstructor";
		String actualOutput = instructionTest.label + instructionTest.opcode;
		assertEquals(expectedOutput,actualOutput);
	
	}

	/**
	 * arguments passed to constructor compared with 
	 * member fields of BnzInstruction. 
	 * (Visibilities had to be temporarily changed).
	 */
	@Test
	public void testLinInstructionStringIntIntInt() {
		
		String expectedOutput = labelTest+opcodeTest+registerTest;
		String actualOutput = instructionTest.label+instructionTest.opcode+instructionTest.register;
		assertEquals(expectedOutput,actualOutput);

	}

}
