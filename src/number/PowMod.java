//#
package number;

public class PowMod {

	/*
	 * Modular Exponentiation.
	 * Note that BigIntegers come with a modPow method
	 */
	public static long powmod(long num, long pow, long mod) {
		long result = 1;
		while(pow != 0) {
			if((pow & 1) == 1) result = (result * num) % mod;
			num = (num * num) % mod;
			pow >>= 1;
		}
		return result;
	}
}
