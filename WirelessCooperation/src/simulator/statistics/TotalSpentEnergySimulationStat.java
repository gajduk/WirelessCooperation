package simulator.statistics;

import simulator.Node;
import simulator.SimulationDirector;

public class TotalSpentEnergySimulationStat extends AbstractSimulationStat {

	private double total_energy_spent;
	
	public TotalSpentEnergySimulationStat() {}

	@Override
	public void update(SimulationDirector simulationDirector) {
		//DO NTH
	}
	
	public double getTotal_energy_spent() {
		return total_energy_spent;
	}

	@Override
	public void simulationFinished(SimulationDirector simulationDirector) {
		total_energy_spent = 0;
		for ( Node n : simulationDirector.getWirelessNodeMap().getNodes() ) {
			total_energy_spent += n.getEs().getTotal_spent_energy();
		}
		total_energy_spent /= simulationDirector.getWirelessNodeMap().getNodes().size();
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
