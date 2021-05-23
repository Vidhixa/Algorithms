import java.io.*;
import java.util.*;

public class Solution {

    // Implement Bubble sorting
    static int[] sortedList(int[] arr) {
        for(int i=0 ; i < arr.length-1 ; i++) {
            for(int j=0 ; j< arr.length-i-1 ; j++ ){
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {
        // Hardcoding the input for now
        int arr[] = { 20, 12, 53, 1, 34, 7, 12}; 

        for (int element: sortedList(arr)) {
            System.out.println(element);
        }
    }
}
