package simulator.fitness;

public class ImprovedDeltaFitnessCalculator implements DeltaFitnessCalculator {

	@Override
	public double getDeltaF(FittnessMemory fm) {
		return fm.getLastFitnessValue();
	}

	@Override
	public String toString() {
		return "imporved";
	}


}
