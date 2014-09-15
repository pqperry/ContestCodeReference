//#
package misc;

public class MagicSquare {

	/*
	 * Odd-Size Magic Square Generator
	 * Running time: O(n^2) [n = size]
	 * Tested on: UVA 1266;
	 * Note: the number 1 is placed in the center of the first row.
	 */
	public static int[][] magicOdd(int size) {
		int[][] square = new int[size][size];
		int x = size/2 - 1, y = 1;
		int val = 1;
			
		while(val <= size*size) {
			if(--y < 0) y += size;
			x = (x+1) % size;
			
			if(square[y][x] != 0) {
				y = (y+2) % size;
				if(--x < 0) x += size;
			}
			square[y][x] = val++;
		}
		return square;
	}


}
