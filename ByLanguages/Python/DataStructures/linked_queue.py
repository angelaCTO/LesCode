### Linked Queue - Python Implentations
### Queue implemented using Nodes

class Node(object):
    def __init__(self, value=None, ptr=None):
        self.value = value
        self.ptr = ptr

class Linked_Queue(object)
    def __init__(self):
        self.head = None
        self.tail = None
    
    
    def isEmpty(self):
        return not bool(self.head)
    
    
    def dequeue(self):
        if self.head:
            value = self.head.value
            self.head = self.head.ptr
            return value
        else:
            return 'Queue Empty'
    
    
    def enqueue(self, item):
        node = Node(item)
        if not self.head:
            self.head = node
            self.tail = node
        else:
            if self.tail:
                self.tail.ptr = node
            self.tail = node
    
    
    def size(self):
        node = self.head
        count = 0
        while(node):
            count += 1
            node = node.ptr
        return count
    
    
    def peek(self):
        return self.head.value
    
    
    def print_queue(self):
        node = self.head
        if node:
            print node.value
            node = node.ptr
            
                
