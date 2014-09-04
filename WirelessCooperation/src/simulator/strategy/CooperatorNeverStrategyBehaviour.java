package simulator.strategy;

import simulator.fitness.FittnessMemory;

/**
 * In this strategy node changes to defector. Use it to asses border cases (when all the nodes are cooperators)
 * @author Andrej Gajduk
 *
 */
public class CooperatorNeverStrategyBehaviour extends AbstractStrategyBehaviour {

	public CooperatorNeverStrategyBehaviour(
			ProbabilityCalculator pc) {
		setPc(pc);
	}

	@Override
	public boolean toCooperateOrNotToCooperate(boolean is_cooperator,
			FittnessMemory fm) {
		return false;
	}

	@Override
	protected boolean toCooperateOrNotToCooperateInternal(
			boolean is_cooperator, boolean decision) {
		return false;
	}

	@Override
	public StrategyBehavior copy() {
		return this;
	}

	@Override
	public String toString() {
		return "D";
	}
	
}
