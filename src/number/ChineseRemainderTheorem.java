//#
package number;


public class ChineseRemainderTheorem {
	
	/*
	 * Solves x = a mod m1, x = b mod m2
	 * Returns null if it fails to solve (which happens when m1, m2 aren't relatively prime)
	 * 三人同行七十稀，五树梅花廿一枝，七子团圆正月半，除百零五便得知。
	 */
	public static Long aznRemainder(long a, long b, long m1, long m2) {
		long[] gcd = GCD.extendedGCD(m1, m2);
		if(gcd[2] != 1) return null;
		long res = (a * m2 * gcd[1] + b * m1 * gcd[0]) % (m1 * m2);
		if(res < 0) res += m1 * m2;
		return res;
	}
	
	/*
	 * Solves x = a_n mod m_n. rhs = a_n, mod = m_n
	 * Returns null if it fails to solve (which happens when m_ns aren't relatively prime)
	 */
	public static Long aznRemainder(long[] rhs, long[] mod) {
		long modProduct = 1;
		for(long m : mod) modProduct *= m;
		long res = 0;
		for(int i = 0; i < rhs.length; i++) {
			long[] gcd = GCD.extendedGCD(mod[i], modProduct / mod[i]);
			if(gcd[2] != 1) return null;
			res += gcd[1] * (modProduct / mod[i]) * rhs[i];
			res %= modProduct;
		}
		if(res < 0) res += modProduct;
		return res;
	}

}
