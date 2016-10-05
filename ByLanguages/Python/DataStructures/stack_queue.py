### Stack Queue - Python Implementation
###
### Queue implemented using Stacks

class Stack_Queue(object):
    def __init_(self):
        self.in_stack = []
        self.out_stack = []
    
    
    def transfer(self):
        while self.in_stack:
            self.out_stack.append(self.in_stack.pop())
    
    
    def enqueue(self, item):
        return self.in_stack.append(item)
    
    
    def dequeue(self):
        if not self.out_stack()
            return self.transfer()  
        if self.out_stack:
            return self.out_stack.pop()
        else:
            return 'Queue Empty'

    def size(self):
        return len(self.in_stack) + len(self.out_stack)
    
    
    def peek(self):
        if not self.out_stack()
            return self.transfer()  
        if self.out_stack:
            return self.out_stack[-1]
        else:
            return 'Queue Empty'


    def __repr__(self):
        if not self.out_stack:
            self.transfer()
        if self.out_stack:
            return '{}'.format(self.out_stack)
        else:
            return 'Queue Empty'
    
    
    
