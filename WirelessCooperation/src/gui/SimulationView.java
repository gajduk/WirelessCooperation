package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import options.OptionMultiClass;
import simulator.Node;
import simulator.SimulationDirector;
import simulator.SimulationExperiment;
import simulator.energy_distribution.EnergyDistribution;
import simulator.strategy.StrategyBehavior;

class PlotActionListener implements ActionListener {
	
	protected PlotFrame pd;
	
	public PlotActionListener(PlotFrame pd) {
		this.pd = pd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pd.display();
	}
	
}

public class SimulationView extends JFrame {
	
	public static final int redraw_interval = 30;
	
	private SimulationDirector sd;
	private JButton pause;
	private JMenuBar menuBar;
	private JMenu main_menu,plot_submenu,strategy_submenu;
	
	private NetworkPanel np;
	private Timer timer;
	private JSlider slider;
	private boolean paused = true;
	private List<PlotFrame> plots;
	private static final double log_base = 1.5;
		
	public void setPaused(boolean paused) {
		this.paused = paused;
		setPausedButtonText(paused);
	}
	
	private void setPausedButtonText(boolean paused) {
		if ( ! paused ) 
			pause.setText("PAUSE");
		else 
			pause.setText("RESUME");
	}
		
	private long getSpeedFromSlider(int value) {
		return (long) Math.pow(log_base, value)-1;
	}
	
	private int getValueForSlider(long speed) {
		return (int) (Math.log(sd.getSpeed()+1)/Math.log(log_base));
	}
	
	public void setSpeed(long speed) {
		slider_changing = true;
		slider.setValue(getValueForSlider(speed));
		slider_changing = false;
	}
	
	private boolean slider_changing = false;
	
