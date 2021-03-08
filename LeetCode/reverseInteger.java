class Solution {
    public int reverse(int x) {
        // Return quickly if it's a 0
        if(x == 0) {
            return 0;
        }
        
        
        boolean isNegative = x < 0 ? true : false;
        // Doing below is not a good idea coz if max, then there is a risk to go out of bounds
        /*if(isNegative) {
            x = x*(-1);
            System.out.println("X is after * -1 " + x);
        }*/
        
        long result = 0;
        int mod = 0;

        while(x != 0) {
            mod = x % 10;
            // Mod of 0 % 10 will also render right result
            result = (result * 10) + mod;
            System.out.println(result);
            x = x/10; 
            
            // Instead of lenghty code below, we can simplify by making result long and direct comparing if it goes over the int maxes of not
            if((result > Integer.MAX_VALUE && !isNegative) ||
               (result < Integer.MIN_VALUE && isNegative)) {
                   return 0;
                
            }

            // We want to use absolute in the comparison to keep things fair for -ve and +ve numbers
            // Correctly remember that max and min values are MAX_VALUE and MIN_VALUE
            //if(Math.abs(result) > Integer.MAX_VALUE/10 && x != 0) {
            //    result = 0;
            //    break;
            //}
            
            //if(Math.abs(result) == Integer.MAX_VALUE/10 && ((isNegative && (x % 10) > 8) || (!isNegative && (x % 10) > 7)))             //{
            //    result = 0;
            //    break;
            //}
        }
        
        System.out.println("Result " + result);
        return (int)result;
    }
}
