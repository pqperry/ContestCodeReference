//#
package graph;

import java.util.*;

public class EulerianPathCircuit {

	/*
	 * Eulerian Circuit
	 * Running time: O(E)
	 * Tested on: Nothing;
	 * This also finds an Eulerian path if you call it with start equal to one of the path end verticies.
	 */
	public static int[] euler(boolean[][] adj, int start) {
		int len = adj.length * adj.length;
		
		int[] stack = new int[len], ptr = new int[len];
		stack[0] = start;
		int wr = 1;
		
		int[] ncheck = new int[adj.length];
		
		int[] val = new int[len], next = new int[len];
		next[0] = 1;
		int wr2 = 2;
		
		out: while(wr > 0) {
			int curr = stack[wr-1];
			for(int cc = ncheck[curr]; cc < adj.length; cc = ++ncheck[curr]) {
				if(!adj[curr][cc]) continue;
				adj[curr][cc] = adj[cc][curr] = false; //adj[curr][cc] = false; for directed graphs
				
				val[wr2] = stack[wr] = cc;
				next[wr2] = next[ptr[wr-1]];
				next[ptr[wr-1]] = wr2;
				ptr[wr++] = wr2++;
	
				continue out;
			}
			wr--;
		}
		
		int[] ret = new int[len];
		int rd = 0, loc = 0;
		do ret[rd++] = val[loc = next[loc]]; while(loc != 0);
		return Arrays.copyOf(ret, rd-2);
	}
	
	/*
	 * Note: if a node has an edge to itself, do deg[i][i] += 2;
	 * otherwise, deg[i][j]++, deg[j][i]++ for undirected
	 * deg[i][j]++ for directed
	 */
	
	public static boolean hasUndirectedCircuit(int[][] adj) {
		int[] deg = new int[adj.length];
		boolean[] seen = new boolean[adj.length];
		boolean first = true;
		
		for(int i = 0; i < adj.length; i++) {
			for(int j = 0; j < adj.length; j++) {
				deg[i] += adj[i][j];
				if(adj[i][j] > 0 && first) {
					dfs(adj, seen, i);
					first = false;
				}
			}
		}
		
		for(int i = 0; i < adj.length; i++)
			if(deg[i] % 2 != 0 || (!seen[i] && deg[i] > 0)) return false;
		return true;
	}
	
	public static boolean hasUndirectedPath(int[][] adj) {
		int[] deg = new int[adj.length];
		boolean[] seen = new boolean[adj.length];
		boolean first = true;
		
		for(int i = 0; i < adj.length; i++) {
			for(int j = 0; j < adj.length; j++) {
				deg[i] += adj[i][j];
				if(adj[i][j] > 0 && first) {
					dfs(adj, seen, i);
					first = false;
				}
			}
		}
		
		int odd = 0;
		for(int i = 0; i < adj.length; i++) {
			if(!seen[i] && deg[i] > 0) return false;
			if(deg[i] % 2 != 0) odd++;
		}
		return odd <= 2;
	}
	
	/*
	 * Tested on: SPOJ WORDS1
	 */
	public static boolean hasDirectedPath(int[][] adj) {
		int[] deg = new int[adj.length];
		boolean[] seen = new boolean[adj.length];
		boolean[] hasEdge= new boolean[adj.length];
		boolean first = true;
		
		for(int i = 0; i < adj.length; i++) {
			for(int j = 0; j < adj.length; j++) {
				if(adj[i][j] <= 0) continue;
				deg[i] += adj[i][j];
				deg[j] -= adj[i][j];
				hasEdge[i] = hasEdge[j] = true;
				if(first) {
					dfs(adj, seen, i);
					first = false;
				}
			}
		}
		
		int neg = 0, pos = 0;
		for(int i = 0; i < adj.length; i++) {
			if(!seen[i] && hasEdge[i]) return false;
			if(deg[i] == 0) continue;
			else if(deg[i] == -1) neg++;
			else if(deg[i] == 1) pos++;
			else return false;
		}
		return neg <= 1 && pos <= 1;
	}
	
	public static void dfs(int[][] adj, boolean[] seen, int loc) {
		seen[loc] = true;
		for(int i = 0; i < adj.length; i++) 
			if(adj[loc][i] + adj[i][loc] > 0 && !seen[i]) dfs(adj, seen, i);
	}
	
	
}
