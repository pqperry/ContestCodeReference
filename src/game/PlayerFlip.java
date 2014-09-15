//#
package game;

import geom.Pt;

public class PlayerFlip {
	
	/*
	 * Flips the players in a payoff matrix. 
	 * i.e. the row player becomes the column player; the column player becomes the row player
	 */
	public static Pt[][] flip(Pt[][] pay) {
		int y = pay.length, x = pay[0].length;
		Pt[][] ret = new Pt[x][y];
		
		for(int i = 0; i < y; i++) 
			for(int j = 0; j < x; j++) 
				ret[j][i] = new Pt(pay[i][j].y, pay[i][j].x);
		
		return ret;
	}

}
