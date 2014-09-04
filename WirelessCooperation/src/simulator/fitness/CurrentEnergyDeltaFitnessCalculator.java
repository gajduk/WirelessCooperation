package simulator.fitness;

public class CurrentEnergyDeltaFitnessCalculator implements  DeltaFitnessCalculator {

	public static double save_coef;
	public static double total_capacity;
	
	@Override
	public double getDeltaF(FittnessMemory fm) {
		return fm.getLastFitnessValue()-(save_coef*total_capacity);
	}

	@Override
	public int getMinimumNumberOfValuesForDeltaCalculation() {
		return 1;
	}

}
