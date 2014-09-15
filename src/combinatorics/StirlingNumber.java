//#
package combinatorics;

import java.math.BigInteger;

public class StirlingNumber {
	
	/*
	 * Computes Stirling Numbers of the first or second kind
	 * Running time: O(n^2) (assuming BigInteger arithmetic is O(1))
	 * Tested on: Nothing
	 * Note: For first kind stirling numbers, result[n][k] = s(n,k) = the number of permutations of n elements with k disjoint cycles
	 * For second kind stirling numbers, result[n][k] = s(n,k) = the number of ways to partition a set of n labelled objects into k nonempty unlabelled subsets
	 */
	public static BigInteger[][] stirlingNumber(int n, int k)
	{
		BigInteger[][] result = new BigInteger[n+1][k+1];
		result[0][0] = BigInteger.ONE;
		for(int i = 1; i <= n; i++) result[i][0] = BigInteger.ZERO;
		for(int i = 1; i <= k; i++) result[0][i] = BigInteger.ZERO;
		
		for(int i = 1; i <= n; i++)
			for(int j = 1; j <= k; j++)
				result[i][j] = result[i-1][j].multiply(BigInteger.valueOf(i-1)).add(result[i-1][j-1]);
				// use BigInteger.valueOf(j) for second kind stirling number.
		return result;
	}

}
