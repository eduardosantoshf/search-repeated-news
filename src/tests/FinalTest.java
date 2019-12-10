package tests;

import modules.News;
import modules.Shingles;
import modules.MinHash;
import modules.BloomFilter;

import java.util.*;
import java.io.*;

public class FinalTest {
	
	static ArrayList<News> news = new ArrayList<>(); //creating the HashMap
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		/******************************************* working with the file *******************************************/
		
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
				News noticia;
	
				if(new StringBuilder(newsContent).reverse().toString().substring(1, 8).equals("strong>")) {
					noticia = new News(new StringBuilder(x).reverse().toString().substring(5), new StringBuilder(newsContent).reverse().toString().substring(8)); //create an news object
				}
				else {
					noticia = new News(new StringBuilder(x).reverse().toString().substring(5), new StringBuilder(newsContent).reverse().toString()); //create an news object
				}
				news.add(noticia); //add the news to the ArrayList
				
				line = bfr.readLine(); //read next line
				
			}
			
		} catch (IOException e) { 
			
			System.out.println("Error during file reading!"); //if there´s an error, print this message
			
		}
		
		int op = 0;
		while (op != 4) {
			Scanner scn = new Scanner(System.in); //creating the scanner
			
			//initial menu
			System.out.println("Choose one of the following options to run the tests:");
			System.out.println("1: [Bloom Filter] List repeat news with same titles"); 
			System.out.println("2: [MinHash & Shingles] List news with similar titles");
			System.out.println("3: [MinHash & Shingles] List news with similar contents");
			System.out.println("4: Terminate program");
			
			while (!scn.hasNextInt()) {
				
				scn.next();
				System.out.println("Invalid value, please enter a valid one: "); //while scanned value  a valid integer, ask again for the number 
				
			}
			
			op = scn.nextInt();
			
			switch(op) {
			
			/******************************************** Bloom Filter test ********************************************/
			
				case 1:
					BloomFilter bf = new BloomFilter(news.size(), (int)(news.size() / 0.1), 4);
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
					System.out.printf("In the file, there are %d repeated news.\n", repeatedNews.size());
					System.out.println("Those news are the following:");
					
					int contador = 1;
					
					for (String noticia: repeatedNews) {
						
						contador++;
						System.out.printf("Repeated news #%d: %s\n", contador, noticia);
						
					}
					System.out.println();
					break;
					
			/****************************************** MinHash & Shingles test ******************************************/
					
				case 2:
					System.out.println("Choose the similarity percentage: ");
					while (!scn.hasNextInt()) {
						
						scn.next();
						System.out.println("Invalid value, please enter a valid percentage [0, 100]: "); 
						
					}
					
					op = scn.nextInt();
					while(op < 0 | op > 100) {
						System.out.println("Invalid value, please enter a valid percentage [0, 100]: "); 
						op = scn.nextInt();
					}
					
					
					MinHash mh = new MinHash(100);
					HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
					
					for(int i = 0; i < news.size(); i++) {
						Shingles sh = new Shingles(news.get(i).getNewsTitle(), 2);
						ArrayList<String> shingles = sh.getShingles();
						map.put(i, mh.createMinHash(shingles));
					}
					long inicio = System.currentTimeMillis();
					mh.printSimilaresTitles(map, op, news);
					long fim = System.currentTimeMillis();
					System.out.println("This operation took " + (fim - inicio) + " ms.");
					System.out.println();
					break;
					
				case 3:
					System.out.println("Choose the similarity percentage: ");
					while (!scn.hasNextInt()) {
						
						scn.next();
						System.out.println("Invalid value, please enter a valid percentage [0, 100]: "); 
						
					}
					
					op = scn.nextInt();
					while(op < 0 | op > 100) {
						System.out.println("Invalid value, please enter a valid percentage [0, 100]: "); 
						op = scn.nextInt();
					}
						
					MinHash mh2 = new MinHash(100);
					HashMap<Integer, ArrayList<Integer>> map2 = new HashMap<>();
					
					for(int i = 0; i < news.size(); i++) {
						Shingles sh = new Shingles(news.get(i).getNewsContent(), 10);
						ArrayList<String> shingles = sh.getShingles();
						map2.put(i, mh2.createMinHash(shingles));
					}
					long inicio2 = System.currentTimeMillis();
					mh2.printSimilaresContents(map2, op, news);
					long fim2 = System.currentTimeMillis();
					System.out.println("This operation took " + (fim2 - inicio2) + " ms.");
					System.out.println();
					break;
					
			}
		}
	}

}