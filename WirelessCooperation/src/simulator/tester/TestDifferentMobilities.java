package simulator.tester;

import java.util.List;

import builder.SimulationBuilder;
import simulator.Mobility;

public class TestDifferentMobilities extends TestDifferentValues<Mobility> {

	public TestDifferentMobilities(Mobility... t) {
		super(t);
	}

	@Override
	protected SimulationBuilder getBuilder(SimulationBuilder builder,
			Mobility sb) {
		return builder.withMobility(sb);
	}

	@Override
	protected String getDescription(SimulationBuilder builder) {
		return "Mobility:"+builder.getMobility().name();
	}
	
	

}
