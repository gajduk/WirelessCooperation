package gui;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import simulator.Node;
import simulator.SimulationDirector;

public class AveragePercentCooperators extends LinePlotFrame {

	public AveragePercentCooperators(SimulationDirector sd, String title,
			int last, int update_on, Node n, Color clr) {
		super(sd, title, last, update_on, n, clr);
		// TODO Auto-generated constructor stub
		values = new LinkedList<Double>();
	}
	
	LinkedList<Double> values;

	@Override
	protected double getCurrentValue() {
		double count_coop = 0;
		for ( Node n : sd.getWirelessNodeMap().getNodes() ) {
			count_coop += n.isCooperator()?1:0;
		}
		if ( values.size() > 1500 )
			values.removeLast();
		values.addFirst(count_coop/sd.getWirelessNodeMap().getNodes().size());
		double sum = 0.0;
		for ( Double d : values ) {
			sum += d;
		}
		sum /= values.size();
		return sum;
	}
	
	public double get() {
		return getCurrentValue();
	}

}
