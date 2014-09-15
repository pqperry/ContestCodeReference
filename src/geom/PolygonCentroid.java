//#
package geom;

public class PolygonCentroid {
	
	/*
	 * Polygon Centroid
	 * Running time: O(n). [n = number of points]
	 * Requires: points define the points of a non-self intersecting closed polygon
	 * Tested on: UVA 10173
	 */
	public static Pt polygonCentroid(Pt[] points) {
		double x = 0, y = 0;
		int len = points.length;
		double area = 0; // this is actually the area times 2
		
		for(int i = 0; i < len; i++) {
			double x1 = points[i].x;
			double x2 = points[(i+1)%len].x;
			double y1 = points[i].y;
			double y2 = points[(i+1)%len].y;
			
			double a = (x1*y2 - x2*y1); // area via shoelace formula
			x += (x1 + x2) * a;
			y += (y1 + y2) * a;
			area += a;
		}
		return new Pt(x/(3*area), y/(3*area));
	}

}
