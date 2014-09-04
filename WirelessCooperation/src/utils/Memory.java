package utils;

import java.util.LinkedList;

public class Memory {
	
	private LinkedList<Double> values;
	private int remember_last;
	private double sum;
	
	public Memory(int remember_last) {
		this.remember_last = remember_last;
		values = new LinkedList<Double>();	
		sum = 0;
	}

	public LinkedList<Double> getValues() {
		return values;
	}

	public int getRemember_last() {
		return remember_last;
	}
	
	public void addValue(double value) {
		if (values.size()  > remember_last ) {
			sum -= values.removeLast();
		}
		sum += value;
		values.addFirst(value);
	}
	
	public double avg() {
		return sum()/count();
	}

	public int count() {
		return values.size();
	}

	public double sum() {
		return sum;
	}

	public double lastValue() {
		if ( values.size() > 0 )
			return values.getLast();
		return Double.NaN;
	}
	
	
	
}

