package simulator.fitness;

import simulator.Node;

public class ImprovedFitnessCalculator extends AbstractFitnessCalculator {

	@Override
	public void updateFitnessForNode(Node n) {
		double current_fittness_value = n.getEs().getEnergy_spent_sad_from_last_update()-(n.getEs().getEnergy_spent_cooperator_from_last_update()+n.getEs().getEnergy_spent_defector_from_last_update());
		n.addFitnessValue(current_fittness_value);
	}
	
	@Override
	public String toString() {
		return "improved";
	}

}
