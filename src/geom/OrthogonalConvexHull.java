//#
package geom;

import java.util.*;

public class OrthogonalConvexHull {
	
	/*
	 * Orthogonal Convex Hull
	 * Running time: O(nlgn)
	 * Tested on: ECNA2013-D
	 * Returns: Array of hull points in ccw order with array[0] = points with smallest y and x lexicographically.
	 * Note: The orthogonal convex hull will contain repeated points each time there's a zero-width spike.
	 */
	public static Pt[] orthogonalConvexHull(List<Pt> pts) {
		Collections.sort(pts, new Comparator<Pt>() {
			public int compare(Pt o1, Pt o2) {
				if(o1.y != o2.y) return Double.compare(o1.y, o2.y); //consider using EPS
				return Double.compare(o1.x, o2.x);
			}
		});
		
		Deque<Pt> ret = inner(pts, 1);
		Deque<Pt> temp1 = inner(pts, -1);
		Collections.reverse(pts);
		
		Deque<Pt> temp2 = inner(pts, 1);
		while(!temp2.isEmpty()) ret.add(temp2.pollLast());
		ret.addAll(inner(pts, -1));
		while(!temp1.isEmpty()) ret.add(temp1.pollLast());
		
		ret.addFirst(ret.pollLast());
		return ret.toArray(new Pt[0]);
	}
	
	static Deque<Pt> inner(List<Pt> pts, int xdir) {
		Deque<Pt> ret = new ArrayDeque<Pt>();
		double max = -xdir * Double.POSITIVE_INFINITY;
		for(Pt p : pts) {
			if((p.x - max) * xdir <= 0) continue; //use < to keep colinear points
			
			if(!ret.isEmpty() && p.y != ret.peekLast().y) ret.add(new Pt(max, p.y));
			else ret.pollLast();
			
			ret.add(p);
			max = p.x;
		}
		return ret;
	}

}
