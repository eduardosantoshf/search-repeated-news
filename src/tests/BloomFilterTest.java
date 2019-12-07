package tests;

import modules.BloomFilter;


import java.util.*;

public class BloomFilterTest {

	public static void main(String[] args) {
		
		int nElements = 1000; //number of elements to insert 
		int size = 10000; //size of the bloom filter
		int stringLen = 40; //length of the strings
		int nHashFunctions = 3; //number of hash functions
		
		//initializing bloom filter
		BloomFilter bf = new BloomFilter(nElements, size, nHashFunctions);
		bf.initBloomFilter();
		bf.initHashFunction(stringLen);
		//generating 1000 random strings
		 char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
				 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
				 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		 
		 ArrayList<String> generatedStrings = new ArrayList<String>(); //creating an ArrayList
		 
		 for (int i = 0; i < nElements; i++) {
			 
			 StringBuilder sb = new StringBuilder(stringLen); //initialize StringBuilder with size = stringLen
			 
			 for (int k = 0; k < stringLen; k++) {
				 
				 int index = (int) (alpha.length * Math.random()); //choose random char from alphabet
				 sb.append(alpha[index]); //concatenate each char with random string
				 
			 }
			 
			 generatedStrings.add(sb.toString()); //add each random string to the generated strings array
			 
			 bf.insertElement(sb.toString()); //add each random string to the bloom filter
			 
		 }
		 
		 int probablyInBF = 0;
		 int notInBF = 0;
		 
		 System.out.println("Check if the generated string is in the Bloom Filter:");
		 System.out.println("----------------------------------------------------");
		 for (String s: generatedStrings) {
			 if (bf.isMember(s)) {
				 probablyInBF++;
				 System.out.printf("The string %d is probably in the bloom filter\n", generatedStrings.indexOf(s)+1);
				 
			 } else {
				 notInBF++;
				 System.out.printf("The string %d is not in the bloom filter\n", generatedStrings.indexOf(s)+1);
			 }
		 }
		 
		 System.out.println("----------------------------------------------------");
		 System.out.println("Number of strings that probably are in the Bloom Filter: " + probablyInBF);
		 System.out.println("Number of strings that are not in the Bloom Filter: " + notInBF);
		 
		 int falsePositives = FalsePositives(bf, stringLen);
		 System.out.println("----------------------------------------------------");
		 System.out.println("Number of false positives (using another set of random strings to test): " + falsePositives);
		 
		 System.out.println();
		 System.out.println("----------------------------------------------------");
		 System.out.println();
		 System.out.println("Test number of false positives given a different number of hash functions: ");
		 System.out.println();
		 testKFunctions(generatedStrings, stringLen);
		 
		 System.out.println();
		 System.out.println("----------------------------------------------------");
		 System.out.println();
		 System.out.println("Test number of false positives given a different size of the bloom filter: ");
		 System.out.println();
		 testSizeBF(generatedStrings, stringLen);
		 
	}
	
	public static int FalsePositives(BloomFilter B, int length) {
        int falsePositives = 0;
        int	i;
        boolean flag;
        char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
				 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
				 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        
        for (i = 0; i < 1000; i++) {
        	StringBuilder sb = new StringBuilder(length); //initialize StringBuilder with size = stringLen
			 
			 for (int k = 0; k < length; k++) {
				 
				 int index = (int) (alpha.length * Math.random()); //choose random char from alphabet
				 sb.append(alpha[index]); //concatenate each char with random string
				 
			 }  
			 flag = B.isMember(sb.toString());
			 if (flag)
                falsePositives++;
        }
        return falsePositives;
    }
	
	public static void testKFunctions(ArrayList<String> strings, int strlen) {
  
            
            int falsePositives;
            int size = 1000;
            for (int k = 1; k < 16; k++) {
                BloomFilter b = new BloomFilter(size, (int)(size/0.1), k);
                b.initBloomFilter();
                b.initHashFunction(strlen);
                int len = 40;
                for(String s: strings) {
                	b.insertElement(s);
                }
                falsePositives = FalsePositives(b,len);
                System.out.printf("With %d hash functions, we have %d false positives\n", k, falsePositives);

            }

        
    }
	
	public static void testSizeBF(ArrayList<String> strings, int strlen) {
		  
        
        int falsePositives;
        int size = 1000;
        double sub;
        for (sub = 0.1; sub	< 0.7; sub = sub + 0.1) {
            BloomFilter b = new BloomFilter(size, (int)(size/sub), 3);
            b.initBloomFilter();
            b.initHashFunction(strlen);
            int len = 40;
            for(String s: strings) {
            	b.insertElement(s);
            }
            falsePositives = FalsePositives(b,len);
            System.out.printf("With size %d of the bloom filter, we have %d false positives\n", (int)(size/sub), falsePositives);

        }

    
}
	
	
	

}