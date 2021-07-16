/*
 * Click `Run` to execute the snippet below!
 
 
 Given 3 arrays, A, B and queries. there are 2 forms of query. [0, i, j] means we need to update B[i] = j. Another type is [1, k], which means we need to find out the number of pairs in in A and B that sums up to k (1 from A and another from B).
The number in a and b is guaranteed to be greater than or equal to 0.
For example:
a = [1,2,3]
b = [2,4]
query = [[1,5], [0, 0, 1], [1, 5]]
For the first query, [1, 5], we can find 2 pairs, (1,4) and (3,2)
For the second query, we update b[0] = 1, so now b = [1,4]
For the third query, we can only find one pair to sum up to 5, which is (1,4)
 
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
    int[] a = new int[]{1,2,3};
    int[] b = new int[]{2,4};
    int[][] queries = new int[][]{{1,5},{0,0,1},{1,5}};
    
    Array arr = new Array(a, b);
    processQueries(arr, queries);
  }
  
  public static void processQueries(Array arr, int[][] queries) {
    for(int i=0; i<queries.length; i++) {
      if(queries[i].length == 2) { // Get sum pairs query
        System.out.println(arr.createSumPairs(queries[i][1]));
      } else if(queries[i].length == 3){
        arr.updateMap(queries[i][1], queries[i][2]);
      } else {
       // continue; We know of no other types of queries 
      } 
    }
  }
  
  static class Pair {
    int x, y;
    Pair(int x, int y) {
      this.x = x;
      this.y =y;
    }
    
    public String toString() {
      return("(" + x + "," + y + ")");
    }
  }
  
  static class Array {
    Map<Integer, Integer> A;
    Map<Integer, Integer> B;
    int[] a;
    int[] b;
    
    Array(int[] a, int[] b) {
      this.a = a;
      this.b = b;
      A = new HashMap<>();
      B = new HashMap<>();
      for(int i=0; i<a.length; i++) {
         A.put(a[i], A.getOrDefault(a[i], 0)+1);
      }
      for(int i=0; i<b.length; i++) {
         B.put(b[i], B.getOrDefault(b[i], 0)+1);
      }
    }
    
    public void updateMap(int index, int val) {
      int currentVal = B.get(b[index]);
      if(currentVal == 1)
        B.remove(b[index]);
      else
        B.put(b[index], B.get(b[index])-1);
      b[index] = val;
      B.put(b[index], B.getOrDefault(b[index], 0)+1);
    }
    
    public List<Pair> createSumPairs(int sum) {
      Set<Pair> result = new HashSet<>();
      for(int i=0; i<a.length; i++) {
        if(B.containsKey(sum-a[i]))
          result.add(new Pair(a[i], sum-a[i]));
      }
      return new ArrayList<>(result);
    }
    
  }
}
