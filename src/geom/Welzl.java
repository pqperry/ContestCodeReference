//#
package geom;

import geom.SimpleGeom.Circle;

import java.util.*;

public class Welzl {
	
	/*
	 * Welzl's algorithm (smallest bounding circle)
	 * Running time: O(n) [n = number of points];
	 * Tested on: nothing
	 */
	static Random rand = new Random(1234567890);
	
	public static Circle welzl(Pt[] pts) {
		List<Pt> temp = Arrays.asList(pts);
		Collections.shuffle(temp, rand);
		return welzlInner(temp.toArray(new Pt[0]), new Pt[3], pts.length, 0);
	}
	
	private static Circle welzlInner(Pt[] pts, Pt[] bound, int ptsloc, int boundloc) {
		Circle circ = new Circle(new Pt());
		if(boundloc == 1) circ = new Circle(bound[0]);
		else if(boundloc == 2) circ = new Circle(bound[0], bound[1]);
		else if(boundloc == 3) circ = new Circle(bound[0], bound[1], bound[2]);
		
		for(int i = 0; i < ptsloc; i++) {
			if(circ.contains(pts[i])) continue;
			bound[boundloc] = pts[i];
			circ = welzlInner(pts, bound, i, boundloc+1);
		}
		return circ;
	}
	
	// Canonical statement of welzl's algorithm. Do NOT use this. You will stack-overflow
	public static Circle welzlCanonical(List<Pt> pts){
		Random rand = new Random(1234567890);
		Collections.shuffle(pts, rand);
		return welzlCanonicalInner(pts.toArray(new Pt[0]), new Pt[3], 0, 0);
	}
	
	private static Circle welzlCanonicalInner(Pt[] pts, Pt[] bound, int ptsloc, int boundloc){
		if(ptsloc == pts.length || boundloc == 3) {
			if(boundloc == 0) return new Circle(new Pt());
			if(boundloc == 1) return new Circle(bound[0]);
			if(boundloc == 2) return new Circle(bound[0], bound[1]);
			return new Circle(bound[0], bound[1], bound[2]);
		}

		Pt pt = pts[ptsloc++];
		Circle circ = welzlCanonicalInner(pts, bound, ptsloc, boundloc);
		if(!circ.contains(pt)) {
			bound[boundloc++] = pt;
			circ = welzlCanonicalInner(pts, bound, ptsloc, boundloc);
		}
		return circ;
	
	}

}
