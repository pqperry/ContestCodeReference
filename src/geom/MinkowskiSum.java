//#
package geom;

public class MinkowskiSum {
	
	/*
	 * Minkowski Sum of Convex Polygons
	 * Running time: O(n+m) [n = poly1.length, m = pol2.length];
	 * Tested on: UVA 10256;
	 * Note: MinkowskiDifference = MinkowskiSum(poly1, negate(poly2))
	 * poly1 and poly2 MUST be convex polygons in ccw order with the first point having the lowest y-coord.
	 * The returned polygon may have colinear edges.
	 */
	public static Pt[] minkowskiSum(Pt[] poly1, Pt[] poly2) {
		int len1 = poly1.length, len2 = poly2.length;
		Pt[] ret = new Pt[len1 + len2];
		Pt curr = new Pt(poly1[0].x + poly2[0].x, poly1[0].y + poly2[0].y);
		int i = 0, j = 0;
		while(i + j < len1 + len2) {	
			double x1 = poly1[(i+1)%len1].x - poly1[i%len1].x;
			double y1 = poly1[(i+1)%len1].y - poly1[i%len1].y;
			double x2 = poly2[(j+1)%len2].x - poly2[j%len2].x;
			double y2 = poly2[(j+1)%len2].y - poly2[j%len2].y;
			double ccw = x1*y2 - y1*x2; // long cast to prevent overflow if using ints
			if(ccw < 0 && j < len2 || i >= len1) ret[i+j++] = new Pt(curr.x + x2, curr.y + y2);
			else ret[j+i++] = new Pt(curr.x + x1, curr.y + y1);
			curr = ret[i+j-1];
		}
		return ret;
	}
	
	/*
	 * Negates a polygon such that for every point, newX = -oldX and newY = -oldY.
	 * In addition, this reorders the points so that the new lowest Y coordinate point is first.
	 */
	public static Pt[] negate(Pt[] poly) {
		int len = poly.length;
		int loc = -1;
		for(int i = 0; i < len; i++) {
			Pt p = poly[i];
			p.x = -p.x;
			p.y = -p.y;
			if(loc == -1 || p.y < poly[loc].y || (p.y == poly[loc].y && p.x < poly[loc].x)) loc = i;
		}
		Pt[] ret = new Pt[len];
		for(int i = 0; i < len; i++) ret[i] = poly[(i + loc)%len];
		return ret;
	}

}
