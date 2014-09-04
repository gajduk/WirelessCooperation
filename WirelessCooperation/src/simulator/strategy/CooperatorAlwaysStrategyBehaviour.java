package simulator.strategy;


/**
 * In this strategy node changes to cooperator. Use it to asses border cases (when all the nodes are cooperators)
 * @author Andrej Gajduk
 *
 */
public class CooperatorAlwaysStrategyBehaviour extends
		AbstractStrategyBehaviour {

	public CooperatorAlwaysStrategyBehaviour(
			ProbabilityCalculator pc) {
		setPc(pc);
	}

	@Override
	protected boolean toCooperateOrNotToCooperateInternal(
			boolean is_cooperator, boolean decision) {
		return true;
	}

	@Override
	public StrategyBehavior copy() {
		return this;
	}

	@Override
	public String toString() {
		return "C";
	}
}
