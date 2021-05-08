class Solution {
    public List<String> removeInvalidParentheses(String s) {
        if(s == null || s.length() < 1)
            return null;
        
        Map<String, Boolean> validParen = new HashMap<>();
        Set<String> result = new HashSet<>();
        
        recurseInvalidParenthesis(s, result, validParen);
        
        if(result.size() > 1 && result.contains(""))
            result.remove("");
        
        return new ArrayList<String>(result);
    }
    
    public void recurseInvalidParenthesis(String initial, Set<String> result, Map<String, Boolean> validParen) {
        Queue<Queue<String>> queue = new LinkedList<>();
        Queue<String> levelQueue = new LinkedList<>();
        levelQueue.add(initial);
        queue.add(levelQueue);
        
        while(!queue.isEmpty()) {
            levelQueue = queue.poll();  
            Queue<String> temp = new LinkedList<>();
            
            while(!levelQueue.isEmpty()) {
                String s = levelQueue.poll();
                if(validParen.getOrDefault(s, false) == true || isValidParenthesis(s)) {
                        validParen.put(s, true);
                        result.add(s);
                    }
                    else 
                        validParen.put(s, false);
                for(int removeIndex=0; removeIndex<s.length(); removeIndex++) {
                    String modified = "";
                    if(removeIndex == 0)
                        modified = s.substring(removeIndex+1, s.length());
                    else if(removeIndex == s.length()-1)
                        modified = s.substring(0, removeIndex);
                    else
                        modified = s.substring(0, removeIndex) + s.substring(removeIndex+1, s.length());

                    System.out.println(modified);
                    if(validParen.getOrDefault(modified, false) == true || isValidParenthesis(modified)) {
                        validParen.put(modified, true);
                        result.add(modified);
                    }
                    else 
                        validParen.put(modified, false);
                    temp.add(modified);
                }
            }
            
            if(result.size() > 0)
                return;
            
            if(!temp.isEmpty())
                queue.add(temp);
        }

        return ;
    }
    
    public boolean isValidParenthesis(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray()) {
            if(c == '(')
                stack.push('(');
            else if(c == ')') {
                if(!stack.isEmpty())
                    stack.pop();
                else
                    return false;
            }
        }
        
        return stack.isEmpty();
    }
}
