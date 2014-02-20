package smlTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
//	private 	AddInstruction instruction;
	private Translator translator; 

	@Before
	public void setUp() throws Exception {
		machine = new Machine();		
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
		translator = new Translator("instructionsTestAdd.txt");
		translator.readAndTranslate(machine.getLabels(), machine.getProg());
		machine.execute();
		System.out.println(machine.getRegisters() + ".");
		int actualOutput = machine.getRegisters().registers[2];
		int expectedOutput = (88+43);
		assertEquals(expectedOutput,actualOutput);
	}

	/**
	 * Testing getLabels() by concatenating the labels that getLAbels()
	 * returns from the input file 'instructionsTestAdd.txt' and 
	 * comparing with the string that this should create. 
	 */
	@Test
	public void testGetLabels() {
		translator = new Translator("instructionsTestAdd.txt");
		translator.readAndTranslate(machine.getLabels(), machine.getProg());
		String actualOutput = "";
		for (int i=0;i<machine.getLabels().labels.size();i++) {
			actualOutput += machine.labels.labels.get(i);
		}
		String expectedOutput = "fof1f2f3";
		assertEquals(expectedOutput,actualOutput);
	}

	/**
	 * The test creates an 'AddInstruction' object (which some random arguments
	 * for the constructor) and directly adds it to the 'prog' arrayList, 
	 * which holds instruction objects. The method getProg() should return 
	 * the arrayList of instructions currently held in prog. 
	 * To test this, the instruction which has just been added (and should 
	 * therefore be stored at position 0) is compared with what the method 
	 * getProg() returns.   
	 */
	@Test
	public void testGetProg() {
		Instruction instruction = new AddInstruction("string",1,2,3);
		machine.getProg().add(instruction);
		Instruction actualOutput = machine.getProg().get(0);
		Instruction expectedOutput = instruction; 
		assertEquals(expectedOutput,actualOutput);
	}

	/**
	 * Testing getRegisters() in two ways: First, the creation of registers is performed by Machine's execute()
	 * method which in the absence of input from an instructions file, initialises the register int array in 
	 * the Registers class to 32 0s. So, I first iterate through the array that getRegister() should return  
	 * if it works, and I prove this by testing that there are indeed 32 0s in the array. The second part of the 
	 * test is designed to show that getRegisters() works when a non-empty instructions file has been input as 
	 * well. The two tests are not really testing different things, but it's just reassuring to see both work! 
	 */
	@Test
	public void testGetRegisters() {
		System.out.println("(before input of instructions file, testGetRegisters() gives: "+machine.getRegisters()+")");	

//		translator = new Translator("emptyFileTest.txt"); //same thing happens if this file is input
//		translator = new Translator("OneLabelOnlyFileTest.txt");//same thing happens if this file is input
		machine.execute();
		int[] result = machine.getRegisters().registers;
		int noOfRegisters = 0;
		for (int i=0;i<result.length;i++) {
			if (result[i] == 0) {
				noOfRegisters += 1;
			}
		}
		int actualOutput1 = noOfRegisters;
		int expectedOutput1 = 32;
		System.out.println("(after input of empty instructions file, testGetRegisters() gives: "+machine.getRegisters()+")");	
		assertEquals(expectedOutput1,actualOutput1);

		translator = new Translator("instructionsTestAdd.txt");
		translator.readAndTranslate(machine.getLabels(), machine.getProg());
		machine.execute();
		int countValuesInRegisters = 0;
		
		result = machine.getRegisters().registers;
		for (int i=0;i<result.length;i++) {
			countValuesInRegisters += result[i];
		}
		
		int actualOutput2 = countValuesInRegisters;
		int expectedOutput2 = (88+43+131);
		
		System.out.println("(after input of the instructionsTestAdd.txt file, testGetRegisters() gives: "+machine.getRegisters()+")");	
		
		assertEquals(expectedOutput2,actualOutput2);

	}

	/**
	 * Very simple test which effectively involves testing both 
	 * setPc() as well as getPc().
	 */
	@Test
	public void testGetPc() {
		machine.setPc(10);
		int expectedOutput = 10;
		int actualOutput = machine.getPc(); 
		assertEquals(expectedOutput,actualOutput);
	}

	/**
	 * This setter is created by Lombok @Data, so it must be a simple
	 * 'this.labels = labels'. First I create an object of the Labels 
	 * class to which I add two label strings via Labels' addLabel() 
	 * method. Then I test that the method setLabels() works by setting
	 * Machine's member field labels with the newly created 'labels' object.
	 * I have to use Machine's getLabels() method to return that which
	 * setLabels(labels) has assigned. The final comparison is made between 
	 * the arrayList of strings of Labels' labels. 
	 */
	@Test
	public void testSetLabels() {
		Labels labels = new Labels();
		labels.addLabel("fo");
		labels.addLabel("f1");
		machine.setLabels(labels);
		Labels actualLabels = machine.getLabels();
		ArrayList<String> actualOutput = actualLabels.labels;
		
		List<String> expectedList = new ArrayList<>();
		expectedList.add("fo");
		expectedList.add("f1");
		List<String> expectedOutput = expectedList;
				
		assertEquals(expectedOutput,actualOutput);
	}

	/**
	 * A 'prog' is an arrayList of 'instructions'. So, first this test 
	 * creates two instructions, tehn adds them directly to an arrayList. 
	 * The setProg() method is tested with this arrayList, which should 
	 * now be assigned to the prog member field of Machine. The test
	 * requires that a second method of Machine works as well (getProg()).
	 */
	@Test
	public void testSetProg() {
		Instruction instruction1 = new AddInstruction("string",1,2,3);
		Instruction instruction2 = new AddInstruction("string",4,5,6);
		ArrayList<Instruction> instructions = new ArrayList<>();
		instructions.add(instruction1);
		instructions.add(instruction2);
		machine.setProg(instructions);
		ArrayList<Instruction> actualOutput = machine.getProg();
		ArrayList<Instruction> expectedOutput = instructions; 
		assertEquals(expectedOutput,actualOutput);
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
