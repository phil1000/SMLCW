package sml;

import lombok.Data;

/**
 * This class is the superclass of the classes for machine instructions.
 * 
 * @author someone
 */
@Data
public abstract class Instruction {
	//temporarily changed visibility of both from protected to public for junit test
	public String label;
	public String opcode;

	// Constructor: an instruction with label l and opcode op
	// (op must be an operation of the language)

	public Instruction(String l, String op) {
		this.label = l;
		this.opcode = op;
	}

	// = the representation "label: opcode" of this Instruction

	@Override
	public String toString() {
		return label + ": " + opcode;
	}

	// Execute this instruction on machine m.

	public abstract void execute(Machine m);
}
