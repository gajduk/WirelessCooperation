package main;

import builder.SimulatorBuilder;
import simulator.SimulationDirector;


public class Test {
	
	public static void main(String[] args) throws Exception {
		SimulatorBuilder sb = new SimulatorBuilder();
		SimulationDirector sd = sb.build();
		sd.runSimulation(100000000L);
	}

}
