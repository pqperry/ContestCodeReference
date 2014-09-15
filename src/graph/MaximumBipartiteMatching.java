//#
package graph;

import java.util.*;

public class MaximumBipartiteMatching {	

	/*
	 * Maximum Bipartite Matching using Augmenting Paths
	 * Running time: O(EV)
	 * Tested on: UVA 11418, 10080;
	 * Usage: set ugraph = the adjacency list for one bipartite set.
	 */
	static List<Integer>[] ugraph;
	static int[] umatch, vmatch;
	
	public static void maximumBipartiteMatching(int vnum) {
		Arrays.fill(umatch = new int[ugraph.length], -1);
		Arrays.fill(vmatch = new int[vnum], -1);
		for(int u = 0; u < ugraph.length; u++) dfs(u, new boolean[ugraph.length]);
	}
	
	static boolean dfs(int u, boolean[] seen) {
		if(u == -1) return true;
		if(seen[u]) return false;
		seen[u] = true;
		
		for(int v : ugraph[u]) {
			if(!dfs(vmatch[v], seen)) continue;
			vmatch[v] = u;
			umatch[u] = v;
			return true;
		}
		return false;
	}
	
//	for(int u = 0; u < ugraph.length; u++)
//		if(umatch[u] == -1 && dfs(u, new boolean[ugraph.length])) u = 0;
	
	
	
//	static List<Integer>[] ugraph;
//	static int unum, vnum;
//	
//	static int[] umatch, vmatch;
//	
//	public static void maximumBipartiteMatching() {
//		Arrays.fill(umatch = new int[unum], -1);
//		Arrays.fill(vmatch = new int[vnum], -1);
//		for(int u = 0; u < unum; u++) dfs(u, new boolean[unum]);
//	}
//	
//	static boolean dfs(int u, boolean[] seen) {
//		if(u == -1) return true;
//		if(seen[u]) return false;
//		seen[u] = true;
//		
//		for(int v : ugraph[u]) {
//			if(dfs(vmatch[v], seen)) {
//				vmatch[v] = u;
//				umatch[u] = v;
//				return true;
//			}
//		}
//		return false;
//	}
}
