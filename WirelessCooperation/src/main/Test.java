package main;

import simulator.SimulationDirector;
import simulator.SimulatorBuilder;


public class Test {
	
	public static void main(String[] args) throws Exception {
		SimulatorBuilder sb = new SimulatorBuilder();
		SimulationDirector sd = sb.build();
		sd.runSimulation(1000000L);
	}

}
