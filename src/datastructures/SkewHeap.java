package datastructures;

/*
 * Skew Heap. Merge, insert, delete min all amortized O(lgn)
 * Tested on: GNY2009D (insert + deleteMin only)
 * Note: decreaseKey and delete(node) not implemented. Requires more complex implementation that tracks parent nodes (can't just swap nodes)
 */
public class SkewHeap {
	
	Node head; // peekMin() = SkewHeap.head.data
	
	// Merge other into this. Don't use other at all after merge
	public void merge(SkewHeap other) {
		head = mergeInner(this.head, other.head);
	}
	
	public void insert(int data) {
		head = mergeInner(this.head, new Node(data));
	}
	
	public int deleteMin() {
		int ret = head.data;
		head = mergeInner(head.l, head.r);
		return ret;
	}
	
	Node mergeInner(Node n1, Node n2) {
		if(n1 == null) return n2;
		if(n2 == null) return n1;
		if(n2.data < n1.data) return mergeInner(n2, n1);
		Node temp = n1.l;
		n1.l = mergeInner(n1.r, n2);
		n1.r = temp;
		return n1;
	}

	static class Node {
		int data;
		Node l, r;
		
		Node(int data) {
			this.data = data;
		}
	}
	
}
