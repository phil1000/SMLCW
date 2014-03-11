package smlTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sml.Labels;

public class LabelsTest {

	Labels labels;
	
	@Before
	public void setUp() throws Exception {
		labels = new Labels();
	}

	@After
	public void tearDown() throws Exception {
		labels = null;
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
		String label = "fo";
		labels.addLabel(label);
		String actualOutput = labels.getLabels().get(0);
		String expectedOutput = label;
		assertEquals(expectedOutput,actualOutput);
	}

	/**
	 * Test for method that is only called by the branch instruction BnzInstruction.
	 * It facilitates setting the program counter (pc) of machine to the instruction 
	 * that the branch instruction branches to.
	 */
	@Test
	public void testIndexOf() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testReset() {
		fail("Not yet implemented");
	}

}
