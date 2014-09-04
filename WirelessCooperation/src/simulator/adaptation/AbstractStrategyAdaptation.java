package simulator.adaptation;

import java.util.ArrayList;
import java.util.List;

import simulator.Node;

public abstract class AbstractStrategyAdaptation implements StrategyAdaptation {
	
	protected List<Node> nodes;
	
	protected int adapt_count[];
	
	protected int time_since_beggining_of_simulation = 0;
	protected int time_since_last_node_updated = 0;
	
	public AbstractStrategyAdaptation() {
		
	}
	
	public void setNodes(List<Node> nodes) {
		this.nodes = new ArrayList<Node>(nodes);
		adapt_count = new int[nodes.size()];
	}
	
	public AbstractStrategyAdaptation(List<Node> nodes) {
		setNodes(nodes);
	}
	
	@Override
	public final List<Node> getNodesToAdaptStrategy(long current_step) {
		List<Node> res = getNodesToAdaptStrategyInternal(current_step);
		++time_since_beggining_of_simulation;
		++time_since_last_node_updated;
		if ( res != null && res.size() > 0 ) {
			time_since_last_node_updated = 0;
			for ( Node n : res ) {
				++adapt_count[n.getIdx()]; 
			}
		}
		return res;
	}

	protected abstract List<Node> getNodesToAdaptStrategyInternal(long current_step);

	
}
