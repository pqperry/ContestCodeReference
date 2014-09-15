//# keepclass
package string;

import java.math.BigInteger;
import java.util.*;

public class RollingHash {
	
	static long prime = 31;
	static long mod = Integer.MAX_VALUE; // max int is a mersenne prime
	
	static long inv = BigInteger.valueOf(prime).modInverse(BigInteger.valueOf(mod)).longValue(); //2,078,209,981
	
	public long hash = 0;
	
	long mult = 1;
	Deque<Character> deq = new ArrayDeque<Character>();
	
	// [ABCD] << X
	public void addLast(char c) {
		hash = (hash * prime + c) % mod;
		mult = (mult * prime) % mod;
		deq.addLast(c);
	}
	
	// X >> [ABCD]
	public void addFirst(char c) {
		hash = (hash + mult * c) % mod;
		mult = (mult * prime) % mod;
		deq.addFirst(c);
	}
	
	// [ABCD] >> X
	public void removeLast() {
		mult = (mult * inv) % mod;
		hash = ((hash - deq.removeLast()) * inv) % mod;
		if(hash < 0) hash += mod;
	}
	
	// X << [ABCD]
	public void removeFirst() {
		mult = (mult * inv) % mod;
		hash = (hash - mult * deq.removeFirst()) % mod; 
		if(hash < 0) hash += mod;
	}

}
