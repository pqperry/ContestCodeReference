//# ignore
import static java.lang.Math.*;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.math.*;

@SuppressWarnings({ "unused"})
public class FastIO {
	
	public static void main(String[] args) throws IOException {
		
	}
	
	static Pattern pat = Pattern.compile("\\s+");
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Scanner sc = new Scanner(System.in);
	static StringTokenizer st = null;
	static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	
	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
		return st.nextToken();
	}
	
	static boolean hasNext() throws IOException {
		while(st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if(line == null) return false;
			st = new StringTokenizer(line);
		}
		return st.hasMoreTokens();
	}
	
	static int nextInt() throws IOException { return Integer.parseInt(next()); }
	static long nextLong() throws IOException { return Long.parseLong(next()); }
	static double nextDouble() throws IOException { return Double.parseDouble(next()); }
	static BigInteger nextBigInteger() throws IOException { return new BigInteger(next()); }

}
