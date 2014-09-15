package misc;

import java.util.*;

public class RollingMedian {
	
	PriorityQueue<Double> min = new PriorityQueue<Double>(Arrays.asList(Double.POSITIVE_INFINITY));
	PriorityQueue<Double> max = new PriorityQueue<Double>(Arrays.asList(Double.POSITIVE_INFINITY));

	public void add(double val) {
		if(val < min.peek()) min.add(-val);
		else max.add(val);
		
		if(min.size() > max.size()) max.add(-min.poll());
		if(max.size() > min.size() + 1) min.add(-max.poll());
	}
	
	public double query() {
		return (min.size() + max.size()) % 2 != 0 ? max.peek() : (max.peek() + min.peek()) / 2;
	}
	
}
