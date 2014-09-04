package simulator.fitness;

import java.util.Iterator;

public class SimpleDeltaFitnessCalculator implements DeltaFitnessCalculator {

	@Override
	public double getDeltaF(FittnessMemory fm) {
		Iterator<Double> iter = fm.getPrevious_fitness_values();
		if ( ! iter.hasNext() ) return 0.0;
		double fn = iter.next();
		if ( ! iter.hasNext() ) return 0.0;
		double fn_1 = iter.next();
		double res = fn-fn_1;
		return res;
	}

	@Override
	public int getMinimumNumberOfValuesForDeltaCalculation() {
		return 2;
	}
	
	@Override
	public String toString() {
		return "simple";
	}
	
}
