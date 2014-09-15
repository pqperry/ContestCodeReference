//#
package combinatorics;

public class Combination {
	
	/*
	 * Generates all nCr (n "choose" r) permutations of items
	 * Running time: O(nCr) [n = arr.length, r = perm.length]
	 * Tested on: nothing
	 * Usage: arr = the array to permute. perm = empty array of the same type as arr. set perm's length
	 * equal to desired r value in nCr. nextloc = 0. nextpick = 0
	 */
	public static <T> void combination(T[] arr, T[] perm, int nextloc, int nextpick) {
		if(nextloc >= perm.length) {
			handle(perm);
			return;
		}
		
		for(int loc = nextpick; loc < arr.length; loc++) {
			perm[nextloc] = arr[loc];
			combination(arr, perm, nextloc + 1, loc + 1);
		}
	}
	
	public static <T> void handle(T[] perm) {
		 // implement yourself
	}
	
	
	/*
	 * Generates an array such that arr[a][b] = aCb (aka Pascal's Triangle)
	 * Running time: O(n^2)
	 */
	public static long[][] nCr(int n) {
		long[][] tri = new long[n+1][];
		tri[0] = new long[]{1};
		for(int i = 1; i <= n; i++) {
			tri[i] = new long[i+1];
			for(int j = 0; j <= i; j++) {
				if(j < tri[i-1].length) tri[i][j] += tri[i-1][j];
				if(j > 0) tri[i][j] += tri[i-1][j-1]; 
			}
		}
		return tri;
	}
	
	
}


