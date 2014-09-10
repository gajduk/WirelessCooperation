package simulator.adaptation;

import java.util.List;

import simulator.Node;

public abstract class AbstractStrategyAdaptation implements StrategyAdaptation {
	
	protected int last_update = 0;
	
	@Override
	public final List<Node> getNodesToAdaptStrategy(List<Node> nodes,long current_step) {
		List<Node> res = getNodesToAdaptStrategyInternal(nodes,current_step);
		if ( res != null && res.size() > current_step ) {
			last_update = 0;
		}
		return res;
	}

	protected abstract List<Node> getNodesToAdaptStrategyInternal(List<Node> nodes,long current_step);
	
}
