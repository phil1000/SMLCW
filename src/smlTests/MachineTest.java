package smlTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sml.*;

/**
 * 
 * @author Shahin/Phil
 *
 * Note: the member fields of Machine need to be made temporarily 
 * accessible from this test class by changing 'private' to 'public'. 
 * They are 'Labels labels', 'ArrayList<Instruction> prog', 
 * 'Registers registers', 'int pc'. 
 * 
 */
public class MachineTest {

	private Machine machine;
	private 	AddInstruction instruction;

	@Before
	public void setUp() throws Exception {
		machine = new Machine();
		String label = "label for test";
		String op = "op for test";
		instruction = new AddInstruction(label, op);
		
	}

	@After
	public void tearDown() throws Exception {
		machine = null;
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}


	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecute() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLabels() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProg() {
		machine.prog.add(instruction);
		Instruction actualOutput = machine.getProg().get(0);
		Instruction expectedOutput = instruction; 
		assertEquals(expectedOutput,actualOutput);
	}

	@Test
	public void testGetRegisters() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPc() {
		machine.setPc(10);
		int expectedOutput = 10;
		int actualOutput = machine.getPc(); 
		assertEquals(expectedOutput,actualOutput);
	}

	@Test
	public void testSetLabels() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetProg() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetRegisters() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPc() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testCanEqual() {
		fail("Not yet implemented");
	}

	@Test
	public void testMachine() {
		fail("Not yet implemented");
	}

}
