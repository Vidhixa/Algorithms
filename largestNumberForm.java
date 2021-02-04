class Solution {
    public String largestNumber(int[] arr) {
        // We will compare each number with another and swap whenever required to make sure the sorting
        // takes care of comparisons between any 2 numbers
        for(int i=0; i<(arr.length-1) ; i++) {
            for(int j=i+1; j<arr.length ;  j++) {
                String a = String.valueOf(arr[i]);
                String b = String.valueOf(arr[j]);
                
                if(Long.parseLong(a+b) < Long.parseLong(b+a)) {
                    // We need to switch ints
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        
        String result = "";
        // We want to cover cases where starting many digits are 0
        boolean flag = true;
        // Now iterate through sorted array to get us the result string
        for(int val: arr) {
            if(val == 0 && flag) {
                 // ignore, these are starting zeros
            } else {
                flag = false;
                result += String.valueOf(val);
            } 
        }
        
        // Edge case - If flag is still true, it means that all digits were Zero so we return a single "0" 
        if(flag) {
            result = "0";
        }
        
        return result;
        
    }
}
