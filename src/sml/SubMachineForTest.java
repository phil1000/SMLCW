package sml;

import java.util.ArrayList;
import lombok.Data;

@Data
public class SubMachineForTest extends Machine {
	private Labels labels;
	private ArrayList<Instruction> prog;
	private Registers registers;
	private int pc;
		
	{
		labels = new Labels();
		prog = new ArrayList<>();
		pc = 0;
	}
	
	public void execute() {
		setPc(0);
		setRegisters(new Registers());
		while (getPc() < getProg().size()) {
			Instruction ins = getProg().get(getPc());
			setPc(getPc() + 1);
			ins.execute(this);
		}
	}
}
