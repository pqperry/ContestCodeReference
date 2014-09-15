//#
package geom;
import java.util.*;

public class ClosestPair {
	
	/*
	 * Closest Pair of Points
	 * Running time: O(nlogn) [n = number of points];
	 * Tested on: UVA 10245, 11378;
	 * Note: This is the actual n*logn version, not the n*logn*logn one you often see
	 */
	public static double closestPair(Pt[] pts) {
		Arrays.sort(pts, new Comparator<Pt>() {
			public int compare(Pt o1, Pt o2) {
				return Double.compare(o1.x, o2.x);
			}
			
		});
		return closestPairInner(pts, 0, pts.length);
	}
	
	static final int LOOKBACK = 6; // number of points to look back. May be different for different distance metrics
	
	// define the distance function here. The default is euclidean distance
	static double dist(Pt p1, Pt p2) {
		return p1.distance(p2);
	}
	
	static double closestPairInner(Pt[] pts, int start, int end) {
		if(end - start == 1) return Double.POSITIVE_INFINITY;
		
		int split = (start + end)/2;
		double splitX = pts[split].x;
		double dist = Math.min(closestPairInner(pts, start, split), closestPairInner(pts, split, end));
		
		Pt[] sorted = new Pt[end - start];
		Pt[] checkL = new Pt[split - start], checkR = new Pt[end - split];
		int wr = 0, wrl = start, wrr = split, wrcl = 0, wrcr = 0;
		
		while(wr < sorted.length) {
			if(wrr >= end || (wrl < split && pts[wrl].y <= pts[wrr].y)) {
				Pt p = sorted[wr++] = pts[wrl++];
				if(Math.abs(p.x - splitX) < dist) {
					checkL[wrcl++] = p;
					for(int i = wrcr-1; i >= Math.max(0, wrcr-LOOKBACK); i--) dist = Math.min(dist, dist(p, checkR[i])); //not sure how many points to look back. 6? 3?
				}
			}
			else {
				Pt p = sorted[wr++] = pts[wrr++];
				if(Math.abs(p.x - splitX) < dist) {
					checkR[wrcr++] = p;
					for(int i = wrcl-1; i >= Math.max(0, wrcl-LOOKBACK); i--) dist = Math.min(dist, dist(p, checkL[i])); //same as above. wrcl-6? wrcl-3?
				}
			}
		}
		System.arraycopy(sorted, 0, pts, start, sorted.length);
		return dist;
	}

	
	
	//This version returns the actual pair of points
	public static Ret closestPair2(Pt[] pts) {
		Arrays.sort(pts, new Comparator<Pt>() {
			public int compare(Pt o1, Pt o2) {
				return Double.compare(o1.x, o2.x);
			}
			
		});
		return closestPairInner2(pts, 0, pts.length);
	}
	
	static Ret closestPairInner2(Pt[] pts, int start, int end) {
		if(end - start == 1) return new Ret(Double.POSITIVE_INFINITY, pts[0], pts[0]);
		
		int split = (start + end)/2;
		double splitX = pts[split].x;
		Ret ret1 = closestPairInner2(pts, start, split), ret2 = closestPairInner2(pts, split, end), ret = ret1.dist < ret2.dist ? ret1 : ret2;
		double dist = Math.min(ret1.dist, ret2.dist);
		
		Pt[] sorted = new Pt[end - start];
		Pt[] checkL = new Pt[split - start], checkR = new Pt[end - split];
		int wr = 0, wrl = start, wrr = split, wrcl = 0, wrcr = 0;
		
		while(wr < sorted.length) {
			if(wrr >= end || (wrl < split && pts[wrl].y <= pts[wrr].y)) {
				Pt p = sorted[wr++] = pts[wrl++];
				if(Math.abs(p.x - splitX) < dist) {
					checkL[wrcl++] = p;
					for(int i = wrcr-1; i >= Math.max(0, wrcr-LOOKBACK); i--) {
						double newdist = dist(p, checkR[i]);
						if(newdist < dist) ret = new Ret(dist = newdist, p, checkR[i]);
					}
				}
			}
			else {
				Pt p = sorted[wr++] = pts[wrr++];
				if(Math.abs(p.x - splitX) < dist) {
					checkR[wrcr++] = p;
					for(int i = wrcl-1; i >= Math.max(0, wrcl-LOOKBACK); i--) {
						double newdist =dist(p, checkL[i]);
						if(newdist < dist) ret = new Ret(dist = newdist, p, checkL[i]);
					}
				}
			}
		}
		System.arraycopy(sorted, 0, pts, start, sorted.length);
		return ret;
	}
	
	static class Ret {
		double dist;
		Pt p1, p2;
		
		public Ret(double dist, Pt p1, Pt p2) {
			this.dist = dist;
			this.p1 = p1;
			this.p2 = p2;
		}
	}

}
