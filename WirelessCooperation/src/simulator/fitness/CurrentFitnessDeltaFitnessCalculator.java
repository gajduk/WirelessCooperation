package simulator.fitness;

public class CurrentFitnessDeltaFitnessCalculator implements DeltaFitnessCalculator {

	@Override
	public double getDeltaF(FittnessMemory fm) {
		return fm.getLastFitnessValue();
	}

	@Override
	public String toString() {
		return "current_fitness";
	}


}
