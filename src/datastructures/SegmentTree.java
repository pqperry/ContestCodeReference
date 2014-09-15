//# keepclass
package datastructures;

import java.util.*;

public class SegmentTree {
	
	SegmentTree l, r;
	Segment range;
	Set<Segment> segs = new HashSet<Segment>();
	boolean leaf = false;
	
	public double length = 0; //*
	
	// pts MUST be sorted
	public SegmentTree(double[] pts, int from, int to) {
		range = new Segment(pts[from], pts[to-1]);
		if(leaf = to - from <= 2) return;
		int split = (from + to)/2;
		l = new SegmentTree(pts, from, split+1);
		r = new SegmentTree(pts, split, to);	
	}
	
	public void insert(Segment nseg) {
		if(nseg.contains(range)) segs.add(nseg);
		else if(!leaf){
			if(nseg.intersects(l.range)) l.insert(nseg);
			if(nseg.intersects(r.range)) r.insert(nseg);
		}
		
		if(!segs.isEmpty()) length = range.end - range.start; //*
		else length = leaf ? 0 : l.length + r.length; //*
	}
	
	public void delete(Segment nseg) {
		if(nseg.contains(range)) segs.remove(nseg);
		else if(!leaf){
			if(nseg.intersects(l.range)) l.delete(nseg);
			if(nseg.intersects(r.range)) r.delete(nseg);
		}
		
		if(!segs.isEmpty()) length = range.end - range.start; //*
		else length = leaf ? 0 : l.length + r.length; //*
	}
	
	public Set<Segment> stab(double loc) {
		Set<Segment> ret = new HashSet<Segment>();
		ret.addAll(segs);
		if(l != null && l.range.containsPt(loc)) ret.addAll(l.stab(loc));
		if(r != null && r.range.containsPt(loc)) ret.addAll(r.stab(loc));
		return ret;
	}
	
	public static class Segment {
		double start, end;
		
		public Segment(double start, double end) {
			this.start = start;
			this.end = end;
		}
		
		boolean contains(Segment seg) {
			return start <= seg.start && end >= seg.end;
		}
		
		boolean containsPt(double loc) {
			return start <= loc && end >= loc;
		}
		
		boolean intersects(Segment seg) {
			return (seg.start <= end) && (start <= seg.end);
		}
	}
}
