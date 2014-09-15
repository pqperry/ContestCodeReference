package datastructures;

public class Trie {

	Node root = new Node('\0');
	
	public void insert(String s) {
		Node curr = root;
		for(char c : s.toCharArray()) {
			if(curr.next[c] == null) curr.next[c] = new Node(c);
			curr.leaf = false;
			curr = curr.next[c];
		}
	}
	
	public boolean contains(String s) {
		Node n = search(s);
		return n != null && n.leaf;
	}
	
	public boolean containsPrefix(String s) {
		return search(s) != null;
	}
	
	Node search(String s) {
		Node curr = root;
		for(char c : s.toCharArray()) {
			if(curr.next[c] == null) return null;
			curr = curr.next[c];
		}
		return curr;
	}
	
	static class Node {
		char c;
		Node[] next = new Node[128];
		boolean leaf = true;
		
		Node(char c) {
			this.c = c;
		}
	}
}
