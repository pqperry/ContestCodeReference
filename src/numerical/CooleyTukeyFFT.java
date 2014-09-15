package numerical;

import java.util.Arrays;

public class CooleyTukeyFFT {

	/*
	 * Cooley-Tukey Radix-2 Decimation in Time Fast Fourier Transform (whatever that means)
	 * Running time: O(nlgn) [n = arr.length]
	 * Tested on: nothing
	 * Input: arr = array of numbers. arr[i] = ith number. arr[i][0] = real part of ith number. arr[i][1] = complex part.
	 * If inv == true, an inverse fft is performed.
	 * Note: arr.length must be a power of 2
	 */
	public static double[][] fft(double[][] arr, boolean inv) {
		double[][] ret = fft_inner(arr, inv, 0, arr.length, 1);
		if(!inv) return ret;
		
		for(int i = 0; i < arr.length; i++) {
			ret[i][0] /= arr.length;
			ret[i][1] /= arr.length;
		}
		
		return ret;
	}

	public static double[][] fft_inner(double[][] arr, boolean inv, int start, int len, int split) {
		if(len == 1) return new double[][]{arr[start]};
		
		double[][] even = fft_inner(arr, inv, start, len/2, split * 2);
		double[][] oddd = fft_inner(arr, inv, start + split, len/2, split * 2);

		double[][] ret = Arrays.copyOf(even, len);
		System.arraycopy(oddd, 0, ret, len/2, len/2);

		for(int i = 0; i < len/2; i++) {
			double ang = (inv ? -2 : 2) * Math.PI * i /len;
			double cos = Math.cos(ang), sin = Math.sin(ang);
			double re = ret[i + len/2][0], im = ret[i + len/2][1];
			
			ret[i + len/2][0] = ret[i][0] - (cos * re) - (sin * im);
			ret[i + len/2][1] = ret[i][1] - (cos * im) + (sin * re);
			
			ret[i][0] += (cos * re) + (sin * im);
			ret[i][1] += (cos * im) - (sin * re);
		}
		
		return ret;
	}

}
