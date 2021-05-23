# Replace the array with another sorted array and search with element to be searched

array = [1, 2, 3, 4, 5, 6, 7]
search = 5

start = 0
end = len(array) - 1
found = False

while start < end:
    #print "Start " + str(start) + ", End " + str(end)
    mid = (start + end)/2
    if array[mid] == search:
        print "Found at index " + str(mid)
        found = True
        break
    else:
        if array[mid] > search:
            end = mid - 1
        else:
            start = mid + 1

if not found:
    print "Not found in the array"
