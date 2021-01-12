import re
import sys

# Complete the rotLeft function below.
def rotLeft(a, d):
    #eg: array is [1, 2, 3, 4, 5] d is 2
    if d == 0 or d == len(a) or len(a) <= 1:
        return a
    
    newArr = list()
    
    # Front part of rotated array
    for i in range(d, len(a)): # since first 2 elements will be rotated to back, we iterate from index 2 i.e. d upto end of array
        newArr.append(a[i])
     
    # Back part of rotated array
    for j in range(0, d): # since we now only want to append first d elements to back of array, we will start with 0 upto d
        newArr.append(a[j])
        
    return newArr
    
if __name__ == '__main__':
    a = [1, 2, 3, 4, 5]
    d = 2
    result = rotLeft(a, d)
    print str(result)
