package simulator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import utils.Utils;

public class PossibleCooperatorsCache {
	
	private HashMap<Node,HashMap<Node,List<Node>>> cache;
	
	public PossibleCooperatorsCache(List<Node> nodes) {
		cache = new HashMap<Node,HashMap<Node,List<Node>>>();
		for ( int i= 0 ; i < nodes.size() ; ++i ) {
			Node a = nodes.get(i);
			cache.put(a,new HashMap<Node,List<Node>>());
			for ( int k = i+1 ; k < nodes.size() ; ++k ) {
				Node b = nodes.get(k);
				cache.get(a).put(b, new LinkedList<Node>());
				double distab = Utils.distSqr(a,b);
				for ( Node n : nodes ) {
					
				}
			}
		}
	}
	
	
	
	public List<Node> getPossibleCooperators(Node send,Node recv) {
		if ( send.getIdx() < recv.getIdx() )
			return  cache.get(send).get(recv);
		else
			return cache.get(recv).get(send);
	}
	
	

}
