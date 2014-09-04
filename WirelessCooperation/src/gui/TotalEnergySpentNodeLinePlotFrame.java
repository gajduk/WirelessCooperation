package gui;

import java.awt.Color;

import simulator.Node;
import simulator.SimulationDirector;

public class TotalEnergySpentNodeLinePlotFrame extends LinePlotFrame {

	public TotalEnergySpentNodeLinePlotFrame(SimulationDirector sd,
			String title, int last, int update_on, Node n, Color clr) {
		super(sd, title, last, update_on, n, clr);
	}

	@Override
	protected double getCurrentValue() {
		return n.getTotal_spent_energy();
	}

}
