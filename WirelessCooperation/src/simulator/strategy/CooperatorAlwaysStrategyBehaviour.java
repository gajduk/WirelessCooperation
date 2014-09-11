package simulator.strategy;

import simulator.fitness.FittnessMemory;


/**
 * In this strategy node changes to cooperator. Use it to asses border cases (when all the nodes are cooperators)
 * @author Andrej Gajduk
 *
 */
public class CooperatorAlwaysStrategyBehaviour extends
		AbstractStrategyBehaviour {

	public CooperatorAlwaysStrategyBehaviour() {
		super(null,null);
	}
	
	@Override
	public boolean toCooperateOrNotToCooperate(boolean is_cooperator,
			FittnessMemory fm) {
		return true;
	}

	@Override
	protected boolean toCooperateOrNotToCooperateInternal(
			boolean is_cooperator, boolean decision) {
		//NOT SUPPOSED TO BBE INVOKED
		return true;
	}

	@Override
	public String toString() {
		return "C";
	}
}
