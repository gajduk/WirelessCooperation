package simulator;

import java.util.Random;

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
