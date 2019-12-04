package tests;

import modules.News;
import modules.BloomFilter;

import java.util.*;
import java.io.*;

public class FinalTest {
	
	static ArrayList<News> news = new ArrayList<>(); //creating the HashMap
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		/*
		 * 
		 * 
		 * working with the file
		 * 
		 * 
		 */
		
		FileReader filereader = new FileReader("Articles.csv");
		
		BufferedReader bfr = null;
		
		bfr = new BufferedReader(filereader);
		try {
			
			bfr.readLine(); //ignore first line
			
			String line = bfr.readLine(); //read line
			
			while (line != null) {//read every line
				
				String t = new StringBuilder(line).reverse().toString(); //reverse line
				String newsTitle = t.split("/", 2)[0]; //get the title of each line/news
				String x = newsTitle.split(",", 2)[1]; //remove the category
				String newsContent = t.split("/", 2)[1].substring(7); //get the news content from each news
				
				/*news.put(new StringBuilder(x).reverse().toString().substring(5), 
						new StringBuilder(newsBody).reverse().toString());*/ //add to the HashMap each pair title - news content
				
				News noticia = new News(new StringBuilder(x).reverse().toString().substring(5), new StringBuilder(newsContent).reverse().toString()); //create an news object
				news.add(noticia); //add the news to the ArrayList
				
				line = bfr.readLine(); //read next line
				
			}
			
			/*
			 * print each news
			for (News n: news) {
				
				System.out.printf("Title: %s; Content: %s\n", n.getNewsTitle(), n.getNewsContent()); //print every news (title - news content)
				
			}
			*/
			
		} catch (IOException e) { 
			
			System.out.println("Error during file reading!"); //if there´s an error, print this message
			
		}
		
		
		/*
		 * 
		 * 
		 * Bloom Filter test
		 * 
		 * 
		 */
		
		BloomFilter bf = new BloomFilter(news.size(), (int)(news.size() * 0.8), 4);
		bf.initBloomFilter();
		bf.initHashFunction(1000);
		ArrayList<String> repeatedNews = new ArrayList<>();
		
		
		for(int i = 0; i < news.size(); i++) {
			
			if(!bf.isMember(news.get(i).getNewsTitle())) {
				bf.insertElement(news.get(i).getNewsTitle()); //if the news title is not in the bloom filter, add it
			}
			else {
				repeatedNews.add(news.get(i).getNewsTitle()); //if the news title is already in the bloom filter, add it to the repeatedNews ArrayList
			}
			
		}
		
		//print repeated news
		System.out.printf("No ficheiro utilizado, estão %d noticias repetidas.\n", repeatedNews.size());
		System.out.println("Essas noticias são as seguintes:");
		
		int contador = 1;
		
		for (String noticia: repeatedNews) {
			contador++;
			System.out.printf("noticia repetida #%d: %s\n", contador, noticia);
		}
		
		
	}

}
