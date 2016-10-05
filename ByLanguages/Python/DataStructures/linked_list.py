### Linked List - Python Implementation

class Node(object):

    def __init__(self, value=None, ptr=None):
        self.head = value
        self.ptr = ptr
     
    
    def get_value(self):
        return self.value
    
    
    def set_value(self, value)
        self.value = value
    
    
    def get_next(self):
        return self.ptr
    
    
    def set_next(self, ptr):
        self.ptr = ptr


class Linked_List(object):
    
    def __init__(self):
        self.head = None
        self.length = 0
    
    
    def print_list(self):
        node = self.head
        if node:
            print node.value
            node = node.ptr
    
    def delete_node(self, prev, node):
        self.length -= 1
        if not prev:
            self.head = node.ptr
        else:
            prev.ptr = node.ptr


    def add_front(self, value):
        self.length += 1
        self.head = Node(value, self.head)


    def add_back(self, value):
        self.length += 1
        node = self.head
        while node:
            node = node.ptr
        node.ptr = Node(value)


    def index_find(self, index):
        if index > self.length:
            return 'Node DNE'
        else:
            if self.length > 0:
                prev = None
                node = self.head
                i = 1
                while ( i != index):
                    prev = node
                    node = node.ptr
                    i += 1
                return prev
                
    def value_find(self, value):
        if self.length > 0:
            node = self.head
            while node:
                if node.value == value:
                    return node 
                
