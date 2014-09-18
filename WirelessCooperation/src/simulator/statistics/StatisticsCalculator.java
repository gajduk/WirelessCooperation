package simulator.statistics;

public interface StatisticsCalculator {

	public abstract SimulationStat getSimulationStat();
	
	public abstract String getAggregatedResult();
	
	public abstract void clear();
	
}
