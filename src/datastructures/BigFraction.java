//# keepclass
package datastructures;

import java.math.BigInteger;

public class BigFraction {

	private BigInteger n; // numerator
	private BigInteger d; // denominator
	
	public BigFraction(BigInteger num, BigInteger den) {
		BigInteger gcd = num.gcd(den);
		n = num.divide(gcd);
		n = den.divide(gcd);
	}
	
	public BigFraction add(BigFraction f) {
		return new BigFraction(n.multiply(f.d).add(f.n.multiply(d)), d.multiply(f.d));
	}
	
	public BigFraction subtract(BigFraction f) {
		return add(f.negate());
	}
	
	public BigFraction multiply(BigFraction f) {
		return new BigFraction(n.multiply(f.n), d.multiply(f.d));
	}

	public BigFraction divide(BigFraction f) {
		return multiply(f.inverse());
	}
	
	public BigFraction negate() {
		return new BigFraction(n.negate(), d);
	}
	
	public BigFraction inverse() {
		return new BigFraction(d, n);
	}
	
	public BigFraction power(int exp) {
		return new BigFraction(n.pow(exp), d.pow(exp));
	}

	
	// The following methods are auto-generated using Eclipse
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((d == null) ? 0 : d.hashCode());
		result = prime * result + ((n == null) ? 0 : n.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BigFraction other = (BigFraction) obj;
		if (d == null) {
			if (other.d != null)
				return false;
		} else if (!d.equals(other.d))
			return false;
		if (n == null) {
			if (other.n != null)
				return false;
		} else if (!n.equals(other.n))
			return false;
		return true;
	}
	

}
