package modules;

import java.util.ArrayList;
import java.util.HashMap;


public class MinHash {
	
	private HashFunction hash;
	
	public MinHash() {
		this.hash = new HashFunction(100);
	}
	
	public ArrayList<Integer> createMinHash( ArrayList<String> sh) {
		
		ArrayList<Integer> vetor = new ArrayList<>();
		
		for(int i = 0; i < 100; i ++) {
			
			int temp = this.hash.gh(sh.get(0), i);
			
			for(int j = 0; j < sh.size(); j++) {
				
				int temp2 = this.hash.gh(sh.get(j), i);
				
				if (temp2 < temp) {
					temp = temp2;
				}	
			}
			
			vetor.add(temp);
			
		}
		
		
		return vetor;
	}
	
	
	public void printVetor(ArrayList<Integer> hashes) {
		for(Integer h: hashes) {
			System.out.println(h);
		}
	}
	
	public int numIguais(ArrayList<Integer> hashes1, ArrayList<Integer> hashes2) {
		int occ = 0;
		
		for(int i = 0; i < hashes1.size(); i++) {
			
			if(hashes2.contains(hashes1.get(i))) {
				occ++;
			}
		}
		
		return occ;
		
	}
	
	public int[] printSimilares(HashMap<Integer, ArrayList<Integer>> mapa, double percentagem ) {
		int keysLength = mapa.keySet().size();
		double occ = 0;
		int contador = 0;
		int [] valores = null;
		for(int i = 0; i < keysLength; i++) {
			for(int j = i + 1; j < keysLength; j++) {
				occ = numIguais(mapa.get(i), mapa.get(j));
				if((occ/100 > percentagem/100) && (occ/100 < 1.00)) {
					//System.out.println(occ/100 + " " + percentagem/100);
					System.out.println("Noticia número " + i + "é similar à noticia número " + j);
					
				}
			}
		}
		System.out.println(contador);
		return valores;
	}
	
}
