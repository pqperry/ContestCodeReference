//#
package graph;

import java.util.*;

public class EdmondsKarp {
	
	/*
	 * Edmonds-Karp Algorithm (Maximum flow problem)
	 * Running time: O(max{V * E^2, E * F}) (F = max flow)
	 * Tested on: UVA 820;
	 */
	public static int[][] edmondsKarp(int[][] graph, int source, int sink) {
		int len = graph.length;
		int[][] used = new int[len][len];
		
		while(true) {
			int[] parent = new int[len];
			Arrays.fill(parent, -1);
			parent[source] = source;
			
			int[] max = new int[len];
			max[source] = Integer.MAX_VALUE;
			
			Queue<Integer> q = new ArrayDeque<Integer>();
			q.add(source);
			
			while(!q.isEmpty()) {
				int curr = q.poll();
				if(curr == sink) {
					int flow = max[sink];
					while(curr != source) {
						int prev = parent[curr];
						used[prev][curr] += flow;
						used[curr][prev] -= flow;
						curr = prev;
					}
					break;
				}
				for(int next = 0; next < len; next++) {
					if(graph[curr][next] - used[curr][next] > 0 && parent[next] == -1) {
						parent[next] = curr;
						max[next] = Math.min(max[curr], graph[curr][next] - used[curr][next]);
						q.add(next);
					}
				}
			}
			
			if(parent[sink] == -1) return used; // for amount: for(int n : used[source]) sum += n;
		}
		
	}

}
