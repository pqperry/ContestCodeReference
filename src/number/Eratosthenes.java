//#
package number;

import java.util.*;

public class Eratosthenes {
	/*
	 * Sieve of Eratosthenes with basic optimization (Prime number finder)
	 * Running time: O(N) [N = limit]
	 * Tested on: UVA 543
	 */
	public static int[] eratosthenes(int limit) {
		boolean[] out = new boolean[limit+1];
		
		int num = 3;
		while(num*num <= limit) {
			if(!out[num]) {
				int curr = num*num;
				while(curr <= limit) {
					out[curr] = true;
					curr += num;
				}
			}
			num += 2;
		}
		
		int[] primes = new int[limit+1];
		int loc = 1;
		primes[0] = 2;
		for(int i = 3; i < out.length; i+=2)
			if(!out[i]) primes[loc++] = i;
		return Arrays.copyOf(primes, loc);
	}

	
	public static List<Integer> eratosthenes2(int limit) {
		boolean[] out = new boolean[limit+1];
		List<Integer> primes = new ArrayList<Integer>();
		primes.add(2);
		
		int num = 3;
		while(num*num <= limit) {
			if(!out[num]) {
				int curr = num*num;
				while(curr <= limit) {
					out[curr] = true;
					curr += num;
				}
			}
			num += 2;
		}
		for(int i = 3; i < out.length; i+=2)
			if(!out[i]) primes.add(i);
		return primes;
	}
}
