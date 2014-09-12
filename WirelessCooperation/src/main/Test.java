package main;

import builder.SimulatorBuilder;
import simulator.Mobility;
import simulator.SimulationDirector;
import simulator.strategy.Strategies;


public class Test {
	
	public static void main(String[] args) throws Exception {
		SimulationDirector sd = new SimulatorBuilder().
				withStrategy(Strategies.WinStayLoseShift).withMobility(Mobility.RandomWaypoint).
				withCoop(1).withN(70).withT(4000L).build();
		sd.runSimulation(1000000000L);
	}
	
	

}
