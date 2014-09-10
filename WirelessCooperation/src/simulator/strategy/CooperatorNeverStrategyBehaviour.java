package simulator.strategy;

import simulator.fitness.FittnessMemory;

/**
 * In this strategy node changes to defector. Use it to asses border cases (when all the nodes are cooperators)
 * @author Andrej Gajduk
 *
 */
public class CooperatorNeverStrategyBehaviour extends AbstractStrategyBehaviour {

	public CooperatorNeverStrategyBehaviour() {
		super(null,null);
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
	public String toString() {
		return "D";
	}
	
}
