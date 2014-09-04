package gui;

import java.awt.Color;
import java.util.Arrays;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import simulator.Node;
import simulator.SimulationDirector;
import utils.Utils;

public class HistogramPlotDirector extends AbstractPlotFrame {
	
	private double step;

	public HistogramPlotDirector(SimulationDirector sd, String title,
			int last,int update_on,Node n,Color clr,double step) {
		super(sd, title,last,update_on,n,clr);
		this.step = step;
	}

	@Override
	protected void updateInternal() {
		
		double h[] = getHeights();
		int m = h.length;
		double wh[][] = new double[m][2];
		double xy[][] = new double[m][2];
		for ( int i = 0 ; i < m ; ++i ) {
			wh[i][0] = 1;
			wh[i][1] = h[i];
			xy[i][0] = i+0.5;
			xy[i][1] = wh[i][1]/2;
		}
		p2d.addBoxPlot("Sample", xy,wh);
		p2d.includeInBounds(0.0,0.0);
		p2d.includeInBounds(0.0,1.0);
		
	}
	
	private double[] getHeights() {
		double a = sd.getWirelessNodeMap().geta();
		int n = (int)(Utils.dist(0, 0, a/2, a/2)/step)+1;
		Node center = new Node(a/2, a/2, -1, -1, false, null, null);
		double res[] = new double[n];
		double count[] = new double[n];
		for ( Node nn : sd.getWirelessNodeMap().getNodes() ) {
			int idx = (int)(Utils.dist(nn,center)/step);
			++count[idx];
			res[idx] += nn.getTotal_spent_energy();
		}
		double sum = 0.0;
		for ( int i = 0 ; i < n ; ++i ) {
			if ( count[i] > 0 )
				res[i] /= count[i];
			sum += res[i];
		}
		for ( int i = 0 ; i < n ; ++i ) {
			res[i] /= sum;
		}
		return res;
	}
	

}
