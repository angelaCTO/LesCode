### Queue - Python Implementation
### (List Structure)
### FIFI structure

class Queue(object):
    def __init__(self):
        self.items = []
        
    
    def is_Empty(self)
        return not bool(self.items)
    
    
    def enqueue(self, item):
        self.items.insert(0, item)
    
    
    def dequeue(self.item):
        self.items.pop()
    
    
    def size(self):
        return len(self.items)
    
    
    def peek(self):
        return self.items[-1]
    
    
    def __repr__(self):
        return '{}'.format(self.items)
    

if __name__ == '__main__':
    q = Queue()
