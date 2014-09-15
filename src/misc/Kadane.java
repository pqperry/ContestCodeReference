//#
package misc;

public class Kadane {
	
	/*
	 * Kadane's algorithm (Maximum contiguous subarray)
	 * Running time: O(n)
	 * Tested on: UVA 10684 
	 */
	public static int kadane(int[] arr) {		
		int maxHere = 0; int maxSoFar = 0;
		for(int val : arr) {
			maxHere = Math.max(maxHere + val, 0); // Assuming sum of empty array is 0
			maxSoFar = Math.max(maxHere, maxSoFar);
		}
		return maxSoFar;
	}

}
