//#
package graph;

import java.awt.Point;
import java.util.*;

public class MinCut {

	public Point[] minCut(int[][] graph, int[][] used, int source) {
		int len = graph.length;
		Set<Point> ret = new HashSet<Point>();
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(source);
		while(!q.isEmpty()) {
			int curr = q.poll();
			for(int next = 0; next < len; next++) {
				if(graph[curr][next] == 0) continue;
				if(graph[curr][next] > used[curr][next]) q.add(next);
				else ret.add(new Point(Math.min(curr, next), Math.max(curr, next)));
			}
		} 
		return ret.toArray(new Point[0]);
	}


}
