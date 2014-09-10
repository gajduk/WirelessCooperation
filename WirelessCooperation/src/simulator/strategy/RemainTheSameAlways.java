package simulator.strategy;


public class RemainTheSameAlways extends AbstractStrategyBehaviour {

	public RemainTheSameAlways() {
		super(null, null);
	}

	@Override
	protected boolean toCooperateOrNotToCooperateInternal(
			boolean is_cooperator, boolean decision) {
		return is_cooperator;
	}

}
