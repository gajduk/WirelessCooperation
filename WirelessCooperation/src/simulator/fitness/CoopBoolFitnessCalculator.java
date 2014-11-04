package simulator.fitness;

import simulator.Node;

public class CoopBoolFitnessCalculator extends AbstractFitnessCalculator {

	@Override
	public void updateFitnessForNode(Node n) {
		double fitness_value = 1.0d;
		if ( n.getEs().getEnergy_spent_sad_from_last_update() == n.getEs().getEnergy_spent_from_last_update() ) {
			//nobody helped me
			fitness_value = -1.0;
		}
		n.addFitnessValue(fitness_value);
	}

}
