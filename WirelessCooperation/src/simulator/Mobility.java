package simulator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Pair;
import utils.Utils;


public enum Mobility {
	None {
		@Override
		public
		void updateLocations(List<Node> nodes, double a) {
			//DO NTH
		}

		@Override
		public void init() {
			//DO NTH
		}
	},RandomWaypoint {
		

		class Waypoint {
			
			double target_x;
			double target_y;
			double velocity_x;
			double velocity_y;
			
			int steps_to_get_there;
		
			public Waypoint(double target_x, double target_y, double velocity_x,
					double velocity_y, int steps_to_get_there) {
				super();
				this.target_x = target_x;
				this.target_y = target_y;
				this.velocity_x = velocity_x;
				this.velocity_y = velocity_y;
				this.steps_to_get_there = steps_to_get_there;
			}
		
		
		}
		
		Map<Integer,Waypoint> waypoints;
				
		@Override
		public
		void updateLocations(List<Node> nodes, double a) {
			for ( Node n : nodes ) {
				double x = n.getNodeIden().getX();
				double y = n.getNodeIden().getY();
				int idx = n.getNodeIden().getIdx();
				Waypoint waypoint = waypoints.get(idx);
				if ( waypoint == null ) {
					Pair<Double,Double> target = Utils.generateRandomPoint2DInsideCricle(a);
					int stept_to_get_there = (int)((Math.random()*300)+100);
					double dx = x-target.a;
					dx /= stept_to_get_there;
					double dy = y-target.b;
					dy /= stept_to_get_there;
					waypoint = new Waypoint(target.a,target.b,dx,dy,stept_to_get_there);
					waypoints.put(idx, waypoint);
				}
				double start_dist = Utils.distSqr(waypoint.target_x, waypoint.target_y, x, y);
				n.getNodeIden().setX(x-waypoint.velocity_x);
				n.getNodeIden().setY(y-waypoint.velocity_y);
				x = n.getNodeIden().getX();
				y = n.getNodeIden().getY();
				double end_dist = Utils.distSqr(waypoint.target_x, waypoint.target_y, x, y);
				if ( start_dist < end_dist ) {
					System.out.println(idx);
				}
				--waypoint.steps_to_get_there;
				if ( waypoint.steps_to_get_there == 0 ) 
					waypoints.remove(idx);
			}
		}

		@Override
		public void init() {
			waypoints = new HashMap<Integer,Waypoint>();
		}
	};
	
	public abstract void updateLocations(List<Node> nodes,double a);
	
	public abstract void init();
	
}
