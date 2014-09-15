//#
package graph;

import java.util.*;

public class Dinics {
	
	/*
	 * Dinic's Algorithm (Maximum flow problem)
	 * Running time: O(V^2 * E). O(sqrt(V) * E) if all capacities are 1
	 * Tested on: UVA 820;
	 */
	public static int[][] dinic(int[][] graph, int source, int sink) {
		int len = graph.length;
		int[][] used = new int[len][len];
		
		while(true) {
			int[] dist = new int[len];
			Arrays.fill(dist, -1);
			dist[source] = 0;

			Queue<Integer> q = new ArrayDeque<Integer>();
			q.add(source);
			while(!q.isEmpty()) {
				int curr = q.poll();
				for(int next = 0; next < len; next++) {
					if(graph[curr][next] - used[curr][next] > 0 && dist[next] == -1) {
						dist[next] = dist[curr] + 1;
						q.add(next);
					}
				}
			}
			
			if(dist[sink] == -1) return used;

			int[] path = new int[len];
			int[] nextloc = new int[len];
			boolean[] seen = new boolean[len];
			int[] max = new int[len];
			
			int loc = 0;
			path[loc] = source;
			max[0] = Integer.MAX_VALUE;
			
			dfs: while(loc >= 0) {
				int curr = path[loc];
				if(curr == sink) {
					int flow = max[loc];
					for(; loc > 0; loc--) {
						used[path[loc-1]][path[loc]] += flow;
						used[path[loc]][path[loc-1]] -= flow;
					}
					Arrays.fill(seen, false);
					continue dfs;
				}
				
				for(int next = nextloc[curr]; next < len; next = ++nextloc[curr]) {
					if(graph[curr][next] - used[curr][next] > 0 && dist[next] == dist[curr] + 1 && !seen[next]) {
						path[++loc] = next;
						max[loc] = Math.min(max[loc-1], graph[curr][next] - used[curr][next]);
						seen[next] = true;
						continue dfs;
					}
				}
				loc--;
			}
		}
	}
	
}
