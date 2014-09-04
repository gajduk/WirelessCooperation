package simulator;

public class CenterReciverTrafficManager extends TrafficManager {
	
	public Pair<Node> chooseConnectionpair() throws Exception {
		if ( nodes == null || nodes.length <= 1 ) throw new Exception("Traffic manager can't choose a connection pair because it doesn't know enough nodes");
		int sender = (int)(random.nextDouble()*(nodes.length-1))+1;
		int recv = 0;
		return new Pair<Node>(nodes[sender],nodes[recv]);
	}

}
