package simulator.adaptation;

import java.util.List;

import simulator.Node;

public interface StrategyAdaptation {
	
	public abstract List<Node> getNodesToAdaptStrategy(List<Node> nodes,long current_step);

}
