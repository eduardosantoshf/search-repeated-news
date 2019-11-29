package modules;

import java.util.*;
import java.io.*;

public class ReadCSV {
	
	static ArrayList<String[]> separateNew = new ArrayList<String[]>();

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		FileReader filereader = new FileReader("Articles2.csv");
		
		BufferedReader bfr = null;
		
		bfr = new BufferedReader(filereader);
		int newsNumber = 1;
		try {
			
			bfr.readLine(); //ignore first line
			
			String line = bfr.readLine(); //read line
			String[] line2 = {};
			
			while (line != null) {
				//System.out.println(line);
				separateNew.add(line.split("</strong"));
				
				line = bfr.readLine();
 				
			}
			
		} catch (IOException e) {
			
			System.out.println("Error during file reading!");
			
		}
		
		for (String[] news: separateNew) {
			newsNumber++;
			System.out.printf("news #: %d; title: %s\n", newsNumber, news[0]);
		}
		
	}
	

}
