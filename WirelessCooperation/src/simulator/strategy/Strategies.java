package simulator.strategy;

import simulator.fitness.DeltaFitnessCalculator;
import simulator.fitness.FittnessMemory;
import simulator.fitness.SimpleDeltaFitnessCalculator;

public enum Strategies implements StrategyBehavior {
	TitForTat(new TitForTatStrategyBehaviour(new SimpleDeltaFitnessCalculator(), new HeavisideProbabilityCalculator(0))),
	WinStayLoseShift(new WinStayLoseShiftStrategyBehaviour(new SimpleDeltaFitnessCalculator(), new HeavisideProbabilityCalculator(0))),
	Coop(new CooperatorAlwaysStrategyBehaviour()),
	Def(new CooperatorNeverStrategyBehaviour()),
	StatusQuo(new RemainTheSameAlways());

	private StrategyBehavior sb;
	private Strategies(StrategyBehavior sb) {
		this.sb = sb;
	}

	@Override
	public boolean toCooperateOrNotToCooperate(boolean is_cooperator,
			FittnessMemory fm) {
		return sb.toCooperateOrNotToCooperate(is_cooperator, fm);
	}

	@Override
	public DeltaFitnessCalculator getDfc() {
		return sb.getDfc();
	}

	@Override
	public ProbabilityCalculator getPc() {
		return sb.getPc();
	}

}
