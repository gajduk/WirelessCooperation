package simulator.adaptation;

import java.util.Collections;
import java.util.List;
import simulator.Node;

public class SynchronousStrategyAdaptation extends AbstractStrategyAdaptation {

	private double T;
	
	public SynchronousStrategyAdaptation(List<Node> nodes,double T) {
		super(nodes);
		setT(T);
	}
	
	public SynchronousStrategyAdaptation() {
		
	}

	@Override
	protected List<Node> getNodesToAdaptStrategyInternal(long current_step) {
		if ( time_since_last_node_updated > T ) {
			return nodes;
		}
		return Collections.emptyList();
	}

	@Override
	public void setT(double T) {
		this.T = T;
	}
	
	@Override
	public StrategyAdaptation copy() {
		return new SynchronousStrategyAdaptation();
	}

}
