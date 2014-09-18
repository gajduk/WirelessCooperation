package simulator.tester;

import simulator.Mobility;
import builder.SimulationBuilder;

public class TestDifferentN  extends TestDifferentValues<Integer> {

	public TestDifferentN(Integer... t) {
		super(t);
	}

	@Override
	protected SimulationBuilder getBuilder(SimulationBuilder builder,
			Integer N) {
		return builder.withN(N);
	}

	@Override
	protected String getDescription(SimulationBuilder builder) {
		return "N:"+builder.getN();
	}

}
