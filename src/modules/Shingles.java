package modules;

import java.util.ArrayList;

public class Shingles {
	
	private int k;
	private ArrayList<String> shingles;
	private HashFunction hash;
	
	/*********************************************** constructor ***********************************************/
	
	public Shingles (String mapa, int k) {
		this.k = k;
		this.hash = new HashFunction(this.k);
		this.shingles = addShingles(mapa);
		
	}

	/************************************************* getters *************************************************/
	
	public int getK() {
		return k;
	}
	
	public ArrayList<String> getShingles() {
		return shingles;
	}

	public HashFunction getHash() {
		return hash;
	}
	
	/************************************************ methods ************************************************/
	
	//create k-shingles of a string
	public ArrayList<String> addShingles(String valor){
		
		ArrayList<String> shingles = new ArrayList<String>(); //Initialize the array of shingles
		
		String shingle = "";
			
		for (int i = 0; i < valor.length() - this.k; i++) {
			
			for ( int w = i; w < i + this.k; w++) {
				
				shingle += valor.charAt(w); //concatenate each char to create the shingle
				
			}
			
			shingles.add(shingle); //add each shingle to the shingles array
			shingle = "";
		}
		
		return shingles;
	}
	
	//print shingles
	public void printSh() {
		for (String s: shingles ) {
			
			System.out.println(s);
			
		}
	}

}