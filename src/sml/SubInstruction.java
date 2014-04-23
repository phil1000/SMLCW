package sml;

public class SubInstruction extends Instruction {

	//temporarily changed visibility of all three from private to public for junit test
	public int result;
	public int op1;
	public int op2;

	public SubInstruction(String label, String op) {
		super(label, op);
	}

	public SubInstruction(String label, int result, int op1, int op2) {
		this(label, "sub");
		this.result = result;
		this.op1 = op1;
		this.op2 = op2;
	}

	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		m.getRegisters().setRegister(result, value1 - value2);
	}

	@Override
	public String toString() {
		return super.toString() + " " + op1 + " - " + op2 + " to " + result;
	}
}
