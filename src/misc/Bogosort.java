//#
package misc;

import java.util.*;

public class Bogosort {
	
	/*
	 * Bogosort
	 * Running time: O(n!) on average. O(infinity) worst case
	 * Tested on: nothing;
	 */
	public static <T extends Comparable<? super T>> void bogosort(List<T> vals) {
		boolean isDone = false;
		while(!isDone) {
			T prev = null;
			isDone = true;
			for(T val : vals) {
				if(prev == null) prev = val;
				else if(prev.compareTo(val) > 0) {
					isDone = false;
					break;
				}
			}
			if(!isDone) Collections.shuffle(vals);
		}
	}

}
