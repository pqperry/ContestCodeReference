//#
package game;

import geom.Pt;

public class IEDS {
	
	public static boolean[][] ieds(Pt[][] pay) {
		int y = pay.length, x = pay[0].length;
		boolean[] rowelim = new boolean[y], colelim = new boolean[x];
		boolean hasElim = true;
		
		while(hasElim) {
			hasElim = false;
			for(int i = 0; i < x; i++) {
				if(colelim[i]) continue;
				out1: for(int j = 0; j < x; j++) {
					if(colelim[j] || i == j) continue;
					for(int k = 0; k < y; k++) {
						if(rowelim[k]) continue;
						if(pay[k][i].y <= pay[k][j].y) continue out1; //use < to elim weakly dominated strategies
					}
					hasElim = colelim[j] = true;
				}
			}
			
			for(int i = 0; i < y; i++) {
				if(rowelim[i]) continue;
				out2: for(int j = 0; j < y; j++) {
					if(rowelim[j] || i == j) continue;
					for(int k = 0; k < x; k++) {
						if(colelim[k]) continue;
						if(pay[i][k].x <= pay[j][k].x) continue out2; //use < to elim weakly dominated strategies
					}
					hasElim = rowelim[j] = true;
				}
			}
		}
		
		return new boolean[][]{rowelim, colelim};
	}
	
	public static Pt[][] compress(Pt[][] pay, boolean[] rowelim, boolean[] colelim) {
		int ncols = 0, nrows = 0;
		for(boolean b : rowelim) if(!b) nrows++;
		for(boolean b : colelim) if(!b) ncols++;
		
		Pt[][] ret = new Pt[nrows][ncols];
		int wrr = 0, wrc = 0;
		
		for(int i = 0; i < pay.length; i++) {
			if(rowelim[i]) continue;
			for(int j = 0; j < pay[0].length; j++) {
				if(colelim[j]) continue;
				ret[wrr][wrc++] = pay[i][j];
			}
			wrr++;
			wrc = 0;
		}
		
		return ret;
	}

}
