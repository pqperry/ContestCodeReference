//#
package geom;

import geom.SimpleGeom.Circle;

import java.util.*;

public class Skyum {

	public static Circle skyum(Pt[] pts) {
		pts = AndrewsMonotoneChain.monotoneChain(pts);

		Node[] nodes = new Node[pts.length];
		for(int i = 0; i < nodes.length; i++) nodes[i] = new Node(i, pts[i]);
		
		TreeSet<Node> set = new TreeSet<Node>();
		for(int i = 0; i < nodes.length; i++) {
			nodes[i].link(nodes[(i-1+nodes.length)%nodes.length], nodes[(i+1)%nodes.length]);
			set.add(nodes[i]);
		}
		
		while(set.last().angle > Math.PI/2) set.pollLast().delete(set);
		return set.pollLast().mbc();
	}
	
	private static class Node implements Comparable<Node> {
		
		int id;
		Pt point;
		Node next, prev;
		double radius, angle;
		
		Node(int id, Pt point) {
			this.id = id;
			this.point = point;
		}
		
		void link(Node next, Node prev) {
			this.next = next;
			this.prev = prev;
			update();
		}
		
		void update() {
			if(this == prev || this == next) radius = new Circle(prev.point, next.point).rad;
			else if(prev == next) radius = new Circle(prev.point, point).rad;
			else radius = new Circle(prev.point, point, next.point).rad;
			
			if(prev == next) angle = 0;
			else angle = Math.acos(((prev.point.getX() - point.getX())*(next.point.getX() - point.getX()) + (prev.point.getY() - point.getY())*(next.point.getY() - point.getY()))/(prev.point.distance(point)*next.point.distance(point)));
		}
		
		Circle mbc() {
			Circle circ = new Circle(prev.point, point);
			if(circ.contains(next.point)) return circ;
			
			circ = new Circle(point, next.point);
			if(circ.contains(prev.point)) return circ;
			
			circ = new Circle(next.point, prev.point);
			if(circ.contains(point)) return circ;
			
			return new Circle(prev.point, point, next.point);
		}
		
		void delete(Set<Node> set) {	
			set.remove(prev);
			set.remove(next);
			
			prev.link(next, prev.prev);
			next.link(next.next, prev);

			set.add(prev);
			set.add(next);
		}
		
		public int compareTo(Node o) {
			if(radius != o.radius) return Double.compare(radius, o.radius);
			if(angle != o.angle) return Double.compare(angle, o.angle);
			return id - o.id;
		}
	}
	
}
