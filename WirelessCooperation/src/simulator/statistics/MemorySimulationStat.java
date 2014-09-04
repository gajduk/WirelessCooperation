package simulator.statistics;

import java.io.PrintWriter;

import simulator.SimulationDirector;
import utils.Memory;

public abstract class MemorySimulationStat extends AbstractSimulationStat {

	private Memory memory;
	
	public MemorySimulationStat(PrintWriter out,String additinal_info,int remember_last) {
		super(out,additinal_info);
		this.memory = new Memory(remember_last);
	}
	
	@Override
	public void update(SimulationDirector simulationDirector) {
		double value = getValue(simulationDirector);
		memory.addValue(value);
	}
	
	protected double averageValue() {
		return memory.avg();
	}
	
	protected int numValues() {
		return memory.count();
	}
	
	protected double cumulativeValues() {
		return memory.sum();
	}
	
	protected abstract double getValue(SimulationDirector simulationDirector);
	
	@Override
	public String messageToLog() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.messageToLog()).append("\n");
		sb.append("Total values remembered:").append(numValues()).append("\n");
		sb.append("Last value:").append(memory.lastValue()).append("\n");
		sb.append("Maximum number of remembered values:").append(memory.getRemember_last()).append("\n");
		sb.append("Sum of remembered values:").append(cumulativeValues()).append("\n");
		sb.append("Average of remembered values:").append(averageValue()).append("\n");
		sb.append("\n").append("\n");
		return sb.toString();
	}
	
}
