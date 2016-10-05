### Hash Table  - Python Implementation

class Hash_Table(object):

    def __init__(self, slots=10):
        self.slots = slots
        self.table = []
        self.create_table()
    
    
    def hash_key(self, value):
        return hash(value) % self.slots
    
    
    def create_table(self):
        for i in range(self.slots):
            self.table.append([])
    
    
    def add_item(self, value):
        key = self.hash_key(value)
        self.table[key].append(value)
    
    
    def print_table(self):
        for key in range(len(self.table)):
            print "Key = %s, Value = %s" % (key, self.table[key])
    
    def find_item(self, item):
        key = self.hash_key(item)
        if item in self.table[key]:
            return True
        else:
            return False
            
if __name__ == '__main__':
    dic = Hash_Table(5)
    for i in range(1, 40, 2):
        dic.add_item(i)
    dic.print_table()
    assert(dic.find_item(20) == False)
    assert(dic.find_item(21) == True)
         
