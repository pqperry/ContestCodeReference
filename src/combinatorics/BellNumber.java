//#
package combinatorics;

import java.math.BigInteger;

public class BellNumber {
	
	/*
	 * Computes bell numbers up to and including n using the triangle scheme
	 * Running time: O(n^2) (assuming BigInteger arithmetic is O(1))
	 * Tested on: GNY 2003 G
	 * Note: The nth bell number (result[n]) is the number of partitions of a set with n members.
	 * The smallest bell number larger than max_int is the 16th (result[16]);
	 * The smallest bell number larger than max_long is the 26th (result[26]);
	 * The sequence starts out 1, 1, 2, 5, 15, 52, 203, 877, 4140, 21147, 115975, 678570, 4213597
	 */
	public static BigInteger[] bellNumber(int n) {
		BigInteger[] result = new BigInteger[n+1];
		result[0] = BigInteger.ONE;
		BigInteger[] row1 = {BigInteger.ONE};
		
		for(int i = 1; i <= n; i++) {
			BigInteger[] row2 = new BigInteger[row1.length + 1];
			result[i] = row2[0] = row1[row1.length - 1];
			for(int j = 0; j < row1.length; j++) row2[j+1] = row2[j].add(row1[j]);
			row1 = row2;
		}
		return result;
	}

} 
