//#
package game;

import matrix.GaussianElimination;

import geom.Pt;

public class MixedStrategyNashEquilibrium {
	
	/*
	 * Mixed Strategy Nash Equilibrium
	 * Running time: O(n^3) [n = pay.length]
	 * This only works for square payoff matricies
	 * This returns a double[] containing the probability of playing each strategy for player 2
	 * For player 1's result, call PlayerFlip.flip(pay)
	 */
	public static double[] mixedStrategyNashEquilibrium(Pt[][] pay) {
		int len = pay.length;
		double[][] mat1 = new double[len][len], mat2 = new double[len-1][len]; 
		
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len; j++) {
				mat1[i][j] = pay[i][j].x;
				if(j != len-1) mat1[i][j] -= pay[i][len-1].x;
				if(i == 0) continue;
				mat2[i-1][j] = mat1[0][j] - mat1[i][j];
				if(j == len-1) mat2[i-1][j] *= -1;
			}
		}

		GaussianElimination.gaussElim(mat2); // pivoting required
		double[] ret = new double[len];
		ret[len-1] = 1;
		for(int i = 0; i < len-1; i++) ret[len-1] -= ret[i] = mat2[i][len-1];
		return ret;
	}
	
	public static Pt expectedPayoff(Pt[][] pay, double[] prob1, double[] prob2) {
		Pt ret = new Pt(0, 0);
		for(int i = 0; i < pay.length; i++) {
			for(int j = 0; j < pay[0].length; j++) {
				ret.x += prob1[i] * prob2[j] * pay[i][j].x;
				ret.y += prob1[i] * prob2[j] * pay[i][j].y;
			}
		}
		return ret;
	}

}
