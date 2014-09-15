package datastructures;

/*
 * Order statistic AVL Tree.
 * add, remove, elementAt, getIndex, findClosest all O(lgn)
 * IMPORTANT: This tree stores a SET of elements. There will be no duplicates
 */
public class OrderStatAVLTree<T extends Comparable<? super T>> {
	
	Node root;
	@SuppressWarnings("unchecked")
	Node[] path = new OrderStatAVLTree.Node[100]; 
	boolean[] left = new boolean[100];
	int wr = 0;
	
	public int size() {
		return root == null ? 0 : root.size;
	}
	
	public void add(T t) {
		if(find(t) != null) return;
		if(root == null) root = new Node(t);
		else if(left[wr-1]) path[wr-1].l = new Node(t);
		else path[wr-1].r = new Node(t);
		while(wr-- > 0) restore(path[wr]);
	}
	
	public void remove(T t) {
		Node n = find(t);
		if(n == null) return;
		
		if(n.l != null && n.r != null) {
			Node o = n.l, p = n;
			for(; o.r != null; p = o, o = o.r) path[wr++] = o;
			n.data = o.data;
			if(p != n) p.r = o.l;
			else p.l = o.l;
			update(p);
		}
		else {
			Node child = n.l == null ? n.r : n.l;
			if(n == root) root = child;
			else if(left[--wr-1]) path[wr-1].l = child;
			else path[wr-1].r = child;
		}
		
		while(wr-- > 0) restore(path[wr]);
	}
	
	public T elementAt(int i) {
		if(root == null || i >= root.size || i < 0) return null;
		Node n = root;
		while(true) {
			int ls = sizeOf(n.l);
			if(i < ls) n = n.l;
			else if(i > ls) {
				n = n.r;
				i -= ls + 1;
			}
			else return n.data;
		}
	}
	
	public int getIndex(T t) {
		find(t);
		int ret = sizeOf(path[--wr].l);
		while(wr-- > 0) 
			if(!left[wr]) ret += sizeOf(path[wr].l) + 1;
		return ret;
	}
	
	// If floor == true,  return largest  value x such that x <= t
	// If floor == false, return smallest value x such that x >= t (aka ceil)
	public T findClosest(T t, boolean floor) {
		Node n = find(t);
		if(n != null) return n.data;
		while(wr-- > 0) 
			if(left[wr] ^ floor) return path[wr].data;
		return null;
	}
	
	// private methods below
	
	int balance(Node n) {
		return height(n.l) - height(n.r);
	}
	
	int sizeOf(Node n) {
		return n == null ? 0 : n.size;
	}
	
	int height(Node n) {
		return n == null ? -1 : n.height;
	}
	
	void update(Node n) {
		n.height = Math.max(height(n.l), height(n.r)) + 1;
		n.size = sizeOf(n.l) + sizeOf(n.r) + 1;
	}
	
	Node find(T t) {
		wr = 0;
		Node n = root;
		while(n != null) {
			int cmp = t.compareTo(n.data);
			left[wr] = cmp < 0;
			path[wr++] = n;
			if(cmp < 0) n = n.l;
			else if(cmp > 0) n = n.r;
			else return n;
		}
		return null;
	}
	
	void restore(Node n) {
		update(n);
		
		if(balance(n) == 2) {
			if(balance(n.l) == -1) rot(n.l, true);
			rot(n, false);
		}
		else if(balance(n) == -2) {
			if(balance(n.r) == 1) rot(n.r, false);
			rot(n, true);
		}
	}
	
	void rot(Node n, boolean left) {
		Node p = left ? n.r : n.l;
		if(left) {
			n.r = p.r;
			p.r = p.l;
			p.l = n.l;
			n.l = p;
		}
		else {
			n.l = p.l;
			p.l = p.r;
			p.r = n.r;
			n.r = p;
		}
		T temp = n.data;
		n.data = p.data;
		p.data = temp;
		update(p);
		update(n);
	}

	class Node {
		
		T data;
		Node l, r;
		int height = 0, size = 1;
		
		public Node(T data) {
			this.data = data;
		}

	}

}

