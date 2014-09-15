//#
package misc;

import java.util.*;

public class MinimumSetCover {
	
    public static int minimumSetCover(int numElem, int[] subsets) {
        int max = (1 << numElem) - 1;
        int[] dp = new int[max+1];
        Arrays.fill(dp, 1, dp.length, Integer.MAX_VALUE/4);
  
        for(int i = 0; i < subsets.length; i++) {
            int[] ndp = new int[max+1];
            for(int j = 0; j < dp.length; j++) ndp[j] = Math.min(dp[j], 1 + dp[j & ~subsets[i]]);
            dp = ndp;
        }
        return dp[max];
    }

}
