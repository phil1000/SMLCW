package smlTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sml.Machine;
import sml.Registers;
import sml.Translator;

public class RegistersTest {

	private Machine machine;
	private Translator translator; 
	Registers registers;


	@Before
	public void setUp() throws Exception {
		machine = new Machine();
		registers = new Registers();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test based on how I wrote the same method in MachineTest.
	 */
	@Test
	public void testHashCode() {
		translator = new Translator("instructionsTestAdd.txt");
		translator.readAndTranslate(machine.getLabels(), machine.getProg());
		machine.execute();
		registers.setRegisters(machine.getRegisters().registers);

		Registers registers2 = new Registers();
		assertFalse(registers.equals(registers2) && registers2.equals(registers));
		assertFalse(registers.hashCode() == registers2.hashCode());
		
		registers = registers2;
		assertTrue(registers.equals(registers2) && registers2.equals(registers));
		assertTrue(registers.hashCode() == registers2.hashCode());
		
	}

	@Test
	public void testRegisters() {
		fail("Not yet implemented");
	}

	/**
	 * Test for setRegister(int i,int v), (different from Lombok's setRegisters()). 
	 */
	@Test
	public void testSetRegister() {
		registers.setRegister(1,45);
		int expectedOutput = 45;
		int actualOutput = registers.getRegister(1);
		
		assertEquals(expectedOutput,actualOutput);

	}

	/**
	 * Test for getRegister(int i) (different from Lombok's getRegisters()). 
	 */
	@Test
	public void testGetRegister() {
		translator = new Translator("instructionsTestAdd.txt");
		translator.readAndTranslate(machine.getLabels(), machine.getProg());
		machine.execute();
		registers.setRegisters(machine.getRegisters().registers);
		int actualOutput = registers.getRegister(0);
		int expectedOutput = 88;
		assertEquals(expectedOutput,actualOutput);
	}

	/**
	 * Test for Lombok's getRegisters() (different from getRegister(int i)). 
	 * Effectively also tests setRegisters().
	 */
	@Test
	public void testGetRegisters() {
		translator = new Translator("instructionsTestAdd.txt");
		translator.readAndTranslate(machine.getLabels(), machine.getProg());
		machine.execute();
		registers.setRegisters(machine.getRegisters().registers);
		int[] result = registers.getRegisters();
		String actualOutput = "";
		for (int i = 0; i<result.length; i++) {
			actualOutput += result[i];
		}		
		String expectedOutput = "8843131";
// 3 registers are used up by 88, 43, and 131, so there should be  
// 29 registers left, that were initialised to contain 0.
		for (int i = 1; i < 30 ;i++) {
			expectedOutput += "0";
		}
		assertEquals(expectedOutput,actualOutput);
	}

	@Test
	public void testSetRegisters() {
		int[] regTestArray = {6,7,8};
		registers.setRegisters(regTestArray);
		int[] actualArray = registers.getRegisters();
		int actualOutput = 0;
		for (int i=0;i<actualArray.length;i++) {
			actualOutput += actualArray[i];
		}			
		int expectedOutput = 21;
		assertEquals(expectedOutput,actualOutput);
	}

	@Test
	public void testEqualsObject() {
		Registers registers2 = new Registers()  ;
		
	}

	@Test
	public void testCanEqual() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
