package sml;

public class OutInstruction extends Instruction {
	private int register;
	private int value;

	public OutInstruction(String label, String opcode) {
		super(label, opcode);
	}

	public OutInstruction(String label, int register) {
		super(label, "out");
		this.register = register;
	}

	@Override
	public void execute(Machine m) {
		int value = m.getRegisters().getRegister(register);
		System.out.println("Register " + register + " value is " + value);
	}

	@Override
	public String toString() {
		return super.toString() + " register " + register;
	}
}
