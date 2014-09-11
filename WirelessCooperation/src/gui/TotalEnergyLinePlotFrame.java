package gui;

import java.awt.Color;

import simulator.Node;
import simulator.SimulationDirector;
import simulator.WirelessNodeMap;

public class TotalEnergyLinePlotFrame extends LinePlotFrame {


	public TotalEnergyLinePlotFrame(SimulationDirector sd, String title,
			int last,int update_on,Node n,Color clr) {
		super(sd, title, last,update_on,n,clr);
	}

	@Override
	public double getCurrentValue() {
		double res = 0.0;
		WirelessNodeMap wnm = sd.getWirelessNodeMap();
		for ( Node n : wnm.getNodes() ) {
			res += n.getEs().getTotal_spent_energy();
		}
		return res;
	}
	

}
