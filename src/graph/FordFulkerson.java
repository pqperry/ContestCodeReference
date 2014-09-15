//#
package graph;

public class FordFulkerson {

	public static int[][] fordFulkerson(int[][] graph, int source, int sink) {
		int[][] used = new int[graph.length][graph.length];
		while(dfs(graph, used, source, sink, new boolean[graph.length], Integer.MAX_VALUE/2) > 0);
		return used;
	}
	
	public static int dfs(int[][] graph, int[][] used, int curr, int sink, boolean[] seen, int max) {
		if(curr == sink) return max;
		if(seen[curr]) return 0;
		seen[curr] = true;
		for(int i = 0; i < graph.length; i++) {
			if(used[curr][i] >= graph[curr][i]) continue;
			int flow = dfs(graph, used, i, sink, seen, Math.min(max, graph[curr][i] - used[curr][i]));
			if(flow <= 0) continue;
			used[curr][i] += flow;
			used[i][curr] -= flow;
			return max;
		}
		return 0;
	}	

}

