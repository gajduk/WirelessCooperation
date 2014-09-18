package simulator.tester;

import builder.SimulationBuilder;

public class TestDifferentAlpha  extends TestDifferentValues<Double> {

	public TestDifferentAlpha(Double... t) {
		super(t);
	}

	@Override
	protected SimulationBuilder getBuilder(SimulationBuilder builder,
			Double alpha) {
		return builder.withAlpha(alpha);
	}

	@Override
	protected String getDescription(SimulationBuilder builder) {
		return String.format("Alpha:%.2f",builder.getAlpha());
	}

}
