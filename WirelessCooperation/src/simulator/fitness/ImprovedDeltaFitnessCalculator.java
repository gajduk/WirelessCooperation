package simulator.fitness;

public class ImprovedDeltaFitnessCalculator implements DeltaFitnessCalculator {

	@Override
	public double getDeltaF(FittnessMemory fm) {
		return fm.getLastFitnessValue();
	}

	@Override
	public int getMinimumNumberOfValuesForDeltaCalculation() {
		return 1;
	}
	
	@Override
	public String toString() {
		return "imporved";
	}


}
