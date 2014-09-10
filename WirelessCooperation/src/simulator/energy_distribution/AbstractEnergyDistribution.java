package simulator.energy_distribution;

import java.util.LinkedList;
import java.util.List;

import simulator.Node;
import utils.Utils;

class NodeEnergyConsumedPair {
	
	public Node n;
	public double energy;
	public boolean def;
	
	public NodeEnergyConsumedPair(Node n, double energy,boolean def) {
		this.n = n;
		this.energy = energy;
		this.def = def;
	}
	
}

public abstract class AbstractEnergyDistribution implements EnergyDistribution {
	
	protected double ni;
	protected double alpha;
	
	@Override
	public double getNi() {
		return ni;
	}

	@Override
	public double getAlpha() {
		return alpha;
	}

	public AbstractEnergyDistribution(double ni,double alpha){
		this.ni = ni;
		this.alpha = alpha;
	}

	public List<Node> getRealCooperators(Node sender, Node recv,List<Node> cooperators) {
		List<Node> real_cooperators = new LinkedList<Node>();
		double dsr_ni = Utils.distSqr(sender, recv)*ni;
		for ( Node c : cooperators ) {
			double dsc = Utils.distSqr(sender, c);
			if ( dsc < dsr_ni ) {
				real_cooperators.add(c);
			}
		}
		/*
		System.out.print(sender.getIdx()+" - ");
		System.out.print(recv.getIdx()+" : ");
		for ( Node c : real_cooperators ) {
			System.out.print(c.getIdx()+" , ");
		}
		System.out.println();
		*/
		return real_cooperators;
	}
	
	@Override
	public final double energyConsumption(Node sender, Node recv,
			List<Node> cooperators) {
		for ( Node n : cooperators ) ++n.count_intermediate;
		double pd = energyForDirectTransmision(sender,recv);
		sender.updateenergy_spent_sad_from_last_update(pd);
		List<Node> potential_coops = getRealCooperators(sender, recv, cooperators); 
		List<NodeEnergyConsumedPair> necps = getEnergyConsumedForEachNodeInternal(sender,recv,potential_coops);
		double res = 0.0;
		for ( NodeEnergyConsumedPair necp : necps ) {
			if ( ! necp.def ) {
				necp.n.updateenergy_spent_cooperator_from_last_update(necp.energy);
				necp.n.incCooperations();
			}
			else {
				necp.n.updateenergy_spent_defector_from_last_update(necp.energy);
			}
			necp.n.spendEnergy(necp.energy);
			res += necp.energy;
		}
		return res;
	}
	
	protected abstract List<NodeEnergyConsumedPair> getEnergyConsumedForEachNodeInternal(Node sender, Node recv, List<Node> cooperators);

	protected double energyForDirectTransmision(Node a,Node b) {
		return energyForDirectTransmision(a,b,1.0);
	}
	
	protected double energyForDirectTransmision(Node a,Node b,double ni) {
		return Math.pow(Utils.distSqr(a,b)*ni,alpha/2);
	}
	
}
