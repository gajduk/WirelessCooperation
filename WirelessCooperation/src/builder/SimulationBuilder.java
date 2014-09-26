package builder;

import gui.SimulationView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import simulator.Architecture;
import simulator.Mobility;
import simulator.Node;
import simulator.NodeIdentity;
import simulator.SimulationDirector;
import simulator.WirelessNodeMap;
import simulator.adaptation.SynchronousStrategyAdaptation;
import simulator.energy_distribution.ClosestAllEnergyDistribution;
import simulator.energy_distribution.EnergyDistribution;
import simulator.fitness.FitnessCalculator;
import simulator.fitness.ImprovedFitnessCalculator;
import simulator.statistics.SimulationStat;
import simulator.strategy.Strategies;

public class SimulationBuilder {
	
	FitnessCalculator fc = new ImprovedFitnessCalculator();
	Strategies sb = Strategies.TitForTat;
	double a = 100.0d;
	double p = 1.0d;
	int N = 30;
	long T = 1000L;
	EnergyDistribution ed = new ClosestAllEnergyDistribution(0.39d,3.0d);
	boolean gui = true;
	boolean paused = true;
	int coop  = 0;
	Architecture arc = Architecture.AdHoc;
	Mobility mob = Mobility.RandomWaypoint;
	List<SimulationStat> simulation_stats = new ArrayList<>();
	
	public SimulationBuilder withSimulationStat(SimulationStat ss) {
		simulation_stats.add(ss);
		return this;
	}
	
	public SimulationBuilder withoutSimulationStats() {
		simulation_stats.clear();
		return this;
	}
	
	public SimulationBuilder withArchitecture(Architecture arc) {
		this.arc = arc;
		return this;
	}
	
	public SimulationBuilder withCoop(int coop) {
		this.coop = coop;
		return this;
	}

	public SimulationBuilder withN(int N) {
		this.N = N;
		return this;
	}
	
	public SimulationBuilder withStrategy(Strategies s) {
		this.sb = s;
		return this;
	}
	
	public SimulationBuilder withMobility(Mobility m) {
		this.mob = m;
		return this;
	}
	
	public SimulationBuilder withT(long T) {
		this.T = T;
		return this;
	}
	
	public SimulationBuilder running() {
		this.paused = false;
		return this;
	}
	
	public SimulationBuilder hidden() {
		this.gui = false;
		return this;
	}
	
	@SuppressWarnings("deprecation")
	public SimulationDirector build() {
		List<NodeIdentity> node_idens = arc.generateNodes(a, N);
		List<Node> nodes = new ArrayList<Node>(node_idens.stream().map(node_iden -> new Node(node_iden,2,false,sb,fc)).collect(Collectors.toList()));
		WirelessNodeMap wnm = new WirelessNodeMap(a,nodes);
		SimulationView sv = null;
		if ( gui ) {
			sv = new SimulationView();
		}
		SimulationDirector sd = new SimulationDirector(T, p, wnm, arc.getTrafficManager(), ed, new SynchronousStrategyAdaptation(T),sv,mob);
		simulation_stats.forEach(s -> sd.addSimulationStat(s));
		
		for ( int i = 0 ; i < coop ; ++i ) {
			while ( true ) {
				int idx = (int) (Math.random()*sd.getWirelessNodeMap().getNodes().size());
				Node n = sd.getWirelessNodeMap().getNodes().get(idx);
				if ( n.isCooperator() ) continue;
				n.setCooperator(true); break;
			}
		}
		
		if ( gui ) {
			sv.setSD(sd);
			sv.show();
			sv.setVisible(true);
		}
		if ( !paused ) {
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
		return sd;
	}

	public Strategies getStrategy() {
		return sb;
	}

	public Mobility getMobility() {
		return mob;
	}

	public Architecture getArchitecture() {
		return arc;
	}

	public int getN() {
		return N;
	}

	public SimulationBuilder withAlpha(Double alpha) {
		this.ed.setAlpha(alpha);
		return this;
	}

	public double getAlpha() {
		return ed.getAlpha();
	}

}
