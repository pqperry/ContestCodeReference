//#
package string;

import java.util.*;

public class KMP {

	/*
	 * Knuth-Morris-Pratt algorithm (single pattern string search)
	 * Running time: O(n+m) [n = text.length, m = match.length]
	 * Tested on: nothing;
	 */
	public static Integer[] kmp(String text, String pattern) {
		List<Integer> ret = new ArrayList<Integer>();
		char[] t = text.toCharArray(), p = pattern.toCharArray();
		
		int[] fail = new int[p.length];
		for(int i = 2; i < p.length; i++) 
			if(p[fail[i-1]+1] == p[i]) fail[i] = fail[i-1]+1;
		
		for(int i = 0, loc = 0; i < t.length; i++) {
			while(t[i] != p[loc] && loc != 0) loc = fail[loc];
			if(++loc >= p.length) {
				ret.add(i - p.length + 1);
				if(t[i] == p[loc = fail[loc-1]]) loc++;
			}			
		}
		return ret.toArray(new Integer[0]);
	}
}

