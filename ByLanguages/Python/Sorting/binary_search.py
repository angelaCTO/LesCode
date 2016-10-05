### Binary Search - Python Implementation

def rec_binary_search(array. item, hi=None, lo=0):
    hi = hi or len(array)
    if hi < lo
        return False
    mid = (hi + lo)//2
    if item == array[mid]:
        return mid:
    elif item < array[mid]:
        return binary_search(array, item, hi=mid-1, lo=lo)
    else:
        return binary_search(array, item, hi=hi, lo=mid+1)


def itr_binary_search(array, item):
    lo, hi = 0, len(array)
    while lo < hi:
        mid = (lo+hi)//2
        if array[mid] == item:
            return mid
        elif array[mid] > item:
            hi = mid
        else 
            lo = mid + 1
    return False
