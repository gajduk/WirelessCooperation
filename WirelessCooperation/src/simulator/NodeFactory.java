package simulator;

import java.util.Random;

import simulator.fitness.FitnessCalculator;
import simulator.fitness.SimpleDeltaFitnessCalculator;
import simulator.fitness.SimpleFitnessCalculator;
import simulator.strategy.BStrategyBehaviour;
import simulator.strategy.StrategyBehavior;

public class NodeFactory {
	
	private Random random = new Random();
	
	public void setRandom(Random rnd) {
		random = rnd;
	}
	
	private StrategyBehavior sb = new BStrategyBehaviour(new SimpleDeltaFitnessCalculator());;
	private FitnessCalculator fc = new SimpleFitnessCalculator();
	private int remember_last_fitness_values = 2;
	
	public NodeFactory() {
		random = new Random();
	}
			
	public NodeFactory(StrategyBehavior sb, FitnessCalculator fc,
			int remember_last_fitness_values) {
		super();
		this.sb = sb;
		this.fc = fc;
		this.remember_last_fitness_values = remember_last_fitness_values;
		random = new Random();
	}

	public StrategyBehavior getSb() {
		return sb;
	}

	public void setSb(StrategyBehavior sb) {
		this.sb = sb;
	}

	public FitnessCalculator getFc() {
		return fc;
	}

	public void setFc(FitnessCalculator fc) {
		this.fc = fc;
	}
	
	public int getRemember_last_fitness_values() {
		return remember_last_fitness_values;
	}

	public void setRemember_last_fitness_values(int remember_last_fitness_values) {
		this.remember_last_fitness_values = remember_last_fitness_values;
	}

	public Node generateNodeWithRandomLocation(double a, int i) {
		double x = random.nextDouble()*a;
		double y = random.nextDouble()*a;
		Node res = new Node(x, y, i, remember_last_fitness_values, false, sb, fc);
		return res;
	}
	
	public Node generateNodeWithLocation(double x,double y, int i) {
		Node res = new Node(x, y, i, remember_last_fitness_values, false, sb, fc);
		return res;
	}
	
}
