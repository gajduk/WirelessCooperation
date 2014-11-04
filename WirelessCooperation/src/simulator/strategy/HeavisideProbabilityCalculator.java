package simulator.strategy;

import simulator.fitness.DeterministicProbabilityCalculator;
import utils.Utils;

/**
 * A class that calculates the probability of a positive change based on the delta fitness.
 * It uses a heavisede function as the probability function:
 * a Heavisede function h(t) is defined as:
 * 	/ 
 * { h(t,x) = 0.0, if x < t 
 * { h(t,x) = 1.0, otherwise
 *  \
 *  if ( x == t ) that is a special case when we are not certain of the type of change that happens so we return 0.5 probability (definite uncertainty)
 *  
 *  See also {@link ProbabilityCalculator}
 * @author Andrej Gajduk
 *
 */
public class HeavisideProbabilityCalculator extends DeterministicProbabilityCalculator {
	
	//the parameter t of the Heaviside function @HeavisideProbabilityCalculator
	private double t;

	
	/**
	 * See the way the probabilty is calculated in {@link HeavisideProbabilityCalculator}
	 * See the purpose of this function in {@link ProbabilityCalculator#getPositiveDecisionProbability(double)}
	 */
	@Override
	public double getPositiveDecisionProbability(double fitness_change) {
		return Utils.doubleEqual(fitness_change,t)?Double.MIN_VALUE:(fitness_change>t?1.0:0.0);
	}

	public HeavisideProbabilityCalculator(double t) {
		super();
		this.t = t;
	}

}
