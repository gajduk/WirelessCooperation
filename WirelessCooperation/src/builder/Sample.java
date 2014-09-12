package builder;

import simulator.Mobility;
import simulator.SimulationDirector;

public class Sample {
	
	public static void main(String[] args) throws Exception {
		SimulationDirector sd = new SimulatorBuilder().withMobility(Mobility.None).withCoop(10).withN(50).build();
		sd.runSimulation(100000000L);
	}

}
