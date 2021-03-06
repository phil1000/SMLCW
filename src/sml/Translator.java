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
	//line temporarily made public for JUnit test
	public String line = "";
	
	//labels temporarily made public for JUnit test
	public Labels labels; // The labels of the program being translated
	
	//program temporarily made public for JUnit test
	public ArrayList<Instruction> program; // The program to be created

	//temporarily changed from private to public for JUnit
	public String fileName; // source file of SML code

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
		labels = lab; // I don't understand the point of Machine passing 'lab' and 'prog'..
		labels.reset();// ..and assigning their values to Translator's copy...
		program = prog;// ..They're empty, and then their values (empty or otherwise).. 
		program.clear();// ..are assigned to local copies, only to be immediately cleared (?)
		
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
	public String forJUnitTestNewString = "";// this string is for JUnit test only
	
	public Instruction getInstruction(String label) {

		Object[] parameterList;//This array used to store int or string parameters to be passed to the constructor
		int parmCount=0; //not used ?
		
		if (line.equals(""))
			return null;

		// the name of the instruction to call is the initcapped incoming label with a postfix of Instruction
		// also need to prefix who name with the package name
		// i.e. add becomes sml.AddInstruction and mul becomes sml.MulInstruction and so on
		String ins = scan();
		char c = ins.charAt(0);
		c = Character.toUpperCase(c);
		StringBuffer buf = new StringBuffer(ins);
		buf.setCharAt(0,c);
		String newString = buf.toString( );
		newString = "sml." + newString + "Instruction";
		forJUnitTestNewString = newString;// this is assignment is for JUnit test only
		Class myClass = null;
		try {
			myClass = Class.forName(newString);
			Constructor[] constructors = myClass.getConstructors();
			Constructor myConstructor = constructors[1]; // ignoring the first constructor
			Class[] parameterTypes = myConstructor.getParameterTypes();
			parameterList = new Object[parameterTypes.length];//create big enough parameterList array to store all constructor parms
			//now need to populate an array of parameters to be passed to the class constructor
			//need to check whether the parm type is a string or int as the instruction scan method
			//differs depending on parameter type e.g. scan() or scanInt()
			for (int j=0;j<parameterList.length;j++) {
				if (j==0) {
					parameterList[j]=label;
				} else {
					Class tempClass = parameterTypes[j];
					if (tempClass.getName().equals("java.lang.String")) {
						String parm = scan();
						parameterList[j]=parm;
					} else if (tempClass.getName().equals("int")) {
						Integer parm = scanInt();
						parameterList[j]=parm;
					}
				}
			}
			return (Instruction) myConstructor.newInstance(parameterList); // create new instance using the previously create parameter list
			
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
