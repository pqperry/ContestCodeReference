//# keepclass
package datastructures;

/*
 * Running time: O(lgn) query and insert
 * Tested on: SPOJ RPLN
 */
public class RangeTree {
	
	public int[] dat; // dat[0..size] = individual elements. Can read, but do not write to this.
	int len = 1;
	   
	public RangeTree(int size) {
		while(len < size) len <<= 1;
		dat = new int[len << 1];
	}
	   
	public void update(int i, int val) {
		for(dat[i] = val; i < dat.length - 1; i = i >> 1 | len)
			dat[i >> 1 | len] = Math.min(dat[i], dat[i ^ 1]);
	}
	 
	public int query(int lo, int hi) {
		int res = 0;
		for(; lo <= hi; lo = (lo + 1) >> 1 | len, hi = (hi - 1) >> 1 | len) {
			if((lo & 1) == 1) res = Math.min(res, dat[lo]);
			if((hi & 1) == 0) res = Math.min(res, dat[hi]);
		}
		return res;
	}
 
}