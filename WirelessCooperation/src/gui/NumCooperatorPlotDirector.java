package gui;

import java.awt.Color;

import simulator.Node;
import simulator.SimulationDirector;

public class NumCooperatorPlotDirector extends LinePlotFrame {

	public NumCooperatorPlotDirector(SimulationDirector sd, String title,
			int last, int update_on, Node n, Color clr) {
		super(sd, title, last, update_on, n, clr);
	}

	@Override
	public double getCurrentValue() {
		double res = 0.0;
		for ( Node n : sd.getWirelessNodeMap().getNodes() ) {
			if ( n.isCooperator() ) ++res;
		}
		return res/sd.getWirelessNodeMap().getNodes().size();
	}

}
