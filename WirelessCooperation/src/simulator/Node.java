package simulator;

import simulator.fitness.FitnessCalculator;
import simulator.fitness.FittnessMemory;
import simulator.strategy.StrategyBehavior;

public final class Node {

	//location and index
	private NodeIdentity node_iden;
	
	//remembers the past fitness states (use this when you update your strategy based on previous fitness)
	private FittnessMemory fm;
	//statistics about the energy consumption
	private NodeEnergyStatistics es;
	//whether or not this node is a cooperator
	private boolean cooperator;
	
	//the StrategyBehavoiur for this Node
	private StrategyBehavior sb;
	//the FittnessCalculator for this node
	private FitnessCalculator fc;
	//number of messages where it was an intermediate node
	public long count_intermediate;
	//number of times where the node spent energy for cooperation
	public long count_real_cooperation;
	
	public void setStrategyBehaviour(StrategyBehavior sb) {
		this.sb = sb;
	}
	
	public boolean isCooperator() {
		return cooperator;
	}

	public void setCooperator(boolean cooperator) {
		this.cooperator = cooperator;
	}

	public FittnessMemory getFm() {
		return fm;
	}


	public StrategyBehavior getSb() {
		return sb;
	}

	public NodeIdentity getNodeIden() {
		return node_iden;
	}

	public NodeEnergyStatistics getEs() {
		return es;
	}


	public FitnessCalculator getFc() {
		return fc;
	}

	public Node(NodeIdentity ni, int remember_last_fitness_values,boolean cooperator,StrategyBehavior sb,FitnessCalculator fc) {
		this.node_iden = ni;
		this.es = new NodeEnergyStatistics();
		fm = new FittnessMemory(remember_last_fitness_values);
		this.cooperator = cooperator;
		this.sb = sb;
		this.fc = fc;
	}
	
	public void update() {
		//DO NTH
	}
	
	
	public void spendEnergy(double value) {
		//DO NTH
	}
	
	public void updateTotalEnergySpent(double value) {
		es.total_spent_energy += value;
	}
	
	public void updatetotal_energy_spent_deflector(double value) {
		es.total_energy_spent_defector += value;
		this.updateTotalEnergySpent(value);
	}
	
	public void updateenergy_spent_sad_from_last_update(double value) {
		es.energy_spent_sad_from_last_update += value;
		this.updateTotalEnergy_spen_sad(value);
	}
	
	private void updateTotalEnergy_spen_sad(double value) {
		es.total_energy_spent_sad += value;
	}

	public void updateenergy_spent_from_last_update(double value) {
		es.energy_spent_from_last_update += value;
	}
	
	public void updateenergy_spent_defector_from_last_update(double value) {
		es.energy_spent_defector_from_last_update += value;
		updatetotal_energy_spent_deflector(value);
		updateenergy_spent_from_last_update(value);
	}
	
	public void updateenergy_spent_cooperator_from_last_update(double value) {
		es.energy_spent_cooperator_from_last_update += value;
		updatetotal_energy_spent_cooperator(value);
		updateenergy_spent_from_last_update(value);
	}
	
	public void updatetotal_energy_spent_cooperator(double value) {
		es.total_energy_spent_cooperator += value;
		this.updateTotalEnergySpent(value);
	}
	
	
	
	/**
	 * reset all the energy counter from last update to 0 
	 */
	public void resetEnergySpentFromLastUpdate() {
		es.energy_spent_from_last_update = 0;
		es.energy_spent_defector_from_last_update = 0;
		es.energy_spent_cooperator_from_last_update = 0;
		es.energy_spent_sad_from_last_update = 0;
	}
	
	public void cooperateInSending(Node a, Node b) {
		// TODO Auto-generated method stub
	}

	public void receiveMessage(Node a) {
		// TODO Auto-generated method stub
		
	}

	public void sendMessage(Node b) {
		// TODO Auto-generated method stub
		
	}

	public void adaptStrategy() {
		fc.updateFitnessForNode(this);
		setCooperator(sb.toCooperateOrNotToCooperate(isCooperator(),getFm()));
		resetEnergySpentFromLastUpdate();
	}

	public void addFitnessValue(double value) {
		fm.addNewFitnessValue(value);
	}

	public void incCooperations() {
		count_real_cooperation++;		
	}

}
