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
	AddInstruction instruction;
	
	@Before
	public void setUp() throws Exception {
	
		instruction = new AddInstruction("test",1,2,3);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToString() {
		
		String expectedOutput = instruction.label + ": " + instruction.opcode  + " " + instruction.op1 + " + " + instruction.op2 + " to " + instruction.result;
		String actualOutput = instruction.toString();
		assertEquals(expectedOutput,actualOutput);
	}

	@Test
	public void testExecute() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddInstructionStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddInstructionStringIntIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testInstruction() {
		
		;
	}

}
