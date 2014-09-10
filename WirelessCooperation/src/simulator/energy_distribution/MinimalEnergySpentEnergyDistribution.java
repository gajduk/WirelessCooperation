package simulator.energy_distribution;

import java.util.LinkedList;
import java.util.List;

import simulator.Node;

public class MinimalEnergySpentEnergyDistribution extends AbstractEnergyDistribution {

	public MinimalEnergySpentEnergyDistribution(double ni, double alpha) {
		super(ni, alpha);
	}

	@Override
	protected List<NodeEnergyConsumedPair> getEnergyConsumedForEachNodeInternal(
			Node sender, Node recv, List<Node> cooperators) {
		/*
		System.out.println(ni);
		System.out.println(sender.getIdx());
		System.out.println(recv.getIdx());
		for ( Node n : cooperators ) {
			System.out.print(n.getIdx()+",");
		}
		System.out.println();
		*/
		LinkedList<NodeEnergyConsumedPair> res = new LinkedList<NodeEnergyConsumedPair>();
		if ( cooperators.size() == 0 ) {
			double pd = energyForDirectTransmision(sender, recv);
			res.add(new NodeEnergyConsumedPair(sender, pd, true));
		}
		else {
			Node min_n = cooperators.get(0);
			double min_power = energyForDirectTransmision(sender, min_n)+energyForDirectTransmision(min_n,recv);
			for ( Node n : cooperators ) {
				double power = energyForDirectTransmision(sender, n)+energyForDirectTransmision(n,recv);
				if ( power < min_power ) {
					min_n = n;
					min_power = power;
				}
			}
			res.add(new NodeEnergyConsumedPair(min_n,energyForDirectTransmision(min_n,recv),false));
			res.add(new NodeEnergyConsumedPair(sender,energyForDirectTransmision(sender,min_n),true));
		}
		return res;
	}
	
	@Override
	public String toString() {
		return "minimal";
	}
	
	@Override
	public List<Node> getRealCooperators(Node sender, Node recv,
			List<Node> cooperators) {
		return cooperators;
	}

}
