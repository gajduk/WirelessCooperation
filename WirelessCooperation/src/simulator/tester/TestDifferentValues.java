package simulator.tester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import builder.SimulationBuilder;
import simulator.SimulationDirector;
import simulator.statistics.StatisticsCalculators;

public abstract class TestDifferentValues <T> {
	
	TestDifferentValues<?> inner_test;

	List<T> t;
	
	int i = 0;
	
	public  TestDifferentValues(T... t) {
		this.t = new ArrayList<T>(Arrays.asList(t));
	}
	
	public void test(SimulationBuilder builder, List<StatisticsCalculators> scs,int repetitions, long iterations,String description) {
		i = 0;
		while ( true ) {
			SimulationBuilder sbs = getNextBuilder(builder);
			if ( sbs == null ) return;
			String descr = description+";"+getDescription(sbs);
			if( inner_test != null )
				inner_test.test(sbs, scs,repetitions,iterations,descr);
			else {
				for ( int i = 0 ; i < repetitions ; ++i ) {
					builder.withoutSimulationStats();
					for ( StatisticsCalculators sc : scs )
						builder.withSimulationStat(sc.getSimulationStat());
					SimulationDirector sd = builder.build();
					sd.runSimulation(iterations);
				}
				System.out.println(descr);
				for ( StatisticsCalculators sc : scs ) {
					System.out.println(sc.getAggregatedResult());
					sc.clear();
				}
			}
		}
	}

	private SimulationBuilder getNextBuilder(SimulationBuilder builder) {
		if ( i >= t.size() ) return null;
		return getBuilder(builder,t.get(i++));
	}
	
	protected abstract SimulationBuilder getBuilder(SimulationBuilder builder, T sb);

	protected abstract String getDescription(SimulationBuilder builder);
	
	public static void testDifferentValues(SimulationBuilder builder, List<StatisticsCalculators> scs,int repetitions, long iterations,TestDifferentValues<?>... testers) {
		TestDifferentValues<?> last_t = null;
		for ( TestDifferentValues<?> t : testers ) {
			t.inner_test = last_t;
			last_t = t;
		}
		testers[testers.length-1].test(builder, scs, repetitions, iterations, "");
	}
	
}
