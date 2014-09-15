//#
package matrix;

public class MatrixDeterminant {

	/*
	 * Matrix determinant via LU decomposition
	 * Running time: O(n^3)
	 * Tested on: nothing
	 * 
	 * By the invertible matrix theorem, the following are either all true or all false 
	 * -- The determinant of M is non-zero
	 * -- M and M^T is invertible
	 * -- M is row equivalent to the identity matrix
	 * -- M has n pivot positions
	 * -- The equation Mx = 0 has only the trivial solution
	 * -- The columns of M form a linearly independent set
	 * -- The columns of M span R^n
	 * -- There exists a matrix A such that MA = I and a matrix B such that BM = I
	 * -- Num M = {}
	 * -- The number 0 is not an eigenvalue of M
	 * (from Linear Algebra and it's applications, David C Lay)
	 */
	static double EPS = 1e-10;
	
	public static double matrixDet(double[][] mat) {
		int len = mat.length;
		double det = 1;
		
		for(int p = 0; p < len; p++) {
			
			double pivot = mat[p][p];
			
			int loc = p; // remove the next few lines if you don't need pivoting
			for(int i = p+1; i < len; i++) {
				if(Math.abs(mat[i][p]) <= Math.abs(pivot)) continue;
				pivot = mat[i][p];
				loc = i;
			}
			if(p != loc) det *= -1;
			if(pivot < EPS) return 0;
			double[] temp = mat[loc];
			mat[loc] = mat[p];
			mat[p] = temp; // up to  and including here
			
			for(int i = p+1; i < len; i++) {
				double mult = mat[i][p] / pivot;
				for(int j = p; j < len; j++) mat[i][j] -= mult * mat[p][j]; 
			}
		}
		
		for(int i = 0; i < len; i++) det *= mat[i][i];
		return det;
	}
}
