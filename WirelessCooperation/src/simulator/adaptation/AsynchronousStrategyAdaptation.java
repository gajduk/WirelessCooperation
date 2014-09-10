package simulator.adaptation;

import java.util.LinkedList;
import java.util.List;

import simulator.Node;

public class AsynchronousStrategyAdaptation extends AbstractStrategyAdaptation {

	/**
	 * frequency the same as probability this should be some really small number e.g. .00001 
	 */
	private double frequency;
	
	public AsynchronousStrategyAdaptation(double frequency) {
		super();
		this.frequency = frequency;
	}


	@Override
	protected List<Node> getNodesToAdaptStrategyInternal(List<Node> nodes,long current_step) {
		List<Node> res = new LinkedList<Node>();
		for ( Node n : nodes ) {
			if ( Math.random() < frequency ) res.add(n);
		}
		return res;
	}

}
