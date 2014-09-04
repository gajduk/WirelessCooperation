package simulator;

import java.util.List;
import java.util.Random;

class Pair<T> {
	public T a,b;
	public Pair(T aa,T bb) {
		a = aa; b = bb;
	}
}

public class TrafficManager {
	
	public Node[] nodes;
	Random random;
	
	public TrafficManager(List<Node> nodes) {
		setNodes(nodes);
	}
	
	public TrafficManager() {
		
	}
	
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes.toArray(new Node[nodes.size()]);
	}

	public Pair<Node> chooseConnectionpair() throws Exception {
		if ( nodes == null || nodes.length <= 1 ) throw new Exception("Traffic manager can't choose a connection pair because it doesn't know enough nodes");
		int sender = (int)(random.nextDouble()*nodes.length);
		int recv = sender;
		while ( recv == sender ) 
			recv = (int)(random.nextDouble()*nodes.length);
		return new Pair<Node>(nodes[sender],nodes[recv]);
	}

	public TrafficManager copy() {
		return new TrafficManager();
	}

	public void setRandom(Random rnd) {
		random = rnd;
	}
	
}
