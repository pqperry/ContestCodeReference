//#
package graph;

import java.util.*;

public class Kosaraju {
	
	/*
	 * Kosaraju's Algorithm (Strongly Connected Components)
	 * Running time: O(V + E) [V = verticies, E = edges]
	 * Tested on: SPOJ MOWS 
	 * Params: graph = adjacency list; rgraph = adjacency list of transpose graph (graph with edge directions reversed)
	 * Returns: A list containing lists that are verticies of the strongly connected components
	 */
	public static List<Set<Integer>> kosaraju(List<Integer>[] graph, List<Integer>[] rgraph) {
		List<Integer> nodes = new ArrayList<Integer>();
		boolean[] seen = new boolean[graph.length];
		for(int i = 0; i < graph.length; i++) dfs(i, graph, nodes, seen);
		
		Collections.reverse(nodes);
		Arrays.fill(seen, false);
		
		List<Set<Integer>> ret = new ArrayList<Set<Integer>>();// List<Set<Integer>> is used for 2SAT. Replace with List<List<Integer>> for speed.
		for(int loc : nodes) {
			if(seen[loc]) continue;
			Set<Integer> comp = new HashSet<Integer>();
			dfs(loc, rgraph, comp, seen);
			ret.add(comp);
		}
		return ret;
	}
	
	// watch for stack overflow
	static void dfs(int loc, List<Integer>[] graph, Collection<Integer> nodes, boolean[] seen) {
		if(seen[loc]) return;
		seen[loc] = true;
		
		for(int next : graph[loc]) dfs(next, graph, nodes, seen);
		nodes.add(loc);
	}
}
