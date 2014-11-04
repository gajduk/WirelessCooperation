package simulator;

import gui.SimulationView;

import java.util.LinkedList;
import java.util.List;

import javax.xml.crypto.NodeSetData;

import simulator.adaptation.StrategyAdaptation;
import simulator.energy_distribution.EnergyDistribution;
import simulator.statistics.SimulationStat;
import utils.Utils;


public class SimulationDirector {
	
	private TrafficManager tm;
	private WirelessNodeMap wnm;
	private EnergyDistribution ec;
	private SimulationView sv;
	private StrategyAdaptation sa;
	//probability that there will be a message sent during each time step
	private double p;
	private boolean paused;
	private long speed;
	private long time_steps_during_single_iteration;
	private long current_time_step;
	private long time_steps;
	private List<SimulationStat> simulation_stats;
	private Mobility mob;
	
	public SimulationDirector(long time_steps_during_single_iteration,double p,WirelessNodeMap wnm,TrafficManager tm,EnergyDistribution ec,StrategyAdaptation sa,SimulationView sv, Mobility mob) {
		this.wnm = wnm;
		this.tm = tm;
		this.ec = ec;
		this.sa  = sa;
		this.p = p;
		this.sv = sv;
		this.paused = true;
		this.speed = 0;
		this.simulation_stats = new LinkedList<SimulationStat>();  
		this.mob = mob;
		this.time_steps_during_single_iteration = time_steps_during_single_iteration;
	}
	
	public void addSimulationStat(SimulationStat ss) {
		ss.init(this);
		this.simulation_stats.add(ss);
	}
	
	public void clearSimulationStat() {
		for ( SimulationStat ss : simulation_stats ) 
			ss.close(this);
		this.simulation_stats.clear();
	}
	
	public List<SimulationStat> getSimulation_stats() {
		return simulation_stats;
	}
	
	public long getCurrentTimeStep() {
		return current_time_step;
	}
	
	public WirelessNodeMap getWirelessNodeMap() {
		return wnm;
	}
	
	public void runSimulation(long time_steps) {
		mob.init();
		try {
			this.time_steps = time_steps;
			while ( this.time_steps > 0 ) {
				if ( ! isPaused() )
						runIteration();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		simulationFinished();
		if ( sv != null ) {
			sv.setVisible(false);
		}
	}
	
	private void sendMessage(Node a, Node b) {
		List<Node> cooperators = wnm.getCooperatorsForConnection(a, b);
		a.sendMessage(b);
		b.receiveMessage(a);
		for ( Node coop : cooperators ) {
			coop.cooperateInSending(a,b);
		}
		ec.energyConsumption(a, b, cooperators);
	}


	public void pause() {
		setPaused(true);
	}
	
	public void resume() {
		setPaused(false);
	}
	
	public void simulationFinished() {
		for ( SimulationStat ss : simulation_stats ) 
			ss.simulationFinished(this);
	}

	public void runIteration() throws Exception {
		if ( sv != null ) {
			sv.update();
		}
		int goal_time_step = (int) Math.max(time_steps-time_steps_during_single_iteration,0);
		while ( time_steps > goal_time_step ) {
				if ( Math.random() < p ) {
					Pair<Node> connection_pair = tm.chooseConnectionpair(wnm.getNodes());
					sendMessage(connection_pair.a,connection_pair.b);
				}
				List<Node> nodes_to_adapt_strategy = sa.getNodesToAdaptStrategy(wnm.getNodes(),time_steps);
				for ( Node n : nodes_to_adapt_strategy ) {
					n.adaptStrategy();
				}
				--time_steps;
		}
		mob.updateLocations(wnm.getNodes(),wnm.geta());
		current_time_step += time_steps_during_single_iteration;
		for ( SimulationStat ss : simulation_stats )
			ss.update(this);
		if ( getSpeed() > 0 ) {
				try {
					Thread.sleep(getSpeed());
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
		}
	}


	public synchronized void setSpeed(long speed) {
//		Thread.currentThread().interrupt();
		this.speed = speed;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	public EnergyDistribution getEc() {
		return ec;
	}

	public void setEc(EnergyDistribution ec) {
		this.ec = ec;
	}
	
	public long getCurrent_time_step() {
		return current_time_step;
	}
	
	public synchronized long getSpeed() {
		return speed;
	}

}
