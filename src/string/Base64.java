//#
package string;

public class Base64 {
	
	private static final char[] base64Table = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
	private static final char padChar = '=';
	private static int[] reverseTable = new int[256];
	
	static {
		for(int i = 0; i < base64Table.length; i++) reverseTable[base64Table[i]] = i;
	}
	
	public static String base64Encode(char[] bytes) {
		int pad = (3 - (bytes.length % 3)) % 3;
		int[] twobit = new int[4 * bytes.length + pad];
		int wr = 0, rd = 0;
		for(char b : bytes) {
			for(int shift = 6; shift >= 0; shift -= 2) twobit[wr++] = b >> shift & 3;
		}
		
		StringBuilder value = new StringBuilder();
		while(rd < wr) value.append(base64Table[twobit[rd++] << 4 | twobit[rd++] << 2 | twobit[rd++]]);
		while(pad-- > 0) value.append(padChar);
		
		return value.toString();
	}
	
	public static char[] base64Decode(char[] ca) {
		int[] twobit = new int[3 * ca.length];
		int wr = 0, rd = 0, pad = 0;
		for(char c : ca) {
			for(int shift = 4; shift >= 0; shift -= 2) twobit[wr++] = reverseTable[c] >> shift & 3;
			if(c == padChar) pad += 4;
		}

		char[] value = new char[(wr - pad)/4];
		while(rd < wr - pad) value[rd/4] = (char)(twobit[rd++] << 6 | twobit[rd++] << 4 | twobit[rd++] << 2 | twobit[rd++]);
		return value;
	}
	

}
