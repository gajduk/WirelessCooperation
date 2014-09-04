package simulator.statistics;

import java.io.PrintWriter;

import simulator.SimulationDirector;

public abstract class AbstractSimulationStat implements SimulationStat {
	
	private SimulationDirector sd; 
	private PrintWriter out;
	private String additional_info;
	
	public AbstractSimulationStat(PrintWriter out,String additional_info) {
		this.out = out;
		this.additional_info = additional_info;
	}

	public SimulationDirector getSimulationDirector() {
		return sd;
	}

	public void setSimulationDirector(SimulationDirector sd) {
		this.sd = sd;
	}	
	
	@Override
	public void simulationFinished(SimulationDirector simulationDirector) {
		out.print(messageToLog());
	}
	
	@Override
	public void init(SimulationDirector simulationDirector) {
		setSimulationDirector(simulationDirector);
	}
	
	@Override
	public String messageToLog() {
		return additional_info+"\n";
	}
	
	@Override
	public void close(SimulationDirector simulationDirector) {
		//DO NTH, BUT ALLOW OVERRIDING TO DO SOME CLEAN-UP
	}

}
