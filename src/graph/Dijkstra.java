//#
package graph;

import java.util.*;

public class Dijkstra {

	/*
	 * Dijkstra's algorithm (shortest path in a positively weighted directed graph)
	 * Running time: O((V^2)lgV)
	 * Tested on: nothing;
	 * Usage: dist[i][j] = distance between i and j. Set to Integer.MAX_VALUE/2 if no path
	 */	
    public static double[] dijkstra(double[][] dist, int start) {
        final double[] cost = new double[dist.length];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        cost[start] = 0;
        boolean[] done = new boolean[dist.length];
          
        TreeSet<Integer> pq = new TreeSet<Integer>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                if(cost[o1] != cost[o2]) return Double.compare(cost[o1], cost[o2]);
                return o1 - o2;
            }
        });
        pq.add(start);
          
        while(!pq.isEmpty()) {
            int curr = pq.pollFirst();
            done[curr] = true;
            for(int i = 0; i < dist.length; i++) {
            	double newDist = cost[curr] + dist[curr][i];
                if(done[i] || newDist >= cost[i]) continue;
                pq.remove(i);
            	cost[i] = newDist;
            	pq.add(i);
            }
        }
        return cost;
    } 


	// O((E+V)lg(E+V)) adjacency list version
    public static double[] dijkstra2(List<Integer>[] adj, List<Double>[] dist, int start) {
        final double[] cost = new double[adj.length];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        cost[start] = 0;
        boolean[] done = new boolean[adj.length];
          
        TreeSet<Integer> pq = new TreeSet<Integer>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                if(cost[o1] != cost[o2]) return Double.compare(cost[o1], cost[o2]);
                return o1 - o2;
            }
        });
        pq.add(start);
          
        while(!pq.isEmpty()) {
            int curr = pq.pollFirst();
            done[curr] = true;
            for(int i = 0; i < adj[curr].size(); i++) {
                int to = adj[curr].get(i);
                double newDist = cost[curr] + dist[curr].get(i);
                if(done[to] || newDist >= cost[to]) continue;
                pq.remove(to);
            	cost[to] = newDist;
            	pq.add(to);
            }
        }
        return cost;
    }
    
}

