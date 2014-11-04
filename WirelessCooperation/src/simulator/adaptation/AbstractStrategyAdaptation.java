package simulator.adaptation;

import java.util.List;

import simulator.Node;

public abstract class AbstractStrategyAdaptation implements StrategyAdaptation {
	
	protected long last_update = -1;
	
	@Override
	public final List<Node> getNodesToAdaptStrategy(List<Node> nodes,long current_step) {
		if ( last_update == -1 )
			last_update = current_step;
		List<Node> res = getNodesToAdaptStrategyInternal(nodes,current_step);
		if ( res != null && res.size() > 0 ) {
			last_update = current_step;
		}
		return res;
	}

	protected abstract List<Node> getNodesToAdaptStrategyInternal(List<Node> nodes,long current_step);
	
}
