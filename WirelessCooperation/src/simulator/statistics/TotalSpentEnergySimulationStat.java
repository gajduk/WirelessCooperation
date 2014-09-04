package simulator.statistics;

import java.io.PrintWriter;

import simulator.Node;
import simulator.SimulationDirector;

public class TotalSpentEnergySimulationStat extends AbstractSimulationStat {

	private double total_energy_spent;
	
	public TotalSpentEnergySimulationStat(PrintWriter out,
			String additional_info) {
		super(out, additional_info);
	}

	@Override
	public void update(SimulationDirector simulationDirector) {
		
	}
	
	@Override
	public void simulationFinished(SimulationDirector simulationDirector) {
		total_energy_spent = 0;
		for ( Node n : simulationDirector.getWirelessNodeMap().getNodes() ) {
			total_energy_spent += n.getTotal_spent_energy();
		}
		super.simulationFinished(simulationDirector);
	}
	
	@Override
	public String messageToLog() {
		StringBuilder sb = new StringBuilder("Total Energy spent by all nodes\n");
		sb.append(super.messageToLog());
		sb.append("Value:").append(total_energy_spent).append("\n");
		return sb.toString();
	}

}
