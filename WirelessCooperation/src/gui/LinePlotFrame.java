package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.LinkedList;

import org.math.plot.*;

import simulator.Node;
import simulator.SimulationDirector;

import javax.swing.JFrame;

public abstract class LinePlotFrame extends AbstractPlotFrame {
	
	protected LinkedList<Double> values;
	
	public LinePlotFrame(SimulationDirector sd,String title,int last,int update_on,Node n,Color clr) {
		super(sd,title,last,update_on,n,clr);
		values = new LinkedList<>();
	}
	
	public void updateLinePlot(double x[] , double y[]) {
		p2d.addLinePlot("Sample plot",color, x,y);
	}

	@Override
	protected void updateInternal() {
		addValue(getCurrentValue());
		if ( !visible ) return;
		double x[] = getXs(last);
		double y[] = getYs(last);
		updateLinePlot(x, y);
	}

	protected abstract double getCurrentValue();
	
	protected double[] getYs(int last) {
		double res[] = new double[last];
		int i = 0;
		for ( Double d : values ) {
			res[i++] = d;
		}
		while ( i < last ) {
			res[i++] = 0;
		}
		return res;
	}

	protected double[] getXs(int last) {
		double res[] = new double[last];
		if ( intervals_passed > last ) {
			for ( int i = 0 ; i < last ; ++i ) {
				res[i] = intervals_passed-last+i;
			}
			
		}
		else {
			for ( int i = 0 ; i < last ; ++i ) {
				res[i] = i;
			}
		}
		return res;
	}
	
	private void addValue(double val) {
		if ( values.size() >= last ) {
			values.removeFirst();
		}
		values.addLast(val);
	}
	
	protected double getLastValue() {
		if ( values == null || values.size() == 0 ) return 0;
		return values.getLast();
	}

}
