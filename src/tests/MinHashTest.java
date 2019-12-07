package tests;

import java.util.*;

import modules.Shingles;
import modules.MinHash;

public class MinHashTest {

	public static void main(String[] args) {
		
		String teste1 = "ISLAMABAD: As per Federal Government�s Gazette Notification dated June 04, 2015 the old design banknotes will cease to be legal tender with effect from December 01, 2016. Therefore it has now been decided to phase out all remain in gold design banknotes of Rs 10, 50, 100 and 1000, said a statement issued by the State Bank of Pakistan here on Friday.</strongThe Rs. 5 banknote and the old design Rs. 500 banknote have already been demonetized.It is worth mentioning that the State Bank of Pakistan issued a new design banknote series which started with the issuance of Rs20 denomination banknote in 2005 to improve the security durability and aesthetic quality of banknotes.The process of issuance of complete series of new design banknotes comprising eight denominations (Rs 5 10 20 50 100 500 1000 and 5000) was completed in 2008.The commercial and microfinance banks will accept the old design banknotes of Rs 10 50 100 and 1000 and exchange the same with the new design banknotes and coins of all denominations up to 30th November 2016 only.However, SBP BSC field offices will continue to accept the old design banknotes of Rs 10, 50, 100 and 1000 from general public up to December 31 2021. The last day to exchange all old design banknotes from banks is November 30, 2016.All old design banknotes shall cease to be legal tender on December 01 2016 while last day to exchange all such banknotes from SBP BSC field offices is December 31, 2021";
		String teste2 = "ISLAMABAD: �The old design banknotes will cease to be legal tender with effect from December 1, 2016 as per Federal Government�s Gazette Notification.According to SBP notification, it has now been decided to phase out all remaining old design banknotes of Rs 10, 50, 100 and 1000 while the Rs 5 banknote and the old design Rs 500 banknote have already been demonetized.It is worth mentioning that SBP issued a new design banknote series, which started with the issuance of Rs 20 denomination banknote in 2005, to improve the security, durability and aesthetic quality of banknotes.The process of issuance of complete series of new design banknotes, comprising eight denominations (Rs 5, 10, 20, 50, 100, 500, 1000 and 5000) was completed in 2008.The commercial microfinance banks will accept the old design banknotes of Rs 10, 50, 100 and 1000 and exchange the same with the new design banknotes and coins of all denominations up to 30th November 2016 only.However SBP BSC field offices will continue to accept the old design banknotes of Rs 10, 50, 100 &amp; 1000 from general public up to December 31, 2021.The last day to exchange all old design banknotes from banks is November 30, 2016.All old design banknotes shall cease to be legal tender on December 01, 2016 while last day to exchange all such banknotes from SBP BSC field offices is December 31, 2021, it added";	
		
		Shingles sh = new Shingles(teste1, 10);
		Shingles sh2 = new Shingles(teste2, 10);
		MinHash minhash = new MinHash(100);
		ArrayList<Integer> vetor1 = minhash.createMinHash(sh.getShingles());
		ArrayList<Integer> vetor2 = minhash.createMinHash(sh2.getShingles());
		
		System.out.println("Array of hash values generated for the first string: ");
		minhash.printVetor(vetor1);
		System.out.println();
		System.out.println();
		System.out.println("Array of hash values generated for the second string: ");
		minhash.printVetor(vetor2);
		System.out.println();
		
		double numIguais = 0;
		
		//check strings similarities
		for(int i = 0; i < vetor1.size(); i++) {
			
				if(vetor2.contains(vetor1.get(i))) {
					numIguais++;
				}
			
		}
		double sim = numIguais/100;
		
		System.out.println();
		System.out.println("These 2 strings have a similarity of: " + sim);
		

	}

}