package simulator.tester;

import java.util.List;

import builder.SimulationBuilder;
import simulator.Architecture;

public class TestDifferentArchitectures extends TestDifferentValues<Architecture>{

	public TestDifferentArchitectures(Architecture... t) {
		super(t);
	}

	@Override
	protected SimulationBuilder getBuilder(SimulationBuilder builder,
			Architecture sb) {
		return builder.withArchitecture(sb);
	}

	@Override
	protected String getDescription(SimulationBuilder builder) {
		return builder.getArchitecture().name();
	}
	
	

}
