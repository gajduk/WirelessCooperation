package simulator.adaptation;

import java.util.List;

import simulator.Node;

public interface StrategyAdaptation {
	
	public abstract List<Node> getNodesToAdaptStrategy(long current_step);
	
	public abstract void setT(double T);
	
	public abstract void setNodes(List<Node> nodes);

	public abstract StrategyAdaptation copy();

}
