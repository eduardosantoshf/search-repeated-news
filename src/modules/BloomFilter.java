package modules;

import java.util.Random;

public class BloomFilter {
	
	private int nElements; //number of bits of the bloom filter
	private int size; //size do Bloom Filter
	private int nHashFunctions; //number of Hash Functions
	private Integer [] bloomFilter; //array of the Bloom Filter
	private int primeNumber; //will be needed in Hash Functions
	private int [][] RandomValuesA; //random values 
	private int [][] RandomValuesB; //random values
	
	/*********************************************** constructor ***********************************************/
	
	public BloomFilter(int nElements, int size, int nHashFunctions) {
		this.nElements = nElements;
		this.size = size;
		this.nHashFunctions = nHashFunctions;
	}
	
	/******************************************* getters and setters *******************************************/

	public int getnElements() {
		return nElements;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getnHashFunctions() {
		return nHashFunctions;
	}
	
	public Integer[] getBloomFilter() {
		return bloomFilter;
	}

	public void setnElements(int nElements) {
		this.nElements = nElements;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public void setnHashFunctions(int nHashFunctions) {
		this.nHashFunctions = nHashFunctions;
	} 
	
	public void setBloomFilter(Integer[] bloomFilter) {
		this.bloomFilter = bloomFilter;
	}
	
	/************************************************ methods ************************************************/
	
	//initializing the Bloom filter array
	public void initBloomFilter() {
		
		bloomFilter = new Integer[this.size];
		for(int i = 0; i < this.size; i++) {
			bloomFilter[i] = 0;
		}
	}
	
	//checks if a certain number is prime
	private boolean isPrime(int number) {
		for(int i = 2; i < number; i++) {
			if (number % i == 0) 
				return false;
		}
		return true;
	}
	
	//creating the hash function -> H (x) = ( (a * x + b) mod p) mod n
	private int hashFunction(String str, int k) { // k -> number of hash functions
		
		int hashValue = 0;
		for (int i = 0; i < str.length(); i++) {
			hashValue += ((RandomValuesA[k][i] * (int) str.charAt(i) + RandomValuesB[k][i] ) % this.primeNumber) % this.size; //calculate hash value to each char
		}
		
	return hashValue;
		
	}
	
	//calculates the random numbers needed in the hash function and initializes it
	public void initHashFunction (int length) {
		
		this.primeNumber = 999931; //big prime number, value can change
		
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

	//add element to the Bloom Filter
	public void insertElement(String element) {
		int index;
		for (int i = 0; i < nHashFunctions; i++) {
			index = hashFunction(element, i) % (bloomFilter.length);
			bloomFilter[index]++;
		}
	}
	
	//checks if the given element is in the Bloom Filter
	public boolean isMember(String element) {
		boolean flag = true;
		int index;
		
		for (int i = 0; i < nHashFunctions; i++) {
			index = hashFunction(element, i) % (bloomFilter.length);
			if (bloomFilter[index] == 0) {
				flag = false;
			}
		}
		return flag;
	}

}
