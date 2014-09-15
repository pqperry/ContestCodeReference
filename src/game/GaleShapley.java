//#
package game;

import java.util.*;

public class GaleShapley {

	/*
	 * Gale-Shapley algorithm (stable marriage problem)
	 * Running time: O(n^2) [n = number of people]
	 * Tested on: LiveArchive 3837
	 * This is male-optimal. For female optimal, flip guy and gal
	 * guy[i][j] = guy i's jth most liked partner. Same goes for gal[i][j]
	 */
	static int[] mmatch, wmatch;
	
	public static void galeShapley(int[][] guy, int[][] gal) {
		Queue<Integer> free = new LinkedList<Integer>();
		for(int i = 0; i < guy.length; i++) free.add(i);
		
		int[][] rguy = new int[guy.length][gal.length], rgal = new int[gal.length][guy.length];
		for(int i = 0; i < guy.length; i++) 
			for(int j = 0; j < gal.length; j++) rguy[i][guy[i][j]] = j;
		
		for(int i = 0; i < gal.length; i++) 
			for(int j = 0; j < guy.length; j++) rgal[i][gal[i][j]] = j;
		
		int[] guynext = new int[guy.length];
		Arrays.fill(mmatch = new int[guy.length], -1);
		Arrays.fill(wmatch = new int[gal.length], -1);
		
		while(!free.isEmpty()) {
			int m = free.poll();
			int w = guy[m][guynext[m]]; // note: if there'll be single people at the end, w may be -1 here or below
			while(wmatch[w] != -1 && rgal[w][wmatch[w]] < rgal[w][m]) w = guy[m][++guynext[m]];
			if(wmatch[w] != -1) {
				free.add(wmatch[w]);
				mmatch[wmatch[w]] = -1;
			}
			mmatch[m] = w;
			wmatch[w] = m;
		}
	}
	
	
	
	// non-array based version. After running this, Person.match tells you the partner
	public static void galeShapley(Person[] men, Person[] wom) {
		Queue<Person> free = new LinkedList<Person>(Arrays.asList(men));
		for(Person p : men) p.genRevRank();
		for(Person p : wom) p.genRevRank();
				
		while(!free.isEmpty()) {
			Person m = free.poll();
			Person w = wom[m.rank[m.nexti]]; // note: if there'll be single people at the end, w may be null here or below
			while(w.match != null && w.rrank[w.match.id] < w.rrank[m.id]) w = wom[m.rank[++m.nexti]];
			if(w.match != null) {
				w.match.match = null;
				free.add(w.match);
			}
			m.match = w;
			w.match = m;
		}
	}
	
	private static class Person {
		int id; // id of the person.
		int[] rank; // ranking[i] = ith most liked partner
		
		// ---------- everything above this line must be set by user ----------
		
		int[] rrank; // revrank[i] = ranking of person i;
		int nexti = 0; // index of the next proposal in ranking[]
		Person match = null;
		
		void genRevRank() {
			rrank = new int[rank.length];
			for(int i = 0; i < rank.length; i++) rrank[rank[i]] = i;
		}
	}
}
