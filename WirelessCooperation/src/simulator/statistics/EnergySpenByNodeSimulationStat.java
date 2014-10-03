package simulator.statistics;

import java.util.ArrayList;

import simulator.Node;
import simulator.SimulationDirector;

public class EnergySpenByNodeSimulationStat extends AbstractSimulationStat {
	
	private ArrayList<Double> energies;
	
	public EnergySpenByNodeSimulationStat() {
	}

	@Override
	public void update(SimulationDirector simulationDirector) {
		//DO NTH	
	}

	public ArrayList<Double> getEnergies() {
		return energies;
	}
	

	@Override
	public void simulationFinished(SimulationDirector simulationDirector) {
		this.energies = new ArrayList<>();
		for ( Node n : simulationDirector.getWirelessNodeMap().getNodes() ) {
			energies.add(n.getEs().getTotal_spent_energy());
		}
		super.simulationFinished(simulationDirector);
	}
	
	@Override
	public String messageToLog() {
		StringBuilder sb = new StringBuilder("Energy spent by individual nodes\n");
		sb.append(super.messageToLog());
		sb.append("Value:").append(energies).append("\n");
		return sb.toString();
	}


}
