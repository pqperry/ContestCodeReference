//#
package geom;

import java.util.*;

import datastructures.SegmentTree;
import datastructures.SegmentTree.Segment;


public class RectangleAreaUnion2D {
	
	static Point[] pts;
	static double[] ycoords;
	static int addloc = 0;
	static int numpts = 0;
	
	static void reset(int numRects) {
		numpts = numRects*2;
		ycoords = new double[numpts];
		addloc = 0;
		pts = new Point[numpts];
	}
	
	static void add(double x, double y, double w, double h) {
		Segment seg = new Segment(y, y+h);
		ycoords[addloc] = y;
		ycoords[addloc+1] = y+h;
		pts[addloc++] = new Point(x, seg, false);
		pts[addloc++] = new Point(x+w, seg, true);
	}
	
	static double calc() {
		Arrays.sort(pts, new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				return Double.compare(o1.x, o2.x);
			}
		});
		Arrays.sort(ycoords);
		
		SegmentTree st = new SegmentTree(ycoords, 0, addloc);
		
		double area = 0;
		double lastx = 0;
		for(Point p : pts) {
			area += (p.x - lastx) * st.length;
			lastx = p.x;
			if(p.isEnd) st.delete(p.seg);
			else st.insert(p.seg);
		}
		return area;
	}
	
	static class Point {
		double x;
		Segment seg;
		boolean isEnd;
		
		Point(double x, Segment seg, boolean isEnd) {
			this.x = x;
			this.seg = seg;
			this.isEnd = isEnd;
		}
	}

}
