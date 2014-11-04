package simulator.adaptation;

import java.util.Collections;
import java.util.List;
import simulator.Node;

public class SynchronousStrategyAdaptation extends AbstractStrategyAdaptation {

	private double T;
	
	public SynchronousStrategyAdaptation(double T) {
		this.T = T;
	}
	
	@Override
	protected List<Node> getNodesToAdaptStrategyInternal(List<Node> nodes,long current_step) {
		if ( last_update-current_step >= T ) {
			return nodes;
		}
		return Collections.emptyList();
	}

}
