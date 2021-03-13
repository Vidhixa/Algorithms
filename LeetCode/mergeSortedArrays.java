class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Edge case where only one array is given
        if(m == 0) {
            for(int j=0; j<n; j++) {
                nums1[j] = nums2[j];
            }
            System.out.println("Comes here");
            return;
        }    
        if(n == 0)
            return;
        
        int i = m+n-1;
        int pointerOne = m-1;
        int pointerTwo = n-1;
        // Rest of the cases; We start filling arrray backwards
        while(i>=0 && pointerOne>=0 && pointerTwo>=0) {
            if(nums1[pointerOne] > nums2[pointerTwo]) {
                nums1[i] = nums1[pointerOne];
                pointerOne -= 1;
                i -= 1;
            } else {
                nums1[i] = nums2[pointerTwo];
                pointerTwo -= 1;
                i -= 1;
            }
        }
    
        // There might be leftover values, do not forget them
        if(pointerOne >= 0) {
            while(i>=0 && pointerOne>=0) {
                nums1[i] = nums1[pointerOne];
                pointerOne -= 1;
                i -= 1;
            }
        }
        if(pointerTwo >= 0) {
            while(i>=0 && pointerTwo>=0) {
                nums1[i] = nums2[pointerTwo];
                pointerTwo -= 1;
                i -= 1;
            }
        }
    }
    
    //[2,0] [1] 1 1 i=1,p1=0,p2=0
    // True -> 2>1 T -> nums1[1]=2 -> i=0,p1=-1,p2=0
    
    // [1, 2, 3, 0, 0, 0]
    // i=3+3-1=5, p1=2, p2=2
    // 1st iteration: num1[2] is 3, nums2[2] is 6; nums1[5]=6
    // 2nd iteration: num1[2] is 3, nums2[1] is 5; nums1[4]=5
}
