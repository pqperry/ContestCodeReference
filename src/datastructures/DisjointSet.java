//# keepclass
package datastructures;

import java.util.*;

/*
 * Disjoint Set
 * Running Time: Constructor takes O(num), everything else takes O(alpha(num))
 * Tested on: UVA 10608;
 * Remove lines marked with //*1 if you don't need to get the number of sets
 * Remove lines and methods marked with //*2 if you don't need to get the size of a set
 */
public class DisjointSet {
	
	public int sets; //*1
	int[] size; //*2
	
	int[] parent, rank;
	
	public DisjointSet(int num) {
		parent = new int[num];
		for(int i = 0; i < num; i++) parent[i] = i;
		rank = new int[num];
		
		sets = num; //*1
		size = new int[num]; //*2
		Arrays.fill(size, 1); //*2
	}
	
	public void union(int a, int b) {
		int r1 = find(a), r2 = find(b);
		if(r1 == r2) return;
		sets--; //*1
		
		if(rank[r1] < rank[r2]) parent[r1] = r2;
		else parent[r2] = r1;

		if(rank[r1] == rank[r2]) rank[r1]++;
		size[r2] = size[r1] = size[r2] + size[r1]; //*2
	}

	
	public int find(int x) {
		if(parent[x] != x) parent[x] = find(parent[x]);
		return parent[x];
	}
	
	//*2
	public int getSize(int x) { 
		return size[find(x)];
	}

}
