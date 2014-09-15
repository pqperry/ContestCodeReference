//#
package graph;
import java.util.*;


public class TopologicalSort {
	
	/*
	 * Topological Sort.
	 * The input must be the adjacency list representation of a DAG
	 * Tested on: nothing
	 * Complexity: O(E + V)
	 * Returns: array of indexes to adj that is the sorted order.
	 */
	public static int[] toposort(List<Integer>[] adj) {
		int[] indeg = new int[adj.length];
		for(List<Integer> list : adj)
			for(int i : list) indeg[i]++;
		
		int[] stack = new int[adj.length], ret = new int[adj.length];
		int loc = 0, wr = 0;
		for(int i = 0; i < adj.length; i++) 
			if(indeg[i] == 0) stack[loc++] = i;
		
		while(loc > 0) {
			int curr = stack[--loc];
			ret[wr++] = curr;
			for(int next : adj[curr]) 
				if(--indeg[next] == 0) stack[loc++] = next;
		}
		return ret;
	}
	
}

