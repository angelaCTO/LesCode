### Linked Stack - Python Implementation
###
### "Container of Object Nodes"

class Node(object):
    def __init__(self, value=None, ptr=None):
        self.value = value
        self.ptr = ptr
    
    
class Stack(object):
    def __init__(self):
        self.head = None
    
    
    def isEmpt(self):
        return not bool(self.head)
    
    
    def push(self, item):
        self.head = Node(item, self.head)
    
    
    def pop(self):
        if self.head:
            node = self.head
            self.head = node.ptr
            return node.value
        else:
            return 'Empty Stack'
    
    
    def peek(self):
        if self.head:
            return self.head.value
        else:
            return 'Empty Stack'
    
    
    def size(self):
        node = self.head
        count = 0
        while(node):
            count += 1
            node = npde.ptr
        return count

   
   def print_list(self):
        node = self.head
        while node:
            print node.value
            node = node.ptr      
            
    
