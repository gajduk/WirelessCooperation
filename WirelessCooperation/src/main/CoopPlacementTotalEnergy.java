package main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class CoopPlacementTotalEnergy {

	public static void main(String[] args) throws Exception {
		HashMap<Strat_ec,TreeMap<Integer,ArrayList<Double>>> map = new HashMap<>();
		
		Scanner jin = new Scanner(new File("nodes9center_energy.txt"));
		while ( jin.hasNextLine() ) {
			String strat = jin.nextLine().split(":")[1];
			String ec = jin.nextLine().split(":")[1];
			int coop_r = (int)Double.parseDouble(jin.nextLine().split(":")[1]);
			int N = Integer.parseInt(jin.nextLine().split(":")[1]);
			ArrayList<Double> nodes_energy = new ArrayList<>();
			for ( int k = 0 ; k < N ; ++k ) {
				String line = jin.nextLine();
//				System.out.println(line);
				String line_s[] = line.split(" ");
				double rad = Double.parseDouble(line_s[0].split(":")[1]);
				double e = Double.parseDouble(line_s[1].split(":")[1]);
				double pc = Double.parseDouble(line_s[2].split(":")[1]);
				int r = (int) rad;
				nodes_energy.add(e);
			}
			Strat_ec se = new Strat_ec(strat, ec);
			if ( ! map.containsKey(se) ) {
				TreeMap<Integer,ArrayList<Double>> tm = new TreeMap<Integer,ArrayList<Double>>();
				for ( int i = 0 ; i < 50 ; ++i ) {
					tm.put(i, new ArrayList<Double>());
				}
				map.put(se,tm);
			}
			ArrayList<Double> c_val = map.get(se).get(coop_r);
			c_val.add(avg(nodes_energy));
		}
		for ( Strat_ec se : map.keySet() ) {
			StringBuilder sb = new StringBuilder();
			sb.append(se+" = [");
			TreeMap<Integer,ArrayList<Double>> vals = map.get(se);
			for ( Integer r : vals.keySet() ) {
				sb.append(avg(vals.get(r))+",");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("];");
			System.out.println(sb.toString());
		}
		Strat_ec se = map.keySet().iterator().next();
		StringBuilder sb = new StringBuilder();
		sb.append("r = [");
		TreeMap<Integer,ArrayList<Double>> vals = map.get(se);
		for ( Integer r : vals.keySet() ) {
			sb.append(r+",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("];");
		System.out.println(sb.toString());
		jin.close();
	}
	
	private static double avg(ArrayList<Double> arrayList) {
		if ( arrayList == null || arrayList.size()== 0 ) return 0.0d;
		double res = 0.0d;
		for ( Double d : arrayList ) res += d;
		return res/arrayList.size();
	}

}
