package gui;

import java.awt.Color;
import java.util.LinkedList;

import simulator.Node;
import simulator.SimulationDirector;

public class AverageEnergySpentLinePlotFrame extends LinePlotFrame {

	private LinkedList<Double> prev_vals;
	private double last_val = 0;
	private int count = 10;
	
	public AverageEnergySpentLinePlotFrame(SimulationDirector sd, String title,
			int last, int update_on, Node n, Color clr,int count) {
		super(sd, title, last, update_on, n, clr);
		prev_vals = new LinkedList<Double>();
		this.count = count;
	}

	@Override
	protected double getCurrentValue() {
		double current = 0.0;
		for ( Node n : sd.getWirelessNodeMap().getNodes() ) {
			current += n.getEs().getTotal_spent_energy();
		}
		double res = current-last_val;
		last_val = current;
		if ( prev_vals.size() > count ) {
			prev_vals.removeFirst();
		}
		prev_vals.add(res);
		double sum = 0.0;
		for ( Double v : prev_vals ) {
			sum += v;
		}
		return sum/prev_vals.size();
	}

}
