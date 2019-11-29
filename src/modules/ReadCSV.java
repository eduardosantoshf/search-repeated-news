package modules;

import java.util.*;
import java.io.*;

public class ReadCSV {
	
	static ArrayList<String> separateNew = new ArrayList<String>();

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		FileReader filereader = new FileReader("Articles.csv");
		
		BufferedReader bfr = null;
		
		bfr = new BufferedReader(filereader);
		
		try {
			
			bfr.readLine(); //ignore first line
			
			String line = bfr.readLine(); //read line
			
			while (line != null) {
				
				System.out.println(line);
 				
			}
			
		} catch (IOException e) {
			
			System.out.println("Error during file reading!");
			
		}
		
	}
	

}
