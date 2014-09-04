package simulator.strategy;

import simulator.Node;
import simulator.fitness.DeltaFitnessCalculator;

/**
 * WIN_STAY LOSE_SHIFT
 * Simple strategy behavior.
 * The idea is very natural (evolution):
 * 	if the fitness calculator declares a positive change in fitness then the node retains its state
 * 		i.e. it remains a defector if previously was a defector and remains a cooperator if previously was a cooperator
 *  if there was a megative change in fitness the node ({@link Node}) becomes changes its state from a cooperator to a defector and vice vers
 * This strategy is supposed to mimic the greedy "only I am important" driving force of evolution
 *  if some other node was cooperator and helped me during the last iteration. I am a good guy and I will return the favor 
 * this iteration by being a cooperator and by helping others.
 * @author Andrej Gajduk
 *
 */
public class BStrategyBehaviour extends AbstractStrategyBehaviour {
	
	public BStrategyBehaviour() {
		
	}

	public BStrategyBehaviour(DeltaFitnessCalculator dfc) {
		super(dfc);
	}
	
	public BStrategyBehaviour(ProbabilityCalculator pc) {
		super(pc);
	}
	
	public BStrategyBehaviour(DeltaFitnessCalculator dfc,ProbabilityCalculator pc) {
		super(dfc,pc);
	}
		
	@Override
	protected boolean toCooperateOrNotToCooperateInternal(
			boolean is_cooperator, boolean decision) {
		if ( decision ) return is_cooperator;
		return !is_cooperator;
	}

	@Override
	public StrategyBehavior copy() {
		return new BStrategyBehaviour(dfc,pc);
	}
	
	@Override
	public String toString() {
		return "B";
	}

}
