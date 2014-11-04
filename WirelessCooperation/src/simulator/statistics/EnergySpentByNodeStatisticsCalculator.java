package simulator.statistics;

import java.util.ArrayList;
import java.util.Collections;

public class EnergySpentByNodeStatisticsCalculator extends AbstractStatisticsCalculator<EnergySpentByNodeSimulationStat> {
	
	@Override
	public String getAggregatedResult() {
		ArrayList<Double> res = new ArrayList<>();
		stats.forEach(s -> res.addAll(s.getEnergies()));
		Collections.sort(res);
		return res+"";
	}

	@Override
	protected EnergySpentByNodeSimulationStat getSimulationStatInternal() {
		return new EnergySpentByNodeSimulationStat();
	}
	
}
