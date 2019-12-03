package tests;

import modules.BloomFilter;

import java.util.*;
import java.io.*;

public class BloomFilterTest {

	public static void main(String[] args) {
		
		int nElements = 5; //number of elements to insert 
		int size = 8000; //size of the bloom filter
		int stringLen = 40; //length of the strings
		int nHashFunctions = 7; //number of hash functions
		
		//initializing bloom filter
		BloomFilter bf = new BloomFilter(nElements, size, nHashFunctions);
		bf.initBloomFilter();
		
		//generating 1000 random strings
		 char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
				 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
				 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		 
		 ArrayList<String> generatedStrings = new ArrayList<String>();
		 
		 for (int i = 0; i < nElements; i++) {
			 
			 String randomString = ""; //initialize empty random string
			 StringBuilder sb = new StringBuilder(stringLen); //initialize StringBuilder with size = stringLen
			 
			 for (int k = 0; k < stringLen; k++) {
				 
				 int index = (int) (alpha.length * Math.random()); //choose random char from alphabet
				 sb.append(alpha[index]); //concatenate each char with random string
				 
			 }
			 
			 generatedStrings.add(sb.toString()); //add each random string to the generated strings array
			 
			 //ERRO ESTÁ NA LINHA DE BAIXO AO INSERIR NO BLOOM FILTER
			 //CHAMA A HASH FUNCTION E DÁ ERRO
			 bf.insertElement(sb.toString()); //add each random string to the bloom filter
			 
		 }
		 
		 //print generated strings
		 for (String s: generatedStrings) {
			 
			 System.out.println(s);
			 
		 }
		 
	}

}
