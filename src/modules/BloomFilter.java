package modules;

public class BloomFilter {
	
	private int nElements; //nº de bits do filtro
	private int size; //nº de elementos do conjunto (size do Bloom Filter)
	private int nHashFunctions; //nº de Hash functions
	private double falsePositiveProb; //probabilidade de falsos positivos
	
	public BloomFilter (int size, double falsePositiveProb) {
		this.size = size;
		this.falsePositiveProb = falsePositiveProb;
		this.nElements =
	}

}
