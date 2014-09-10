package simulator;

import java.util.List;

public class CenterReceiverTrafficManager extends TrafficManager {
	
	public Pair<Node> chooseConnectionpair(List<Node> nodes) throws Exception {
		if ( nodes == null || nodes.size() <= 1 ) throw new Exception("Traffic manager can't choose a connection pair because it doesn't know enough nodes");
		int sender = (int)(Math.random()*(nodes.size()-1))+1;
		int recv = 0;
		return new Pair<Node>(nodes.get(sender),nodes.get(recv));
	}

}
