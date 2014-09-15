//#
package numerical;

public class SimpsonsRule {
	
	// define your function here
	public static double f(double x) {
		return x*x * Math.sin(x) * Math.exp(-x);
	}
	
	// basic simpson's rule
	public static double simpson(double lo, double hi) {
		return (hi - lo)/6 * (f(lo) + 4 * f((lo + hi)/2) + f(hi));
	}
	
	// adaptive simpson's rule.
	public static double adaptiveSimpson(double lo, double hi, double eps) {
		double mid = (lo + hi)/2;
		double flo = f(lo), fc = f(mid), fhi = f(hi);
		double area = (hi - lo)/6 * (flo + 4 * fc + fhi);
		return adsimp_inner(lo, mid, hi, flo, fc, fhi, area, eps);
	}
	
	static double adsimp_inner(double lo, double mid, double hi, double flo, double fmid, double fhi, double area, double eps) {
		double l = (lo + mid)/2, r = (mid + hi)/2;
		double fl = f(l), fr = f(r);
		double al = (mid - lo)/6 * (flo + 4 * fl + fmid);
		double ar = (hi - mid)/6 * (fmid + 4 * fr + fhi);
		if(Math.abs(al + ar - area) <= 15 * eps) return al + ar + (al + ar - area)/15;
		else return adsimp_inner(lo, l, mid, flo, fl, fmid, al, eps/2) + adsimp_inner(mid, r, hi, fmid, fr, fhi, ar, eps/2);
	}
	
	// shorter, but slower by a factor of 4 to 5
	public static double adaptiveSimpsonSlow(double lo, double hi, double eps) {
		double mid = (lo + hi)/2;
		double al = simpson(lo, mid), ar = simpson(mid, hi), at = simpson(lo, hi);
		if(Math.abs(al + ar - at) <= 15 * eps) return al + ar + (al + ar - at)/15;
		else return adaptiveSimpsonSlow(lo, mid, eps/2) + adaptiveSimpsonSlow(mid, hi, eps/2);
	}
	
}
