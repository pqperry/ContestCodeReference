//# keepclass
package datastructures;

public class FenwickTree {

	double[] tree;
	
	public FenwickTree(int max) {
		tree = new double[max+1];
	}
	
	// Sums all values up to but NOT including the one at loc.
	// note: loc -= (loc & -loc) zeros last non-zero digit of loc
	// note: getVal(loc) = sum(loc) - sum(loc-1)
	// note: sumRange(from, to) = sum(to) - sum(from)
	public double sum(int loc) {
		double ret = 0;
		for(; loc > 0; loc -= (loc & -loc)) ret += tree[loc];
		return ret;
	}
	
	// one line recursive version
	public double sumR(int loc) {
		return loc > 0 ? tree[loc] + sumR(loc - (loc & -loc)) : 0;
	}

	public void increment(int loc, double val) {
		for(loc++; loc <= tree.length; loc += (loc & -loc)) tree[loc] += val;
	}
	
	public void scaleAll(double mult) {
		for(int i = 0; i < tree.length; i++) tree[i] *= mult;
	}
	
}
