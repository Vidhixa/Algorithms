import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// Problem:: It is New Year's Day and people are in line for the Wonderland rollercoaster ride. Each person wears a sticker indicating their initial position in the queue. 
// Initial positions increment by  from  at the front of the line to  at the back.
// Any person in the queue can bribe the person directly in front of them to swap positions. If two people swap positions, they still wear the same sticker denoting their original places in line. 
// 1 2 3 4 5 6 7 8 -> 1 2 5 3 4 7 8 6 is allowed has total 4 bribes -> 2 by 5, 1 by 7 and 1 by 8 
public class Solution {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int allowedBribes = 2;
        int totalBribes = 0;
        boolean isChaotic = false;
        // The best way to evaluate number of bribes is by looking back and seeing
        // the number of people that are dislocated
        for(int i=0; i< q.length; i++) {
            for(int j=0; j<i; j++) {
                if(q[j] > q[i])
                    totalBribes += 1;
            }
            
            // Calculate displacement for finding chaotic
            if(q[i] > i+1) {
                int bribes = q[i]-i-1;
                if(bribes > allowedBribes) {   
                    isChaotic = true;
                    System.out.println("Too chaotic");
                    break;
                }
            }
        }
            
        if(!isChaotic) {
            System.out.println(totalBribes);
        }
        
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}
