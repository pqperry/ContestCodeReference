//#
package geom;

import java.util.*;

public class RectangleAreaUnion {

	/*
	 * Axis-aligned rectangle area union in d dimensions
	 * Running time: O(n^d + nlgn) [n = number of hyperrectangles, d = number of dimensions];
	 * Tested on: SPOJ COVER2
	 * Usage: call reset() at the start of each case, add in points with add(), call calc()
	 */
	static Event[][] pts;
	static boolean[][] active;
	static int addloc = 0, dim = 0, numpts = 0;
	
	static void reset(int numRects, int dimensions) {
		numpts = numRects*2;
		addloc = 0;
		dim = dimensions;
		pts = new Event[dim][numpts];
		active = new boolean[dim][numRects];
	}
	
	// anchor is the smallest x, y, z... coord. size is width, height, depth...
	static void add(int[] anchor, int[] size) {
		for(int i = 0; i < dim; i++) {
			pts[i][2*addloc] = new Event(anchor[i], addloc);
			pts[i][2*addloc+1] = new Event(anchor[i] + size[i], addloc);
		}
		addloc++;
	}
	
	static long calc() {
		for(int i = 0; i < dim; i++)  {
			Arrays.sort(pts[i], new Comparator<Event>() {	
				public int compare(Event o1, Event o2) {
					return o1.loc - o2.loc;
				}
			});
		}
		return union(dim);
	}
	
	static long union(int currDim) {
		if(currDim == 0) return 1;
		int dimloc = dim - currDim;
		Arrays.fill(active[dimloc], false);
		long acc = 0;
		int numActive = 0, last = 0;
		for(int i = 0; i < numpts; i++) {
			Event curr = pts[dimloc][i];
			if(currDim != dim && !active[dimloc-1][curr.id]) continue;
			if(numActive > 0) acc += union(currDim-1) * (curr.loc - last);
			if(active[dimloc][curr.id] ^= true) numActive++; else numActive--;
			last = curr.loc;
		}
		return acc;
	}

	static class Event {
		int loc; int id;
		
		Event(int loc, int id) {
			this.loc = loc;
			this.id = id;
		}
	}
	
}
