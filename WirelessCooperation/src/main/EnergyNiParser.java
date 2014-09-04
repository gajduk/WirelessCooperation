package main;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import simulator.strategy.StrategyBehavior;

class Strat_ec {
	
	String strat;
	String ec;
	 
	public Strat_ec(String strat, String ec) {
		super();
		this.strat = strat;
		this.ec = ec;
	}
	
	@Override
	public String toString() {
		return strat+"_"+ec;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ec == null) ? 0 : ec.hashCode());
		result = prime * result + ((strat == null) ? 0 : strat.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Strat_ec other = (Strat_ec) obj;
		if (ec == null) {
			if (other.ec != null)
				return false;
		} else if (!ec.equals(other.ec))
			return false;
		if (strat == null) {
			if (other.strat != null)
				return false;
		} else if (!strat.equals(other.strat))
			return false;
		return true;
	}
	
	
	 
}

public class EnergyNiParser {
	
	public static void main(String[] args) throws Exception {
		int times = 100;
		HashMap<Strat_ec,TreeMap<Double,Double>> map = new HashMap<>();
		Scanner jin = new Scanner(new File("energy_ni1.txt"));
		while ( jin.hasNextLine() ) {
			jin.nextLine();
			String strat = jin.nextLine().split(":")[1];
			double ni = Double.parseDouble(jin.nextLine().split(":")[1]);
			String ec = jin.nextLine().split(":")[1];
			double val = Double.parseDouble(jin.nextLine().split(":")[1]);
			Strat_ec se = new Strat_ec(strat, ec);
			if ( ! map.containsKey(se) ) map.put(se, new TreeMap<Double,Double>());
			TreeMap<Double,Double> vals = map.get(se);
			Double c_val = vals.get(ni);
			if ( c_val == null ) c_val = 0.0d;
			c_val += val;
			vals.put(ni, c_val);
		}
		for ( Strat_ec se : map.keySet() ) {
			StringBuilder sb = new StringBuilder();
			sb.append(se+" = [");
			TreeMap<Double,Double> vals = map.get(se);
			for ( Double ni : vals.keySet() ) {
				sb.append(vals.get(ni)/times+",");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("];");
			System.out.println(sb.toString());
		}
		Strat_ec se = map.keySet().iterator().next();
		StringBuilder sb = new StringBuilder();
		sb.append("ni = [");
		TreeMap<Double,Double> vals = map.get(se);
		for ( Double ni : vals.keySet() ) {
			sb.append(ni+",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("];");
		System.out.println(sb.toString());
		jin.close();
	}

}
