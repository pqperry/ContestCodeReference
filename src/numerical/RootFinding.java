//#
package numerical;

import java.util.*;

public class RootFinding {
	
	public static double newtonsMethod(double x, double precision) {
		double xprev;
		do {
			xprev = x;
			x = x - f(x)/fprime(x);
		}
		while(Math.abs(xprev - x) >= precision);
		return x;
	}
	
	public static double bisectionMethodSingle(double end1, double end2, double precision) {
		double pos = f(end1) >= 0 ? end1 : end2;
		double neg = f(end1) <= 0 ? end1 : end2;
		double mid;
		do {
			mid = (pos + neg)/2;
			if(f(mid) > 0) pos = mid;
			else neg = mid;
		}
		while(Math.abs(pos - neg) >= precision);
		return mid;
	}
	
	public static List<Double> bisectionMethodRange(double start, double end, double nprobe, double precision) {
		List<Double> roots = new ArrayList<Double>();
		double probeDist = (end-start)/nprobe;
		
		double lastf = f(start);
		while(start < end) {
			double rangeEnd = Math.min(start + probeDist, end);
			double eval = f(rangeEnd);
			if(lastf * eval <= 0) roots.add(bisectionMethodSingle(start, rangeEnd, precision));
			start = rangeEnd;
			lastf = eval;
		}
		return roots;
	}
	
	static double f(double x) {
		return 3*x*x*x - 16*x*x - 9*x + 10;
	}
	
	static double fprime(double x) {
		return 9*x*x - 32*x - 9;
	}
}
