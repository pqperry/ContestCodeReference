//#
package number;

import java.math.BigInteger;


public class ModInverse {
	
	/*
	 * Calculates modular multiplicative inverse of x modulo m. 
	 * (i.e a number y such that x*y = 1 mod m)
	 * The modular inverse of x modulo m exists iff gcd(x,m) = 1. None of the three versions check this precondition.
	 * The first version uses Java's built in Biginteger.modInverse. (This version should be preferred)
	 * The second uses the extended GCD algorithm (xy = 1 mod m -> xy - 1 = km -> xy - km = 1)
	 * The third version uses Euler's totient function (x^totient(m) = 1 mod m, so x^(totient(m)-1) = x^-1)
	 */
	public static long modInverse1(long x, long m) {
		return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(m)).longValue();
	}
	
	public static long modInverse2(long x, long m) {
		long temp = GCD.extendedGCD(x, m)[0] % m;
		if(temp < 0) temp += m;
		return temp;
	}
	
	public static long modInverse3(long x, long m) {
		return PowMod.powmod(x, EulerTotient.totient(m)-1, m);
	}

}
