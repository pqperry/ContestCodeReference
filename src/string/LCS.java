//#
package string;
import java.util.*;

public class LCS {

	/*
	 * Longest Common Subsequence and variants
	 * Running time: O(nm) [n = string1.length, m = string2.length]
	 * Tested on: nothing;
	 */
	public static int lcs(String string1, String string2) {
		char[] str1 = (" " + string1).toCharArray();
		char[] str2 = (" " + string2).toCharArray();
		int[][] table = new int[str1.length][str2.length];
		
		for(int i = 1; i < str1.length; i++)
			for(int j = 1; j < str2.length; j++) 
				table[i][j] = str1[i] == str2[j] ? table[i-1][j-1] + 1 : Math.max(table[i-1][j], table[i][j-1]);

		return table[str1.length-1][str2.length-1];
		// If you want the actual list of subsequences: 
		// return traceBack(table, str1, str2, str1.length-1, str2.length-1);
	}
	
	
	// Note: using a set doesn't work, since we're modifying contents with StringBuilder.append
	public static List<StringBuilder> traceBack(int[][] table, char[] str1, char[] str2, int i, int j) {
		List<StringBuilder> ret = new ArrayList<StringBuilder>();
		if(i == 0 || j == 0)  {
			ret.add(new StringBuilder());
			return ret;
		}
		
		if(str1[i] == str2[j]) {
			ret = traceBack(table, str1, str2, i-1, j-1);
			char current = str1[i];
			for(StringBuilder sb : ret) sb.append(current);
			return ret;
		}
		
		if(table[i-1][j] >= table[i][j-1]) ret =  traceBack(table, str1, str2, i-1, j);
		if(table[i-1][j] <= table[i][j-1]) ret.addAll(traceBack(table, str1, str2, i, j-1));
		
		return ret;
	}
	
	
	// shortest common supersequence
	public static int scsLength(String string1, String string2) {
		return string1.length() + string2.length() - lcs(string1, string2);
	}
	
	// edit distance when only insertion/deletion is allowed OR when substitution costs twice as much as insertion/deletion
	public static int editDistNoSub(String string1, String string2) {
		return string1.length() + string2.length() - (2 * lcs(string1, string2));
	}
	
	// longest palindromic subsequence 
	public static int lps(String string) {
		return lcs(string, new StringBuffer(string).reverse().toString());
		// also inside for(StringBuilder sb) in traceback, add:
		//if(s.length() <= lcslen/2 || current == s.charAt(lcslen - s.length() - 1))
	}
	

}
