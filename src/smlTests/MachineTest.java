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
	private Translator translator; 

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
		instruction = null;
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
		translator = new Translator("instructionsTestAdd.txt");
		translator.readAndTranslate(machine.getLabels(), machine.getProg());
		machine.execute();
		int actualOutput = instruction.result;
		int expectedOutput = (88+43);
		assertEquals(expectedOutput,actualOutput);
	}

	@Test
	public void testGetLabels() {
		translator = new Translator("instructionsTests1.txt");
		translator.readAndTranslate(machine.getLabels(), machine.getProg());
		machine.execute();
		String actualOutput = "";
		for (int i=0;i<machine.labels.labels.size();i++) {
			actualOutput += machine.labels.labels.get(i);
		}
		String expectedOutput = "fof1f2f3f4f5f6f7f8f9f10f11f12";
		assertEquals(expectedOutput,actualOutput);
	}

	@Test
	public void testGetProg() {
		machine.getProg().add(instruction);
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
