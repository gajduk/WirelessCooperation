package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import simulator.SimulationExperiment;

public class NodesCenterMinimalEnergy {
	
	//-D 150000000 -S C -SA synch -T 19999 -N 1000 -a 100 -EC minimal -alpha 4 -ni .3 -TL center -DF simple -F improved
	

	public static void main(String[] args) throws Exception {
		final AtomicInteger count = new AtomicInteger(0);
		SimulationExperiment.out_nodes = new PrintWriter(new BufferedWriter(new FileWriter("nodes12center_energy.txt", true)));
		int num_threads = Runtime.getRuntime().availableProcessors()+1;
		int times = 2000;
		double ni = 1;
		ThreadPoolExecutor executor = new ThreadPoolExecutor(num_threads,times*2*2*100+1,Long.MAX_VALUE,TimeUnit.MINUTES,new ArrayBlockingQueue<Runnable>(times*2*2*100+1));
		for ( String s : new String[]{"C"} ) {
			for ( String ec : new String[]{"minimal"} ) {
				final String params = "-SHOW 0 -PAUSED 0 -D 1000000 -S "+s+" -SA synch -T 1999 -N 100 -a 100 -EC "+ec+" -alpha 4.0 -ni "+ni+" -F improved -DF simple -COOP 1 -TL center";
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
