//#
package misc;

public class InversionCount {

	public static int inversionCount(int[] arr, int lo, int hi) {
		if(hi - lo == 1) return 0;
		
		int split = (lo + hi)/2;
		int acc = inversionCount(arr, lo, split) + inversionCount(arr, split, hi);
		
		int[] sorted = new int[hi - lo];
		int rd1 = lo, rd2 = split, wr = 0;
		while(wr < sorted.length) {
			if(rd1 != split && (rd2 == hi || arr[rd1] < arr[rd2])) sorted[wr++] = arr[rd1++];
			else {
				sorted[wr++] = arr[rd2++];
				acc += split - rd1;
			}
		}
		System.arraycopy(sorted, 0, arr, lo, wr);
		return acc;
	}
	
	
	public static int inversionCount2(int[] arr, int lo, int hi) {
		if(hi - lo == 1) return 0;
		
		int split = (lo + hi)/2;
		int acc = inversionCount(arr, lo, split) + inversionCount(arr, split, hi);
		
		int[] sorted = new int[hi - lo];
		int rd1 = lo, rd2 = split, wr = 0;
		while(rd1 < split && rd2 < hi) {
			if(arr[rd1] < arr[rd2]) sorted[wr++] = arr[rd1++];
			else {
				sorted[wr++] = arr[rd2++];
				acc += split - rd1;
			}
		}
		while(rd1 < split) sorted[wr++] = arr[rd1++];
		while(rd2 < hi) sorted[wr++] = arr[rd2++];
		System.arraycopy(sorted, 0, arr, lo, wr);
		return acc;
	}
	
}
