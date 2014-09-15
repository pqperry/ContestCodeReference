//#
package misc;

import java.util.*;

public class QuickSelect {
	
	/*
	 * Randomized QuickSelect
	 * Running time: O(n) expected
	 * Tested on: nothing;
	 * Note: this version uses constant extra space (including stack space)
	 */
	static Random rand = new Random(1234567890);
	
	//public static <T extends Comparable<? super T>> T quickSelect(T[] vals, int k) // use this version if you don't want to use comparators
	public static <T> T quickSelect(T[] vals, int k, Comparator<T> comp) {
		if(vals.length == 1) return vals[0];
		int from = 0, to = vals.length;
		vals = vals.clone();
		
		while(true) {
			int pivotloc = rand.nextInt(to - from) + from;
			T pivot = vals[pivotloc];
			vals[pivotloc] = vals[--to];
			int wr = from;
			for(int i = from; i < to; i++) {
				if(comp.compare(vals[i], pivot) > 0) continue; 
				//if(vals[i].compareTo(pivot) > 0) continue; // use this version if you don't want to use comparators
				T temp = vals[i];
				vals[i] = vals[wr];
				vals[wr++] = temp;
			}
			if(wr == k) return pivot;
			else if(wr > k)  to = wr;
			else { from = wr; k--;}
		}
		
	}

}
