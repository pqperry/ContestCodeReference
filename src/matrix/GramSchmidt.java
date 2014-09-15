//#
package matrix;

import java.util.Arrays;


public class GramSchmidt {

	/*
	 * Gram-Schmidt process
	 * Running time: O(n^2 * d) [n = vects.length, d = dimension of vectors]
	 * Tested on: nothing;
	 * For both input and return val, vects[0] is the first vector, vects[1] is the second, etc.
	 * This version detects and removes vectors that are linear combinations of others. If you don't want that behavior, remove lines marked with a *
	 */
	public static double[][] gramSchmidt(double[][] vects) {
		final double EPS = 1E-9; // adjust as necessary
	
		int len = vects.length;
		boolean[] removed = new boolean[len]; //*
		for(int i = 0; i < len; i++) {
			if(removed[i]) continue; //*
			SimpleMath.normalize(vects[i]);
			for(int j = i+1; j < len; j++) {
				if(removed[j]) continue; //*
				double dotp = SimpleMath.dot(vects[i], vects[j]);
				for(int k = 0; k < vects[j].length; k++) vects[j][k] -= dotp * vects[i][k];
				removed[j] = SimpleMath.lengthSq(vects[j]) < EPS; //*
			}
		}
		// return vects here if you don't want linear combination detection.
		
		int loc = 0;
		for(int i = 0; i < len; i++)
			if(!removed[i]) vects[loc++] = vects[i];
		return Arrays.copyOf(vects, loc);
	}
	

}
