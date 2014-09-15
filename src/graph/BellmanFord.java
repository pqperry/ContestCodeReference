//#
package graph;

import java.util.*;

public class BellmanFord {

	/*
	 * Bellman-Ford (Shortest path allowing for negative costs)
	 * Running time: O(VE) [V = verticies, E = edges]
	 * Tested on: UVA 558 
	 * Returns: null if there exists a negative cycle, else an int[] where array[i] = smallest cost from start to i
	 */
	public static int[] bellmanFord(List<Integer>[] adj, List<Integer>[] dist, int start) {
		int[] best = new int[adj.length];
		Arrays.fill(best, Integer.MAX_VALUE/2);
		best[start] = 0;
		
		for(int i = 0; i < adj.length; i++) 
			for(int j = 0; j < adj.length; j++) 
				for(int k = 0; k < adj[j].size(); k++) 
					best[adj[j].get(k)] = Math.min(best[adj[j].get(k)], best[j] + dist[j].get(k));

		for(int j = 0; j < adj.length; j++) 
			for(int k = 0; k < adj[j].size(); k++) 
				if(best[j] + dist[j].get(k) < best[adj[j].get(k)]) return null;

		return best;
	}
	
	
	// This version stores the actual path. Untested
	public static int[][] bellmanFord2(List<Integer>[] adj, List<Integer>[] dist, int start) {
		int[] best = new int[adj.length], prev = new int[adj.length];
		Arrays.fill(best, Integer.MAX_VALUE/2);
		best[start] = 0;
		
		for(int i = 0; i < adj.length; i++) {
			for(int j = 0; j < adj.length; j++) {
				for(int k = 0; k < adj[j].size(); k++) {
					int to = adj[j].get(k);
					if(best[j] + dist[j].get(k) < best[to]) {
						best[to] = best[j] + dist[j].get(k);
						prev[to] = j;
					}
				}
			}
		}

		for(int j = 0; j < adj.length; j++) 
			for(int k = 0; k < adj[j].size(); k++) 
				if(best[j] + dist[j].get(k) < best[adj[j].get(k)]) return null;

		return new int[][]{best, prev};
	}

}

