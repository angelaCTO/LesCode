### Max Heap
###
### Conceptually, a heap is a binary tree where each node is smaller (larger) 
### than its children. Heaps are generally useful for applications that repeatedly ### access the smallest (largest) element in the list. A min-(max-)heap lets you
### to find the smallest (largest) element in O(1) and to extract/add/replace it
### in O(ln n).
### 
### Note, Pythons importable 'heapq' module

class Max_Heap(object):

    def __init__(self, data=None):
        self.data = data or []
        for i in range(len(data)//2, -1, -1):
            self.__max_heapify__(i)


    def __repr__(self):
        return '{}'.format(self.data)
    
    
    def parent(self, i):
        return i >> 1
    
    
    def left_child(self, i):
        return (i << 1) + 1
    
    
    def right_child(self, i):
        return (i << 1) + 2     # +2 instead of +1 bc its 0 indexed
    
    
    def __max_heapify__(self, i):
        greatest = i
        left = self.left_child(i)
        right = self.right_child(i)
        n = len(self.data)
        greatest = (left < n and self.data[left] > self.data[i]) and left or i
        greatest = (right < n and self.data[right] > self.data[greatest]) and right or greatest
        if i is not greatest:
            self.data[i], self.data[greatest] = self.data[greatest], self.data[i]
            self.__max_heapify__(greatest)


    def extract_max(self):
        n = len(self.data)
        max_elem = self.data[0]
        self.data[0] = self.data[n-1]
        self.data = self.data[:n-1]
        self.__max_heapify__(0)
        return max_elem



def test_heapify():
    l1 = [3, 2, 5, 1, 7, 8, 2, 9]
    h = Max_Heap(l1)
    assert(h.extract_max() == 9)
    print h.data       

test_heapify()
    
