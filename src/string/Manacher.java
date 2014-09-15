//#
package string;

public class Manacher {
	/*
	 * Manacher's Algorithm (Longest palindromic substring)
	 * Running time: O(n) [n = s.length()]
	 * Tested on: SPOJ LPS
	 * Taken from UBC's codearchive.
	 *  length of odd palin centered at s[i] is lengths[2*i]
	 *  even palin btwn s[i],s[i+1]: lengths[2*i+1]
	 */
	public static int[] manacher(String s) {
		int[] dp = new int[2*s.length()];
		dp[0] = 1;
		for(int d, i = 1; i + 2 < 2 * s.length(); i += d) {
			while((i - dp[i] - 1)/2 >= 0 && (i + dp[i] + 1)/2 < s.length() 
					&& s.charAt((i - dp[i] - 1)/2) == s.charAt((i + dp[i] + 1)/2)) dp[i] += 2;
			for(d = 1; dp[i-d] < dp[i] - d; d++) dp[i+d] = dp[i-d];
			dp[i+d] = dp[i] - d;
		}
		return dp;
	}
}
