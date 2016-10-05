### Stack - Python Implementation
###
### Stack is LIFO and can be implemented using just append() and pop() 
### O(1): push, pop, peek, empty, size


class Stack(object):

    def __init__(self):
        self.content = []
        self.min_array = []
        self.min = float('inf')

    
    def push(self, value):
        if value < self.min:
            self.min = value
        self.content.append(value)
        self.min_array.append(self.min)

    
    def pop(self):
        if self.content:
            value = self.content.pop()
            self.min_array.pop()
            if self.min_array:
                self.min = self.min_array[-1]
            return value
        else:
            return 'Empty Stack'

    
    def find_min(self):
        if self.min_array:
            return self.min_array[-1]
        else:
            return 'Empty Stack, No Min Value'

    
    def size(self):
        return len(self.content)

    
    def isEmpty(self):
        return not bool(self.content)

    
    def peek(self):
        if self.content:
            return self.content[-1]
        else:
            return 'Emty Stack'

    
    def __repr__(self):
        return '{}'.format(self.content)
    
    
    
