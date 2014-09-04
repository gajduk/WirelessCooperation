package main;

import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import simulator.SimulationExperiment;

public class NodesEnergyPercentCoop {

	
	public static void main(String[] args) throws Exception {
		final AtomicInteger count = new AtomicInteger(0);
		SimulationExperiment.out_nodes = new PrintWriter("nodes4.txt");
		int num_threads = Runtime.getRuntime().availableProcessors()+1;
		int times = 2000;
		double ni = 0.389d;
		ThreadPoolExecutor executor = new ThreadPoolExecutor(num_threads,times*2*2*100+1,Long.MAX_VALUE,TimeUnit.MINUTES,new ArrayBlockingQueue<Runnable>(times*2*2*100+1));
		for ( String s : new String[]{"A","B","C","D"} ) {
			for ( String ec : new String[]{"dfair","closest"} ) {
				final String params = "-SHOW 0 -PAUSED 0 -D 200000 -S "+s+" -SA synch -T 999 -N 30 -a 100 -EC "+ec+" -alpha 4.0 -ni "+ni+" -F improved -DF simple -COOP 1";
				for ( int k = 0 ; k < times ; ++k ) {
					executor.execute(new Runnable() {
		
						@Override
						public void run() {
							try {
								SimulationExperiment.main(params.split(" ++"));
								System.out.println(count.incrementAndGet());
							} catch (Exception e) {
								e.printStackTrace();
								System.exit(0);
							}
						}
						
					});
				}
			}
		}
		executor.shutdown();
		executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
		SimulationExperiment.out_nodes.close();
	}
}
