package simulator.statistics;

import java.io.PrintWriter;

import simulator.SimulationDirector;

public abstract class AbstractSimulationStat implements SimulationStat {
	
	private SimulationDirector sd; 
	private PrintWriter out;
	private String additional_info;
	
	public AbstractSimulationStat() {}

	public SimulationDirector getSimulationDirector() {
		return sd;
	}

	public void setSimulationDirector(SimulationDirector sd) {
		this.sd = sd;
	}	
	
	public void setPrintWriter(PrintWriter out) {
		this.out = out;
	}
	
	public void setAdditionalInfo(String ai) {
		this.additional_info = ai;
	}
	
	@Override
	public void simulationFinished(SimulationDirector simulationDirector) {
		if ( out != null )
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
