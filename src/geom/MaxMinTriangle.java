package geom;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaxMinTriangle {

	/*
	 * Maximium and minimum area triangle from a point set
	 * Running time: O(n^2) [n = pts.length]
	 * Tested on: SEUSA 2013 H;
	 * Returns int[2] such that arr[0] = 2 * min area and arr[1] = 2 * max area
	 */
	static int[] maxMinTriangle(Pt[] pts) {
		Arrays.sort(pts, new Comparator<Pt>() {
			public int compare(Pt o1, Pt o2) {
				if(o1.x != o2.x) return o1.x - o2.x;
				return o1.y - o2.y;
			}
		});
		
		for(int i = 0; i < pts.length; i++) pts[i].loc = i;
		
		List<Line> lines = new ArrayList<Line>();
		for(int i = 0; i < pts.length; i++)
			for(int j = i + 1; j < pts.length; j++) lines.add(new Line(pts[i], pts[j]));
		
		Collections.sort(lines, new Comparator<Line>() {
			public int compare(Line o1, Line o2) {
				return Double.compare(o1.ang, o2.ang);
			}
		});
		
		int max = -1, min = Integer.MAX_VALUE/2;
		
		for(Line l : lines) {
			max = Math.max(max, Math.abs(ccw(l.p1, l.p2, pts[0])));
			max = Math.max(max, Math.abs(ccw(l.p1, l.p2, pts[pts.length - 1])));

			if(l.p1.loc > 0) min = Math.min(min, Math.abs(ccw(l.p1, l.p2, pts[l.p1.loc - 1]))); //this relies on
			if(l.p2.loc < pts.length - 1) min = Math.min(min, Math.abs(ccw(l.p1, l.p2, pts[l.p2.loc + 1]))); // l.p1.loc < l.p2.loc

			Pt tempp = pts[l.p1.loc];
			pts[l.p1.loc] = pts[l.p2.loc];
			pts[l.p2.loc] = tempp;
			
			int temp = l.p1.loc;
			l.p1.loc = l.p2.loc;
			l.p2.loc = temp;
		}
		return new int[]{min, max};
	}
	
    public static int ccw(Pt p1, Pt p2, Pt p3) {
        return (p2.x - p1.x)*(p3.y - p1.y) - (p2.y - p1.y)*(p3.x - p1.x);
    } 
	
	@SuppressWarnings("serial")
	static class Pt extends Point {

		int loc = 0;

		public Pt(int x, int y) {
			super(x, y);
		}
		
	}
	
	static class Line {
		
		Pt p1, p2;
		double ang;
		
		public Line(Pt p1, Pt p2) {
			super();
			this.p1 = p1;
			this.p2 = p2;
		
			ang = Math.atan2(p2.y - p1.y, p2.x - p1.x);
		}
		
	}
}
