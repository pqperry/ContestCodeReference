//#
package matrix;

import java.math.BigInteger;
import java.util.*;

public class MatrixPowmod {
	
	/*
	 * Matrix PowMod (returns (mat^pow)%mod)
	 * Running time: O(n^3 * lgN) [n = matrix size, N = pow]
	 * Tested on: Z-Trenning Super Climber
	 * Matrix must be a square matrix
	 */
	public static long[][] matrixPowmod(long[][] mat, long pow, long mod) {
		int size = mat.length;
		long[][] result = new long[size][size];
		for(int i = 0; i < size; i++) result[i][i] = 1;
		
		while(pow != 0) {
			if((pow & 1) == 1) result = matrixMult(mat, result, mod);
			mat = matrixMult(mat, mat, mod);
			pow >>= 1;
		}
		return result;
	}
	
	public static long[][] matrixMult(long[][] mat1, long[][] mat2, long mod) {
		int size = mat1.length;
		long[][] result = new long[size][size];
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				for(int k = 0; k < size; k++) {
					result[i][j] += (mat1[i][k] * mat2[k][j]) % mod;
					result[i][j] %= mod;
				}
			}
		}
		return result;
	}


	/*
	 * Big Matrix Pow (returns mat^pow)
	 * Running time: O(n^3 * lgN) [n = matrix size, N = pow]
	 * Matrix must be a square matrix
	 */
	public static BigInteger[][] bigMatrixPow(BigInteger[][] mat, long pow) {
		int size = mat.length;
		BigInteger[][] result = new BigInteger[size][size];
		for(int i = 0; i < size; i++) Arrays.fill(result[i], BigInteger.ZERO);
		for(int i = 0; i < size; i++) result[i][i] = BigInteger.ONE;
		
		while(pow != 0) {
			if((pow & 1) == 1) result = bigMatrixMult(mat, result);
			mat = bigMatrixMult(mat, mat);
			pow >>= 1;
		}
		return result;
	}
	
	public static BigInteger[][] bigMatrixMult(BigInteger[][] mat1, BigInteger[][] mat2) {
		int size = mat1.length;
		BigInteger[][] result = new BigInteger[size][size];
		for(int i = 0; i < size; i++) Arrays.fill(result[i], BigInteger.ZERO);
		
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				for(int k = 0; k < size; k++)
					result[i][j] = result[i][j].add(mat1[i][k].multiply(mat2[k][j]));
		return result;
	}
}
