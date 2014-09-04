package simulator.energy_distribution;

import java.util.LinkedList;
import java.util.List;

import simulator.Node;

public class EvenlyDistributedEnergyDistribution extends AbstractEnergyDistribution {

	public EvenlyDistributedEnergyDistribution(double ni, double alpha) {
		super(ni,alpha);
	}

	public EvenlyDistributedEnergyDistribution() {
	}

	@Override
	public EnergyDistribution copy() {
		return new EvenlyDistributedEnergyDistribution(ni,alpha);
	}

	@Override
	protected List<NodeEnergyConsumedPair> getEnergyConsumedForEachNodeInternal(
			Node sender, Node recv, List<Node> cooperators) {
		LinkedList<NodeEnergyConsumedPair> res = new LinkedList<NodeEnergyConsumedPair>();
		if ( cooperators.size() == 0 ) {
			double pd = energyForDirectTransmision(sender, recv);
			res.add(new NodeEnergyConsumedPair(sender, pd, true));
		}
		else {
			double pi = energyForDirectTransmision(sender,recv,ni);
			res.add(new NodeEnergyConsumedPair(sender, pi, true));
			for ( Node n : cooperators ) {
				double pic = energyForDirectTransmision(n, recv)/cooperators.size();
				res.add(new NodeEnergyConsumedPair(n, pic, false));
			}
		}
		return res;
	}

	@Override
	public String toString() {
		return "even";
	}
	
}
