package modules;

import java.util.*;
import java.io.*;

public class ReadCSV {
	
	static HashMap<String, String> news = new HashMap<>(); //creating the HashMap
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		FileReader filereader = new FileReader("Articles2.csv");
		
		BufferedReader bfr = null;
		
		bfr = new BufferedReader(filereader);
		int newsNumber = 1;
		try {
			
			bfr.readLine(); //ignore first line
			
			String line = bfr.readLine(); //read line
			
			while (line != null) {//read every line
				String t = new StringBuilder(line).reverse().toString(); //reverse line
				String title = t.split("/", 2)[0]; //get the title of each line/news
				String x = title.split(",", 2)[1]; //remove the category
				String newsBody = t.split("/", 2)[1].substring(7); //get the news content from each news
				
				news.put(new StringBuilder(x).reverse().toString().substring(5), 
						new StringBuilder(newsBody).reverse().toString()); //add to the HashMap each pair title - news content
				
				line = bfr.readLine(); //read next line
			}
			
			for (String valor: news.keySet()) {
				System.out.printf("titulo: %s; content: %s\n", valor, news.get(valor)); //print every news (title - news content)
			}
			
			
		} catch (IOException e) {
			
			System.out.println("Error during file reading!");
			
		}
		
	}

}
