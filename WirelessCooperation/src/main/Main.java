package main;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import simulator.SimulationExperiment;
import utils.MultipleRunsAverager;


public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SimulationExperiment.out_coop = new PrintWriter(new FileOutputStream("coops_very_long.txt"),true);
		SimulationExperiment.out_energy = new PrintWriter(new FileOutputStream("energy_very_long.txt"),true);
		/*
		SimulationExperiment.out_time = new PrintWriter(System.out);
		String ecs [] = { "minimal", "closest" ,"dfair", "even" };
		int times = 30;
		double nu_step = .05;
		double nu_start = .0;
		double nu_end = 1.000001;
		double alphas[] = { 2.0,3.0, 4.0 };
		int total_num_threads = (int) (times*(((nu_end-nu_start)/nu_step)+1)*alphas.length*ecs.length);
		System.out.println(total_num_threads);
		int num_threads = Runtime.getRuntime().availableProcessors()+1;
		ThreadPoolExecutor executor = new ThreadPoolExecutor(num_threads,Math.max(total_num_threads+1,num_threads+1),Long.MAX_VALUE,TimeUnit.MINUTES,new ArrayBlockingQueue<Runnable>(total_num_threads+1));
		for ( String s : ecs ) {
			for ( double alpha : alphas ) {
				for ( double nu = nu_start ; nu < nu_end ; nu += nu_step ) {
					final String params = "-SHOW 0 -PAUSED 0 -D 150000 -S C -SA synch -T 999 -N 30 -COOP 30 -a 100 -EC "+s+" -alpha "+alpha+" -ni "+nu;
					
					for ( int k = 0 ; k < times ; ++k ) {
						executor.execute(new Runnable() {
							
							@Override
							public void run() {
								try {
									SimulationExperiment.main(params.split(" ++"));
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							
						});
					}
				}
			}
		}
		executor.shutdown();
		executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
	
		SimulationExperiment.out_coop.close();
		SimulationExperiment.out_energy.close();
		*/
		
	//	String ecs [] = { "closest" , "dfair" , "even" };
		String fcdfc_combinations[] = { " -F simple -DF simple " , " -F improved -DF simple " , " -F improved -DF improved " };
		String strategies[] = { "A","B" };
		int times = 10;
		double nu_step = .05;
		double nu_start = .0;
		double nu_end = 1.000001;
		double alphas[] = { 2.0,3.0,4.0 };
		int total_num_threads = (int) (times*((nu_end-nu_start)/nu_step)*alphas.length*fcdfc_combinations.length*strategies.length);
		System.out.println(total_num_threads);
		int num_threads = Runtime.getRuntime().availableProcessors()+1;
		ThreadPoolExecutor executor = new ThreadPoolExecutor(num_threads,Math.max(total_num_threads+1,num_threads+1),Long.MAX_VALUE,TimeUnit.MINUTES,new ArrayBlockingQueue<Runnable>(total_num_threads+1));
		for ( String fcdfc : fcdfc_combinations ) {
			for ( String s : strategies ) {
				for ( double alpha : alphas ) {
					for ( double nu = nu_start ; nu < nu_end ; nu += nu_step ) {
						final String params = "-SHOW 0 -PAUSED 0 -D 1000000 -S "+s+" -SA synch -T 999 -N 30 -a 100 -EC closest -alpha "+alpha+" -ni "+nu+fcdfc;
						
						for ( int k = 0 ; k < times ; ++k ) {
							executor.execute(new Runnable() {
								
								@Override
								public void run() {
									try {
										SimulationExperiment.main(params.split(" ++"));
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
								
							});
						}
					}
				}
			}
		}
		executor.shutdown();
		executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);

		SimulationExperiment.out_coop.close();
		SimulationExperiment.out_energy.close();
		
		
		Scanner jin = new Scanner(new File("energy_very_long.txt"));
		MultipleRunsAverager mra = new MultipleRunsAverager();
		while ( jin.hasNextLine() ) {
			jin.nextLine();
			String type = jin.nextLine()+" "+jin.nextLine()+" "+jin.nextLine();
			double value = Double.parseDouble(jin.nextLine().split(":")[1]);
			jin.nextLine();
			mra.addRun(type, value);
		}
		System.out.println(mra.toString());
		/*
		TreeSet<String> descriptions = new TreeSet<String>(mra.getAllDescriptions());
		for ( String descr : descriptions ) {
			System.out.println(descr+","+mra.getAverageForDescription(descr));
		}
		*/
		jin.close();
		
		/*
		
		Scanner jin = new Scanner(new File("results.txt"));
		double avg_energy_spent = 0;
		int count = 0;
		while ( jin.hasNextDouble() ) {
			double energy_spent = jin.nextDouble();
			++count;
			avg_energy_spent += energy_spent;
		}
		System.out.println(avg_energy_spent/count/100000000.0);
		jin.close();
		
		*/
		
		/*
		double a = 4;
		for ( double v= 0.1 ; v < 0.91 ; v += 0.01 ) {
			double left = 1-Math.pow(v,a); 
			double right = Math.pow(-0.417*v+1,4);
			if ( left < right ) System.out.println("SHIT");
		}
		
		*/
	}

}
