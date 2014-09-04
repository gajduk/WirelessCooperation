package simulator.strategy;

import simulator.fitness.DeltaFitnessCalculator;
import simulator.fitness.FittnessMemory;
import utils.Utils;

public abstract class AbstractStrategyBehaviour implements StrategyBehavior {
	
	public AbstractStrategyBehaviour() {
		
	}
	
	protected DeltaFitnessCalculator dfc;
	protected ProbabilityCalculator pc;
	
	public AbstractStrategyBehaviour(DeltaFitnessCalculator dfc) {
		this.dfc = dfc;
	}
	
	public ProbabilityCalculator getPc() {
		return pc;
	}

	public void setPc(ProbabilityCalculator pc) {
		this.pc = pc;
	}

	public AbstractStrategyBehaviour(DeltaFitnessCalculator dfc,ProbabilityCalculator pc) {
		this.dfc = dfc;
		this.pc = pc;
	}
	
	public AbstractStrategyBehaviour(ProbabilityCalculator pc) {
		this.pc = pc;
	}
	

	public DeltaFitnessCalculator getDfc() {
		return dfc;
	}

	public void setDfc(DeltaFitnessCalculator dfc) {
		this.dfc = dfc;
	}
	
	public boolean toCooperateOrNotToCooperate(boolean is_cooperator, FittnessMemory fm) {
		double pp = getPositiveDecisionProbability(fm);
		if ( change_not_valid(fm,pp) ) return is_cooperator;
		boolean decision = Math.random()<pp;
		return toCooperateOrNotToCooperateInternal(is_cooperator, decision);
	}
	
	protected abstract boolean toCooperateOrNotToCooperateInternal(boolean is_cooperator,boolean decision);

	public boolean change_not_valid(FittnessMemory fm, double pp) {
		return Utils.doubleEqual(pp, 0.5) || fm.getCurrentNumValues() < dfc.getMinimumNumberOfValuesForDeltaCalculation();
	}
	
	public double getPositiveDecisionProbability(FittnessMemory fm) {
		//inital state
		/*
		double df_norm = dfc.getDeltaF(fm)/Math.abs(fm.getLastFitnessValue());
		if ( dfc.getMinimumNumberOfValuesForDeltaCalculation() > fm.getCurrentNumValues() ) return 0.5;
		*/
		//modified state
		double df_norm = dfc.getDeltaF(fm);
		return pc.getPositiveDecisionProbability(df_norm);
	}
	

}
