package gui;

import java.awt.Color;
import java.util.LinkedList;

import simulator.Node;
import simulator.SimulationDirector;

public class AverageEnergySpentNodeLinePlotFrame extends LinePlotFrame {

	private int k;
	private LinkedList<Double> prev_vals;
	private double last_val;
	
	public AverageEnergySpentNodeLinePlotFrame(SimulationDirector sd,
			String title, int last, int update_on, Node n, Color clr,int k) {
		super(sd, title, last, update_on, n, clr);
		this.k = k;
		this.prev_vals = new LinkedList<Double>();
	}

	@Override
	protected double getCurrentValue() {
		if ( k < prev_vals.size() ) {
			prev_vals.removeFirst();
		}
		double current_val = n.getEs().getTotal_spent_energy()-last_val;
		last_val = n.getEs().getTotal_spent_energy();
		prev_vals.addLast(current_val);
		double sum = 0.0;
		for ( Double v : prev_vals ) {
			sum += v;
		}
		sum /= prev_vals.size();
		return sum;		
	}

}
