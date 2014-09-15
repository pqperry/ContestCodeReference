//#
package geom;

import java.awt.geom.AffineTransform;

public class MinimumBoundingRectangle {
	
	/*
	 * Minimum Bounding Rectangle using Rotating Calipers
	 * Running time: O(nlgn). O(n) without the convex hull finding step [n = number of points]
	 * Tested on: UVA 10173
	 * Apparently, there's a way to do this without using any trigonometric functions. If you know how, please teach me.
	 */
	public static double minimumBoundingRectangle(Pt[] pts) {
		pts = AndrewsMonotoneChain.monotoneChain(pts);
		int len = pts.length;
		int minXloc = 0, maxXloc = 0, minYloc = 0, maxYloc = 0;
		for(int i = 0; i < len; i++) {
			if(pts[i].x < pts[minXloc].x) minXloc = i;
			if(pts[i].x > pts[maxXloc].x) maxXloc = i;
			if(pts[i].y < pts[minYloc].y) minYloc = i;
			if(pts[i].y > pts[maxYloc].y) maxYloc = i;
		}
		
		double min = Double.POSITIVE_INFINITY;
		Pt next = null;
		for(int i = 0; i < len; i++) {
			double ang = -Math.atan2(pts[(i+1)%len].y - pts[i].y, pts[(i+1)%len].x - pts[i].x);
			AffineTransform at = AffineTransform.getRotateInstance(ang);

			Pt minX = pts[(minXloc)%len].transform(at);
			Pt maxX = pts[(maxXloc)%len].transform(at);
			Pt minY = pts[(minYloc)%len].transform(at);
			Pt maxY = pts[(maxYloc)%len].transform(at);
			
			while((next = pts[(minXloc+1)%len].transform(at)).x < minX.x) { minX = next; minXloc++; }
			while((next = pts[(maxXloc+1)%len].transform(at)).x > maxX.x) { maxX = next; maxXloc++; }
			while((next = pts[(minYloc+1)%len].transform(at)).y < minY.y) { minY = next; minYloc++; }
			while((next = pts[(maxYloc+1)%len].transform(at)).y > maxY.y) { maxY = next; maxYloc++; }
			
			min = Math.min(min, (maxX.x - minX.x) * (maxY.y - minY.y));
			// for minimum perimeter: min = Math.min(min, 2*(maxX.x - minX.x) + 2*(maxY.y - minY.y)));
		}
		return min;
	}


}
