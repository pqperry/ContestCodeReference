//#
package geom;

import java.util.*;

public class GrahamScan {
	/*
	 * Graham Scan (convex hull)
	 * Running time: O(nlogn) [n = number of points];
	 * Tested on: UVA 11096, 10065, 10256, SPOJ BSHEEP, CodeForces 166B;
	 * Note: Points with identical coordinates seem to be kept regardless of whether you use < or <=
	 */
	public static Pt[] grahamScan(Pt[] pts) {
		if(pts.length <= 1) return pts;
		Pt pivotTemp = pts[0];
		for(Pt p : pts) 
			if(p.y < pivotTemp.y || (p.y == pivotTemp.y && p.x < pivotTemp.x)) pivotTemp = p;
		final Pt pivot = pivotTemp;
		Arrays.sort(pts, new Comparator<Pt>() {
			public int compare(Pt o1, Pt o2) {
				double ccw = SimpleGeom.ccw(pivot, o2, o1);
				if(ccw != 0) return Double.compare(ccw, 0);
				return Double.compare(o1.distance(pivot), o2.distance(pivot));
			}
		});
		
		Pt[] stack = new Pt[pts.length];
		int len = 0;
		for(int i = 0; i < pts.length; stack[len++] = pts[i++]) 
			while(len >= 2 && SimpleGeom.ccw(stack[len-2], stack[len-1], pts[i]) <= 0) len--; // use < 0 if you want colinear points
		return Arrays.copyOf(stack, len - (pts[0].equals(pts[1]) ? 1 : 0));
	}
	

}
