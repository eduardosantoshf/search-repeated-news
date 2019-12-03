package modules;

import java.util.Random;

public class BloomFilter {
	
	private int nElements; //number of bits of the bloom filter
	private int size; //nº de elementos do conjunto (size do Bloom Filter)
	private int nHashFunctions; //nº de Hash functions
	private double falsePositiveProb; //probabilidade de falsos positivos
	private Integer [] bloomFilter; //array do bloom filter
	private int primeNumber; //will be needed in hash functions
	private int [][] RandomValuesA; //random values 
	private int [][] RandomValuesB; //random values
	
	//Construtors ---------------------------------------------------------------------------------------------------
	
	public BloomFilter(int nElements, int size, int nHashFunctions) {
		this.nElements = nElements;
		this.size = size;
		this.nHashFunctions = nHashFunctions;
	}
	
	//getters --------------------------------------------------------------------------------------------------------

	public int getnElements() {
		return nElements;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getnHashFunctions() {
		return nHashFunctions;
	}
	
	public double getFalsePositiveProb() {
		return falsePositiveProb;
	}
	
	public Integer[] getBloomFilter() {
		return bloomFilter;
	}

	// setters --------------------------------------------------------------------------------------------------------

	public void setnElements(int nElements) {
		this.nElements = nElements;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public void setnHashFunctions(int nHashFunctions) {
		this.nHashFunctions = nHashFunctions;
	}

	public void setFalsePositiveProb(double falsePositiveProb) {
		this.falsePositiveProb = falsePositiveProb;
	}
	
	public void setBloomFilter(Integer[] bloomFilter) {
		this.bloomFilter = bloomFilter;
	}
	
	// private methods --------------------------------------------------------------------------------------------------------
	
	// Inicializing the Bloom filter array
	
	public void initBloomFilter() {
		
		bloomFilter = new Integer[this.size];
		for(int i = 0; i < this.size; i++) {
			bloomFilter[i] = 0;
		}
	}
	
	/*
	 *  
	 * Checks if a certain number is prime
	 * 
	 * it will help creating the hash functions
	 * 
	 */
	
	private boolean isPrime( int number) {
		for(int i = 2; i < number; i++) {
			if (number % i == 0) 
				return false;
		}
		return true;
	}
	
	/*
	 * 
	 * Hash function 
	 * 
	 * Carter Wegman Algorithm
	 * 
	 * H (x) = ( (a * x + b) mod p) mod n 
	 * 
	 */
	
	private int hashFunction(String str, int k) { // k = number of hash functions
		
		int hashValue = 0;
		for ( int i = 0; i < str.length(); i++) {
			hashValue = hashValue + ((RandomValuesA[k][i] * (int) str.charAt(i) + RandomValuesB[k][i] ) % this.primeNumber) % this.size;
		}
		
	return hashValue;
		
	}
	
	/*
	 * 
	 * Calculates the random numbers needed in the hash function
	 * 
	 */
	
	public void initHashFunction (int length) {
		
		this.primeNumber = 10001; //this can change
		
		if (this.primeNumber % 2 == 0) {
            this.primeNumber++;
        }

        while (isPrime(this.primeNumber) != true) {
            this.primeNumber += 2;
        }
		
		this.RandomValuesA = new int[this.nHashFunctions][length];
		this.RandomValuesB = new int[this.nHashFunctions][length];
		
		Random random = new Random();
		
		for (int i = 0; i < this.nHashFunctions; i++) {
			for (int w = 0; w < length; w++) {
				this.RandomValuesA[i][w] = random.nextInt(primeNumber - 1);
				this.RandomValuesB[i][w] = random.nextInt(primeNumber - 1);
			}
		}
		
	}
	
	
	/*
	 * 
	 * Adds an element to the bloom filter
	 * 
	 */
	
	public void insertElement(String element) {
		int index;
		for (int i = 0; i < nHashFunctions; i++) {
			index = hashFunction(element, i) % (bloomFilter.length);
			bloomFilter[index]++;
		}
	}
	
	/*
	 * 
	 * Checks if the element given is in the bloom filter
	 * 
	 */
	
	public boolean isMember(String element) {
		boolean flag = true;
		int index;
		
		for(int i = 0; i < this.size; i++) {
			index = hashFunction(element, i);
			if(bloomFilter[index] == 0)
				flag = false;
		}
	return flag;
	}
	
	public int count(String element) {
		if(!this.isMember(element)) {
			
			return 0;
		
		} else {
			int index;
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < nHashFunctions; j++) {
				
				index = hashFunction(element, j) % (bloomFilter.length);
				if(bloomFilter[index] < min) {
					
					min = this.bloomFilter[index];
				
				}
				
			}
			return min;
		}
		
	}
	
	public void deleteElement(String element) {
		int index;
		for (int i = 0; i < nHashFunctions; i++) {
			
			index = hashFunction(element, i) % (bloomFilter.length);
			if(bloomFilter[index] > 0) {
				this.bloomFilter[index]--;
			}
		}
	}

}
