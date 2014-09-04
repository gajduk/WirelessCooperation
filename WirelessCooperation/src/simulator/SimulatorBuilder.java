package simulator;

import gui.SimulationView;

import java.util.List;

import simulator.adaptation.StrategyAdaptation;
import simulator.adaptation.SynchronousStrategyAdaptation;
import simulator.energy_distribution.ClosestAllEnergyDistribution;
import simulator.energy_distribution.EnergyDistribution;
import simulator.fitness.DeltaFitnessCalculator;
import simulator.fitness.FitnessCalculator;
import simulator.fitness.SimpleDeltaFitnessCalculator;
import simulator.fitness.SimpleFitnessCalculator;
import simulator.strategy.AStrategyBehaviour;
import simulator.strategy.HeavisideProbabilityCalculator;
import simulator.strategy.StrategyBehavior;

public class SimulatorBuilder {
	
	FitnessCalculator fc = new SimpleFitnessCalculator();
	StrategyBehavior sb = new AStrategyBehaviour(new HeavisideProbabilityCalculator(0));
	DeltaFitnessCalculator dfc = new SimpleDeltaFitnessCalculator();
	double a = 1.0d;
	long t = 999;
	double alpha = 2.0;
	double p = 1.0d;
	double ni = 0.5d;
	int N = 50;
	TrafficManager tm = new TrafficManager();
	StrategyAdaptation sa = new SynchronousStrategyAdaptation();
	EnergyDistribution ed = new ClosestAllEnergyDistribution();
	boolean gui = true;
	boolean paused = true;
	int coop  = 0;
	
	public SimulationDirector build() {
		NodeFactory nf = new NodeFactory();
		nf.setFc(fc);
		sb.setDfc(dfc);
		nf.setSb(sb);
		nf.setRemember_last_fitness_values(dfc.getMinimumNumberOfValuesForDeltaCalculation());
		WirelessNodeMap wnm = new WirelessNodeMap(N, a, nf);
		List<Node> nodes = wnm.getNodes();
		tm.setNodes(nodes);
		sa.setT(t);
		sa.setNodes(nodes);
		ed.setAlpha(alpha);
		ed.setNi(ni);
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
