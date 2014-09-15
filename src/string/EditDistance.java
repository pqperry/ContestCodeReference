//#
package string;

public class EditDistance {

	/*
	 * Edit (Levenshtein) Distance
	 * Running time: O(mn) [n = s1.length, m = s2.length]
	 * Tested on: SPOJ EDIST
	 */
	public static int editDistance(String s1, String s2) {
		char[] ca1 = (" " + s1).toCharArray();
		char[] ca2 = (" " + s2).toCharArray();
		int[][] dp = new int[ca1.length][ca2.length];
		for(int i = 0; i < ca1.length; i++) {
			for(int j = 0; j < ca2.length; j++) {
				if(i == 0 || j == 0) dp[i][j] = Math.max(i, j);
				else dp[i][j] = Math.min(Math.min(dp[i-1][j] + 1, dp[i][j-1] + 1), dp[i-1][j-1] + (ca1[i] == ca2[j] ? 0 : 1));
			}
		}
		return dp[ca1.length-1][ca2.length-1];
	}
}
