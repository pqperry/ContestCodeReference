//#
package geom;

public class FarthestPair {
	
	public static double farthestPair(Pt[] pts) {
		pts = AndrewsMonotoneChain.monotoneChain(pts);
		if(pts.length == 2) return pts[0].distance(pts[1]);
		
		int i1 = 0, i2 = 0;
		double best = 0;
		while(i2 < pts.length - 1) { // this probably checks more points than necessary
			double curr = pts[i1].distance(pts[i2]);
			double next = pts[i1].distance(pts[i2+1]);
			if(next > curr) i2++;
			else i1++;
			best = Math.max(best, curr);
		}
		return best;
	}
}
