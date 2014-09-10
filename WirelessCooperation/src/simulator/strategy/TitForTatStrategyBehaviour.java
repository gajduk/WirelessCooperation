package simulator.strategy;

import simulator.fitness.DeltaFitnessCalculator;

/**
 * TIT_FOR_TAT
 * Simple strategy behavior.
 * The basic idea is as follows:
 * 	if the fitness calculator declares a positive change in fitness then the node becomes cooperator
 *  else the node ({@link Node}) becomes defector
 * The motivation behind this reasoning is that a positive change in fitness can occur only 
 * if some other node was cooperator and helped me during the last iteration. I am a good guy and I will return the favor 
 * this iteration by being a cooperator and by helping others.
 * @author Andrej Gajduk
 *
 */
public class TitForTatStrategyBehaviour extends AbstractStrategyBehaviour {

	public TitForTatStrategyBehaviour(DeltaFitnessCalculator dfc,ProbabilityCalculator pc) {
		super(dfc,pc);
	}
	

	@Override
	protected boolean toCooperateOrNotToCooperateInternal(
			boolean is_cooperator, boolean decision) {
		if ( decision ) return true;
		return false;
	}

	@Override
	public String toString() {
		return "A";
	}
	
}
