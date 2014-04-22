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
 * Note: only execute() and toString() are not written by Lombok.. 
 * 
 */
public class MachineTest {

	private Machine machine;
	private SubMachineForTest subMachineForTest;
	
//	private 	AddInstruction instruction;
	private Translator translator; 

	@Before
	public void setUp() throws Exception {
		
		machine = new Machine();	
		subMachineForTest = new SubMachineForTest();
		
	}

	@After
	public void tearDown() throws Exception {
		
		machine = null;
		subMachineForTest = null;
		
	}

	/**
	 * Testing execute() which must 
	 */
	@Test
	public void testExecute() {		
		
		translator = new Translator("instructionsTestAdd.txt");
		translator.readAndTranslate(machine.getLabels(), machine.getProg());
		machine.execute();
		System.out.println(machine.getRegisters() + ".");
		int actualOutput = machine.getRegisters().getRegisters()[2];
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
			actualOutput += machine.getLabels().labels.get(i);
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
		int[] result = machine.getRegisters().getRegisters();
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
		
		result = machine.getRegisters().getRegisters();
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
	 * creates two instructions, then adds them directly to an arrayList. 
	 * If setProg() works, it should assign this arrayList, which is passed 
	 * to it as an argument, to the prog member field of Machine. (The test
	 * relies on a second method of Machine working as well (getProg()).
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

	/**
	 * setRegisters(Registers) assigns the Registers to the member field 
	 * of Machine class. The Registers class contains an int array that
	 * are the registers. This test first creates the registers int array 
	 * which is initialised by its constructor to contain 32 positions 
	 * with 0. Two ints are added to it. The expected output is the same 
	 * int array and is compared with the assigned Registers using 
	 * getRegisters(). 
	 */
	@Test
	public void testSetRegisters() {
		
		Registers registers = new Registers();
		registers.getRegisters()[1] = 1;
		registers.getRegisters()[2] = 3;
		machine.setRegisters(registers);
		int[] actualOutput = machine.getRegisters().getRegisters();
				
		int[] expectedArray = new int[32];
		for (int i=0;i<expectedArray.length;i++) {
			expectedArray[i] = 0;
		}
		expectedArray[1] = 1;
		expectedArray[2] = 3;
		int[] expectedOutput = expectedArray;
		
		assertEquals(expectedOutput,actualOutput);
	
	}

	/*
	 * Trivial tests of setters and getters but for the sake of completion.
	 * Kept simple as possible this time. Checking value of the newly 
	 * assigned value of 'pc' using the second method of getPc().
	 */
	@Test
	public void testSetPc() {	
		
		machine.setPc(4);
		int actualOutput1 = machine.getPc();
		machine.setPc(10);
		int actualOutput2 = machine.getPc();
		int expectedOutput1 = 4;
		int expectedOutput2 = 10;
		assertEquals(expectedOutput1,actualOutput1);
		assertEquals(expectedOutput2,actualOutput2);
	
	}
	
	/** 
	 * Tests that two machine objects are the same, according to 
	 * Lombok's comparison of all non-static, non-transient member
	 * fields. (In order for this to work though, I had to manually 
	 * add @Data to the 3 classes Labels, Registers and Instruction.
	 * Now only Translator does not have @Data).
	 */
	@Test
	public void testEqualsObject() {
		
		Machine machine1 = new Machine();
		Machine machine2 = new Machine();	
		translator = new Translator("instructionsTestAdd.txt");		
		translator.readAndTranslate(machine1.getLabels(),machine1.getProg());
		translator = new Translator("instructionsTestAdd.txt");
		translator.readAndTranslate(machine2.getLabels(),machine2.getProg());
		machine1.execute();
		machine2.execute();		
		assertTrue(machine1.equals(machine2) && machine2.equals(machine1));// THE TEST FAILS HERE BUT I DON'T THINK IT SHOULD !!

		Instruction instruction = new AddInstruction("string",1,2,3);
		machine1.getProg().add(instruction);
		assertFalse(machine1.equals(machine2) && machine2.equals(machine1));
		
		machine1.setPc(1);
		machine2.setPc(2);
		assertFalse(machine1.equals(machine2) && machine2.equals(machine1));

		machine1 = machine2;
		assertTrue(machine1.equals(machine2) && machine2.equals(machine1));
	
	}
	
	/**
	 * (Not sure about this test yet.) 
	 */
	@Test
	public void testHashCode() {
		
		Machine machine1 = new Machine();
		Machine machine2 = new Machine();
		machine1 = machine2;		
		assertTrue(machine1.equals(machine2) && machine2.equals(machine1));
		assertTrue(machine1.hashCode() == machine2.hashCode());
	
	}
	
	/**
	 * (I learned that when the (machine) object is passed as a parameter 
	 * to println() method, it implicitly calls its toString() method,
	 * i.e. println(machine) is same as println(machine.toString())).
	 * For the txt file used in this test, the overriden toString() 
	 * transforms the output that would have been:
	 * "Machine(labels=(fo, f1, f2), prog=[fo: lin register 0 value is 34, f1: lin register 1 value is 200, f2: add 0 + 1 to 2], registers=Registers(registers=[34, 200, 234, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]), pc=3)"
	 * to the following 3 lines:
	 * "fo: lin register 0 value is 34 
	 * f1: lin register 1 value is 200
	 * f2: add 0 + 1 to 2".
	 * The test just looks to see that it removes the "Machine" string right at the start.
	 * (So, probably not a thorough test of the overriden toString() but checks to see its being called and starts off doing what it should). 
	 */
	@Test
	public void testToString() {
		
		translator = new Translator("3linesOfInstructionsTest.txt");
		translator.readAndTranslate(machine.getLabels(), machine.getProg());
		String expectedOutput = "fo: lin";
		String actualOutput  = machine.toString().substring(0,7);
		assertEquals(expectedOutput,actualOutput);
		
		String failedOverride = "Machine";
		assertFalse(failedOverride.equals(machine.toString().substring(0,7)));
	
	}

	/**
	 * Not sure that this is needed as Machine is not part of any class 
	 * inheritance hierarchy (other than Object). But it's written in various
	 * blurbs on the web (including Lombok's) that whenever an equals() is
	 * overriden, the canEqual() must also be overriden, which it is (by Lombok),
	 * Just to test this method alone, I've decided to create a whole new class 
	 * in the SML package which extends Machine (called SubMachineForTest, in the 
	 * smlTests package). The two objects are then subjected to the exact same 
	 * methods and compared. It is expected that canEqual() should be false as 
	 * they are not the exact same class, but only subclass and base class.  
	 * 
	 */
	@Test
	public void testCanEqual() {
		
		translator = new Translator("3linesOfInstructionsTest.txt");
		translator.readAndTranslate(subMachineForTest.getLabels(), subMachineForTest.getProg());
		subMachineForTest.execute();

		translator = new Translator("3linesOfInstructionsTest.txt");
		translator.readAndTranslate(machine.getLabels(), machine.getProg());
		machine.execute();
		
		assertFalse(subMachineForTest.canEqual(machine));
		
	}

	/**
	 * This tests that the Machine constructor works!
	 * It doesn't take any parameters so there are no
	 * assignments to check. A Machine object is created
	 * in the setUp(), and used throughout the tests in
	 * MachineTest, so it's probably not necessary to do 
	 * further testing. 
	 */
	@Test
	public void testMachine() {}

}
