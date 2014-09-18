package simulator.statistics;

public enum StatisticsCalculators implements StatisticsCalculator {
	TotalEnergy(new TotalEnergyStatisticsCalculator()),
	PercentCoop(new PercentCoopStatisticsCalculator());
	
	
	
	private StatisticsCalculators(StatisticsCalculator sc) {
		this.sc = sc;
	}

	StatisticsCalculator sc;

	@Override
	public SimulationStat getSimulationStat() {
		return sc.getSimulationStat();
	}

	@Override
	public String getAggregatedResult() {
		return sc.getAggregatedResult();
	}

	@Override
	public void clear() {
		sc.clear();
	}

}
