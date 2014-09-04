package simulator.strategy;


/**
 * An interface that serves as a probability calculator that a given change happened in the enviorment that the strategybehaviour class should take into account  
 * @author Andrej Gajduk
 *
 */
public interface ProbabilityCalculator {
	
	/**
	 * A method that gives the probability that a positive change in the enviorment has occured
	 * @param x - a fitness value between (-~,+~), where positive values usually denote positive change
	 * @return the probability that the fitness is a result of a positive change in fitness
	 */
	public abstract double getPositiveDecisionProbability(double x);

}
