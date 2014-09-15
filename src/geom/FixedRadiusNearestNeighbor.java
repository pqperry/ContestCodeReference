//# keepclass
package geom;
import java.util.*;

public class FixedRadiusNearestNeighbor {
	
	static final double EPS = 1e-7;
	
	Map<Long, List<Pt>> map = new HashMap<Long, List<Pt>>();
	Pt[] pts;
	double rad;
	
	public FixedRadiusNearestNeighbor(Pt[] pts, double rad) {
		this.pts = pts;
		this.rad = rad + EPS;
		
		for(Pt pt : pts) {
			long hash = hash(pt, 0, 0);
			List<Pt> list = map.get(hash);
			if(list == null) {
				list = new ArrayList<Pt>();
				map.put(hash, list);
			}
			list.add(pt);
		}
	}
	
	long hash(Pt pt, int dx, int dy) {
		long hi = ((long)Math.floor(pt.x/rad) + dx) << 32;
		long lo = ((long)Math.floor(pt.y/rad) + dy) & Integer.MAX_VALUE;
		return hi | lo;
	}
	
	public List<Pt> get(Pt pt) {
		List<Pt> ret = new ArrayList<Pt>();
		
		for(int dx = -1; dx <= 1; dx++) {
			for(int dy = -1; dy <= 1; dy++) {
				long hash = hash(pt, dx, dy);
				if(!map.containsKey(hash)) continue;
				for(Pt p : map.get(hash)) 
					if(pt.distance(p) <= rad) ret.add(p);
			}
		}
		
		return ret;
	}
	
	public Map<Pt, List<Pt>> getAll() {
		Map<Pt, List<Pt>> ret = new HashMap<Pt, List<Pt>>();
		for(Pt p : pts) ret.put(p, get(p));
		return ret;
	}

	
}
