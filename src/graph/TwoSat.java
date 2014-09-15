package graph;

import java.util.*;

public class TwoSat {

	/*
	 * Aspvall, Plass and Tarjan's Strongly Connected Components based 2SAT solver.
	 * Running time: O(V + E) [V = verticies, E = edges]
	 * Tested on: nothing
	 * Returns: boolean[] of one possible assignment or null if unsatisfiable.
	 * Params: graph = implication graph as an adjacency list. rgraph = transpose of graph (graph with edge directions reversed)
	 */
	public static Boolean[] twoSat(List<Integer>[] graph, List<Integer>[] rgraph) {
		List<Set<Integer>> sccs = Kosaraju.kosaraju(graph, rgraph);
		Boolean[] ret = new Boolean[graph.length / 2];
		
		for(int i = 0; i < sccs.size(); i++) {
			Set<Integer> scc = sccs.get(i);
			boolean mark = false;
			for(int node : scc) 
				if(ret[node / 2] != null) mark = ret[node / 2] ^ ((node & 1) == 1);
					
			for(int node : scc) {
				if(scc.contains(node ^ 1)) return null;
				ret[node / 2] = mark ^ ((node & 1) == 1);
			}
		}
		return ret;
	}
		
	// This version only returns whether or not the implication graph is satisfiable
	public static boolean twoSatSatable(List<Integer>[] graph, List<Integer>[] rgraph) {
		List<Set<Integer>> sccs = Kosaraju.kosaraju(graph, rgraph);
		for(Set<Integer> scc : sccs)
			for(int node : scc)
				if(scc.contains(node ^ 1)) return false;
		return true;
	}
	
	/*
	 * The following three methods are utility methods to help generate the graph. You may chose to ignore them and
	 * generate the graph yourself if desired.
	 * graph[2*i] is the node for variable i. graph[2*i + 1] is the node for the negation of variable i.
	 * Example: for clause (x0 v ~x1), we want (~x0 -> ~x1) and (x1 -> x0), so 
	 * graph[2*0+1].add(2*1+1), rgraph[2*1+1].add(2*0+1), graph[2*1].add(2*0), rgraph[2*0].add[2*1];
	 */
	
	// Generates the adjacency list for a 2sat instance with "vars" number of variables. Call this once for graph and again for rgraph.
	@SuppressWarnings("unchecked")
	public static List<Integer>[] getGraph(int vars) {
		List<Integer>[] ret = new ArrayList[vars * 2];
		for(int i = 0; i < ret.length; i++) ret[i] = new ArrayList<Integer>();
		return ret;
	}
	
	// Adds implication "from" implies "to". negFrom should be true if you want "~from". Likewise for negTo
	public static void addImplication(int from, boolean negFrom, int to, boolean negTo, List<Integer>[] graph, List<Integer>[] rgraph) {
		from = negFrom ? (2 * from) ^ 1 : (2 * from);
		to = negTo ? (2 * to) ^ 1 : (2 * to);
		graph[from].add(to);
		rgraph[to].add(from);
	}
	
	// Adds clause (c1 v c2). neg1 should be true if you want ~c1. Likewise for neg2 and c2.
	public static void addClause(int c1, boolean neg1, int c2, boolean neg2, List<Integer>[] graph, List<Integer>[] rgraph) {
		addImplication(c1, !neg1, c2, neg2, graph, rgraph);
		addImplication(c2, !neg2, c1, neg1, graph, rgraph);
	}
}
