package main;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import simulator.SimulationExperiment;

public class TFTAdHoc {
	
	public static void main(String[] args) throws Exception {
		SimulationExperiment.out_nodes = new PrintWriter(new FileWriter(new File("test_coops_add_stats"),true));
		int times = 1000;
		ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()+1,times*2,Long.MAX_VALUE,TimeUnit.MINUTES,new ArrayBlockingQueue<Runnable>(times*2));
		for ( int i = 0 ; i < times ; ++i ) {
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						SimulationExperiment.main("-SHOW 0 -PAUSED 0 -D 2000000 -S A -COOP 2 -SA synch -T 999 -N 30 -a 100 -EC closest -alpha 4.0 -ni 0.39 -F improved -DF simple -TL uniform".split(" "));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			});
			
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						SimulationExperiment.main("-SHOW 0 -PAUSED 0 -D 2000000 -S B -COOP 2 -SA synch -T 999 -N 30 -a 100 -EC closest -alpha 4.0 -ni 0.39 -F improved -DF simple -TL uniform".split(" "));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
			
		}
		executor.shutdown();
		executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
		SimulationExperiment.out_nodes.close();
	}

}
