package simulator.tester;

import java.util.Arrays;

import builder.SimulationBuilder;
import simulator.strategy.Strategies;

public class TestDifferentStrategies extends TestDifferentValues<Strategies> {

	public TestDifferentStrategies(Strategies... ss) {
		super(ss);
	}

	@Override
	protected String getDescription(SimulationBuilder builder) {
		return "Strategy:"+builder.getClass().getSimpleName();
	}

	@Override
	protected SimulationBuilder getBuilder(SimulationBuilder builder,
			Strategies s) {
		return builder.withStrategy(s);
	}

}
