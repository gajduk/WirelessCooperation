package main;

import simulator.Architecture;
import simulator.Mobility;
import simulator.SimulationDirector;
import simulator.fitness.CoopBoolFitnessCalculator;
import simulator.fitness.CurrentFitnessDeltaFitnessCalculator;
import simulator.fitness.SimpleDeltaFitnessCalculator;
import simulator.strategy.HeavisideProbabilityCalculator;
import simulator.strategy.Strategies;
import simulator.strategy.TitForTatStrategyBehaviour;
import simulator.strategy.WinStayLoseShiftStrategyBehaviour;
import builder.SimulationBuilder;

public class RunGUI {
	
	public static void main(String[] args) {
		SimulationDirector simulation = new SimulationBuilder().
				withT(10000L).withArchitecture(Architecture.CentralAntena).
				withMobility(Mobility.None).
				withStrategy(new TitForTatStrategyBehaviour(new CurrentFitnessDeltaFitnessCalculator(), new HeavisideProbabilityCalculator(0)))
				.withN(100).withFitnessCalculator(new CoopBoolFitnessCalculator()).build();
		simulation.runSimulation(100_000_000L);
	}
	
}
