package tests;

import java.util.*;

import modules.Shingles;
import modules.MinHash;

public class MinHashTest {

	public static void main(String[] args) {
		
		String teste1 = "DHARAMSALA: The persistent rain abandoned the match between Bangladesh and Ireland in the first round group A of the World Twenty20 here at the Himachal Pradesh Cricket Association Stadium on Friday.Thus, Ireland were knocked out of the World T20, when play was called off at 10:20pm local time, after rain had stopped the game after eight overs in the Bangladesh innings.Now, the Bangladesh-Oman encounter will decide who goes through from Group A, with both teams on three points each while the Netherlands and Ireland are on one point each.The match was always at the mercy of the weather after rain forced the Oman-Netherlands game earlier in the afternoon to be abandoned. Rain had stopped at around 7:30pm and allowed the match to start at 9:45pm, but it relented for just over an hour.The rain first delayed the match and was later restricted to 12-overs-a-side before Ireland captain William Porterfield won the toss and sent Bangladesh into bat first.However, Bangladesh batted at nearly 12 runs per over and when they reached 94 for two in just eight overs, rain again stopped the play and later the match was abandoned. Both teams were allotted one point each in the no-result match.Opener Tamim Iqbal made 47 off 26 balls, hitting four sixes and three boundaries.";
		String teste2 = "DHARAMSALA: Ireland captain William Porterfield won the toss and elected to field first against Bangladesh in the eighth match of the of the World Twenty20 (first round, group A) here at the Himachal Pradesh Cricket Association on Friday.Both teams are playing their second match in the tournament. The match, delayed by rain, has been  restricted to 12-overs-a-side.This will be a must-win game for Ireland as they were stunned by Oman in their first match.Rain had washed out the previous match of the day, between Oman and Netherlands, which was called off an hour and 20 minutes after the scheduled start.Bangladesh brought in Abu Hider and Mohammad Mithun in place of Nasir Hossain and Arafat Sunny, who is flying to Chennai on Saturday to have his bowling action tested after it was reported by the ICC. Taskin Ahmed, the other bowler whose action was reported, would play against Ireland.Ireland brought in left-arm spinner George Dockrell for seamer Craig Young.Teams:Bangladesh: Tamim Iqbal, Soumya Sarkar, Sabbir Rahman, Mushfiqur Rahim (wk), Shakib Al Hasan, Mahmudullah, Mithun Ali, Mashrafe Mortaza (captain), Al-Amin Hossain, Abu Hider Roni, Taskin AhmedIreland: William Porterfield (captain), Paul Stirling, Gary Wilson, Niall O'Brien (wk), Kevin O'Brien, Andrew Poynter, Andy McBrine, Max Sorensen, George Dockrell, Boyd Rankin, Craig YoungShakib Al Hasan is 21 runs away from becoming the first Bangladeshi batsman to reach 1,000 runs. Tamim Iqbal is close behind too, needing 58 runs to reach the same milestoneBangladesh has a 3-1 win-loss record over Ireland in T20Is but their last game was in July 2012";	
		Shingles sh = new Shingles(teste1);
		Shingles sh2 = new Shingles(teste2);
		
		//sh.printSh();
		MinHash minhash = new MinHash();
		ArrayList<Integer> vetor1 = minhash.createMinHash(sh.getShingles());
		minhash.printVetor(vetor1);
		System.out.println();
		ArrayList<Integer> vetor2 = minhash.createMinHash(sh2.getShingles());
		minhash.printVetor(vetor2);
		
		int numIguais = 0;
		
		for(int i = 0; i < vetor1.size(); i++) {
			
				if(vetor2.contains(vetor1.get(i))) {
					numIguais++;
				}
			
		}
		double sim = numIguais/100;
		System.out.println();
		System.out.println(numIguais + " teste");
		

	}

}