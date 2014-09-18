package simulator;

import java.util.Random;

import utils.Utils;

public class NodeFactory {
	
		
	public NodeFactory() {
	}

	public NodeIdentity generateNodeWithRandomLocation(double a, int i) {
		utils.Pair<Double, Double> p = Utils.generateRandomPoint2DInsideCricle(a);
		double x = p.a;
		double y = p.b;
		return new NodeIdentity(x, y, i);
	}
	
}
