#!/bin/python

import math
import os
import random
import re
import sys
import copy

# Referenced this solution from https://www.hackerrank.com/challenges/minimum-swaps-2/forum/comments/473577
def minimumSwaps(arr):
    # Use O(n) space to store the sorted array to be used as reference
    ref_arr = sorted(arr)
    # 4 1 2 3
    index_dict = {v: i for i,v in enumerate(arr)}
    #{4:0, 1:1, 2:2, 3:3}
    swaps = 0

    for i,v in enumerate(arr): # 0,4
        correct_value = ref_arr[i] # 1
        if v != correct_value: # 4 != 1
            to_swap_ix = index_dict[correct_value] # to: 1
            arr[to_swap_ix],arr[i] = arr[i], arr[to_swap_ix] # 1, 4, 2, 3 | 1, 2, 4, 3 | 1, 2, 3, 4
            index_dict[v] = to_swap_ix
            index_dict[correct_value] = i
            swaps += 1 
            
    return swaps
        

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(raw_input())

    arr = map(int, raw_input().rstrip().split())

    res = minimumSwaps(arr)

    fptr.write(str(res) + '\n')

    fptr.close()
