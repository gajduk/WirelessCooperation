package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


@Deprecated
public class SynchonizedFileWriter {
	
	public static final String file_name = "results.txt";
	
	public PrintWriter out;
	
	private static SynchonizedFileWriter sfw;
	
	private SynchonizedFileWriter() {
		try {
			out = new PrintWriter(new File(file_name));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized SynchonizedFileWriter getInstance() {
		if ( sfw == null ) {
			sfw = new SynchonizedFileWriter();
		}
		return sfw;
	}
	
	public synchronized void print(String text) {
		out.print(text);
	}
	
	public synchronized void printAndFlush(String text) {
		print(text);
		flush();
	}
	
	public synchronized void flush() {
		out.flush();
	}
	
	public synchronized void close() {
		out.close();
	}
	

}
