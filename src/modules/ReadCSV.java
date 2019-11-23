package modules;

import java.util.*;
import java.io.*;


public class ReadCSV {

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
			
			while (line != null) {
				
				String[] separateLine = line.split(",");
				
				String articleID = separateLine[0];
				String publishDate = separateLine[1];
				String articleSourceLink = separateLine[2];
				String title = separateLine[3];
				String subtitle = separateLine[4];
				String text = separateLine[5];
				System.out.println(articleID);
				
				
				line = bfr.readLine(); //read next line
 				
			}
			
		} catch (IOException e) {
			
			System.out.println("Error during file reading!");
			
		}
		
	}

}
