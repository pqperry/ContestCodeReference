//#
package graph;

public class FloydWarshall {
	
	/*
	 * Floyd-Warshall algorithm (all pairs shortest path on a directed graph)
	 * Running time: O(V^3)
	 * Tested on: UVA 11463;
	 * Input: dp[i][j] = cost of edge from i to j. Set to Integer.MAX_VALUE/2 if no edge
	 * Returns: true on success, false if a negative cycle is detected
	 */
	public static boolean floydWarshall(int[][] dp) {
		for(int n = 0; n < dp.length; n++)
			for(int i = 0; i < dp.length; i++)
				for(int j = 0; j < dp.length; j++)
					dp[i][j] = Math.min(dp[i][j], dp[i][n] + dp[n][j]);
		
		for(int i = 0; i < dp.length; i++) if(dp[i][i] < 0) return false;
		return true;
	}

	
}
