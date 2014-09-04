package simulator.fitness;

public class ExcessEnergyDeltaFitnessCalculator implements DeltaFitnessCalculator {

	@Override
	public double getDeltaF(FittnessMemory fm) {
		return fm.getLastFitnessValue();
	}

	@Override
	public int getMinimumNumberOfValuesForDeltaCalculation() {
		return 1;
	}

}
