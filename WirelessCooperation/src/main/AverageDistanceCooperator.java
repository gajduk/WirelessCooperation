package main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class AverageDistanceCooperator {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Scanner jin = new Scanner(new File("C:\\Users\\Andrej Gajduk\\Dropbox\\Cooperation in wireless networks\\results\\energy distribution types energy spent different ni and alpha.txt"));
		TreeMap<Double,HashMap<String,TreeMap<Double,Double>>> map = new TreeMap<Double,HashMap<String,TreeMap<Double,Double>>>();
		while ( jin.hasNextLine() ) {
			String line = jin.nextLine();
			String params[] = line.split(" ++");
			double alpha = 	Double.parseDouble(params[0].split(":")[1]);
			HashMap<String,TreeMap<Double,Double>> s = map.get(alpha);
			if ( s == null ) {
				s = new HashMap<String,TreeMap<Double,Double>>();
				map.put(alpha, s);
			}
			String type = params[2].split(":")[1];
			TreeMap<Double,Double> ss = s.get(type);
			if ( ss == null ) {
				ss = new TreeMap<Double,Double>();
				s.put(type, ss);
			}
			Double ni = Double.parseDouble(params[1].split(":")[1]);
			Double energy = Double.parseDouble(jin.nextLine().split(":")[1]);
			ss.put(ni, energy);
			jin.nextLine();
			jin.nextLine();
		}
		for ( Double alpha : map.keySet() ) {
			System.out.println("Alpha:"+alpha);
			for ( String s : map.get(alpha).keySet() ) {
				System.out.println(s);
				//System.out.print("ni:");
				for ( Double ni : map.get(alpha).get(s).keySet() ) {
					System.out.print(ni+",");
				}
				System.out.println();
				//System.out.print("energy:");
				for ( Double energy : map.get(alpha).get(s).values() ) {
					System.out.print(energy+",");
				}
				System.out.println();
			}
			System.out.println();
		}
		jin.close();
		/*
		int n = 10000000;
		double xa = 0.0;
		double ya = 0.5;
		double xb = 1.0;
		double yb = 0.5;
		double dab = dist(xa,ya,xb,yb);
		for ( double nu = 0.1 ; nu < 0.91 ; nu += 0.01 ) {
			double avg = 0;
			double count = 0;
			double threshold = dab*nu;
			for ( int i = 0 ; i < n ; ++i ) {
				double x = Math.random();
				double y = Math.random();
				double dac = dist(xa,ya,x,y);
				double dbc = dist(x,y,xb,yb);
				if ( dac < threshold && dbc < dab ) {
					++count;
					avg += dbc;
				}
			}
			avg /= count;
			System.out.print(avg+",");
		}
		*/
	}
	
	public static double dist(double x1,double y1,double x2,double y2) {
		double dx = x1-x2;
		double dy = y1-y2;
		return Math.sqrt(dx*dx+dy*dy);
	}

}
