//#
package graph;

import java.util.*;

import datastructures.DisjointSet;

public class Kruskal {
	
	/*
	 * Kruskal's algorithm (minimum spanning tree)
	 * Running Time: O(nlgn) or O(n*alpha(n)) if edges are already pre-sorted by cost
	 * Tested on: Nothing;
	 * ds is the disjoint set used in the calculation and may include useful information. 
	 * For example, the number of trees in the MST forest is given by ds.sets if the vertex ids are 0...n
	 */
	public static DisjointSet ds;
	
	public static Edge[] kruskal(Edge[] edges, int maxVertexId) {
		ds = new DisjointSet(maxVertexId);
		Edge[] ret = new Edge[edges.length];
		int wr = 0;
		
		Arrays.sort(edges, new Comparator<Edge>() {
			public int compare(Edge o1, Edge o2) {
				return o1.cost - o2.cost;
			}
		});
		
		for(Edge e : edges) {
			if(ds.find(e.u) == ds.find(e.v)) continue;
			ret[wr++] = e;
			ds.union(e.u, e.v);
		}
		return Arrays.copyOf(ret, wr);
	}
	
	static class Edge {
		int u, v; // vertex ids
		int cost;
	}
}
