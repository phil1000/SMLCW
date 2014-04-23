package smlTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sml.Labels;

public class LabelsTest {

	Labels labelsTest;
	String labelTest;
	String labelTest2;
	String labelTest3;
	
	@Before
	public void setUp() throws Exception {
		
		labelsTest = new Labels();

		//adding 3 labels to empty Labels 
		labelTest = "testLab1";
		labelTest2 = "testLab2";
		labelTest3 = "testLab3";
		labelsTest.addLabel(labelTest); // index = 0
		labelsTest.addLabel(labelTest2);//index = 1
		labelsTest.addLabel(labelTest3);// index = 2

	}

	@After
	public void tearDown() throws Exception {
		
		labelsTest = null;
	
	}

	/** 
	 * Test for method called in Translator's readAndTranslate() method
	 * whereby "label", which is the first string that is returned from the 
	 * scan() method is fed into an Instruction object of the corresponding
	 * subclass of instruction and then also fed into Labels's arraylist of 
	 * labels called "labels". 
	 */
	@Test
	public void testAddLabel() {
		
		String labelTest4 = "testLab4";
		labelsTest.addLabel(labelTest4);
		String actualOutput = labelsTest.getLabels().get(3);
		String expectedOutput = labelTest4;
		assertEquals(expectedOutput,actualOutput);
	
	}

	/**
	 * returns an int that corresponds to the pc of the String label specified in
	 * the argument of indexOf(String)
	 * 
	 */
	@Test
	public void testIndexOf() {
		
		int actualOutput = labelsTest.indexOf(labelTest2) ;
		int expectedOutput = 1;
		assertEquals(expectedOutput,actualOutput);
		
	}

	@Test
	public void testToString() {
		
		String expectedOutput = "(testLab1, testLab2, testLab3)";
		String actualOutput = labelsTest.toString();
		assertEquals(expectedOutput,actualOutput);

	}

	@Test
	public void testReset() {

		String actualOutput = "";
		for (int i=0;i<labelsTest.getLabels().size(); i++) {
			
			actualOutput += labelsTest.getLabels().get(i);
				
		}
		assertFalse(actualOutput.length() == 0);
	
		labelsTest.reset();

		String actualOutput2 = "";
		for (int i=0;i<labelsTest.getLabels().size(); i++) {
			
			actualOutput2 += labelsTest.getLabels().get(i);
				
		}
		assertTrue(actualOutput2.length() == 0);
		
	}

}
