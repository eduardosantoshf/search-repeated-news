package modules;

import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;


public class ReadCSV {
	
	static ArrayList<String> separateNew = new ArrayList<String>();
	static String[] exemplo;

	public static void main(String[] args) throws CsvValidationException, IOException {
		
		FileReader filereader = new FileReader("NewsArticles.csv");
		CSVReader csvreader = new CSVReader(filereader);
		
		while ((exemplo = csvreader.readNext()) != null) {
			for (String coluna: exemplo) {
				System.out.println(coluna + "\t");
			}
		}
		
		
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
				System.out.println(line);
				separateNew.add(line);
				
				line = bfr.readLine(); //read next line
 				
			}
			
		} catch (IOException e) {
			
			System.out.println("Error during file reading!");
			
		}
		
	}
	

}
