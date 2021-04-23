class Solution {
  // I have another solution in Dynamic Programming repo
  // This solution uses recursion
   public String longestPalindrome(String s) {
        Map<String, Boolean> palindromeCheck = new HashMap<>();
        return findLongestPalindromeRec(s, palindromeCheck);
    }
    
    public String findLongestPalindromeRec(String s, Map<String, Boolean> palindromeCheck) {
        if(s.length() == 1)
            return s;
        
        if(palindromeCheck.getOrDefault(s, false) || checkPalindrome(s)) {
            //System.out.println(s);
            palindromeCheck.put(s, true);
            return s;
        } else {
            palindromeCheck.put(s, false);
        }
        
        
        String returnFront = findLongestPalindromeRec(s.substring(0, s.length()-1), palindromeCheck);
        String returnBack = findLongestPalindromeRec(s.substring(1, s.length()), palindromeCheck);
        
        return returnFront.length() > returnBack.length() ? returnFront : returnBack;
    }
    
    public boolean checkPalindrome(String s) {
        StringBuilder r = new StringBuilder(s);
        r.reverse();
        return s.equals(r.toString());
    }
}
