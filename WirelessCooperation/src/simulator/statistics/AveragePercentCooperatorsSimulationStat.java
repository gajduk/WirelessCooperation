package simulator.statistics;


import simulator.SimulationDirector;
import utils.Utils;

public class AveragePercentCooperatorsSimulationStat extends MemorySimulationStat {

	public AveragePercentCooperatorsSimulationStat(int remember_last) {
		super(remember_last);
	}

	@Override
	public String messageToLog() {
		StringBuilder sb = new StringBuilder("Average Percent of cooperators");
		sb.append("\n").append(super.messageToLog());
		return sb.toString();
	}

	@Override
	protected double getValue(SimulationDirector simulationDirector) {
		return Utils.percentCooperators(simulationDirector.getWirelessNodeMap().getNodes());
	}
	
	public double getAveragePercentCooperatos() {
		return super.averageValue();
	}

}
