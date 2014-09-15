//#
package geom;

public class ShoelaceFormula {

	public static double shoelaceFormula(Pt[] pts) {
		double acc = 0;
		int len = pts.length;
		for(int i = 0; i < len; i++) {
			acc += pts[i].x * pts[(i+1)%len].y;
			acc -= pts[i].y * pts[(i+1)%len].x;
		}
		return acc/2; // Math.abs if you don't want signed area
	}

}
