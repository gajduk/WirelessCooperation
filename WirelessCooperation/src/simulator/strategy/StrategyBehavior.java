package simulator.strategy;

import simulator.fitness.DeltaFitnessCalculator;
import simulator.fitness.FittnessMemory;

/**
 * An interface that all strategy behaviors must implement.
 * The most important method is toCooperateOrNotToCooperate: which directs a node to be a cooperator or defector
 * There are countless possible strategy behaviors, depending on the current behaviors of the node and the memory of the node.
 * The strategy behavior is coupled with the {@link ProbabilityCalculator} which calculates the probability that a change in the system has happened
 * Additionally the StrategyBehaviour is coupled with the {@link DeltaFitnessCalculator} which gives the definition of delta fitness or the change in fitness
 * 
 * @author Andrej Gajduk
 *
 */
public interface StrategyBehavior {
	
	/**
	 * Method used to direct the behavior of nodes, i.e. whether they are defectors or cooperators
	 * @param is_cooperator - the current behavior of the node
	 * @param fm - the fitness memory of the node {@link FittnessMemory} (used by the delta fitness calculator)
	 * @return true if the node is to be cooperator in the next time step, false if it is to be defector
	 */
	public abstract boolean toCooperateOrNotToCooperate(boolean is_cooperator,FittnessMemory fm);
		
	public DeltaFitnessCalculator getDfc();
	
	public ProbabilityCalculator getPc();
	
}
