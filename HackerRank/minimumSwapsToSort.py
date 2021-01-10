
# Complete the minimumSwaps function below.
def minimumSwaps(arr):
    swaps = 0
    i = 0
    count = 0
    
    while i < len(arr):
        maxDiff = -1
        for j in range(i+1, len(arr)):
            #fptr.write(str(arr) + ", compare " + str(arr[i]) + " and " + str(arr[j]) + '\n')
            diff = arr[i] - arr[j]
            if diff >= maxDiff:
                 maxDiff = diff
                 maxDiffIndex = j 
        if maxDiff < 0:
            i = i+1
            #fptr.write(str("New i ") + str(i) + "value " + str(arr[i]) + '\n')
        
        else:
            temp = arr[i]
            arr[i] = arr[maxDiffIndex]
            arr[maxDiffIndex] = temp
            swaps = swaps + 1
    #fptr.close()
        
    return swaps;
 
