package modules;

import java.util.ArrayList;

public class Shingles {
	
	private int k = 10;
	private ArrayList<String> shingles;
	private HashFunction hash;
	
	/*
	 * 
	 * Construtores
	 * 
	 */
	
	public Shingles (String mapa) {
		hash = new HashFunction(this.k);
		this.shingles = addShingles(mapa);
	}

	
	/*
	 * 
	 * getters
	 * 
	 */
	
	public ArrayList<String> getShingles() {
		return shingles;
	}

	public HashFunction getHash() {
		return hash;
	}
	
	/*
	 * 
	 * cria os shingles de uma string
	 * 
	 */
	
	public ArrayList<String> addShingles(String valor){
		
		ArrayList<String> shingles = new ArrayList<String>();
		
		String shingle = "";
			
			for (int i = 0; i < valor.length() - this.k; i++) {
				
				for ( int w = i; w < i + this.k; w++) {
					shingle += valor.charAt(w);
				}
				
				shingles.add(shingle);
				shingle = "";
			}
		
		return shingles;
	}
	
	
	/*
	 * 
	 * print shingles
	 * 
	 */
	
	public void printSh() {
		for(String s: shingles ) {
			System.out.println(s);
		}
	}

}
