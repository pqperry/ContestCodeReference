//#
package geom;

import java.awt.Point;

import number.GCD;


public class NumberOfContainedIntegerPoints {
	
	/*
	 * Given a simple polygon with integer vertex coordinates, computes the number of integer points contained within
	 * Running time: O(n) [n = pts.length], assuming gcd is O(1)
	 * Tested on: NOTHING
	 * Note: pick's theorem: A = i + (b/2) - 1 (A = area, i = interior points, b = boundary points)
	 */
	public static int numberOfContainedIntegerPoints(Point[] poly) {
		int a = 0; // 2*area (via shoelace formula)
		int b = 0; // number of boundary points (via gcd)
		for(int i = 0; i < poly.length; i++) {
			Point p1 = poly[i], p2 = poly[(i+1)%poly.length];
			a += p1.x * p2.y - p1.y * p2.x ;
			b += Math.abs(GCD.gcd(p2.x - p1.x, p2.y - p2.y));
		}
		return (Math.abs(a) - b + 2)/2; // return (Math.abs(a) + b + 2)/2 to include boundary points
	}

}
