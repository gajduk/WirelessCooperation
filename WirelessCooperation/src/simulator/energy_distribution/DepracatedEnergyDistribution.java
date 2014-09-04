package simulator.energy_distribution;

import java.util.LinkedList;
import java.util.List;

import simulator.Node;

@Deprecated
public class DepracatedEnergyDistribution extends AbstractEnergyDistribution {
	
	public DepracatedEnergyDistribution() {}
	
	public DepracatedEnergyDistribution(double ni,double alpha) {
		super(ni,alpha);
	}

	protected List<NodeEnergyConsumedPair> getEnergyConsumedForEachNodeInternal(Node sender, Node recv, List<Node> cooperators) {
		LinkedList<NodeEnergyConsumedPair> res = new LinkedList<NodeEnergyConsumedPair>();
		if ( cooperators.size() == 0 ) {
			double pd = energyForDirectTransmision(sender, recv);
			res.add(new NodeEnergyConsumedPair(sender,pd,true));
		}
		else {
			double pi = energyForDirectTransmision(sender,recv,ni);
			res.add(new NodeEnergyConsumedPair(sender,pi,true));
			double p[] = new double[cooperators.size()];
			int i = 0;
			for ( Node n : cooperators ) {
				p[i] = energyForDirectTransmision(n, recv);
				++i;
			}
			double sum = 0;
			for ( i = 0 ; i < p.length ; ++i ) {
				sum += p[0]/p[i];
			}
			double energy_consumed_for_coop_for_each_node = 1.0/sum*p[0];
			for ( Node n : cooperators ) {
				res.add(new NodeEnergyConsumedPair(n,energy_consumed_for_coop_for_each_node,false));
			}
		}
		return res;
	}

	@Override
	public EnergyDistribution copy() {
		return new DepracatedEnergyDistribution();
	}

	
	/*
	@Override
	public double energyConsumption(Node sender, Node recv,List<Node> cooperators) {
		
		if ( real_cooperators.size() == 0 ) {
			sender.updateenergy_spent_deflector_from_last_update(pd);
			sender.spendEnergy(pd);
			return pd;
		}
		else {
			double pi = energyForDirectTransmision(sender,recv,ni);
			sender.updateenergy_spent_deflector_from_last_update(pi);
			sender.spendEnergy(pi);
			double p[] = new double[real_cooperators.size()];
			int i = 0;
			for ( Node n : real_cooperators ) {
				p[i] = energyForDirectTransmision(n, recv);
				++i;
			}
			double sum = 0;
			for ( i = 0 ; i < p.length ; ++i ) {
				sum += p[0]/p[i];
			}
			double energy_consumed_for_coop_for_each_node = 1.0/sum*p[0];
			for ( Node n : real_cooperators ) {
				n.updateenergy_spent_cooperator_from_last_update(energy_consumed_for_coop_for_each_node);
				n.spendEnergy(energy_consumed_for_coop_for_each_node);
			}
			return energy_consumed_for_coop_for_each_node*real_cooperators.size() + pi;
		}
		
	}
	*/

}
