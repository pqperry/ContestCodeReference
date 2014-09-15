//#
package misc;
import java.util.Arrays;

public class LIS {
	
	/*
	 * Longest Increasing Subsequence
	 * Running time: O(nlogn) [n = arr.length]
	 * Tested on: nothing
	 */
	public static int lis(int[] arr) {
		int[] best = new int[arr.length];
		Arrays.fill(best, Integer.MAX_VALUE);
		
		for(int i = 0; i < arr.length; i++) {
			int loc = -Arrays.binarySearch(best, arr[i]) - 1;
			if(loc < 0) continue;
			best[loc] = arr[i];
		}
		int len = 0;
		while(len < best.length && best[len] != Integer.MAX_VALUE) len++;
		return len;
	}
	
	// This version returns the actual sequence
	public static int[] lis2(int[] arr) {
		int[] best = new int[arr.length];
		int[] prev = new int[arr.length];
		int[] inds = new int[arr.length];
		Arrays.fill(best, Integer.MAX_VALUE);
		
		for(int i = 0; i < arr.length; i++) {
			int loc = -Arrays.binarySearch(best, arr[i]) - 1;
			if(loc < 0) continue;
			best[loc] = arr[i];
			inds[loc] = i; 
			if(loc - 1 >= 0) prev[i] = inds[loc - 1];
		}
		int len = 0;
		while(len < best.length && best[len] != Integer.MAX_VALUE) len++;
		
		int[] ret = new int[len];
		int index = inds[len - 1];
		for(int i = ret.length - 1; i >= 0; i--) {
			ret[i] = arr[index];
			index = prev[index];
		}
		return ret;
	}


}
