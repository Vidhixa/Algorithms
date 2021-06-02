import java.util.List;
import java.io.*;
import java.util.*;


/*
Suppose we have some input data describing a graph of relationships between parents and children over multiple generations. The data is formatted as a list of (parent, child) pairs, where each individual is assigned a unique positive integer identifier.

For example, in this diagram, 6 and 8 have common ancestors of 4 and 14.

             15
             |
         14  13
         |   |
1   2    4   12
 \ /   / | \ /
  3   5  8  9
   \ / \     \
    6   7     11

parentChildPairs1 = [
    (1, 3), (2, 3), (3, 6), (5, 6), (5, 7), (4, 5),
    (4, 8), (4, 9), (9, 11), (14, 4), (13, 12),
    (12, 9),(15, 13)
]

Write a function that takes the graph, as well as two of the individuals in our dataset, as its inputs and returns true if and only if they share at least one ancestor.

Sample input and output:

hasCommonAncestor(parentChildPairs1, 3, 8) => false
hasCommonAncestor(parentChildPairs1, 5, 8) => true
hasCommonAncestor(parentChildPairs1, 6, 8) => true
hasCommonAncestor(parentChildPairs1, 6, 9) => true
hasCommonAncestor(parentChildPairs1, 1, 3) => false
hasCommonAncestor(parentChildPairs1, 3, 1) => false
hasCommonAncestor(parentChildPairs1, 7, 11) => true
hasCommonAncestor(parentChildPairs1, 6, 5) => true
hasCommonAncestor(parentChildPairs1, 5, 6) => true

Additional example: In this diagram, 4 and 12 have a common ancestor of 11.

        11
       /  \
      10   12
     /  \
1   2    5
 \ /    / \
  3    6   7
   \        \
    4        8

parentChildPairs2 = [
    (1, 3), (11, 10), (11, 12), (2, 3), (10, 2),
    (10, 5), (3, 4), (5, 6), (5, 7), (7, 8),
]

hasCommonAncestor(parentChildPairs2, 4, 12) => true
hasCommonAncestor(parentChildPairs2, 1, 6) => false
hasCommonAncestor(parentChildPairs2, 1, 12) => false

n: number of pairs in the input


*/
public class ParentChildPair {
 
    public static void main(String[] argv) {
        int[][] parentChildPairs = new int[][] {
                {1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5},
                {4, 8}, {4, 9}, {9, 11}, {14, 4}, {13, 12}, {12, 9},
                {15, 13}
        };

        int[][] parentChildPairs2 = new int[][] {
                {1, 3}, {11, 10}, {11, 12}, {2, 3}, {10, 2},
                {10, 5}, {3, 4}, {5, 6}, {5, 7}, {7, 8}
        };

        findNodesWithZeroAndOneParents(parentChildPairs);
        findNodesWithZeroAndOneParentsOptimize(parentChildPairs);
    }

    public static void printResult(List<List<Integer>> result) {
        System.out.println(result);
    }

    // Reference: https://gist.github.com/rahulsonwalkar/dc67cf3c6751336c32e7e5b7ac62461f
    public static List<List<Integer>> findNodesWithZeroAndOneParentsOptimize(int[][] parentChildPairs) {
        Map<Integer, Integer> parentChildrenMap = new HashMap<>();
        for(int[] pair: parentChildPairs) {
            // If child exist then add 1 to parent
            parentChildrenMap.put(pair[1], parentChildrenMap.getOrDefault(pair[1], 0) + 1);
            // If the same does not have parent then make value 0
            if(!parentChildrenMap.containsKey(pair[0]))
                parentChildrenMap.put(pair[0], 0);
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> zeroParents = new ArrayList<>();
        List<Integer> oneParent = new ArrayList<>();

        parentChildrenMap.forEach((key, value) -> {
            if(value == 0)
                zeroParents.add(key);
            if(value == 1)
                oneParent.add(key);
        });
        result.add(zeroParents);
        result.add(oneParent);

        printResult(result);
        return result;
    }

    public static List<List<Integer>> findNodesWithZeroAndOneParents(int[][] parentChildPairs) {
        Map<Integer, List<Integer>> parentChildrenMap = new HashMap<>();
        for(int[] pair: parentChildPairs) {
            List<Integer> children = parentChildrenMap.getOrDefault(pair[0], new ArrayList<>());
            children.add(pair[1]);
            parentChildrenMap.put(pair[0], children);
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(findNodesWithZeroParents(parentChildrenMap));
        result.add(findNodesWithOneParents(parentChildrenMap));
        printResult(result);

        return result;
    }

    public static List<Integer> findNodesWithZeroParents(Map<Integer, List<Integer>> parentChildrenMap) {
        Set<Integer> parentsKeys = new HashSet<>(parentChildrenMap.keySet());
        Set<Integer> childrensSet = new HashSet<>();
        for(int i: parentsKeys) {
            for(int child : parentChildrenMap.getOrDefault(i, new ArrayList<>())) {
                childrensSet.add(child);
            }
        }

        parentsKeys.removeAll(childrensSet);
        return new ArrayList<>(parentsKeys);
    }

    public static List<Integer> findNodesWithOneParents(Map<Integer, List<Integer>> parentChildrenMap) {
        Set<Integer> childrensSet = new HashSet<>();
        for(int i: parentChildrenMap.keySet()) {
            for(int child : parentChildrenMap.getOrDefault(i, new ArrayList<>())) {
                if(childrensSet.contains(child))
                    childrensSet.remove(child);
                else
                    childrensSet.add(child);
            }
        }

        return new ArrayList<>(childrensSet);
    }
}
