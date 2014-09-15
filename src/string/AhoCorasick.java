//# keepclass
package string;

import java.util.*;
  
/*
 * Aho-Corasick Algorithm
 * Tested on: UVA 10679 (contains only);
 */
public class AhoCorasick {
    
    Node root = new Node();
    Set<String> patterns = new HashSet<String>();
    
    public void add(String s) {
    	patterns.add(s);
    	Node curr = root;
    	for(char c : s.toCharArray()) {
    		if(curr.next[c] == null) {
    			Node n = new Node();
    			n.c = c;
    			n.parent = curr;
    			curr.next[c] = n;
    		}
    		curr = curr.next[c];
    	}
    	curr.out.add(s);
    }
    
    public Node step(Node n, char c) {
    	while(n.next[c] == null && n != root) n = n.fail;
    	return n.next[c] == null ? root : n.next[c];
    }
    
    public Set<String> contains(String s) {
    	build(); // this will rebuild :(
    	Set<String> ret = new HashSet<String>();
    	Node curr = root;
    	for(char c : s.toCharArray()) {
    		curr = step(curr, c);
    		ret.addAll(curr.out);
    	}
    	return ret;
    }
    
    public Map<String, SortedSet<Integer>> search(String s) {
    	build();
    	Map<String, SortedSet<Integer>> ret = new HashMap<String, SortedSet<Integer>>();
    	for(String pat : patterns) ret.put(pat, new TreeSet<Integer>());
    	
    	Node curr = root;
    	char[] ca = s.toCharArray();
    	for(int i = 0; i < ca.length; i++) {
    		curr = step(curr, ca[i]);
    		if(curr.out.isEmpty()) continue;
    		for(String pat : curr.out) ret.get(pat).add(i - pat.length() + 1);
    	}
    	return ret;
    }

    void build() {
    	Queue<Node> q = new LinkedList<Node>();
    	q.add(root);
    	while(!q.isEmpty()) {
    		Node curr = q.poll();
    		for(Node child : curr.next)
    			if(child != null) q.add(child);
    		Node fail = curr.parent.fail;
    		while(fail.next[curr.c] == null && fail != root) fail = fail.fail;
    		if(fail.next[curr.c] != null && fail.next[curr.c] != curr) curr.fail = fail.next[curr.c];
    		else curr.fail = root;
    		curr.out.addAll(curr.fail.out);
    	}
    }
    
    static class Node {
    	char c;
    	Node parent = this, fail = this;
    	Node[] next = new Node[128];
    	Set<String> out = new HashSet<String>();
    }
    
} 