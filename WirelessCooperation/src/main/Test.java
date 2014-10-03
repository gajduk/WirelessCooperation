package main;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import simulator.Architecture;
import simulator.Mobility;
import simulator.statistics.StatisticsCalculators;
import simulator.strategy.Strategies;
import simulator.tester.TestDifferentAlpha;
import simulator.tester.TestDifferentArchitectures;
import simulator.tester.TestDifferentMobilities;
import simulator.tester.TestDifferentN;
import simulator.tester.TestDifferentStrategies;
import simulator.tester.TestDifferentValues;
import builder.SimulationBuilder;


public class Test {
	
	public static void main(String[] args) throws Exception {
		System.setOut(new PrintStream(new FileOutputStream("out.txt")));
		System.setErr(new PrintStream(new FileOutputStream("err.txt")));
		List<StatisticsCalculators> scs = new ArrayList<>();
		scs.add(StatisticsCalculators.TotalEnergy);
		scs.add(StatisticsCalculators.EnergyByNode);
		
		SimulationBuilder builder = new SimulationBuilder().
				withCoop(1).withT(1000L).running().hidden();
		
		long iterations = 500000;
		int repetitions = 100;
		
		TestDifferentStrategies tds = new TestDifferentStrategies(
				Strategies.TitForTat,Strategies.Coop,Strategies.WinStayLoseShift,Strategies.Def);
		TestDifferentMobilities tdm = new TestDifferentMobilities(Mobility.RandomWaypoint,Mobility.None);
		TestDifferentArchitectures tda = new TestDifferentArchitectures(Architecture.AdHoc,Architecture.CentralAntena);
		TestDifferentAlpha alpha = new TestDifferentAlpha(2.0d,3.0d,4.0d);
		TestDifferentN N = new TestDifferentN(30,50,80);
		TestDifferentValues.testDifferentValues(builder, scs, repetitions, iterations, N, alpha, tdm, tds,tda);
		System.out.close();
		System.err.close();
		System.exit(0);
	}
	
}
