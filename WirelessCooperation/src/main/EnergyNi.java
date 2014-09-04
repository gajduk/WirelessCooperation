package main;

import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import simulator.SimulationExperiment;

class Par implements Comparable<Par> {
	double r,e,p;

	public Par(double r, double e,double p) {
		super();
		this.r = r;
		this.e = e;
		this.p = p;
	}

	@Override
	public int compareTo(Par p) {
		return Double.compare(r, p.r);
	}
	
}



public class EnergyNi {

	
	public static void main(String[] args) throws Exception {
		
		SimulationExperiment.out_energy = new PrintWriter("energy_ni1.txt");
		int num_threads = Runtime.getRuntime().availableProcessors()+1;
		int times = 100;
		ThreadPoolExecutor executor = new ThreadPoolExecutor(num_threads,times*2*2*100+1,Long.MAX_VALUE,TimeUnit.MINUTES,new ArrayBlockingQueue<Runnable>(times*2*2*100+1));
		for ( double ni = 0 ; ni < 1 ; ni+=0.01 ) {
			//for ( String s : new String[]{"A","B"} ) {
			for ( String s : new String[]{"C"} ) {
				for ( String ec : new String[]{"dfair","closest"} ) {
					final String params = "-SHOW 0 -PAUSED 0 -D 100000 -S "+s+" -SA synch -T 999 -N 30 -a 100 -EC "+ec+" -alpha 4.0 -ni "+ni+" -F improved -DF simple -COOP 1";
					for ( int k = 0 ; k < times ; ++k ) {
						executor.execute(new Runnable() {
			
							@Override
							public void run() {
								try {
									SimulationExperiment.main(params.split(" ++"));
								} catch (Exception e) {
									e.printStackTrace();
									System.exit(0);
								}
							}
							
						});
					}
				}
			}
		}
		executor.shutdown();
		executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
		SimulationExperiment.out_energy.close();
		
		/*
		Scanner jin = new Scanner(new File("nodes.txt"));
		ArrayList<Par> nodes = new ArrayList<>();
		while ( jin.hasNextLine() ) {
			String line = jin.nextLine();
			String s[] = line.split(" ");
			nodes.add(new Par(Double.parseDouble(s[0].split(":")[1]),Double.parseDouble(s[1].split(":")[1]),Double.parseDouble(s[2].split(":")[1])));
		}
		Collections.sort(nodes);
		System.out.print("r = [");
		for ( int i = 0 ; i < nodes.size() ; ++i ) {
			System.out.printf("%.2f",(nodes.get(i).r));
			if ( i!= nodes.size()-1 ) System.out.print(',');
		}
		System.out.println("];");
		System.out.print("p = [");
		for ( int i = 0 ; i < nodes.size() ; ++i ) {
			System.out.printf("%.3f",nodes.get(i).p);
			if ( i!= nodes.size()-1 ) System.out.print(',');
		}
		System.out.println("];");
		jin.close();
		*/
		/*
		String s_combs[] = { "B", "A" };
		String f_df_combs[] = { " -F simple -DF simple " , " -F improved -DF improved " , " -F improved -DF simple " };
		PrintStream old_error = System.err;
		System.setErr(new PrintStream(new NullOutputStream()));
		int total_process_count = 0;
		int times = 20;
		for ( String s : s_combs ) {
			for ( String f_df : f_df_combs ) {
				String params = "-COOP 1 -SHOW 0 -PAUSED 1 -D 1000000 -S "+s+" -SA synch -T 999 -N 30 -a 300 -EC closest -alpha 3.0 -ni 0.5 "+f_df;
				for ( int k = 0 ; k < times ; ++k ) {
					max_process_s.acquire();
					MainThread mt = new MainThread(params.split(" ++"));
					mt.start();
					++total_process_count;
					System.out.println(total_process_count);
				}
			}
		}
		count_s.acquire(total_process_count);
		SynchonizedFileWriter sfw = SynchonizedFileWriter.getInstance();
		sfw.close();
		System.out.println("FINISHED");
		System.setErr(old_error);
		Scanner jin = new Scanner(new File(SynchonizedFileWriter.file_name));
		HashMap<String,LinkedList<Double>> map = new HashMap<String,LinkedList<Double>>(); 
		HashMap<String,LinkedList<Double>> map_coop = new HashMap<String,LinkedList<Double>>(); 
		while ( jin.hasNextLine() ) {
			String line = jin.nextLine();
			String n_line = jin.nextLine();
			double d = Double.parseDouble(n_line.split(" ")[1]);
			if ( ! map.containsKey(line) ) {
				map.put(line, new LinkedList<Double>());
				map_coop.put(line, new LinkedList<Double>());
			}
			map.get(line).add(d);
			n_line = jin.nextLine();
			double avg = Double.parseDouble(n_line.split(" ")[1]);
			map_coop.get(line).add(avg);
		}
		for ( String s : map.keySet() ) {
			double avg = .0;
			for ( Double d : map.get(s) ) {
				avg += d;
			}
			avg /= map.get(s).size();
			System.out.println(s);
			System.out.println("total energy spent: "+avg/100000000);
			avg = .0;
			for ( Double d : map_coop.get(s) ) {
				avg += d;
			}
			avg /= map.get(s).size();
			System.out.println("average percent of cooperators: "+avg);
		}
		*/
		/*
		 a = 100
		 alpha = 2, C = 30000000 
		 alpha = 3, C = 500000000 
		 alpha = 4, C = 10000000000
		 
		 */
		/*
		PrintStream old_error = System.err;
		System.setErr(new PrintStream(new NullOutputStream()));
		int total_process_count = 0;
		int times = 50;
		double step = .05;
		double cs[] = { 30000000.0 , 500000000.0, 10000000000.0};
		int csidx = 0;
		for ( double alpha = 2.0 ; alpha < 4.002 ; alpha += 1.0 ) {
			double C = cs[csidx++];
			for ( int k = 0 ; k < times ; ++k ) {
				for ( double nu = 0.0 ; nu < 1.001 ; nu += step ) {
					max_process_s.acquire();
					String params = "-COOP 1 -SHOW 0 -PAUSED 1 -D 2000000 -C "+C+" -S A -SA synch -T 999 -N 30 -F ce -DF ce -a 100 -EC closest -alpha "+alpha+" -ni "+nu;
					++total_process_count;
					MainThread mt = new MainThread(params.split(" "));
					mt.start();
					System.out.println(total_process_count);
				}
			}
		}
		count_s.acquire(total_process_count);
		System.out.println("FINISHED");
		System.setErr(old_error);
		SynchonizedFileWriter sfw = SynchonizedFileWriter.getInstance();
		sfw.close();
		*/
		/*
		Scanner jin = new Scanner(new File(SynchonizedFileWriter.file_name));
		TreeMap<Double,TreeMap<Double,LinkedList<Double>>> values = new TreeMap<Double,TreeMap<Double,LinkedList<Double>>>();
		while ( jin.hasNextLine() ) {
			String line = jin.nextLine();
			String s_line[] = line.split(" ");
			double alpha = Double.parseDouble(s_line[0]);
			if ( ! values.containsKey(alpha) ) {
				values.put(alpha,new TreeMap<Double,LinkedList<Double>>());
			}
			double ni = Double.parseDouble(s_line[1]);
			if ( ! values.get(alpha).containsKey(ni) ) {
				values.get(alpha).put(ni,new LinkedList<Double>());
			}
			double percent = Double.parseDouble(s_line[2]);
			if ( !Double.isNaN(percent) ) //percent = 0.0;
				values.get(alpha).get(ni).add(percent);
		}
		jin.close();
		System.setOut(new PrintStream(new File("final_resutlts.txt")));
		System.out.println("step ="+step);
		for ( Double alpha : values.keySet() ) {
			System.out.println("alpha="+alpha);
			System.out.println();
			System.out.print("[");
			TreeMap<Double,LinkedList<Double>> valuess = values.get(alpha);
			boolean qwe = false;
			for ( Double nu : valuess.keySet() ) {
				double val_avg = 0.0;
				for ( Double d : valuess.get(nu) ) {
					val_avg += d;
				}
				if ( qwe ) System.out.print(",");
				else qwe = true;
				val_avg /= valuess.get(nu).size();
				System.out.print(val_avg);
			}
			System.out.println("]");
			System.out.println();
		}
		System.out.close();		
		*/
		/*
		Scanner jin = new Scanner(new File("results.txt"));
		TreeMap<Double,TreeMap<Double,ArrayList<Double>>> values = new TreeMap<Double,TreeMap<Double,ArrayList<Double>>>();
		while ( jin.hasNextDouble() ) {
			double alpha = jin.nextDouble();
			double nu = jin.nextDouble();
			double avgpc = jin.nextDouble();
			TreeMap<Double,ArrayList<Double>> values_for_alpha = values.get(alpha);
			if ( values_for_alpha == null ) {
				values_for_alpha = new TreeMap<Double,ArrayList<Double>>();
			}
			ArrayList<Double> current =  values_for_alpha.get(nu);
			if ( current == null ) {
				current = new ArrayList<Double>();
			}
			current.add(avgpc);
			values_for_alpha.put(nu, current);
			values.put(alpha, values_for_alpha);
		}
		jin.close();
		PrintWriter out = new PrintWriter("avg_percent_coop_different_alpha_nu_buffer.txt");
		for ( Double alpha : values.keySet() ) {
			out.println("alpha :"+alpha);
			TreeMap<Double,ArrayList<Double>> values_for_alpha = values.get(alpha);
			ArrayList<Double> avgs = new ArrayList<Double>();
			ArrayList<Double> nus = new ArrayList<Double>();
			for ( Double nu : values_for_alpha.keySet() ) {
				nus.add(nu);
				double avg = 0.0;
				for ( Double val : values_for_alpha.get(nu) ) {
					avg += val;
				}
				avg /= values_for_alpha.get(nu).size();
				avgs.add(avg);
			}
			out.println(nus);
			out.println(avgs);
			out.println();
		}
		out.close();
		*/
	}

}
