package smlTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sml.AddInstruction;
import sml.Machine;
//import static org.mockito.Mockito.*;

public class AddInstructionTest {

	AddInstruction instructionTest;
	int op1Test = 28;   // the register location of one int value to be added. The value is 96 (see execute(Machine))
	int op2Test = 10;   // the register location of the other int value to be added.The value is 69 (see execute(Machine))
	int resultTest = 3; // the register location for the sum of the two int values. The value is 165.
	String labelTest = "labTest";
	String opcodeTest = "add";
	int op1TestValue = 96;
	int op2TestValue = 69;
	
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
		
		String expectedOutput = labelTest + ": " + opcodeTest  + " " + op1Test + " + " + op2Test + " to " + resultTest;
		String actualOutput = instructionTest.toString();
		assertEquals(expectedOutput,actualOutput);
		
	}

	/**
	 * I wasn't confident enough with Mocking to use it here.
	 * Thus, the test relies on correct construction of a Machine object as well as 
	 * a correct execute() method in Machine. These, as well as Registers getters and setters
	 * have already been tested and shown to pass. Therefore I am happy to use them to test 
	 * Instruction.  
	 * 
	 * The function of AddInstruction's execute(Machine) method is to get the two values 
	 * from the two specified register positions, calculate their sum and then assign the sum 
	 * to the specified register position.
	 * So, I set up the machine's registers and put the two values at known positions. 
	 * 
	 * Next I run AddInstruction's execute(Machine) method and check for the correct sum
	 * at the correct position in the register. 
	 * 
	 */
	@Test
	public void testExecute() {

		Machine machineTest = new Machine();
		machineTest.execute();//sets up empty register array
		machineTest.getRegisters().setRegister(op1Test,op1TestValue);//sets the two values at the positions
		machineTest.getRegisters().setRegister(op2Test,op2TestValue);//sets the two values at the positions
		//AddInstruction has already assigned the 3 register positions to its member fields in the constructor call 
		//in @Before setUp() above. So it knows where to look for the values and where to put the sum.
		// now I just run the method
		instructionTest.execute(machineTest);
		//and look for the correct result in the correct position
		int actualOutput = machineTest.getRegisters().getRegister(resultTest);
		int expectedOutput = op1TestValue+op2TestValue;
		
		assertEquals(expectedOutput,actualOutput);
	
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

//	@Test(expected = ...Exception.class)

}
