package gui;

import java.awt.Color;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import simulator.Node;
import simulator.SimulationDirector;

public abstract class AbstractPlotFrame implements PlotFrame {

	protected SimulationDirector sd;
	private JFrame frame;
	protected Plot2DPanel p2d;
	protected Node n;
	public int update_on; 
	private int update_counter = 0;
	protected long intervals_passed = 0;
	protected int last;
	protected boolean visible = false;
	protected Color color;
	
	public AbstractPlotFrame(SimulationDirector sd,String title,int last,int update_on,Node n,Color color) {
		this.last = last;
		this.sd = sd;
		frame = new JFrame(title);
		frame.setLocation(500, 0);
		frame.setSize(800,500);
		p2d = new Plot2DPanel();
		frame.add(p2d);
		frame.setVisible(visible);
		frame.setContentPane(p2d);
		this.n = n;
		this.update_on = 1;
		this.color = color;
	}
	
	@Override
	public void setSD(SimulationDirector sd) {
		this.sd = sd;
	}
		
	public void close() {
		frame.dispose(); 
	}
	
	@Override
	public void display() {
		frame.show();
		visible = true;
	}
	
	public void update () {
		++update_counter;
		if ( update_counter < update_on ) return;
		update_counter = 0;
		++intervals_passed;
		p2d.removeAllPlots();
		updateInternal();
	}

	protected abstract void updateInternal();

}
