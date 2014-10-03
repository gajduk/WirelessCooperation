package results_formatter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ParseResultsToMatlab {
	
	public static void main(String[] args) {
		try ( BufferedReader jin = new BufferedReader(new FileReader("results.txt"))) {
			int N = 10;
			String descr = "";
			System.out.print("N = [");
			for ( N = 10 ; N < 101 ; N += 10 ) {
				System.out.print(N);
				if ( N <= 100 ) System.out.print(",");
			}
			System.out.println("];");
			N = 10;
			while ( jin.ready() ) {
				descr = jin.readLine(); 
				descr = descr.replaceAll(" <\\|> ", "").replaceAll("N:10","").replaceAll("\\.","_").replaceAll(":","_");
				if ( N == 10 )
					System.out.print(descr+" = [");
				double e = Double.parseDouble(jin.readLine());
				System.out.printf("%.2f",e*N);
				N += 10;
				if ( N <= 100 ) System.out.print(",");
				if ( N > 100 ) {
					System.out.println("];");
					N = 10;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
