//#
package number;

public class GCD {
	
	// Euclidean GCD algorithm. Standard form. Use this one unless you have a good reason not to.
	public static long gcd(long x, long y) {
		while(y != 0) {
			long temp = y;
			y = x % y;
			x = temp;
		}
		return x;
	}
	
	public static long multiGCD(long[] nums) {
		long gcd = gcd(nums[0], nums[1]);
		for(long n : nums) gcd = gcd(gcd, n);
		return gcd;
	}
	
	public static long lcm(long x, long y) {
		return (x*y)/gcd(x,y);
	}
	
	// Extended Euclidean GCD. finds gcd(x,y), a, b such that ax + by = gcd(x, y)
	// Tested on: UVA 10090
	// return values: ret[0] = a, ret[1] = b, ret[2] = gcd(x, y)
	public static long[] extendedGCD(long x, long y) {
		long a = 1, b = 0, c = 0, d = 1;
		while(y != 0) {
			long div = x / y;
			long temp = y;
			y = x % y;
			x  = temp;

			long nc = a - c * div;
			long nd = b - d * div;
			a = c;
			b = d;
			c = nc;
			d = nd;
		}
		return new long[]{a, b, x};
	}
	
	public static long[] multiExtendedGCD(long[] n) {
		long[][] mat = new long[n.length][n.length+1]; // extra space to place eventual GCD
		for(int i = 0; i < n.length; i++) mat[i][i] = 1;
		int loc1 = 0, loc2 = 1, next = 2;
		
		while(next <= n.length) {
			long div = n[loc1] / n[loc2];
			n[loc1] = n[loc1] % n[loc2];
			for(int i = 0; i < n.length; i++) mat[loc1][i] -= div * mat[loc2][i];
			int temp = loc1;
			loc1 = loc2;
			loc2 = temp;
			if(n[loc1] == 0) loc1 = next++;
			if(n[loc2] == 0) loc2 = next++;
		}
		
		int locf = n[loc1] != 0 ? loc1 : loc2;
		mat[locf][n.length] = n[locf];
		return mat[locf];
	}
	
	// Subtraction version. only works for positive numbers. slow.
	// Never use this. It's only good for conceptual understanding.
	// How it works: say g = gcd(m,n), then m = kg, n = hg, m-n = (k-h)g so after 
	// subtraction, the new number is still divisible by g, and repeated subtraction = mod
	public static int gcd2(int x, int y) {
		while(x != y) {
			if(x > y) x -= y;
			else y -= x;
		}
		return x;
	}
	
	// recursive version
	public static int gcd3(int x, int y) {
		return y == 0 ? x : gcd3(y, x%y);
	}
	

	
}
