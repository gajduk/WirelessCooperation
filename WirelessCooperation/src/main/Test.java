package main;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import simulator.Mobility;
import simulator.statistics.StatisticsCalculators;
import simulator.statistics.TotalSpentEnergySimulationStat;
import simulator.strategy.Strategies;
import simulator.tester.TestDifferentAlpha;
import simulator.tester.TestDifferentMobilities;
import simulator.tester.TestDifferentN;
import simulator.tester.TestDifferentStrategies;
import simulator.tester.TestDifferentValues;
import builder.SimulationBuilder;


public class Test {
	
	public static void main(String[] args) throws Exception {
		System.setOut(new PrintStream(new FileOutputStream("out5.txt")));
		System.setErr(new PrintStream(new FileOutputStream("err.txt")));
		List<StatisticsCalculators> scs = new ArrayList<>();
		scs.add(StatisticsCalculators.TotalEnergy);
		
		SimulationBuilder builder = new SimulationBuilder().
				withStrategy(Strategies.Def).withMobility(Mobility.None).
				withCoop(1).withN(40).withT(1000L).running().hidden().withSimulationStat(new TotalSpentEnergySimulationStat());
		
		long iterations = 2000000;
		int repetitions = 100;
		
		TestDifferentStrategies tds = new TestDifferentStrategies(
				Strategies.TitForTat,Strategies.Coop,Strategies.WinStayLoseShift,Strategies.Def);
		TestDifferentMobilities tdm = new TestDifferentMobilities(Mobility.None,Mobility.RandomWaypoint);
		TestDifferentAlpha alpha = new TestDifferentAlpha(2.0d,2.5d,3.0d,3.5d,4.0d);
		TestDifferentN N = new TestDifferentN(10,20,30,40,50,60,70,80,90,100);
		TestDifferentValues.testDifferentValues(builder, scs, repetitions, iterations, N,alpha,tdm,tds);
		System.out.close();
		System.err.close();
		System.exit(0);
	}
	
}
