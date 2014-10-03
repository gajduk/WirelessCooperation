package simulator.statistics;

import java.util.ArrayList;
import java.util.Collections;

public class EnergySpentByNodeStatisticsCalculator extends AbstractStatisticsCalculator<EnergySpenByNodeSimulationStat> {
	
	@Override
	public String getAggregatedResult() {
		ArrayList<Double> res = new ArrayList<>();
		stats.forEach(s -> res.addAll(s.getEnergies()));
		Collections.sort(res);
		return res+"";
	}

	@Override
	protected EnergySpenByNodeSimulationStat getSimulationStatInternal() {
		return new EnergySpenByNodeSimulationStat();
	}
	
}
