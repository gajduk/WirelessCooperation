package simulator;

public class NodeEnergyStatistics {

		//the total amount of spent energy since the start of the simulation
		double total_spent_energy = 0;
		//the total amount of spent energy since the last strategy update
		double energy_spent_from_last_update = 0;
		//the amount of energy spent on sending my own messages since the start of the simulation
		double total_energy_spent_defector = 0;
		//the amount of energy spent on sending my own message since the last strategy update
		double energy_spent_defector_from_last_update = 0;
		//the amount of energy spent on sending others people messages since the start of the simulation
		double total_energy_spent_cooperator = 0;
		//the amount of energy spent on sending others people since the last strategy update
		double energy_spent_cooperator_from_last_update = 0;
		//the amount of energy that would be spent on sending my messages if no one ever helped me since the start of the simulation
		double total_energy_spent_sad = 0;
		//the amount of energy that would be spent on sending my messages if no one ever helped me since since the last strategy update
		double energy_spent_sad_from_last_update = 0;
		
		public NodeEnergyStatistics() {
			super();
		}

		public double getTotal_spent_energy() {
			return total_spent_energy;
		}

		public void setTotal_spent_energy(double total_spent_energy) {
			this.total_spent_energy = total_spent_energy;
		}

		public double getEnergy_spent_from_last_update() {
			return energy_spent_from_last_update;
		}

		public void setEnergy_spent_from_last_update(
				double energy_spent_from_last_update) {
			this.energy_spent_from_last_update = energy_spent_from_last_update;
		}

		public double getTotal_energy_spent_defector() {
			return total_energy_spent_defector;
		}

		public void setTotal_energy_spent_defector(double total_energy_spent_defector) {
			this.total_energy_spent_defector = total_energy_spent_defector;
		}

		public double getEnergy_spent_defector_from_last_update() {
			return energy_spent_defector_from_last_update;
		}

		public void setEnergy_spent_defector_from_last_update(
				double energy_spent_defector_from_last_update) {
			this.energy_spent_defector_from_last_update = energy_spent_defector_from_last_update;
		}

		public double getTotal_energy_spent_cooperator() {
			return total_energy_spent_cooperator;
		}

		public void setTotal_energy_spent_cooperator(
				double total_energy_spent_cooperator) {
			this.total_energy_spent_cooperator = total_energy_spent_cooperator;
		}

		public double getEnergy_spent_cooperator_from_last_update() {
			return energy_spent_cooperator_from_last_update;
		}

		public void setEnergy_spent_cooperator_from_last_update(
				double energy_spent_cooperator_from_last_update) {
			this.energy_spent_cooperator_from_last_update = energy_spent_cooperator_from_last_update;
		}

		public double getTotal_energy_spent_sad() {
			return total_energy_spent_sad;
		}

		public void setTotal_energy_spent_sad(double total_energy_spent_sad) {
			this.total_energy_spent_sad = total_energy_spent_sad;
		}

		public double getEnergy_spent_sad_from_last_update() {
			return energy_spent_sad_from_last_update;
		}

		public void setEnergy_spent_sad_from_last_update(
				double energy_spent_sad_from_last_update) {
			this.energy_spent_sad_from_last_update = energy_spent_sad_from_last_update;
		}
		
		
		
}
