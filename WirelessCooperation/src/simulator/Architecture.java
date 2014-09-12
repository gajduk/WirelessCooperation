package simulator;

import java.util.ArrayList;
import java.util.List;

import utils.Utils;

public enum Architecture {
	AdHoc {
		@Override
		public List<NodeIdentity> generateNodes(double a,int N) {
			List<NodeIdentity> res = new ArrayList<>();
			NodeFactory nf = new NodeFactory();
			for ( int i = 1 ; i < N ; ++i ) {
				NodeIdentity n = null;
				while ( true ) {
					n = nf.generateNodeWithRandomLocation(a,i);
					if ( Utils.dist(n.getX(), n.getY(),a/2,a/2) < a/2 )
						break;
				}
				res.add(n);
			}
			return res;
		}

		@Override
		public TrafficManager getTrafficManager() {
			return new TrafficManager();
		}
	},CentralAntena {
		@Override
		public	List<NodeIdentity> generateNodes(double a, int N) {
			List<NodeIdentity> res = new ArrayList<>();
			res.add(new NodeIdentity(a/2,a/2, 0));
			NodeFactory nf = new NodeFactory();
			for ( int i = 2 ; i < N ; ++i ) {
				NodeIdentity n = null;
				while ( true ) {
					n = nf.generateNodeWithRandomLocation(a,i);
					if ( Utils.dist(n.getX(), n.getY(),a/2,a/2) < a/2 )
						break;
				}
				res.add(n);
			}
			return res;
		}

		@Override
		public TrafficManager getTrafficManager() {
			return new CenterReceiverTrafficManager();
		}
	};
	
	public abstract List<NodeIdentity> generateNodes(double a,int N);
	
	public abstract TrafficManager getTrafficManager();

}
