package sml;

public class BnzInstruction extends Instruction {
	
	//temporarily changed visibility of all three from private to public for junit test
	public int register;
	private int value; // Not sure about this value...?
	
	// it looks like its only used locally inside execute()
	private String labelL2;

	public BnzInstruction(String label, String opcode) {
		super(label, opcode);
	}

	public BnzInstruction(String label, int register, String L2) {
		super(label, "bnz");
		this.register = register;
		this.labelL2=L2;
	}

	@Override
	public void execute(Machine m) {
		value = m.getRegisters().getRegister(register);
		if (value!=0) {
			m.setPc(m.getLabels().indexOf(labelL2));
		}
	}

	@Override
	public String toString() {
		return super.toString() + " register " + register + " label = " + labelL2;
	}
}
