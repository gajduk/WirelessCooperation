package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import builder.SimulationBuilder;
import simulator.Mobility;
import simulator.SimulationDirector;
import simulator.statistics.StatisticsCalculator;
import simulator.statistics.StatisticsCalculators;
import simulator.statistics.TotalSpentEnergySimulationStat;
import simulator.strategy.Strategies;
import simulator.tester.TestDifferentArchitectures;
import simulator.tester.TestDifferentMobilities;
import simulator.tester.TestDifferentStrategies;
import simulator.tester.TestDifferentValues;


public class Test {
	
	public static void main(String[] args) throws Exception {

		List<StatisticsCalculators> scs = new ArrayList<>();
		scs.add(StatisticsCalculators.TotalEnergy);
		
		SimulationBuilder builder = new SimulationBuilder().
				withStrategy(Strategies.TitForTat).withMobility(Mobility.None).
				withCoop(1).withN(30).withT(1000L).running().withSimulationStat(new TotalSpentEnergySimulationStat());
		
		long iterations = 1000000L;
		int repetitions = 2;
		
		TestDifferentStrategies tds = new TestDifferentStrategies(
				Strategies.TitForTat,Strategies.Coop,Strategies.WinStayLoseShift,Strategies.Def);
		TestDifferentMobilities tdm = new TestDifferentMobilities(Mobility.None,Mobility.RandomWaypoint);
		TestDifferentValues.testDifferentValues(builder, scs, repetitions, iterations, tds,tdm);
		System.exit(0);
	}
	
}
