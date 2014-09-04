package simulator.strategy;

public class SigmoidProbabilityCalculator extends AbstractProbabilityCalculator {

	private double alpha;
	private double x0;
	
	public SigmoidProbabilityCalculator() {
		alpha = 1.0;
		x0 = 0.0;
	}
	
	public SigmoidProbabilityCalculator(double alpha, double x0) {
		super();
		this.alpha = alpha;
		this.x0 = x0;
	}

	@Override
	public double getPositiveDecisionProbability(double x) {
		return 1.0/(1.0+Math.pow(Math.E,-alpha*(x-x0)));
	}
	
}
	
