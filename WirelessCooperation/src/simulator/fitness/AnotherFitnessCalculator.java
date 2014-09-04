package simulator.fitness;

import simulator.Node;

public class AnotherFitnessCalculator extends AbstractFitnessCalculator {

	@Override
	public void updateFitnessForNode(Node n) {
		double current_value = n.getTotal_energy_spent_sad()-n.getTotal_spent_energy();
		n.addFitnessValue(current_value);
	}

}
