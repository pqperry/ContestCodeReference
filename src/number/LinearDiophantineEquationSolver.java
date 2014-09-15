//#
package number;


public class LinearDiophantineEquationSolver {

	/*
	 * Solves the diophantine equation ax + by = z (finds a and b)
	 * Tested on: UVA 10090
	 * Return value: returns null if it fails to solve, which happens when z % gcd(a,b) != 1. 
	 * else, ret[0] = a, ret[1] = b
	 */
	public static long[] ldes(long x, long y, long z) {
		long[] gcd = GCD.extendedGCD(x, y);
		if(z % gcd[2] != 0) return null;
		long div = z/gcd[2];
		return new long[]{gcd[0] * div, gcd[1] * div};
	}
	
	
	/*
	 * Gets basis vector. In other words, if ax + by = z, (a+ret[0])x + (b-ret[1])x = z
	 * Input: vals = output of ldes()
	 */
	public static long[] getBasis(long x, long y, long[] vals) {
		long lcm = GCD.lcm(x, y);
		return new long[]{lcm / vals[0], lcm / vals[1]};
	}
	
	/*
	 * This version restricts a and b to non-negative values.
	 * Tested on: UVA 10090
	 */
	public static long[] ldesPositive(long x, long y, long z) {
		long[] gcd = GCD.extendedGCD(x, y);
		if(z % gcd[2] != 0) return null;
		long div = z/gcd[2]; // everything up to this line is identical to ldes()
		long a = gcd[0] * div, b = gcd[1] * div;
		long lcm = GCD.lcm(x, y);
		long da = lcm / x, db = lcm / y;
		
		if(a < 0) {
			long mult = a % da == 0 ? -a/da : -a/da + 1;
			a += da * mult;
			b -= db * mult;
		}
		if(b < 0) {
			long mult = b % db == 0 ? -b/db : -b/db + 1;
			a -= da * mult;
			b += db * mult;
		}
		return b < 0 ? null : new long[]{a, b};
	}
	
	/*
	 * Solves ax + by = z such that a,b >= 0 and a*cx + b * cy is minimized
	 * Tested on: UVA 10090
	 * If cx == cy, this solver maximize a.
	 * Return value: returns null if it fails to solve, which happens when z % gcd(a,b) != 1. Or when no positive a,b values exist
	 * else, ret[0] = a, ret[1] = b
	 */
	public static long[] minCostLdes(long x, long cx, long y, long cy, long z) {
		long[] gcd = GCD.extendedGCD(x, y);
		if(z % gcd[2] != 0) return null;
		long div = z/gcd[2];  // everything up to this line is identical to ldes()
		long a = gcd[0] * div, b = gcd[1] * div;
		long lcm = GCD.lcm(x, y);
		long da = lcm / x, db = lcm / y;
		
		if(a < 0) {
			long mult = a % da == 0 ? -a/da : -a/da + 1;
			a += da * mult;
			b -= db * mult;
		}
		if(b < 0) {
			long mult = b % db == 0 ? -b/db : -b/db + 1;
			a -= da * mult;
			b += db * mult;
		} // everything up to this line is identical to ldesPositive()
		if(a < 0) return null;
		
		if(cx * y <= cy * x) { // watch for overflow use if((double)cx/x < (double)cy/y) if precision not a concern. Use >= if you want max cost
			long mult = b/db;
			a += da * mult;
			b -= db * mult;
		}
		else {
			long mult = a/da;
			a -= da * mult;
			b += db * mult;
		}
		return new long[]{a, b};
	}
	
}
