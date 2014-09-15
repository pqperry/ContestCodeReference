//#
package matrix;


public class MatrixInverse {

	// matrix inverse. Matrix must be square. O(n^3)
	public static void matrixInverse(double[][] mat) {
		int len = mat.length;
		double[][] temp = new double[len][2*len];
		for(int i = 0; i < len; i++) {
			temp[i][i+len] = 1;
			for(int j = 0; j < len; j++) temp[i][j] = mat[i][j];
		}
		GaussianElimination.gaussElim(temp);
		for(int i = 0; i < len; i++)
			for(int j = 0; j < len; j++) mat[i][j] = temp[i][j+len];
	}

}
