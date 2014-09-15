//#
package combinatorics;

public class Permutation {

	/*
	 * Generates all nPr (n "pick" r) permutations of items
	 * Running time: O(nPr) [n = arr.length, r = perm.length]
	 * Tested on: nothing
	 * Usage: arr = the array to permute. perm = empty array of the same type as arr. set perm's length
	 * equal to the desired r value in nPr. taken = default boolean array same length as arr. nextloc = 0
	 * Note: nPn = n!
	 */
	public static <T> void permutation(T[] arr, T[] perm, boolean[] taken, int nextloc) {
		if(nextloc >= perm.length) {
			handle(perm); // implement yourself
			return;
		}
		
		for(int loc = 0; loc < arr.length; loc++) {
			if(taken[loc]) continue;
			perm[nextloc] = arr[loc];
			taken[loc] = true;
			permutation(arr, perm, taken, nextloc + 1);
			taken[loc] = false;
		}
	}
	
	public static <T> void handle(T[] perm) {
		 // implement yourself
	}
	
	public static long[][] nPr(int n) {
		long[][] tri = new long[n+1][];
		tri[0] = new long[]{1};
		for(int i = 1; i <= n; i++) {
			tri[i] = new long[i+1];
			for(int j = 0; j <= i; j++) tri[i][j] += j == 0 ? 1 : i * tri[i-1][j-1];
		}
		return tri;
	}
	
	
	
}
