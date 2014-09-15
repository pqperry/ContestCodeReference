//# keepclass
package datastructures;

import java.util.*;


@SuppressWarnings({"rawtypes", "unchecked"}) 
public class SkipList {
	
	/*
	 * Indexable Skip List.
	 * Tested on: NOTHING
	 * The following operations can be performed in O(lgn): 
	 * find(elem), insert(elem), delete(elem), indexOf(elem), elementAt(index), split(elem), split(index), concat(SkipList)
	 * getNext(SLnode) can be performed in O(1)
	 * 
	 * The methods looks kinda long, but the following lines of code are common to nearly all methods, so you can save
	 * a lot of time by leveraging the power of copypasta:
	 * 
	  	SLNode n = head;
		for(int h = startHeight-1; h >= 0; h--) {
			while(n.link[h] != null && n.link[h].data.compareTo(o) < 0) n = n.link[h];
			if(n.link[h] != null && o.equals(n.link[h].data)) something;
		}
	 */
	
	static class SLNode {
		SLNode[] next;
		int[] width;
		Comparable data;
	}
	
	Random rand = new Random(123456789); // pick seed
	
	private SLNode head;
	private int startHeight = 1;
	public int size = 0;
	
	public SkipList() {
		head = new SLNode();
		head.next = new SLNode[32]; 
		head.width = new int[32]; //*
		Arrays.fill(head.width, Integer.MAX_VALUE - 10);
	}
	
	// Inserts element. If two identical elements are inserted, they will both be placed in the skiplist
	public void insert(Comparable o) {
		size++;
		
		int level = 0;
		SLNode newNode = new SLNode();
		int r = rand.nextInt();
		while(((r >>> level++) & 1) == 1);
		if(level > startHeight) startHeight = level;
		newNode.next = new SLNode[level];
		newNode.width = new int[level];
		newNode.data = o;
		
		SLNode n = head;
		SLNode[] prev = new SLNode[startHeight];
		for(int h = startHeight-1; h >= 0; h--) {
			while(n.next[h] != null && n.next[h].data.compareTo(o) < 0) {
				if(level > h) newNode.width[h] += n.width[h];
				n = n.next[h];
			}
			prev[h] = n;
			if(level > h) {
				newNode.next[h] = n.next[h];
				n.next[h] = newNode;
			}
		}
		
		for(int i = 1; i < level; i++) newNode.width[i] += newNode.width[i-1];
		for(int i = level - 1; i > 0; i--) {
			newNode.width[i] = prev[i].width[i] - newNode.width[i-1];
			prev[i].width[i] = newNode.width[i-1] + 1;
		}
		newNode.width[0] = 1;
		for(int i = level; i < startHeight; i++) prev[i].width[i]++;
	}
	
	// deletes element. If multiple identical elements are present, only the first found is deleted.
	// If element not found, this method does nothing
	public boolean delete(Comparable o) {
		SLNode[] prev = new SLNode[startHeight];
		boolean found = false;
		SLNode n = head;
		for(int h = startHeight-1; h >= 0; h--) {
			while(n.next[h] != null && n.next[h].data.compareTo(o) < 0) n = n.next[h];
			prev[h] = n;
			if(n.next[h] != null && o.equals(n.next[h].data)) {
				if(!found) {
					for(int i = h+1; i < startHeight; i++) prev[i].width[i]--;
					size--;
					found = true;
				}
				n.width[h] += n.next[h].width[h] - 1;
				n.next[h] = n.next[h].next[h];
			}
		}
		return found;
	}
	
	// finds element. Returns the SLNode containing element if found, else returns null
	public SLNode find(Comparable o) {
		SLNode n = head;
		for(int h = startHeight-1; h >= 0; h--) {
			while(n.next[h] != null && n.next[h].data.compareTo(o) < 0) n = n.next[h];
			if(n.next[h] != null && o.equals(n.next[h].data)) return n.next[h];
		}
		return null;
	}
	
	// returns the element at index i. if index < 0 || index >= size, behavior is undefined
	public SLNode elementAt(int i) {
		i++; 
		SLNode n = head;
		for(int h = startHeight-1; h >= 0; h--) {
			while(n.next[h] != null && n.width[h] <= i) {
				i -= n.width[h];
				n = n.next[h];
			}
		}
		return n;
	}
	
	// returns the index of an element in the list. If element not found, returns the location the element would be
	public int indexOf(Comparable o) {
		int index = 0;
		SLNode n = head;
		for(int h = startHeight-1; h >= 0; h--) {
			while(n.next[h] != null && n.next[h].data.compareTo(o) < 0) {
				index += n.width[h];
				n = n.next[h];
			}
		}
		return index;
	}
	
	// splits the skiplist into two at index i. Returns the new skiplist
	public SkipList split(int i) {
		return null; 
	}
	
	// splits the skiplist at element. Returns the
	public SkipList split(Comparable o) {
		return null; 
	}
	
	// concats skiplist. assumes all elements in the second skiplist are larger than all elements in the first
	public void concat(SkipList s) {
	
	}
}
