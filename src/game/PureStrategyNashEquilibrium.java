//#
package game;

import geom.Pt;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PureStrategyNashEquilibrium {

	public static Point[] pureStrategyNashEquilibrium(Pt[][] pay) {
		int y = pay.length, x = pay[0].length;
		double[] rowmax = new double[y], colmax = new double[x];
		Arrays.fill(rowmax, Double.NEGATIVE_INFINITY);
		Arrays.fill(colmax, Double.NEGATIVE_INFINITY);
		
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				if(pay[i][j].x > colmax[i]) colmax[i] = pay[i][j].x;
				if(pay[i][j].y > rowmax[j]) rowmax[j] = pay[i][j].y;
			}
		}
		
		List<Point> ret = new ArrayList<Point>();
		for(int i = 0; i < y; i++)
			for(int j = 0; j < x; j++)
				if(pay[i][j].x == colmax[i] && pay[i][j].y == rowmax[j]) ret.add(new Point(i, j));
		
		return ret.toArray(new Point[0]);
	}
	
}
