package simulator.statistics;

import main.AverageDistanceCooperator;

public class PercentCoopStatisticsCalculator extends AbstractStatisticsCalculator<AveragePercentCooperatorsSimulationStat> {

	@Override
	public String getAggregatedResult() {
		double res = stats.stream().map(AveragePercentCooperatorsSimulationStat::getAveragePercentCooperatos).reduce((a,b) -> a+b).get();
		res /= stats.size();
		return String.format("%.2f", res);
	}

	@Override
	protected AveragePercentCooperatorsSimulationStat getSimulationStatInternal() {
		return new AveragePercentCooperatorsSimulationStat(1000);
	}

}
