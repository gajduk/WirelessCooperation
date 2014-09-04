package simulator.fitness;

public interface DeltaFitnessCalculator {
	
	public abstract double getDeltaF(FittnessMemory fm);

	public abstract int getMinimumNumberOfValuesForDeltaCalculation();

}
