package gui;

import java.awt.Color;

import simulator.Node;
import simulator.SimulationDirector;

public class DeltaTotalEnergyPlotDirector extends LinePlotFrame {

	public DeltaTotalEnergyPlotDirector(SimulationDirector sd, String title,
			int last, int update_on, Node n, Color clr) {
		super(sd, title, last, update_on, n, clr);
	}

	private double last_val = 0;

	@Override
	public double getCurrentValue() {
		double current = 0.0;
		for ( Node n : sd.getWirelessNodeMap().getNodes() ) {
			current += n.getTotal_spent_energy();
		}
		double res = current-last_val;
		last_val = current;
		return res;
	}

	

}
