//#
package geom;
import java.util.*;

public class CircleCircleIntersection {

	static final double EPS = 1e-8;
	
	public static Pt[] circleIntersection(Pt c1, double r1, Pt c2, double r2) {
		double x = c2.x - c1.x, y = c2.y - c1.y;
		double d = x * x + y * y, r22 = r2 * r2, r12 = r1 * r1;
		
		double det = 2 * r22 * (d + r12) - (d - r12) * (d - r12) - r22*r22;
		if(det > -EPS && det < 0) det = 0;
		
		TreeSet<Pt> ret = new TreeSet<Pt>(new Comparator<Pt>() {
			public int compare(Pt o1, Pt o2) {
				if(Math.abs(o1.x - o2.x) > EPS) return Double.compare(o1.x, o2.x);
				if(Math.abs(o1.y - o2.y) > EPS) return Double.compare(o1.y, o2.y);
				return 0;
			}
		});
		
		int[] sgn = {1, -1};
		for(int ysgn : sgn){
			for(int xsgn : sgn) {
				double yi = (y * (d + r12 - r22) - ysgn * x * Math.sqrt(det)) / (2 * d);
				double xi2 = r12 - yi*yi;
				if(xi2 > -EPS && xi2 < 0) xi2 = 0;
				Pt pt = new Pt(xsgn * Math.sqrt(xi2) + c1.x, yi + c1.y);
				if(Math.abs(pt.distance(c1) - r1) <= EPS && Math.abs(pt.distance(c2) - r2) <= EPS) ret.add(pt);
			}
		}
		
		return ret.toArray(new Pt[0]);
	}

}
