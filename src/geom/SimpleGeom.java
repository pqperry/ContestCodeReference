//#
package geom;

import java.awt.geom.*;


public class SimpleGeom {
	
	public static class Circle {
		public Pt center;
		public double rad;
		
		// Note: all three points MUST be distinct
		public Circle(Pt p1, Pt p2, Pt p3) {
			// We "translate" everything so p1 = (0,0). (We don't actually move any points, since changing their location)
			double x1 = p2.x - p1.x;
			double x2 = p3.x - p1.x;
			double y1 = p2.y - p1.y;
			double y2 = p3.y - p1.y;
			
			double denom = 2*((x1*y2) - (y1*x2));
			double com1 = x1*x1 + y1*y1;
			double com2 = x2*x2 + y2*y2;
			
			double x = (y2*com1 - y1*com2)/denom;
			double y = (x1*com2 - x2*com1)/denom;
			center = new Pt(x + p1.x, y + p1.y);
			rad = center.distance(p1);
			
			//TODO: handle case where two points are the same
		}
		
		public Circle(Pt p1, Pt p2) {
			center = new Pt((p1.x + p2.x)/2, (p1.y + p2.y)/2);
			rad = center.distance(p1);
		}
		
		public Circle(Pt p1) {
			center = new Pt(p1.x, p1.y);
			rad = 0;
		}
		
		public Circle(Pt center, double rad) {
			this.center = center;
			this.rad = rad;
		}
		
		public boolean contains(Pt p) {
			return p.distance(center) <= rad;
		}
	}
	
	public static double ccw(Pt p1, Pt p2, Pt p3) {
		return (p2.x - p1.x)*(p3.y - p1.y) - (p2.y - p1.y)*(p3.x - p1.x);
	}
	
	public static long ccw2(long x1, long y1, long x2, long y2, long x3, long y3) {
		return (x2 - x1)*(y3 - y1) - (y2 - y1)*(x3 - x1);
	}

	public static Point2D.Double midpoint(Line2D.Double line) {
		return new Point2D.Double((line.x1 + line.x2)/2, (line.y1 + line.y2)/2);
	}
	
	public static Point2D.Double midpoint(Point2D.Double p1, Point2D.Double p2) {
		return new Point2D.Double((p1.x + p2.x)/2, (p1.y + p2.y)/2);
	}
	
	public static Line2D.Double perpendicular(Line2D.Double line, Point2D.Double anchor) {
		double rise = line.y2 - line.y1;
		double run  = line.x2 - line.x1;
		return new Line2D.Double(anchor, new Point2D.Double(anchor.x + rise, anchor.y - run));
	}
	
	public static Line2D.Double perpendicularBisector(Line2D.Double line) {
		return perpendicular(line, midpoint(line));
	}
	
	// returns null if lines are parallel (i.e no intersection or infinite number of intersections)
	public static Pt infinteLineIntersection(Line2D l1, Line2D l2) {
		double a1 = l1.getY2() - l1.getY1();
		double b1 = l1.getX1() - l1.getX2();
		double c1 = l1.getX2()*l1.getY1() - l1.getX1()*l1.getY2();
		
		double a2 = l2.getY2() - l2.getY1();
		double b2 = l2.getX1() - l2.getX2();
		double c2 = l2.getX2()*l2.getY1() - l2.getX1()*l2.getY2();
		
		double det = a1*b2 - a2*b1;
		if(det == 0) return null;
		double x = (b1*c2 - b2*c1)/det;
		double y = (a2*c1 - a1*c2)/det;
		return new Pt(x, y);
	}
	
	public static Line2D.Double circleFromThreePoints(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
		Line2D.Double l1 = perpendicularBisector(new Line2D.Double(p1, p2));
		Line2D.Double l2 = perpendicularBisector(new Line2D.Double(p2, p3));
		return new Line2D.Double(infinteLineIntersection(l1, l2), p1);
	}
	

}

