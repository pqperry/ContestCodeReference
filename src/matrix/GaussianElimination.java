//#
package matrix;

public class GaussianElimination {
	
	/*
	 * Gaussian Elimination with partial pivoting
	 * Running time: O(n^3)
	 * Tested on: nothing
	 * Matrix must be as wide or wider than tall
	 * The determinant must not be zero
	 * Pivoting selection: max(abs(pivot))
	 * Format: array[height][width]
	 */
	public static void gaussElim(double[][] mat) {
		int height = mat.length;
		int width = mat[0].length;
		int mindim = Math.min(height, width);
		
		for(int p = 0; p < mindim; p++) {
			
			double pivot = mat[p][p];
			
			int loc = p; // remove the next few lines if you don't need pivoting
			for(int i = p+1; i < height; i++) {
				if(Math.abs(mat[i][p]) <= Math.abs(pivot)) continue;
				pivot = mat[i][p];
				loc = i;
			}
			double[] temp = mat[loc];
			mat[loc] = mat[p];
			mat[p] = temp; // up to  and including here

			for(int j = 0; j < width; j++) mat[p][j] /= pivot;
			
			for(int i = p+1; i < height; i++) {
				double mult = mat[i][p];
				for(int j = p; j < width; j++) mat[i][j] -= mult * mat[p][j]; 
			}
		}
	
		for(int p = 0; p < mindim; p++) {
			for(int i = 0; i < p; i++) {
				double mult = mat[i][p];
				for(int j = p; j < width; j++) mat[i][j] -= mult * mat[p][j];
			}
		}

	}
	
}
