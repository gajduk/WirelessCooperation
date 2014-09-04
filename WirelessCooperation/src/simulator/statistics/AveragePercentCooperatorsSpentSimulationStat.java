package simulator.statistics;

import java.io.PrintWriter;

import simulator.SimulationDirector;
import utils.Utils;

public class AveragePercentCooperatorsSpentSimulationStat extends MemorySimulationStat {

	public AveragePercentCooperatorsSpentSimulationStat(PrintWriter out,String additinal_info, int remember_last) {
		super(out,additinal_info,remember_last);
	}

	@Override
	public void init(SimulationDirector simulationDirector) {
		super.init(simulationDirector);
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

}
