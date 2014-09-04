package simulator.strategy;

public class RemainTheSameAlways extends AbstractStrategyBehaviour {

	@Override
	protected boolean toCooperateOrNotToCooperateInternal(
			boolean is_cooperator, boolean decision) {
		return is_cooperator;
	}

	@Override
	public StrategyBehavior copy() {
		return this;
	}

}
