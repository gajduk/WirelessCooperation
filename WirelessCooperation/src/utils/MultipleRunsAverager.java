package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MultipleRunsAverager {
	
	private HashMap<String,ArrayList<Double>> map;
	
	public MultipleRunsAverager() {
		map = new HashMap<String,ArrayList<Double>>();
	}
	
	public void addRun(String description,double value) {
		ArrayList<Double> list = map.get(description);
		if ( list == null ) {
			list = new ArrayList<Double>();
			map.put(description, list);
		}
		list.add(value);
	}
	
	public ArrayList<Double> getValues(String description) {
		return map.get(description);
	}
	
	public Set<String> getAllDescriptions() {
		return map.keySet();
	}
	
	public double getAverageForDescription(String description) {
		return average(getValues(description));
	}
	
	public double getRunsCountForDescription(String description) {
		return getValues(description).size();
	}

	private double average(ArrayList<Double> values) {
		double avg = 0.0;
		for ( Double d : values ) 
			avg += d;
		avg /= values.size();
		return avg;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for ( String s : getAllDescriptions() ) {
			sb.append(s).append("\n");
			sb.append("Average:").append(average(getValues(s))).append("\n");
			sb.append("From # runs:").append(getRunsCountForDescription(s)).append("\n").append("\n");
		}
		return sb.toString();
	}

}
