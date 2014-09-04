package simulator.energy_distribution;

import java.util.LinkedList;
import java.util.List;

import simulator.Node;
import utils.Utils;

public class DistributedFairEnergyDistribution extends AbstractEnergyDistribution {
	
	public DistributedFairEnergyDistribution() {}
	
	public DistributedFairEnergyDistribution(double ni,double alpha) {
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
			double sum = 0;
			int i = 0;
			for ( Node n : cooperators ) {
				p[i] = Math.pow(Utils.distSqr(n, recv),-alpha/2);
				sum += p[i];
				++i;
			}
			i = 0;
			for ( Node n : cooperators ) {
				double pic = (p[i]/sum)*energyForDirectTransmision(n, recv);
				res.add(new NodeEnergyConsumedPair(n, pic, false));
				++i;
			}
		}
		return res;
	}

	@Override
	public EnergyDistribution copy() {
		return new DistributedFairEnergyDistribution();
	}
	
	@Override
	public String toString() {
		return "dfair";
	}
	
}
