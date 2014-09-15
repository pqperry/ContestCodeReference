package datastructures;

import java.util.Arrays;

/*
 * Array-backed binary min-heap
 * Tested on: GNY2009D
 */
public class BinaryHeap {
			
	int[] arr; // peek() = arr[1]
	int loc = 1; // current size = loc - 1;
	
	// size = max size
	// Complexity: O(size)
	public BinaryHeap(int size) {
		int len = 1;
		while(len < size + 1) len *= 2;
		arr = new int[len];
		Arrays.fill(arr, Integer.MAX_VALUE);
		arr[0] = Integer.MIN_VALUE;
	}
	
	// O(nums.length)
	public BinaryHeap(int[] nums) {
		this(nums.length);
		System.arraycopy(nums, 0, arr, 1, nums.length);
		for(int i = nums.length; i > 0; i--) swapDown(arr[i]);
	}
	
	// Returns: array loc of insert
	public int insert(int val) {
		arr[loc++] = val;
		return swapUp(loc - 1);
	}
	
	// Returns: deleted value
	public int deleteMin() {
		int ret = arr[1];
		arr[1] = arr[--loc];
		swapDown(1);
		return ret;
	}
	
	// Returns: new index location
	public int decreaseKey(int index, int val) {
		arr[index] = val;
		return swapUp(index);
	}
	
	int swapUp(int i) {
		while(arr[i] < arr[i/2]) {
			int temp = arr[i];
			arr[i] = arr[i/2];
			arr[i /= 2] = temp;
		}
		return i;
	}
	
	void swapDown(int i) {
		while(arr[i] > Math.min(arr[2 * i], arr[2 * i + 1])) {
			int sloc = arr[2 * i] < arr[2 * i + 1] ? 2 * i : 2 * i + 1;
			int temp = arr[i];
			arr[i] = arr[sloc];
			arr[i = sloc] = temp;
		}
	}
}
