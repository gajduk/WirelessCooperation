package simulator.statistics;

import java.util.ArrayList;

import simulator.Node;
import simulator.SimulationDirector;
import utils.Utils;

public class NodeCooperationStat extends AbstractSimulationStat {

	StringBuilder sb;
	
	ArrayList<ArrayList<Integer>> counts;

	public NodeCooperationStat() {
		sb = new StringBuilder();
	}

	@Override
	public void update(SimulationDirector simulationDirector) {
		if ( counts == null ) {
			counts = new ArrayList<>();
			for ( int i = 0 ; i < simulationDirector.getWirelessNodeMap().getNodes().size() ; ++i )
				counts.add(new ArrayList<Integer>());
		}
		for ( Node n : simulationDirector.getWirelessNodeMap().getNodes() ) 
			counts.get(n.getNodeIden().getIdx()).add(n.isCooperator()?1:0);
	}
	
	@Override
	public void simulationFinished(SimulationDirector simulationDirector) {
		double a = simulationDirector.getWirelessNodeMap().geta();
		int i = 0;
		for ( Node n : simulationDirector.getWirelessNodeMap().getNodes() ) {
			/*
			double percent_coop = 0;
			for ( Integer i : counts.get(n.getIdx()) ) {
				percent_coop += i;
			}
			
			percent_coop /= counts.get(n.getIdx()).size();
			*/
			sb.append("Radius:"+Utils.dist(n.getNodeIden().getX(), n.getNodeIden().getY(),a/2,a/2)+" Energy_total:"+n.getEs().getTotal_spent_energy()+" PercentCoop:"+counts.get(i++)+"\n");
		}
		
		super.simulationFinished(simulationDirector);
	}
	
	@Override
	public String messageToLog() {
		sb.insert(0,super.messageToLog());
		//sb.insert(0,"Energy spent by nodes distance:");
		return sb.toString();
	}
	
	
}
