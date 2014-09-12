package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import simulator.Mobility;
import simulator.Node;
import simulator.SimulationDirector;
import utils.Pair;
import utils.Utils;

public class NetworkPanel extends JPanel {
	
	SimulationDirector sd;
	SimulationView sv;
	
	double ratio;
	
	double mouseX;
	double mouseY;
	
	double rad = 7;
	double rad_sqr = 100;
	double margin = 30;
	
	public NetworkPanel(SimulationView sv) {
		this.sv = sv;
		setSize(600,600);
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				mouseX = arg0.getX();
				mouseY = arg0.getY();
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		addListeners();
	}
	
	public void setSD(SimulationDirector sd) {
		this.sd = sd;
		ratio = Math.min((getWidth()-margin*2)/sd.getWirelessNodeMap().geta(), (getHeight()-margin*2)/sd.getWirelessNodeMap().geta());
		
	}
	
	private void addListeners() {
			addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( e.getButton() == MouseEvent.BUTTON1 ) {
				
					if ( e.isControlDown() ) {
						for ( Node n : sd.getWirelessNodeMap().getNodes() ) {
							n.setCooperator(!n.isCooperator());
						}
					}
					else {
						Node n = getNodeOnRelativeLocation(e.getX(),e.getY());
						if ( n != null ) {
							n.setCooperator(!n.isCooperator());
						}
					}
				}
				else {
					Node n = getNodeOnRelativeLocation(e.getX(),e.getY());
					if ( n != null ) {
						JPopupMenu popup = getPopupMenuForNode(n);
						popup.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			}
		});
	}
	
	public JPopupMenu getPopupMenuForNode ( Node n) {
		int idx = n.getNodeIden().getIdx();
		JPopupMenu res = new JPopupMenu();
		JLabel lbl_idx = new JLabel(idx+"");
		res.add(lbl_idx);
		JMenuItem menuItem = new JMenuItem("total energy spent",
				KeyEvent.VK_E);
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Plots the total energy spent for the node: "+idx);
		PlotFrame pd = new TotalEnergySpentNodeLinePlotFrame(sd, "Total energy spent for Node:"+idx,SimulationView.last,1,n,Color.blue);
		sv.addPlot(pd);
		menuItem.addActionListener(new PlotActionListener(pd));
		res.add(menuItem);
		
		menuItem = new JMenuItem("delta energy spent",
				KeyEvent.VK_D);
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Plots the delta energy: the energy spent since last time step");
		pd = new AverageEnergySpentNodeLinePlotFrame(sd, "Delta energy for node:"+idx,SimulationView.last,1,n,Color.blue,50);
		sv.addPlot(pd);
		menuItem.addActionListener(new PlotActionListener(pd));
		res.add(menuItem);
		return res;
	}
	
	public Node getNodeOnRelativeLocation(double mx,double my) {
		for ( Node n : sd.getWirelessNodeMap().getNodes() ) {
			Pair<Integer,Integer> p = getCoordintaesForNode(n);
			if ( Utils.distSqr(p.a,p.b,mx,my) < rad_sqr ) return n;
		}
		return null;
	}

	public Node getNodeOnAbsoluteLocation(double x, double y) {
		return getNodeOnRelativeLocation(x+getX(),y+getY());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = bufferedImage.createGraphics();
	    g2d.setColor(Color.black);
	    g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setFont(new Font("Arial", 1, 10));
		for ( Node n : sd.getWirelessNodeMap().getNodes() ) {
			Color c = Color.YELLOW;
			Color op = Color.CYAN;
			if ( n.isCooperator() ) {
			//if ( n.getCurrent_energy() > n.getCapacity()*0.5 ) {
				c = Color.CYAN;
				op = Color.YELLOW;
			}
			g2d.setColor(c);
			Pair<Integer,Integer> p = getCoordintaesForNode(n);
			g2d.fillOval(p.a,p.b,(int) rad*2,(int) rad*2);
			g2d.setColor(op);
			g2d.drawString(n.getNodeIden().getIdx()+"",(float)(p.a),(float)(p.b));
		}
		g2d.setColor(Color.cyan);
		g2d.drawLine(0, 0, 0, getHeight());
		g2d.drawLine(0, 0, getWidth(), 0);
		g2d.drawLine(getWidth(), 0, getWidth(), getHeight());
		g2d.drawLine(0, getHeight(), getWidth(), getHeight());
		g2d.setFont(new Font("Arial", 1, 24));
		g2d.drawString(sd.getCurrent_time_step()+"",5, 30);
		/*
		g2d.setColor(Color.white);
		for ( Node n : sd.getWirelessNodeMap().getNodes() ) {
				double nx = getXCoordinateFornode(n);
				double ny = getYCoordinateFornode(n);
			if ( rad_sqr > Utils.distSqr(nx, ny, mouseX, mouseY) ) {
				g2d.drawString(String.format("%.4f", n.getTotal_spent_energy()),(float) nx, (float)ny);
			}
			
		}
		*/
		/*
		double center = 300;
		for ( int i = 0 ; i < 100 ; ++i ) {
			double r = sv.rad*(i+1)*ratio;
			g2d.drawOval((int)(center-r),(int)( center-r),(int)( r*2),(int)( r*2));
		}
		*/
		Graphics2D g2dComponent = (Graphics2D) g;
	    g2dComponent.drawImage(bufferedImage, null, 0, 0);
	    
	}

	private Pair<Integer, Integer> getCoordintaesForNode(Node n) {
		return new Pair<Integer, Integer>((int)(margin+n.getNodeIden().getX()*ratio-rad),(int)(margin+n.getNodeIden().getY()*ratio-rad));
	}
	

}
