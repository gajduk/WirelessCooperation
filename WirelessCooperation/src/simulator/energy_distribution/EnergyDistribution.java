package simulator.energy_distribution;

import java.util.List;

import simulator.Node;

public interface EnergyDistribution {
	
	public abstract double energyConsumption ( Node sender , Node resv , List<Node> cooperators );

	public abstract double getNi();

	public abstract double getAlpha();
	
}
