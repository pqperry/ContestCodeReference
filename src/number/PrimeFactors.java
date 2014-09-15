//#
package number;

import java.util.*;

public class PrimeFactors {

	/*
	 * Gets prime factors of a number
	 * Running time: O(sqrt(n))
	 * Tested on: SPOJ OPCPRIME
	 * Note: This prime factors are returned in ascending order, and there may be duplicates
	 * For example, primeFactors(8) will yield List{2, 2, 2}
	 */
	public static List<Long> primeFactors(long n) {
		List<Long> ret = new ArrayList<Long>();
		for(long i = 2; i*i <= n; i++) { 
			while(n % i == 0) {
				n /= i;
				ret.add(i);
			}
		}
		if(n > 1) ret.add(n);
		return ret;
	}
	
	
	/*
	 * Calculates the number of divisors of n.
	 * Running time: O(sqrt(n))
	 * Given prime decomposition of n = p1^a1 * p2^a2 * p3^a3..., the number of divisors
	 * is (a1 + 1)*(a2 + 1)*(a3 + 1)...
	 */
	public static long numberOfDivisors(long n) {
		long ret = 1;
		for(long i = 2; i*i <= n; i++) { 
			long count = 1;
			while(n % i == 0) {
				n /= i;
				count++;
			}
			ret *= count;
		}
		if(n > 1) ret *= 2;
		return ret;
	}
}
