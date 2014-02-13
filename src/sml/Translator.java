package sml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.lang.reflect.*;

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public class Translator {

	// word + line is the part of the current line that's not yet processed
	// word has no whitespace
	// If word and line are not empty, line begins with whitespace
	private String line = "";
	private Labels labels; // The labels of the program being translated
	private ArrayList<Instruction> program; // The program to be created
	private String fileName; // source file of SML code

	private static final String SRC = "src";

	public Translator(String fileName) {
		this.fileName = SRC + "/" + fileName;
	}

	// translate the small program in the file into lab (the labels) and
	// prog (the program)
	// return "no errors were detected"
	public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {
		Scanner sc; // Scanner attached to the file chosen by the user
		try {
			sc = new Scanner(new File(fileName));
		} catch (IOException ioE) {
			System.out.println("File: IO error to start " + ioE.getMessage());
			return false;
		}
		labels = lab;
		labels.reset();
		program = prog;
		program.clear();

		try {
			line = sc.nextLine();
		} catch (NoSuchElementException ioE) {
			return false;
		}

		// Each iteration processes line and reads the next line into line
		while (line != null) {
			// Store the label in label
			String label = scan();

			if (label.length() > 0) {
				Instruction ins = getInstruction(label);
				if (ins != null) {
					labels.addLabel(label);
					program.add(ins);
				}
			}

			try {
				line = sc.nextLine();
			} catch (NoSuchElementException ioE) {
				return false;
			}
		}
		return true;
	}

	// line should consist of an MML instruction, with its label already
	// removed. Translate line into an instruction with label label
	// and return the instruction
	public Instruction getInstruction(String label) {
		int s1; // Possible operands of the instruction
		int s2;
		int r;
		int x;

		if (line.equals(""))
			return null;

		// the name of the instruction to call is the initcapped incoming label suffixed with Instruction
		// i.e. add becomes AddInstruction and mul becomes MulInstruction and so on
		String ins = scan();
		char c = ins.charAt(0);
		c=Character.toUpperCase(c);
		StringBuffer buf = new StringBuffer(ins);
		buf.setCharAt(0,c);
		String newString = buf.toString( );
		newString = "sml." + newString + "Instruction";
		//System.out.println(newString);
		
		Class myClass = null;
		try {
			myClass = Class.forName(newString);
			//System.out.println("class " + newString + " was found");
			//Constructor myConstructor = myClass.getConstructor(String.class, Integer.TYPE); // need to amend this
			Constructor myConstructor = myClass.getConstructor(String.class, String.class);
			return (Instruction) myConstructor.newInstance(label, ins);
		} catch (ClassNotFoundException ex ) {
			System.out.println("class " + newString + " not found");
		} catch (NoSuchMethodException ex ) {
			System.out.println("method not found");
		} catch (InstantiationException ex ) {
			System.out.println("could not instantiate");
		} catch (InvocationTargetException ex ) {
			System.out.println("could not invocate target");
		} catch (IllegalAccessException ex ) {
			System.out.println("illegal access exception");
		}
		
		/*System.out.println("ins="+newString+"Instruction");
		switch (ins) {
		case "add":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new AddInstruction(label, r, s1, s2);
		case "sub":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new SubInstruction(label, r, s1, s2);
		case "mul":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new MulInstruction(label, r, s1, s2);
		case "div":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new DivInstruction(label, r, s1, s2);
		case "out":
			s1 = scanInt();
			return new OutInstruction(label, s1);
		case "bnz":
			s1 = scanInt();
			String L2 = scan();
			return new BnzInstruction(label, s1, L2);
			
		case "lin":
			r = scanInt();
			s1 = scanInt();
			return new LinInstruction(label, r, s1);
		}*/


		return null;
	}

	/*
	 * Return the first word of line and remove it from line. If there is no
	 * word, return ""
	 */
	public String scan() {
		line = line.trim();
		if (line.length() == 0)
			return "";

		int i = 0;
		while (i < line.length() && line.charAt(i) != ' '
				&& line.charAt(i) != '\t') {
			i = i + 1;
		}
		String word = line.substring(0, i);
		line = line.substring(i);
		return word;
	}

	// Return the first word of line as an integer. If there is
	// any error, return the maximum int
	public int scanInt() {
		String word = scan();
		if (word.length() == 0) {
			return Integer.MAX_VALUE;
		}

		try {
			return Integer.parseInt(word);
		} catch (NumberFormatException e) {
			return Integer.MAX_VALUE;
		}
	}
}