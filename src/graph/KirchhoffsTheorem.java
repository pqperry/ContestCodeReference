//#
package graph;

import java.util.*;

import matrix.MatrixDeterminant;

public class KirchhoffsTheorem {
	
	/*
	 * Kirchhoff's Theorem. Computes the number of spanning trees.
	 * Running time: O(n^3)
	 * Tested on: nothing
	 * adj = adjacency matrix for the graph. all values should be either 1 or 0
	 * This version uses Gaussian Elimination/LU Decomposition to compute the determinant
	 * Even for fairly small graphs, you'll probably need BigDecimal, which this version doesn't use
	 * Note: For complete graphs, the number of spanning trees of n nodes is n^(n-2) (Cayley's formula)
	 */
	public static double kirchhoff(double[][] adj) {
		int len = adj.length;
		if(len == 1) return 1;
		for(int i = 0; i < len; i++) {
			double acc = 0;
			for(int j = 0; j < len; j++) {
				acc += adj[i][j];
				adj[i][j] *= -1;
			}
			adj[i][i] = acc;
		}
		
		adj = Arrays.copyOf(adj, len-1);
		for(int i = 0; i < len-1; i++) adj[i] = Arrays.copyOf(adj[i], len-1);
		
		return MatrixDeterminant.matrixDet(adj);
	}

}
