package simulator.strategy;

import simulator.fitness.DeltaFitnessCalculator;
import simulator.fitness.FittnessMemory;

public abstract class AbstractStrategyBehaviour implements StrategyBehavior {
	
	protected DeltaFitnessCalculator dfc;
	protected ProbabilityCalculator pc;

	public AbstractStrategyBehaviour(DeltaFitnessCalculator dfc,ProbabilityCalculator pc) {
		this.dfc = dfc;
		this.pc = pc;
	}

	public DeltaFitnessCalculator getDfc() {
		return dfc;
	}
	
	public ProbabilityCalculator getPc() {
		return pc;
	}

	public boolean toCooperateOrNotToCooperate(boolean is_cooperator, FittnessMemory fm) {
		double pp = getPositiveDecisionProbability(fm);
		if ( pp == Double.MIN_VALUE ) return is_cooperator;
		boolean decision = Math.random()<pp;
		return toCooperateOrNotToCooperateInternal(is_cooperator, decision);
	}
	
	protected abstract boolean toCooperateOrNotToCooperateInternal(boolean is_cooperator,boolean decision);

	public double getPositiveDecisionProbability(FittnessMemory fm) {
		double df_norm = dfc.getDeltaF(fm);
		return pc.getPositiveDecisionProbability(df_norm);
	}
	

}
