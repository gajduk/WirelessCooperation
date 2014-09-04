package simulator.statistics;

import simulator.SimulationDirector;

public interface SimulationStat {

	public void init(SimulationDirector simulationDirector);

	public void close(SimulationDirector simulationDirector);

	public void simulationFinished(SimulationDirector simulationDirector);

	public void update(SimulationDirector simulationDirector);
	
	public String messageToLog();
	
}
