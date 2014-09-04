package simulator;

import simulator.fitness.FitnessCalculator;
import simulator.fitness.FittnessMemory;
import simulator.strategy.StrategyBehavior;

public final class Node {
	
	//coordinates in the plane where this node is located (not supposed to be changed with time)
	private final double x,y;
	//unique id for this node (can be used as an index for storing the nodes since the value of the identifiers for the nodes are all in range [0,N-1] where N is the total number of nodes
	private final int idx;
	//remembers the past fitness states (use this when you update your strategy based on previous fitness)
	private FittnessMemory fm;
	//whether or not this node is a cooperator
	private boolean cooperator;
	//the total amount of spent energy since the start of the simulation
	private double total_spent_energy = 0;
	//the total amount of spent energy since the last strategy update
	private double energy_spent_from_last_update = 0;
	//the amount of energy spent on sending my own messages since the start of the simulation
	private double total_energy_spent_defector = 0;
	//the amount of energy spent on sending my own message since the last strategy update
	private double energy_spent_defector_from_last_update = 0;
	//the amount of energy spent on sending others people messages since the start of the simulation
	private double total_energy_spent_cooperator = 0;
	//the amount of energy spent on sending others people since the last strategy update
	private double energy_spent_cooperator_from_last_update = 0;
	//the amount of energy that would be spent on sending my messages if no one ever helped me since the start of the simulation
	private double total_energy_spent_sad = 0;
	//the amount of energy that would be spent on sending my messages if no one ever helped me since since the last strategy update
	private double energy_spent_sad_from_last_update = 0;
	//the StrategyBehavoiur for this Node
	private StrategyBehavior sb;
	//the FittnessCalculator for this node
	private FitnessCalculator fc;
	//number of messages where it was an intermediate node
	public long count_intermediate;
	//number of times where the node spent energy for cooperation
	public long count_real_cooperation;
	
	public double getTotal_energy_spent_defector() {
		return total_energy_spent_defector;
	}

	public double getEnergy_spent_defector_from_last_update() {
		return energy_spent_defector_from_last_update;
	}
	
	public void setStrategyBehaviour(StrategyBehavior sb) {
		if ( this.sb != null && sb.getDfc() == null ) {
			sb.setDfc(this.sb.getDfc());
		}
		this.sb = sb;
	}
	
	public boolean isCooperator() {
		return cooperator;
	}

	public void setCooperator(boolean cooperator) {
		this.cooperator = cooperator;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getIdx() {
		return idx;
	}

	public FittnessMemory getFm() {
		return fm;
	}


	public double getTotal_spent_energy() {
		return total_spent_energy;
	}

	public double getEnergy_spent_from_last_update() {
		return energy_spent_from_last_update;
	}

	public double getTotal_energy_spent_cooperator() {
		return total_energy_spent_cooperator;
	}

	public double getEnergy_spent_cooperator_from_last_update() {
		return energy_spent_cooperator_from_last_update;
	}

	public double getTotal_energy_spent_sad() {
		return total_energy_spent_sad;
	}

	public double getEnergy_spent_sad_from_last_update() {
		return energy_spent_sad_from_last_update;
	}

	public StrategyBehavior getSb() {
		return sb;
	}

	public FitnessCalculator getFc() {
		return fc;
	}
	
	public int getIntX() {
		return (int)x;
	}
	
	public int getIntY() {
		return (int)y;
	}

	public Node(double x,double y,int idx,int remember_last_fitness_values,boolean cooperator,StrategyBehavior sb,FitnessCalculator fc) {
		this.x = x;
		this.y = y;
		this.idx = idx;
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
		total_spent_energy += value;
	}
	
	public void updatetotal_energy_spent_deflector(double value) {
		this.total_energy_spent_defector += value;
		this.updateTotalEnergySpent(value);
	}
	
	public void updateenergy_spent_sad_from_last_update(double value) {
		this.energy_spent_sad_from_last_update += value;
		this.updateTotalEnergy_spen_sad(value);
	}
	
	private void updateTotalEnergy_spen_sad(double value) {
		this.total_energy_spent_sad += value;
	}

	public void updateenergy_spent_from_last_update(double value) {
		this.energy_spent_from_last_update += value;
	}
	
	public void updateenergy_spent_defector_from_last_update(double value) {
		this.energy_spent_defector_from_last_update += value;
		updatetotal_energy_spent_deflector(value);
		updateenergy_spent_from_last_update(value);
	}
	
	public void updateenergy_spent_cooperator_from_last_update(double value) {
		this.energy_spent_cooperator_from_last_update += value;
		updatetotal_energy_spent_cooperator(value);
		updateenergy_spent_from_last_update(value);
	}
	
	public void updatetotal_energy_spent_cooperator(double value) {
		this.total_energy_spent_cooperator += value;
		this.updateTotalEnergySpent(value);
	}
	
	
	
	/**
	 * reset all the energy counter from last update to 0 
	 */
	public void resetEnergySpentFromLastUpdate() {
		energy_spent_from_last_update = 0;
		energy_spent_defector_from_last_update = 0;
		energy_spent_cooperator_from_last_update = 0;
		energy_spent_sad_from_last_update = 0;
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

}
