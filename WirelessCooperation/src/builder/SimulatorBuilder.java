package builder;

import gui.SimulationView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import simulator.Node;
import simulator.NodeIdentity;
import simulator.SimulationDirector;
import simulator.TrafficManager;
import simulator.WirelessNodeMap;
import simulator.adaptation.StrategyAdaptation;
import simulator.adaptation.SynchronousStrategyAdaptation;
import simulator.energy_distribution.ClosestAllEnergyDistribution;
import simulator.energy_distribution.EnergyDistribution;
import simulator.fitness.FitnessCalculator;
import simulator.fitness.SimpleDeltaFitnessCalculator;
import simulator.fitness.SimpleFitnessCalculator;
import simulator.strategy.HeavisideProbabilityCalculator;
import simulator.strategy.StrategyBehavior;
import simulator.strategy.TitForTatStrategyBehaviour;

public class SimulatorBuilder {
	
	FitnessCalculator fc = new SimpleFitnessCalculator();
	StrategyBehavior sb = new TitForTatStrategyBehaviour(new SimpleDeltaFitnessCalculator(), new HeavisideProbabilityCalculator(0));
	double a = 1.0d;
	long t = 999;
	double p = 1.0d;
	int N = 50;
	TrafficManager tm = new TrafficManager();
	StrategyAdaptation sa = new SynchronousStrategyAdaptation(1000);
	EnergyDistribution ed = new ClosestAllEnergyDistribution(0.5d,2.0d);
	boolean gui = true;
	boolean paused = true;
	int coop  = 0;
	Architecture arc = Architecture.AdHoc;
	Mobility mob = Mobility.None;

	
	
	public SimulationDirector build() {
		List<NodeIdentity> node_idens = arc.generateNodes(a, N);
		List<Node> nodes = new ArrayList<Node>(node_idens.stream().map(node_iden -> new Node(node_iden,2,false,sb,fc)).collect(Collectors.toList()));
		WirelessNodeMap wnm = new WirelessNodeMap(a,nodes);
		SimulationView sv = null;
		if ( gui ) {
			sv = new SimulationView();
		}
		SimulationDirector sd = new SimulationDirector(p, wnm, tm, ed, sa,sv);
	
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
		if ( paused ) {
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

}
