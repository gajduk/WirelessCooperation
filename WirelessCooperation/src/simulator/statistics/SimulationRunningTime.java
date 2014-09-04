package simulator.statistics;

import java.io.PrintWriter;
import java.lang.management.ManagementFactory;

import simulator.SimulationDirector;

public class SimulationRunningTime extends AbstractSimulationStat {
	
	long start,end;

	public SimulationRunningTime(PrintWriter out, String additional_info) {
		super(out, additional_info);
		start = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
	}

	@Override
	public void update(SimulationDirector simulationDirector) {
		//DO NTH
	}
	
	@Override
	public void simulationFinished(SimulationDirector simulationDirector) {
		end = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
		super.simulationFinished(simulationDirector);
	}
	
	@Override
	public String messageToLog() {
		StringBuilder sb = new StringBuilder(super.messageToLog());
		sb.append((end-start)/1000000).append(" ms");
		return sb.toString();
	}
	
}
