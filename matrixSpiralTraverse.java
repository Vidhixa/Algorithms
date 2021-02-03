class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = cols - 1;
        
        List<Integer> result = new ArrayList<>();
        
        while(top <= bottom || left <= right) {
            
            goRight(left, right, top, matrix, result);
            top = top + 1;

            goDown(right, top, bottom, matrix, result);
            right = right - 1;
            
            if(top <= bottom) {
                goLeft(bottom, right, left, matrix, result);
                bottom = bottom - 1;
            }
            
            if(left <= right) {
                goUp(left, top, bottom, matrix, result);
                left = left + 1;
            }
            
            
            //System.out.println(left + " " + right + " " + top + " " + bottom);
        }
        
        
        return result;
    }
    
    public void goRight(int left, int right, int top, int[][] matrix, List<Integer> result) {
        System.out.println("Right");
        for(int i=left; i <= right; i++) {
            System.out.println(matrix[top][i]);
            result.add(matrix[top][i]);
        }
    }
    
    public void goDown(int right, int top, int bottom, int[][] matrix, List<Integer> result) {
        System.out.println("Down");
        for(int i=top; i <= bottom; i++) {
            System.out.println(matrix[i][right]);
            result.add(matrix[i][right]);
        }
    }
    
      public void goLeft(int bottom, int right, int left, int[][] matrix, List<Integer> result) {
          System.out.println("Left");
          System.out.println("Right val " + right + " left val " + left);
          System.out.println("Left");
        for(int i=right; i>= left; i--) {
            System.out.println(matrix[bottom][i]);
            result.add(matrix[bottom][i]);
        }
    }
    
    public void goUp(int left, int top, int bottom, int[][] matrix, List<Integer> result) {
        System.out.println("Up");
        for(int i=bottom; i >= top; i--) {
            System.out.println(matrix[i][left]);
            result.add(matrix[i][left]);
        }
    }
}
   
