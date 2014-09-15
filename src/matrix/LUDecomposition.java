//#
package matrix;

public class LUDecomposition {

	/*
	 * LU Decomposition
	 * Running time: O(n^3)
	 * Tested on: nothing
	 * Note: after calling res = lu(mat), res = L, mat = U
	 * No partial pivoting. Not sure if this works when matrix isn't square
	 * The determinant must not be zero
	 * Format: array[height][width]
	 */
	public static double[][] lu(double[][] mat) {
		int height = mat.length;
		int width = mat[0].length;
		
		double[][] l = new double[height][width];
		
		for(int i = 0; i < height; i++) {
			l[i][i] = 1;
			for(int i2 = 0; i2 < i; i2++) {
				double mult = l[i][i2] = mat[i][i2]/mat[i2][i2];
				for(int j = i2; j < width; j++) mat[i][j] -= mult * mat[i2][j];
			}
		}
		return l;
	}
	
	/*
	 * Solves Ax=b given LU Decomposition of A
	 * Running time: O(n^2)
	 * Tested on: nothing
	 * Note: after calling luSolve(l, u, b), b will be overwritten with x
	 */
	public static void luSolve(double[][] l, double[][] u, double[] b) {
		int len = b.length;
		for(int i = 0; i < len; i++)
			for(int j = 0; j < i; j++) b[i] -= l[i][j] * b[j];
		
		for(int i = len-1; i >= 0; i--) {
			for(int j = i+1; j < len; j++) b[i] -= u[i][j] * b[j];
			b[i] /= u[i][i];
		}
	}
	
}
