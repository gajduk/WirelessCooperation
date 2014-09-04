package main;

import simulator.SimulationExperiment;

public class CenterAntenaMain {
	
	public static void main(String[] args) throws Exception {
		//ovoa nema am bash nikakva vrska
		SimulationExperiment.main("-D 150000000 -S C -SA synch -T 19999 -N 50 -a 100 -EC minimal -alpha 4 -ni .5 -TL center -DF simple -F improved".split(" "));
	}

}
