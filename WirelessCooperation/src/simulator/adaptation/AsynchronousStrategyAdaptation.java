package simulator.adaptation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import simulator.Node;

public class AsynchronousStrategyAdaptation extends AbstractStrategyAdaptation {

	/**
	 * frequency the same as probability this should be some really small number e.g. .00001 
	 */
	private double frequency;
	
	public AsynchronousStrategyAdaptation(List<Node> nodes,double frequency) {
		super(nodes);
		this.frequency = frequency;
	}
	
	public AsynchronousStrategyAdaptation() {
		
	}

	@Override
	protected List<Node> getNodesToAdaptStrategyInternal(long current_step) {
		List<Node> res = new LinkedList<Node>();
		for ( Node n : nodes ) {
			if ( Math.random() < frequency ) res.add(n);
		}
		return res;
	}

	@Override
	public void setT(double T) {
		frequency = 1/T;
	}

	@Override
	public StrategyAdaptation copy() {
		return new AsynchronousStrategyAdaptation();
	}

}
