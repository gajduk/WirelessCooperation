package simulator;

import java.util.Random;

import simulator.fitness.FitnessCalculator;
import simulator.fitness.SimpleDeltaFitnessCalculator;
import simulator.fitness.SimpleFitnessCalculator;
import simulator.strategy.WinStayLoaseShiftStrategyBehaviour;
import simulator.strategy.StrategyBehavior;

public class NodeFactory {
	
	private Random random = new Random();
	
	public NodeFactory() {
		random = new Random();
	}

	public NodeFactory(Random random) {
		super();
		this.random = random;
	}

	public NodeIdentity generateNodeWithRandomLocation(double a, int i) {
		double x = random.nextDouble()*a;
		double y = random.nextDouble()*a;
		return new NodeIdentity(x, y, i);
	}
	
}
