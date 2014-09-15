//#
package misc;

public class KnapsackProblem {
	
	
	public static int[] unboundedKnapsack(int[] value, int[] weight, int maxWeight) {
		int[] dp = new int[maxWeight+1];
		for(int w = 1; w <= maxWeight; w++)
			for(int i = 0; i < value.length; i++)
				if(weight[i] <= w) dp[w] = Math.max(dp[w], dp[w-weight[i]] + value[i]);
		return dp;
	}
	
	/*
	 * 0/1 Integer Knapsack Problem
	 * Running time: O(nW) [n = value.length, W = maxWeight]
	 * Tested on: SPOJ KNAPSACK
	 */
	public static int[][] zeroOneKnapsack(int[] value, int[] weight, int maxWeight) {
		int[][] dp = new int[value.length+1][maxWeight+1];
		for(int i = 1; i <= value.length; i++) {
			for(int w = 1; w <= maxWeight; w++) {
				if(weight[i-1] > w) dp[i][w] = dp[i-1][w];
				else dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weight[i-1]] + value[i-1]);
			}
		}
		return dp;
	}

}
