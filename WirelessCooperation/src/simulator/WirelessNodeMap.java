package simulator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import utils.Utils;

public class WirelessNodeMap {
	
	//all the nodes present on the map
	private List<Node> nodes;
	//the dimensions of the map (the map is assumed to be a square grid)
	private double a;
	//the total number of nodes on the map
	private int N;
	//the factory used to generate the nodes
	NodeFactory nf;
	//cache for the available cooperators 
	private PossibleCooperatorsCache cache;
	
	public WirelessNodeMap(int N,double a,NodeFactory nf) {
		this.N = N;
		this.a = a;
		this.nf = nf;
		nodes = new ArrayList<Node>();
		generateRandomNodes();
	//	cache = new PossibleCooperatorsCache(nodes);
	}

	private void generateRandomNodes() {
		Node center_antena = nf.generateNodeWithLocation(a/2,a/2,0);
		nodes.add(center_antena);
		double rad = 259.0*50.0/1000.0;
		double fi = Math.random()*Math.PI*2.0;
		double x = rad*Math.cos(fi);
		double y = rad*Math.sin(fi);
		Node coop = nf.generateNodeWithLocation(a/2+x,a/2+y,1);
		nodes.add(coop);
//		nf.setRandom(new Random(65431658435135L));
		nf.setRandom(new Random());
		for ( int i = 2 ; i < N ; ++i ) {
			Node n = null;
			while ( true ) {
				n = nf.generateNodeWithRandomLocation(a,i);
				if ( Utils.dist(n.getX(), n.getY(),a/2,a/2) < a/2 )
					break;
			}
			nodes.add(n);
		}
	}

	public List<Node> getCooperatorsForConnection(Node a,Node b) {
		LinkedList<Node> res = new LinkedList<Node>();
		List<Node> possible_cooperators = getPossibleCooperatorsForConnection(a,b);
		for ( Node n : possible_cooperators ) 
			if ( n.isCooperator() ) res.add(n);
		return res;
	}

	private List<Node> getPossibleCooperatorsForConnection(Node a, Node b) {
		if ( cache != null )
			return cache.getPossibleCooperators(a, b);
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
