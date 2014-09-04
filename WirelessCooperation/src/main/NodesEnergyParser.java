package main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class NodesEnergyParser {
	
	public static void main(String[] args) throws Exception {
		HashMap<Strat_ec,TreeMap<Integer,ArrayList<Double>>> map = new HashMap<>();
		Scanner jin = new Scanner(new File("test_coops_add_stats"));
		int count = 0;
		while ( jin.hasNextLine() ) {
			++count;
			String strat = jin.nextLine().split(":")[1];
			String ec = jin.nextLine().split(":")[1];
//			System.out.println(jin.nextLine());
			jin.nextLine();
			int N = Integer.parseInt(jin.nextLine().split(":")[1]);
//			System.out.println(jin.nextLine());
			jin.nextLine();
			for ( int k = 1 ; k < N ; ++k ) {
				String line = jin.nextLine();
				String line_s[] = line.split(" ");
//				System.out.println(line);
				double rad = Double.parseDouble(line_s[0].split(":")[1]);
				double e = Double.parseDouble(line_s[1].split(":")[1]);
				double pc = Double.parseDouble(line_s[2].split(":")[1]);
				double energy_coop = Double.parseDouble(line_s[3].split(":")[1]);
				double count_intermediate = Double.parseDouble(line_s[4].split(":")[1]);
				double count_real_coop = Double.parseDouble(line_s[5].split(":")[1]);
				int r = (int) rad;
				Strat_ec se = new Strat_ec(strat, ec);
				if ( ! map.containsKey(se) ) {
					TreeMap<Integer,ArrayList<Double>> tm = new TreeMap<Integer,ArrayList<Double>>();
					for ( int i = 0 ; i < 50 ; ++i ) {
						tm.put(i, new ArrayList<Double>());
					}
					map.put(se,tm);
				}
				ArrayList<Double> c_val = map.get(se).get(r);
				double val = count_real_coop==0?0.0:energy_coop/count_real_coop;
				c_val.add(e);
			}
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
