package utils;

import javafx.geometry.Point2D;
import java.util.List;
import java.util.Random;

import simulator.Node;

public class Utils {
	
	static Random rnd = new Random();
	
	public static double distSqr(double x1,double y1,double x2,double y2) {
		double dx = x1-x2;
		double dy = y1-y2;
		return dx*dx+dy*dy;
	}
	
	public static double dist(double x1,double y1,double x2,double y2) {
		return Math.sqrt(distSqr(x1,y1,x2,y2));
	}
	
	public static double distSqr(Node a,Node b) {
		return distSqr(a.getNodeIden().getX(),a.getNodeIden().getY(),b.getNodeIden().getX(),b.getNodeIden().getY());
	}
	
	public static double dist(Node a,Node b) {
		return Math.sqrt(distSqr(a,b));
	}
	
	public static boolean doubleEqual(double a,double b,double tolerance) {
		return Math.abs(a-b)<tolerance;
	}
	
	public static boolean doubleEqual(double a,double b) {
		return doubleEqual(a,b,Math.max(.000001,a/100));
	}
	
	public static double percentCooperators(List<Node> nodes) {
		double res = 0;
		for ( Node n : nodes ) {
			res += n.isCooperator()?1.0:0.0;
		}
		return res/nodes.size();
	}

	public static double totalEnerrgySpent(List<Node> nodes) {
		double res = 0;
		for ( Node n : nodes ) {
			res += n.getEs().getTotal_spent_energy();
		}
		return res;
	}
	
	public static boolean isPossibleCooperator(Node sender,Node receiver, Node intermidiate) {
		return isPossibleCooperator(sender,receiver,intermidiate,Utils.distSqr(sender, receiver));
	}
	
	public static boolean isPossibleCooperator(Node sender,Node receiver, Node intermidiate,double distab) {
		if ( intermidiate.getNodeIden().getIdx() != sender.getNodeIden().getIdx() &&
			 intermidiate.getNodeIden().getIdx() != receiver.getNodeIden().getIdx() ) {
			double distan = Utils.distSqr(sender, intermidiate);
			if ( distan <= distab ) {
				double distbn = Utils.distSqr(receiver, intermidiate);
				if ( distbn < distab ) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static Point2D generateRandomPoint2DInsideCricle(double a) {
		while ( true ) {
			double x = rnd.nextDouble();
			double y = rnd.nextDouble();
			if ( distSqr(x,y,a/2,a/2) < a*a/4 ) return new Point2D(x, y);
		}
	}

}
