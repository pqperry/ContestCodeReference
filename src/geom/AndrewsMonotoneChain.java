//#
package geom;
import java.util.*;

public class AndrewsMonotoneChain {
	
	/*
	 * Andrew's Monotone Chain (convex hull)
	 * Running time: O(nlogn) [n = number of points];
	 * Tested on: Nothing
	 */
	public static Pt[] monotoneChain(Pt[] pts) {
		if(pts.length <= 1) return pts;
		Arrays.sort(pts, new Comparator<Pt>() {
			public int compare(Pt o1, Pt o2) {
				if(o1.y != o2.y) return Double.compare(o1.y, o2.y); // consider using EPS instead of !=
				return Double.compare(o1.x, o2.x);
			}
		});

		Pt[] stack = new Pt[2 * pts.length];
		int len = 0;
		for(int i = 0; i < pts.length; stack[len++] = pts[i++]) 
			while(len >= 2 && SimpleGeom.ccw(stack[len-2], stack[len-1], pts[i]) <= 0) len--; // use < 0 if you want colinear points
		
		for(int i = pts.length - 1, start = --len; i >= 0; stack[len++] = pts[i--])
			while(len - start >= 2 && SimpleGeom.ccw(stack[len-2], stack[len-1], pts[i]) <= 0) len--; // use < 0 if you want colinear points
		
		return Arrays.copyOf(stack, len - (pts.length == 2 && pts[0].equals(pts[1]) ? 2 : 1));
//		return Arrays.copyOf(stack, len - (pts[0].equals(pts[1]) ? 2 : 1));
	}
	
	
	
	/*
	 * Andrew's Monotone Chain (convex hull)
	 * Running time: O(nlogn) [n = number of points];
	 * Tested on: UVA 11096, 10065, 10256, SPOJ BSHEEP, CodeForces 166B;
	 */
//	public static Pt[] monotoneChain(Pt[] pts) {
//		if(pts.length <= 1) return pts;
//		SimpleGeom.sortByYCoord(pts);
//
//		Pt[] stack = new Pt[2 * pts.length];
//		int len = 0;
//		for(int i = 0; i < pts.length; i++) {
//			while(len >= 2 && SimpleGeom.ccw(stack[len-2], stack[len-1], pts[i]) <= 0) len--; // use < 0 if you want colinear points
//			stack[len++] = pts[i];
//		}
//		int start = --len;
//		for(int i = pts.length - 1; i >= 0; i--) {
//			while(len - start >= 2 && SimpleGeom.ccw(stack[len-2], stack[len-1], pts[i]) <= 0) len--; // use < 0 if you want colinear points
//			stack[len++] = pts[i];
//		}
//		return Arrays.copyOf(stack, len - 1 - (pts[0].equals(pts[1]) ? 1 : 0));
//	}
	
}
