package simulator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import utils.Utils;

public class WirelessNodeMap {
	
	//all the nodes present on the map
	private List<Node> nodes;
	//the size of the circle area
	private double a;
	
	public WirelessNodeMap(double a,List<Node> nodes) {
		this.a = a;
		this.nodes = new ArrayList<Node>(nodes);
	}

	public List<Node> getCooperatorsForConnection(Node a,Node b) {
		LinkedList<Node> res = new LinkedList<Node>();
		List<Node> possible_cooperators = getPossibleCooperatorsForConnection(a,b);
		for ( Node n : possible_cooperators ) 
			if ( n.isCooperator() ) res.add(n);
		return res;
	}

	private List<Node> getPossibleCooperatorsForConnection(Node a, Node b) {
		List<Node> res = new ArrayList<Node>(nodes.size());
		double distab = Utils.distSqr(a, b);
		for ( Node n : nodes ) {
			if ( Utils.isPossibleCooperator(a, b, n, distab))
				res.add(n);
		}
		return res;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public double geta() {
		return a;
	}
	
	
	
}
