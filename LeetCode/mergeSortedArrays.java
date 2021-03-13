class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int last = m+n-1;
        int p1 = m-1;
        int p2 = n-1;
        
        // Rest of the cases; We start filling arrray backwards
        for(int i=last; i>=0; i--) {
            // The below conditio takes care of 2 things
            // 1. if nums2 is size 0 then nothing to process
            // 2. if p2 reduces to negative then all that's left is nums1 which is in it's right array anyways
            if(p2 < 0)
                break;
            
            // Now check the greater than condition while we have nums2 and assign accordingly
            // Notice the optimization to make p1-- inline instead of a new line
            if(p1 >=0 && nums1[p1] > nums2[p2]) {
                nums1[i] = nums1[p1--];
            } else {
                nums1[i] = nums2[p2--];
            }
        }

    }
}
