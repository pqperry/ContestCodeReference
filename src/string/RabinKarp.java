//# keepclass
package string;
import java.util.*;

public class RabinKarp {
	
	/*
	 * Rabin-Karp algorithm (multiple pattern string searching)
	 * Running time: O(n+m) on average [n = text length, m = sum of lengths of patterns]. O(nm) worst case.
	 * Tested on: Nothing
	 * Note: Poor performance if pattern lengths are very different. 
	 * Note: this version assumes the hashcodes for all patterns are unique, so always pray when using this.
	 */
	static final long hashconst = 31;
	int minlen = Integer.MAX_VALUE;
	
	Map<Long, String> map = new HashMap<Long, String>();
	
	public RabinKarp(String[] patterns) {
		for(String s : patterns) minlen = Math.min(minlen, s.length());
		for(String s : patterns) {
			long hash = 0;
			for(int i = 0; i < minlen; i++) hash = hash * hashconst + s.charAt(i);
			map.put(hash, s);
		}		
	}
	
	public Set<String> contains(String s) {
		Set<String> ret = new HashSet<String>();
		Set<Long> seen = new HashSet<Long>();
		long hash = 0, mult = 1;
		int pos = 0;
		while(pos < minlen) {
			hash = hash * hashconst + s.charAt(pos);
			if(pos++ != 0) mult *= hashconst;
		}
		while(true) {
			if(!seen.contains(hash)) {
				String targ = map.get(hash);
				if(targ != null && s.regionMatches(false, pos - minlen, targ, 0, targ.length())) ret.add(targ);
				if(pos == s.length()) break;
			}
			hash -= mult * s.charAt(pos - minlen);
			hash = hash * hashconst + s.charAt(pos);
		}
		return ret;
	}
	
	// Slow if there's a lot of matches
	public Map<String, SortedSet<Integer>> search(String s) {
		Map<String, SortedSet<Integer>> ret = new HashMap<String, SortedSet<Integer>>();
		for(String pat : map.values()) ret.put(pat, new TreeSet<Integer>());

		long hash = 0, mult = 1;
		int pos = 0;
		while(pos < minlen) {
			hash = hash * hashconst + s.charAt(pos);
			if(pos++ != 0) mult *= hashconst;
		}
		while(true) {
			String targ = map.get(hash);
			if(targ != null && s.regionMatches(false, pos - minlen, targ, 0, targ.length())) ret.get(targ).add(pos - minlen);
			if(pos == s.length()) break;
			hash -= mult * s.charAt(pos - minlen);
			hash = hash * hashconst + s.charAt(pos);
		}
		return ret;
	}


}
