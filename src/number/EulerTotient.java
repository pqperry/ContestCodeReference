//#
package number;

public class EulerTotient {
	
	/*
	 * Euler's Totient Function. 
	 * Tested on: UVA 10179, 11327
	 * Source: Topcoder
	 * totient(n) returns the number of positive integers less than or equal to n and relatively prime with n
	 * Useful formulas from wikipedia:
	 * -- if a and n are relatively prime, a^totient(n) = 1 mod n
	 * -- a | b -> totient(a) | totient(b)
	 * -- n | totient(a^n - 1), a, n > 1
	 * -- totient(mn) = totient(m) * totient(n) * gcd(m, n) / totient(gcd(m,n))
	 * -- totient(2m) = 2*totient(m) if m is even, = totient(m) if m is odd
	 * -- totient(n^m) = n^(m-1) * totient(n)
	 * -- totient(lcm(m,n)) * totient(gcd(m,n)) = totient(m) * totient(n)
	 */
	public static long totient(long n) {
		long res = n;
		for(long i = 2; i*i <= n; i++) {
			if(n % i == 0) res -= res/i;
			while(n % i == 0) n /= i;
		}
		if(n > 1) res -= res / n;
		return res;
	}
	
	// alternate form. perhaps easier to understand
	public static long totient2(long n) {
		long div = 1;
		long res = n;
		for(long i = 2; i*i <= n; i++) {
			if(n % i == 0) {
				div *= i;
				res -= res/div;
			}
			while(n % i == 0) n /= i;
		}
		if(n > 1) res -= res / n;
		return res;
	} 
	
	/*
	 * This modified Totient Function, totient(n, m), returns the number of positive integers less than 
	 * or equal to m and relatively prime with n
	 */
	public static long totient(long n, long m) {
		long div = 1;
		long res = m;
		for(long i = 2; i*i <= n; i++) {
			if(n % i == 0) {
				div *= i;
				res -= (m + div - i)/div;
			}
			while(n % i == 0) n /= i;
		}
		if(n > 1) res -= res / n; // not sure if right
		return res;
	}
}