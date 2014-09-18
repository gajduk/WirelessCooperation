package simulator;

import java.io.PrintWriter;

public class SimulationExperiment {
	
	public static void main(String[] args) {
		
	}
	
	/*
	
	public static HashMap<String,OptionHandler> options;
	
	static {
		options = new HashMap<String,OptionHandler>();
		OptionHandler oh = new OptionInteger("N", "total number of nodes. The nodes are randomly distributed with uniform probability across a square grid.", 20);
		options.put("N",oh);
		oh = new OptionDouble("a", "Length of one side of the square simulation grid.", 100.0);
		options.put("a",oh);
		oh = new OptionDouble("alpha", "Coeficient that determines the exponential decay of signal strength as a function from distance. Vaues should range form 2(country enviorment) to 4(dense urban enviorment)", 4.0);
		options.put("alpha",oh);
		oh = new OptionDouble("ni", "Coeficient ranging from (0.0-1.0). It is used when calculating the indirect power consumed when a node sends using cooperators.", 4.0);
		options.put("ni",oh);
		oh = new OptionDouble("p"," Probability that every time step a communication between two senders will occur values 0.0-1.0",1.0);
		options.put("p",oh);
		oh = new OptionDouble("T","Time period between two addaptations of strategy occur in the nodes (This is correlated with the chosen type of update: synchronous or asynchronous)",200000.0);
		options.put("T",oh);
		HashMap<String,FitnessCalculator> calculators = new HashMap<String,FitnessCalculator>();
		calculators.put("simple", new SimpleFitnessCalculator());
		calculators.put("improved", new ImprovedFitnessCalculator());
		calculators.put("another", new AnotherFitnessCalculator());
		oh = new OptionMultiClass<FitnessCalculator>("F","Determines the way fitness is calculated for each node",calculators.get("simple"),"simple",calculators);
		options.put("F",oh);
		HashMap<String,DeltaFitnessCalculator> delta_calcs = new HashMap<String,DeltaFitnessCalculator>();
		delta_calcs.put("simple",new SimpleDeltaFitnessCalculator());
		delta_calcs.put("improved",new ImprovedDeltaFitnessCalculator());
		delta_calcs.put("ce",new CurrentEnergyDeltaFitnessCalculator());
		oh = new OptionMultiClass<DeltaFitnessCalculator>("DF","Determines the way the change in fitness is calculated for each node",delta_calcs.get("simple"),"simple",delta_calcs);
		options.put("DF",oh);
		HashMap<String,StrategyBehavior> strategy_behavs = new HashMap<String,StrategyBehavior>();
		strategy_behavs.put("A",new TitForTatStrategyBehaviour(new HeavisideProbabilityCalculator(0)));
		strategy_behavs.put("B",new WinStayLoaseShiftStrategyBehaviour(new HeavisideProbabilityCalculator(0)));
		strategy_behavs.put("AB",new TitForTatStrategyBehaviour(new HeavisideProbabilityCalculator(-1)));
		strategy_behavs.put("BB",new WinStayLoaseShiftStrategyBehaviour(new HeavisideProbabilityCalculator(10000.0)));
		strategy_behavs.put("AS",new TitForTatStrategyBehaviour(new SigmoidProbabilityCalculator()));
		strategy_behavs.put("BS",new WinStayLoaseShiftStrategyBehaviour(new SigmoidProbabilityCalculator()));
		strategy_behavs.put("ABS",new TitForTatStrategyBehaviour(new SigmoidProbabilityCalculator(1.0,-0.5)));
		strategy_behavs.put("BBS",new WinStayLoaseShiftStrategyBehaviour(new SigmoidProbabilityCalculator(1.0,-1.0)));
		strategy_behavs.put("C",new CooperatorAlwaysStrategyBehaviour(new HeavisideProbabilityCalculator(-1)));
		strategy_behavs.put("D",new CooperatorNeverStrategyBehaviour(new HeavisideProbabilityCalculator(-1)));
		oh = new OptionMultiClass<StrategyBehavior>("S","The strategy for each node. Strategy plays a crucial role in that it determines whether a node becomes a cooperator or deflector",strategy_behavs.get("A"),"A",strategy_behavs);
		options.put("S",oh);
		HashMap<String,TrafficManager> tfs = new HashMap<String,TrafficManager>();
		tfs.put("uniform",new TrafficManager());
		tfs.put("center",new CenterReceiverTrafficManager());
		
		oh = new OptionMultiClass<TrafficManager>("TL","Determines the pattern of which nodes send to whom in each time step.Trafic load",tfs.get("uniform"),"uniform",tfs);
		options.put("TL",oh);
		HashMap<String,StrategyAdaptation> sas = new HashMap<String,StrategyAdaptation>();
		sas.put("synch",new SynchronousStrategyAdaptation());
		sas.put("asynch",new AsynchronousStrategyAdaptation());
		oh = new OptionMultiClass<StrategyAdaptation>("SA","Detemrines when and what nodes are picked to adapt their strategy",sas.get("synch"),"synch",sas);
		options.put("SA",oh);
		HashMap<String,EnergyDistribution> ens = new HashMap<String,EnergyDistribution>();
		ens.put("closest",new ClosestAllEnergyDistribution());
		ens.put("dfair",new DistributedFairEnergyDistribution());
		ens.put("even",new EvenlyDistributedEnergyDistribution());
		ens.put("minimal",new MinimalEnergySpentEnergyDistribution());
		//ens.put("dfair",new DistributedFairEnergyConsumption()); don's use this anymore
		oh = new OptionMultiClass<EnergyDistribution>("EC","Determines when and what nodes are picked to adapt their strategy",ens.get("closest"),"closest",ens);
		options.put("EC",oh);
		oh = new OptionLong("D","The duration of the simulation in time steps",100000000L);
		options.put("D",oh);
		oh = new OptionInteger("SHOW","Do you want to show the plots 1=Y, 0=N",1);
		options.put("SHOW",oh);
		oh = new OptionInteger("PAUSED","Do you want to start odma 1=Y, 0=N",1);
		options.put("PAUSED",oh);
		oh = new OptionInteger("COOP","How many nodes start as cooperators",0);
		options.put("COOP",oh);
	}

	
	public static void main(String[] args) throws Exception {
		if ( args[0].equals("-help") ) {
			System.out.println(AbstractOptionHandler.getHelpHeader());
			for ( OptionHandler<?> oh : options.values() ) {
				System.out.println(oh);
			}
			return;
		}
		NodeFactory nf = new NodeFactory();
		FitnessCalculator fc = (FitnessCalculator) options.get("F").getValue(args);
		StrategyBehavior sb = (StrategyBehavior) options.get("S").getValue(args);
		sb = sb.copy();
		DeltaFitnessCalculator dfc = (DeltaFitnessCalculator) options.get("DF").getValue(args);
		boolean buffers_full = ((int) options.get("FULL").getValue(args)) == 1;
		sb.setDfc(dfc);
		int N = (int) options.get("N").getValue(args);
		double a = (double) options.get("a").getValue(args);
		WirelessNodeMap wnm = new WirelessNodeMap(N, a, nf);
		List<Node> nodes = wnm.getNodes();
		double p = (double) options.get("p").getValue(args);
		TrafficManager tm = (TrafficManager) options.get("TL").getValue(args);
		tm = tm.copy();
		tm.setNodes(nodes);
		double alpha = (double) options.get("alpha").getValue(args);
		double ni = (double) options.get("ni").getValue(args);
		StrategyAdaptation sa = (StrategyAdaptation) options.get("SA").getValue(args);
		double T = (double) options.get("T").getValue(args);
		sa = sa.copy();
		sa.setT(T);
		sa.setNodes(nodes);
		EnergyDistribution ec = (EnergyDistribution) options.get("EC").getValue(args);
		ec = ec.copy();
		ec.setAlpha(alpha);
		ec.setNi(ni);
		int show = (int) options.get("SHOW").getValue(args);
		SimulationView sv = null;
		if ( show == 1 ) {
			sv = new SimulationView();
		}
		SimulationDirector sd = new SimulationDirector(p, wnm, tm,ec, sa,sv);
		int coop = (int) options.get("COOP").getValue(args);
		double coop_dist_from_center = 0.0;
		
		
		for ( int i = 0 ; i < coop ; ++i ) {
			while ( true ) {
				int idx = (int) (Math.random()*sd.getWirelessNodeMap().getNodes().size());
//				int idx = 1;
				Node n = sd.getWirelessNodeMap().getNodes().get(idx);
				if ( n.isCooperator() ) continue;
				coop_dist_from_center = Utils.dist(n.getNodeIden().getX(), n.getNodeIden().getY(),a/2,a/2);
				n.setCooperator(true); break;
			}
		}
		
		if ( show == 1 ) {
			sv.setSD(sd);
			sv.show();
			sv.setVisible(true);
		}
		long simulation_length = (long) options.get("D").getValue(args);
		int paused = (int) options.get("PAUSED").getValue(args);
		if ( paused == 0 ) {
			sd.resume();
			sd.setSpeed(0);
			if ( sv != null )
				sv.setPaused(false);
		}
		else {
			sd.setSpeed(100);
			if ( sv != null )
				sv.setSpeed(100);
		}
		StringBuilder info = new StringBuilder();
		
		info.append("strat:").append(sb.toString()).append("\n");
		
		info.append("Fitness:").append(fc.toString()).append("\n");
		info.append("Delta Fitness:").append(dfc.toString()).append("\n");
		
		info.append("alpha:").append(alpha).append("\n");
		
		info.append("ni:").append(ni).append("\n");
		
		info.append("ec:").append(ec.toString()).append("\n");
//		info.append("coop_dist:").append(coop_dist_from_center+"");
//	    sd.addSimulationStat(new TotalSpentEnergySimulationStat(out_energy,info.toString()));
//    	sd.addSimulationStat(new AveragePercentCooperatorsSpentSimulationStat(out_coop, info.toString(), 100));
//		sd.addSimulationStat(new SimulationRunningTime(out_time, "number of nodes: "+N));
		sd.addSimulationStat(new NodeEnergySimulationStat(out_nodes, info.toString()+"\nN:"+N));
//		sd.addSimulationStat(new NodeCooperationStat(out_nodes, info.toString()+"\nN:"+N));
		sd.runSimulation(simulation_length);
	}
	
	
	
	
	 a = 100
	 
	 alpha = 2, C = 30000000 
	 alpha = 3, C = 500000000 
	 alpha = 4, C = 10000000000
	 
	

	*/
	public static PrintWriter out_coop;
	public static PrintWriter out_energy;
	public static PrintWriter out_time;
	public static PrintWriter out_nodes;
}
