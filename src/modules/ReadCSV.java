package modules;

import java.util.*;
import java.io.*;


public class ReadCSV {
	
	static String[] separateNew;

	public static void main(String[] args) {
		
		BufferedReader bfr = null;
		
		try {
			
			bfr = new BufferedReader(new FileReader("NewsArticles.csv"));
			
		} catch (FileNotFoundException e) {
			
			System.out.println("ERRO ao abrir o ficheiro!");
			
		}
		
		try {
			
			bfr.readLine(); //ignore first line
			
			String line = bfr.readLine(); //read line
			
			
			int i = 0;

			
			while (line != null) {
				//System.out.println(line);
				
				separateNew = line.split("\",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\";;;;;;;;;;;;;;;;;;;;;;;;;;");
				//for (String element: separateNew) {
					
				//	String[] separateLine = element.split(",", 6);
				//	System.out.println(separateLine[3]);
				//}
				
				//String articleID = separateLine[0];
				//String publishDate = separateLine[1];
				//String articleSourceLink = separateLine[2];
				//String title = separateLine[3];
				//String subtitle = separateLine[4];
				//String text = separateLine[5];
				//System.out.println(title);
				
				
				line = bfr.readLine(); //read next line
 				
			}
			for (String element: separateNew) {
			
				String[] separateLine = element.split(",", 6);
				System.out.println(separateLine[3]);
			}
			
		} catch (IOException e) {
			
			System.out.println("Error during file reading!");
			
		}
		
	}

}
