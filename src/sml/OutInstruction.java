package sml;

public class OutInstruction extends Instruction {

	//temporarily changed visibility of register from private to public for JUnit tests
	public int register;
	private int value; //not used, can delete

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
