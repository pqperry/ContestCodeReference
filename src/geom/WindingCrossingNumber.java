//#
package geom;

public class WindingCrossingNumber {

	/*
	 * Winding/Crossing Number
	 * Running time: O(n) [n = poly.length];
	 * Tested on: UVA 634
	 * Unless you need the actual winding/crossing number, consider using Java's Polygon.contains() or Path2D.contains()
	 * A counterclockwise winding gives positive winding number. Clockwise = negative
	 * For points on boundaries, this method behaves the same was as Java's Polygon.contains() which says a point is considered to lie inside a Shape if and only if: 
	 * -- it lies completely inside the Shape boundary or
	 * -- it lies exactly on the Shape boundary and the space immediately adjacent to the point in the increasing X direction is entirely inside the boundary or
	 * -- it lies exactly on a horizontal boundary segment and the space immediately adjacent to the point in the increasing Y direction is inside the boundary. 
	 */
	public static int windingCrossingNumber(Pt[] poly, Pt point) {
		int num = 0;
		Pt p1 = poly[poly.length-1];
		for(int i = 0; i < poly.length; i++) {
			Pt p2 = poly[i];
			double dir = SimpleGeom.ccw(p1, p2, point);
			if(p1.y <= point.y && p2.y > point.y && dir > 0) num++;
			else if(p1.y > point.y && p2.y <= point.y && dir < 0) num--; // use num++ for crossing number
			p1 = p2;
		}
		return num; // Math.abs if you don't want signed number
	}

}
