package simulator.fitness;

import simulator.Node;

public class SimpleFitnessCalculator extends AbstractFitnessCalculator {

	@Override
	public void updateFitnessForNode(Node n) {
		double current_fittness_value = -n.getEs().getEnergy_spent_from_last_update();
		n.addFitnessValue(current_fittness_value);
	}
	
	@Override
	public String toString() {
		return "simple";
	}

}
