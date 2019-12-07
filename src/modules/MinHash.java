package modules;

import java.util.ArrayList;
import java.util.HashMap;

public class MinHash {
	
	private HashFunction hash;
	private int k;
	
	/*********************************************** constructor ***********************************************/
	
	public MinHash(int k) {
		this.k = k;
		this.hash = new HashFunction(k);
	}
	
	/************************************************* getter *************************************************/
	
	public int getK() {
		return k;
	}
	
	
	
	/************************************************ methods ************************************************/
	
	//creating the MinHash signature matrix
	public ArrayList<Integer> createMinHash(ArrayList<String> sh) {
		
		ArrayList<Integer> vetor = new ArrayList<>(); //signature matrix
		
		for (int i = 0; i < 100; i ++) { //for each hash function
			
			int temp = this.hash.gh(sh.get(0), i); //saves the hash value of the 1st shingle
			for (int j = 0; j < sh.size(); j++) { //for each shingle
				
				int temp2 = this.hash.gh(sh.get(j), i);  //saves the hash value
				if (temp2 < temp) { //saves the minimum hash value
					
					temp = temp2;
					
				}
				
			}
			
			vetor.add(temp);
			
		}
		
		return vetor;
	}
	
	
	
	//printing the hashes vector
	public void printVetor(ArrayList<Integer> hashes) {
		System.out.print("[ ");
		for (Integer h: hashes) {
			
			System.out.print( h + ",");
			
		}
		System.out.print(" ]");
	}
	
	//compare two hash vectors and return the number of occurrences
	public int compareHashes(ArrayList<Integer> hashes1, ArrayList<Integer> hashes2) {
		int occ = 0;
		for (int i = 0; i < hashes1.size(); i++) {
			
			if (hashes2.contains(hashes1.get(i))) {
				
				occ++;
				
			}
		}
		
		return occ;
		
	}
	
	//print news with similar titles
	public int[] printSimilaresTitles(HashMap<Integer, ArrayList<Integer>> mapa, double percentagem, ArrayList<News> news ) {
		int keysLength = mapa.keySet().size();
		double occ = 0;
		int [] valores = null;
		for (int i = 0; i < keysLength; i++) {
			
			for (int j = i + 1; j < keysLength; j++) {
				
				occ = compareHashes(mapa.get(i), mapa.get(j));
				if ((occ/100 > percentagem/100) && (occ/100 < 1.00)) { //check if number of occurrences is greater than percentagem && ignore repeated news
					
					System.out.println("News title: " + news.get(i).getNewsTitle());
					System.out.println("is similar to news title: " + news.get(j).getNewsTitle());
					System.out.println();
					
				}
				
			}
			
		}
		return valores;
		
	}
	
	//print news with similar contents
	public int[] printSimilaresContents(HashMap<Integer, ArrayList<Integer>> mapa, double percentagem, ArrayList<News> news ) {
		int keysLength = mapa.keySet().size();
		double occ = 0;
		int [] valores = null;
		for (int i = 0; i < keysLength; i++) {
			
			for (int j = i + 1; j < keysLength; j++) {
				
				occ = compareHashes(mapa.get(i), mapa.get(j));
				if ((occ/100 > percentagem/100) && (occ/100 < 1.00)) { //check if number of occurrences is greater than percentagem && ignore repeated news
					
					System.out.println("News number " + i + " : " + news.get(i).getNewsContent());
					System.out.println("is similar to news number " + j + " : " + news.get(j).getNewsContent());
					System.out.println();
					
				}
				
			}
			
		}
		
		return valores;
		
	}
	
}
