package simulator.energy_distribution;

import java.util.LinkedList;
import java.util.List;

import simulator.Node;

public class ClosestAllEnergyDistribution extends AbstractEnergyDistribution {
	
	public ClosestAllEnergyDistribution() {}

	public ClosestAllEnergyDistribution(double ni,double alpha) {
		super(ni,alpha);
	}

	protected List<NodeEnergyConsumedPair> getEnergyConsumedForEachNodeInternal(Node sender, Node recv, List<Node> cooperators) {
		LinkedList<NodeEnergyConsumedPair> res = new LinkedList<NodeEnergyConsumedPair>();
		if ( cooperators.size() == 0 ) {
			double pd = energyForDirectTransmision(sender, recv);
			res.add(new NodeEnergyConsumedPair(sender, pd, true));
		}
		else {
			double pi = energyForDirectTransmision(sender,recv,ni);
			res.add(new NodeEnergyConsumedPair(sender, pi, true));
			double p[] = new double[cooperators.size()];
			int i = 0;
			int min_idx = 0;
			Node min_n = cooperators.get(0);
			for ( Node n : cooperators ) {
				p[i] = energyForDirectTransmision(n, recv);
				if ( p[min_idx] > p[i] )  { min_idx = i; min_n = n; }
				++i;
			}
			i = 0;
			res.add(new NodeEnergyConsumedPair(min_n, p[min_idx], false));
		}
		return res;
	}

	@Override
	public EnergyDistribution copy() {
		return new ClosestAllEnergyDistribution();
	}
	
	@Override
	public String toString() {
		return "closest";
	}
	
}