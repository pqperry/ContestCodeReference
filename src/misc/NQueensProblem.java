//#
package misc;

import java.awt.Point;

public class NQueensProblem {
	
	/*
	 * Gives a solution for the n-Queens problem.
	 * Running time: O(n)
	 * There is no solution for n = 2 and n = 3.
	 * The board starts at square (0, 0), not (1, 1)
	 */
	public static Point[] nQueensProblem(int n) {
		Point[] ret = new Point[n];
		int wr = 0;
		if(n % 2 == 1) ret[--n] = new Point(n, n);
		
		for(int i = 0; i < n/2; i++) {
			if(n % 6 != 2) {
				ret[wr++] = new Point(i, 2*i + 1);
				ret[wr++] = new Point(i + n/2, 2*i);
			}
			else {
				ret[wr++] = new Point(i, (2*i + n/2 - 1) % n);
				ret[wr++] = new Point(n - i - 1, n - 1 - (2*i + n/2 - 1) % n);
			}
		}
		return ret;
	}
}
