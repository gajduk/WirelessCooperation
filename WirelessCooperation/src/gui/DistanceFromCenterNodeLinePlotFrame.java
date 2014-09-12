package gui;

import java.awt.Color;

import simulator.Node;
import simulator.SimulationDirector;
import utils.Utils;

public class DistanceFromCenterNodeLinePlotFrame extends LinePlotFrame {

	public DistanceFromCenterNodeLinePlotFrame(SimulationDirector sd,
			String title, int last, int update_on, Node n, Color clr) {
		super(sd, title, last, update_on, n, clr);
	}

	@Override
	protected double getCurrentValue() {
		return Utils.dist(n.getNodeIden().getX(),n.getNodeIden().getY(),sd.getWirelessNodeMap().geta()/2,sd.getWirelessNodeMap().geta()/2);
	}

}