	public SimulationView() {
		plots = new LinkedList<PlotFrame>();
		setLayout(new BorderLayout());
		np = new NetworkPanel(this);
		np.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		
		add(np,BorderLayout.CENTER);
		pause = new JButton("START");
		JPanel pnl = new JPanel();
		
		
		JLabel label_speed = new JLabel("Speed");
		
		slider = new JSlider(0,20,0);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if ( ! slider_changing ) {
					sd.setSpeed(getSpeedFromSlider(slider.getValue()));
				}
			}
			
		});
		pnl.add(label_speed);
		pnl.add(slider);
		add(pnl,BorderLayout.SOUTH);
		pause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( paused )
					sd.resume();
				else 
					sd.pause();
				paused = !paused;
				setPausedButtonText(paused);
			}
			
		});
		pnl = new JPanel();
		pnl.add(pause);
		add(pnl,BorderLayout.NORTH);
		setTitle("");
		setSize(800,800);
		setResizable(false);
		timer = new Timer(redraw_interval, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
			
		});
		timer.start(); 
		createMenuBar();
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				for ( PlotFrame pd : plots ) {
					pd.close();
				}
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}
		});
	}
	
	public void setSD(SimulationDirector sd) {
		this.sd = sd;
		this.np.setSD(sd);
		for ( PlotFrame pd : plots ) {
			pd.setSD(sd);
		}
	}
	
	public static int last = 1000;
	public static double rad = 50;
	
	public void createMenuBar() {
		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		main_menu = new JMenu("Main");
		main_menu.setMnemonic(KeyEvent.VK_A);
		
		plot_submenu = new JMenu("Plot");
		plot_submenu.setMnemonic(KeyEvent.VK_P);
		
		plot_submenu.getAccessibleContext().setAccessibleDescription(
				"Plot graphs displaying varoius information");
		
		JMenuItem menuItem = new JMenuItem("Total energy spent",
				KeyEvent.VK_T);
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Plots the total energy spent by all nodes");
		PlotFrame pd = new TotalEnergyLinePlotFrame(sd,"Total energy spent by all nodes",last,1,null,Color.cyan);
		plots.add(pd);
		menuItem.addActionListener(new PlotActionListener(pd));
		plot_submenu.add(menuItem);
		plot_submenu.getAccessibleContext().setAccessibleDescription(
				"Plot the average percent of cooperators");
		
		menuItem = new JMenuItem("Average percent of cooperators",
				KeyEvent.VK_T);
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Plots the average percent of cooperators");
		pd = new AveragePercentCooperators(sd,"Average percent of cooperators",last,1,null,Color.cyan);
		plots.add(pd);
		menuItem.addActionListener(new PlotActionListener(pd));
		plot_submenu.add(menuItem);
		
		
		menuItem = new JMenuItem("percent of cooperators",
				KeyEvent.VK_C);
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Plots the percent of cooperators against all nodes");
		pd = new NumCooperatorPlotDirector(sd,"Procent of cooperators",last,1,null,Color.cyan);
		plots.add(pd);
		menuItem.addActionListener(new PlotActionListener(pd));
		plot_submenu.add(menuItem);
		
		menuItem = new JMenuItem("energy spent by range",
				KeyEvent.VK_C);
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Gives a histogram for the energy spent by range");
		pd = new HistogramPlotDirector(sd,"Energy spent by range",last,1,null,Color.cyan,rad);
		plots.add(pd);
		menuItem.addActionListener(new PlotActionListener(pd));
		plot_submenu.add(menuItem);
		
		menuItem = new JMenuItem("energy spent",
				KeyEvent.VK_C);
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Plots energy spent since last update");
		pd = new DeltaTotalEnergyPlotDirector(sd,"Delta energy spent",last,1,null,Color.cyan);
		plots.add(pd);
		menuItem.addActionListener(new PlotActionListener(pd));
		plot_submenu.add(menuItem);
		
		menuItem = new JMenuItem("naklon na potrosena energija",
				KeyEvent.VK_C);
		menuItem.getAccessibleContext().setAccessibleDescription(
				"naklon na potrosena energija");
		pd = new NaklonEnergyPlotDirector(sd,"Naklon na energijata",last,1,null,Color.cyan);
		plots.add(pd);
		menuItem.addActionListener(new PlotActionListener(pd));
		plot_submenu.add(menuItem);
		
		menuItem = new JMenuItem("Prosecna potrosena enrgija vo poslednite 10 cekori",
				KeyEvent.VK_C);
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Prosecna potrosena enrgija vo poslednite 10 cekori");
		pd = new AverageEnergySpentLinePlotFrame(sd,"prosecna potrosuvacka na energija",last,1,null,Color.cyan,30);
		plots.add(pd);
		menuItem.addActionListener(new PlotActionListener(pd));
		plot_submenu.add(menuItem);		
		/*
		strategy_submenu = new JMenu("Node strategy");
		OptionMultiClass omc = ((OptionMultiClass<StrategyBehavior>)SimulationExperiment.options.get("S"));
		
		ButtonGroup bg = new ButtonGroup();
		for ( Object s : omc.getOption_values().keySet() ) {
			JRadioButtonMenuItem jrb = new JRadioButtonMenuItem(s.toString());
			bg.add(jrb);
			jrb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String value = ((JRadioButtonMenuItem)arg0.getSource()).getText();
					StrategyBehavior sb = ((OptionMultiClass<StrategyBehavior>)SimulationExperiment.options.get("S")).getOption_values().get(value);
					sb.setDfc(sd.getWirelessNodeMap().getNodes().get(0).getSb().getDfc());
					sb.setPc(sd.getWirelessNodeMap().getNodes().get(0).getSb().getPc());
					for ( Node n : sd.getWirelessNodeMap().getNodes() ) {
						n.setStrategyBehaviour(sb);
					}
				}
			});
			strategy_submenu.add(jrb);
		}
	   
		coop_type_submenu = new JMenu("Cooperation type");
		omc = ((OptionMultiClass<EnergyDistribution>)SimulationExperiment.options.get("EC"));
		ButtonGroup bg = new ButtonGroup();
		for ( Object s : omc.getOption_values().keySet() ) {
			JRadioButtonMenuItem jrb = new JRadioButtonMenuItem(s.toString());
			bg.add(jrb);
			jrb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String value = ((JRadioButtonMenuItem)arg0.getSource()).getText();
					EnergyDistribution ec = ((OptionMultiClass<EnergyDistribution>)SimulationExperiment.options.get("EC")).getOption_values().get(value);
					ec.setAlpha(sd.getEc().getAlpha());
					ec.setNi(sd.getEc().getNi());
					sd.setEc(ec);
				}
			});
			coop_type_submenu.add(jrb);
		}
		 */
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( e.getButton() == MouseEvent.BUTTON2 ) {
					Node n = np.getNodeOnAbsoluteLocation(e.getX(), e.getY());
					if ( n != null ) {
					}
					repaint();
				}
			}
		});
		main_menu.add(plot_submenu);
//		main_menu.add(strategy_submenu);
//		main_menu.add(coop_type_submenu);
		menuBar.add(main_menu);
		setJMenuBar(menuBar);
	}
	
	
	
	public void update() {
		for ( PlotFrame pd : plots ) {
			pd.update();
		}
		setTitle(sd.getCurrent_time_step()+"");
		repaint();
	}

	public void addPlot(PlotFrame pd) {
		plots.add(pd);
	}

	public double assist() {
		return ((AveragePercentCooperators)plots.get(1)).get();
	}

	
	
	
	

}
