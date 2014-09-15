//#
package graph;

import java.util.*;

public class HopcroftKarp {
	
	/*
	 * Hopcroft-Karp Algorithm (Maximum Bipartite Matching)
	 * Running time: O(E * sqrt(V))
	 * Tested on: nothing;
	 * Usage: set ugraph = the adjacency list for one bipartite set. Set unum, vnum = number of nodes in the two sets
	 */
	static List<Integer>[] ugraph; //set this
	
	static int[] umatch, vmatch;
	static int[] dist;
	
	public static int hopcroftKarp(int vnum) {
		Arrays.fill(umatch = new int[ugraph.length], -1);
		Arrays.fill(vmatch = new int[vnum], -1);
		dist = new int[ugraph.length];
		
		int sum = 0;
		while(bfs())
			for(int i = 0; i < ugraph.length; i++)
				if(umatch[i] == -1 && dfs(i)) sum++;
		return sum;
	}
	
	static boolean bfs() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		for(int i = 0; i < umatch.length; i++) {
			if(umatch[i] == -1) {
				dist[i] = 0;
				q.add(i);
			}
			else dist[i] = -1;
		}
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			for(int v : ugraph[curr]) {
				if(vmatch[v] == -1) return true;
				else if(dist[vmatch[v]] == -1) {
					dist[vmatch[v]] = dist[curr] + 1;
					q.add(vmatch[v]);
				}
			}
		}
		return false;
	}
	
	static boolean dfs(int loc) {
		for(int v : ugraph[loc]) {
			if(vmatch[v] == -1 || ( dist[vmatch[v]] == dist[loc] + 1 && dfs(vmatch[v]) ) ) {
				vmatch[v] = loc;
				umatch[loc] = v;
				return true;
			}
		}
		dist[loc] = -1; // needed?
		return false;
	}

}
