class Solution {
    public int myAtoi(String s) {
        // Trimming away whitespaces
        String trimmedString =  s.trim();
        
        // If string is empty then return quickly
        if(trimmedString.equals("")) {
            return 0;
        }
        
        String operationSign = "";
        int current = 0;
        
        // Eliminate what we don't want need to be processed
        // make sure the character matching is with single quotes
        // make sure string matching uses 'equals' method and not ==
        if(trimmedString.charAt(0) < '0' || trimmedString.charAt(0) > '9') {
            if(trimmedString.charAt(0) == '-' || trimmedString.charAt(0) == '+') {
               //Run some assignments
                operationSign = operationSign + trimmedString.charAt(0);
                //System.out.println("Sign is " + operationSign);
                ++current;
            } else {
                return 0;
            }
        } 

        
        String result = "";
        for (int i = current; i < trimmedString.length(); i++) { 
            if(trimmedString.charAt(i) >= '0' && trimmedString.charAt(i) <= '9') {
                result = result + trimmedString.charAt(i);
                
                // Hard bit here is to test if val goes over max int or not
                // Check if one digit less is equal or greater than MAX value
                // Then check if it is reasonably long or not to exceed the value
                // TODO: next 2 conditions can be impoved
                if((Integer.parseInt(result) == (Integer.MAX_VALUE/10) && (i+2 <= trimmedString.length()) && trimmedString.charAt(i+1) > '7' && (trimmedString.charAt(i+1) >= '0' && trimmedString.charAt(i+1) <= '9')))
                   {
                    return (operationSign.equals("-") ? Integer.MIN_VALUE : Integer.MAX_VALUE);
                    
                } else if((Integer.parseInt(result) > (Integer.MAX_VALUE/10) && (i+2 <= trimmedString.length()) &&
                     (trimmedString.charAt(i+1) >= '0' && trimmedString.charAt(i+1) <= '9'))) {
                    return (operationSign.equals("-") ? Integer.MIN_VALUE : Integer.MAX_VALUE);
                    
                } else {
                   // Do nothing; Continue
               }
            } else {
                // When we don't get an int, break and process result
                break;
            }
        }
        
        // This means no number was found
        if(result.equals("")) {
            return 0;
        }
        
        result = operationSign + result;
        return Integer.parseInt(result);
    }
}
