//#
package number;

import java.math.*;

import matrix.MatrixPowmod;

public class FibonacciNumber {

	/*
	 * Computes the nth fibonacci number
	 * Running time: O(n) (assuming biginteger arithmetic is O(1))
	 * Tested on: Nothing
	 */
	public static BigInteger fibonacciNumber(int n) {
		BigInteger a = BigInteger.ONE, b = BigInteger.ONE;
		while(n-- > 2) {
			BigInteger sum = a.add(b);
			a = b;
			b = sum;
		}
		return b;
	}
	
	/*
	 * Computes the nth fibonacci number
	 * Running time: O(1) (assuming bigdecimal arithmetic is O(1))
	 * Tested on: Nothing
	 * Note: This is precise only up to n = 68. Beyond that, you'll need to set sqrt5 to a more accurate value.
	 * sqrt5 can be computed to arbitrary precision using Newton's method using f(x) = x^2 - 5;
	 */
	static BigDecimal sqrt5 = BigDecimal.valueOf(Math.sqrt(5));
	static BigDecimal phi = BigDecimal.ONE.add(sqrt5).divide(BigDecimal.valueOf(2));
	static BigDecimal omphi = BigDecimal.ONE.subtract(phi);
	public static BigInteger fibonacciNumber2(int n) {
		return phi.pow(n).subtract(omphi.pow(n)).divide(sqrt5, 0, RoundingMode.HALF_UP).toBigInteger();
	}
	
	/*
	 * Computes the nth fibonacci number
	 * Running time: O(lg(n)) (assuming biginteger arithmetic is O(1))
	 * Tested on: Nothing
	 */
	static BigInteger[][] mat = {{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};
	public static BigInteger fibonacciNumber3(int n) {
		return MatrixPowmod.bigMatrixPow(mat, n)[0][1];
	}

}
