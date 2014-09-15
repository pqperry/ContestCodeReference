package geom;

import java.util.Arrays;

import matrix.GaussianElimination;

public class HypersphereFromPoints {
	
	
	public static void main(String[] args) {
//		double[][] mat = {{2, 1, 3}, {5, 1, 6},
//				{8, 7, 9}};
		
		double[][] mat = {{1, 3, 2}, {1, 6, 5},
				{7, 9, 9}};
		double[] ret = smallestHypersphereFromPoints(mat, mat.length);
		System.out.println(Arrays.toString(ret));
	}

	public static double[] hypersphereFromPoints(double[][] pts) {
		int len = pts.length;
		double[][] mat = new double[len - 1][len];
		for(int i = 0; i < len - 1; i++)
			for(int j = 0; j < len - 1; j++)
				mat[i][len - 1] += (mat[i][j] = pts[i+1][j] - pts[i][j]) * (pts[i+1][j] + pts[i][j]) / 2;
		GaussianElimination.gaussElim(mat);
		double[] ret = new double[len - 1];
		for(int i = 0; i < len - 1; i++) ret[i] = mat[i][len-1];
		return ret;
	}
	
	
	public static double[] smallestHypersphereFromPoints(double[][] pts, int n) {
		int len = pts[0].length + 1;
		double[][] mat = new double[n - 1][len];
		for(int i = 0; i < n - 1; i++)
			for(int j = 0; j < len - 1; j++)
				mat[i][len - 1] += (mat[i][j] = pts[i+1][j] - pts[i][j]) * (pts[i+1][j] + pts[i][j]) / 2;
		GaussianElimination.gaussElim(mat); // may blow up without complete pivoting
		double[] ret = new double[len - 1];
		for(int i = 0; i < len - 1; i++) ret[i] = mat[i][len-1];
		return ret;
	}
}
