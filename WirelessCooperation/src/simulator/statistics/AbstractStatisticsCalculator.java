package simulator.statistics;

import java.util.ArrayList;

public abstract class AbstractStatisticsCalculator <T extends SimulationStat> implements StatisticsCalculator {
	
	ArrayList<T> stats = new ArrayList<>();
	
	@Override
	public void clear() {
		stats.clear();
	}		
	
	@Override
	public SimulationStat getSimulationStat() {
		T ss = getSimulationStatInternal();
		stats.add(ss);
		return ss;
	}

	protected abstract T getSimulationStatInternal();
	
}
