package smlTests;

import java.util.ArrayList;

import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.Registers;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(callSuper=false)
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
