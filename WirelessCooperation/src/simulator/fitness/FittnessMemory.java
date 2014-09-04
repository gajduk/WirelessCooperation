package simulator.fitness;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class FittnessMemory {
	
	//a list of all the previous values for fitness ordered descending (meaning first element of the list is the most recent value)
	private LinkedList<Double> previous_fitness_values;
	//the maximum number of fitness value 
	private int remember_last;
	
	public FittnessMemory(int remember_last) {
		this.setRemember_last(remember_last);
		previous_fitness_values = new LinkedList<Double>();
	}
	
	public void addNewFitnessValue(double value) {
		while ( remember_last < previous_fitness_values.size() ) {
			previous_fitness_values.removeLast();
		}
		previous_fitness_values.addFirst(value);
	}

	public int getRemember_last() {
		return remember_last;
	}

	public void setRemember_last(int remember_last) {
		this.remember_last = remember_last;
	}

	/**
	 * 
	 * @return an iterator over all the previous fitness values starting from the most recent one
	 */
	public ListIterator<Double> getPrevious_fitness_values() {
		return previous_fitness_values.listIterator();
	}

	/**
	 * 
	 * @return the last fitness value
	 */
	public double getLastFitnessValue() {
		if ( previous_fitness_values.size() <= 1 ) return 0.0;
		return previous_fitness_values.getFirst();
	}
	
	public int getCurrentNumValues() {
		return previous_fitness_values.size();
	}


}
