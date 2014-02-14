package sml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
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

		Object[] parameterList = new Object[10]; // This array used to store int or string parameters to be passed to the constructor
		int parmCount=0;
		
		if (line.equals(""))
			return null;

		// the name of the instruction to call is the initcapped incoming label with a postfix of Instruction
		// also need to prefix who name with the package name
		// i.e. add becomes sml.AddInstruction and mul becomes sml.MulInstruction and so on
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
			
			Constructor[] constructors = myClass.getConstructors();
			Constructor myConstructor = constructors[1]; // ignoring the first constructor
			Class[] parameterTypes = myConstructor.getParameterTypes();
			parmCount=parameterTypes.length;
			for (int j=1;j<parameterTypes.length;j++) { // start from 1 as first item is label and already stripped off
				Class tempClass = parameterTypes[j];
				if (tempClass.getName().equals("java.lang.String")) {
					String parm = scan();
					parameterList[j-1]=parm;
				} else if (tempClass.getName().equals("int")) {
					Integer parm = scanInt();
					parameterList[j-1]=parm;
				}
			}
			
			if (parmCount==2) {
				return (Instruction) myConstructor.newInstance(label, 
						(parameterList[0] instanceof String) ? (String) parameterList[0] : (int) parameterList[0]);
			}
			if (parmCount==3) {
				return (Instruction) myConstructor.newInstance(label, 
						(parameterList[0] instanceof String) ? (String) parameterList[0] : (int) parameterList[0], 
								(parameterList[1] instanceof String) ? (String) parameterList[1] : (int) parameterList[1]);
			}
			if (parmCount==4) {
				return (Instruction) myConstructor.newInstance(label, 
						(parameterList[0] instanceof String) ? (String) parameterList[0] : (int) parameterList[0], 
								(parameterList[1] instanceof String) ? (String) parameterList[1] : (int) parameterList[1],
										(parameterList[2] instanceof String) ? (String) parameterList[2] : (int) parameterList[2]);
			}
			
		} catch (ClassNotFoundException ex ) {
			System.out.println("class " + newString + " not found");
		} catch (InstantiationException ex ) {
			System.out.println("could not instantiate");
		} catch (InvocationTargetException ex ) {
			System.out.println("could not invocate target");
		} catch (IllegalAccessException ex ) {
			System.out.println("illegal access exception");
		}
		
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