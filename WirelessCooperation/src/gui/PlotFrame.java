package gui;

import simulator.SimulationDirector;

public interface PlotFrame {
	
	public void display();
	
	public void update();
	
	public void setSD(SimulationDirector sd);
		
	public void close();

}
