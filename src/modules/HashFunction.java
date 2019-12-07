package modules;

import java.util.Random;

public class HashFunction {
	
	private int primeNumber;
	private int [] RandomValuesA; //random values 
	private int [] RandomValuesB; //random values
	private int nHashFunctions;
	
	/*********************************************** constructor ***********************************************/
	
	public HashFunction(int nHashFunctions) {
		this.nHashFunctions = nHashFunctions;
		initHashFunction();
	}
	
	/************************************************* getters *************************************************/
	
	public int getPrimeNumber() {
		return primeNumber;
	}

	public int[] getRandomValuesA() {
		return RandomValuesA;
	}

	public int[] getRandomValuesB() {
		return RandomValuesB;
	}

	public int getnHashFunctions() {
		return nHashFunctions;
	}
	
	/************************************************ methods ************************************************/
	
	//checks if a certain number is prime, it will help creating the hash functions
	private boolean isPrime( int number) {
		for(int i = 2; i < number; i++) {
			if (number % i == 0) 
				return false;
		}
		return true;
	}
	
	//calculates the random numbers needed in the hash function
	public void initHashFunction() {
		
		this.primeNumber = 999931; //big prime number, value can change
		
		if (this.primeNumber % 2 == 0) {
            this.primeNumber++;
        }

        while (isPrime(this.primeNumber) != true) {
            this.primeNumber += 2;
        }
		
		this.RandomValuesA = new int[this.nHashFunctions];
		this.RandomValuesB = new int[this.nHashFunctions];
		
		Random random = new Random();
		
		for (int i = 0; i < this.nHashFunctions; i++) {
				this.RandomValuesA[i] = random.nextInt(primeNumber - 1);
				this.RandomValuesB[i] = random.nextInt(primeNumber - 1);
		}
		
	}
	
	//calculate hash values
	public int gh(String s, int i) {
		
		int hashJava = s.hashCode();
		int h = 0;
		
		h = Math.abs((RandomValuesA[i] * hashJava + RandomValuesB[i]));
		h = h % this.primeNumber;
		
		return h;
		
	}

}