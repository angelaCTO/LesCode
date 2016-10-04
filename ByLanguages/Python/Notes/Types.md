#PYTHONIC TYPES
---


##NUMERICAL TYPES
---

###INTEGERS
  - Python represents integers using the immutable int type. 
  - For immutable objects there is no difference between a variable and an object reference.
  - The size of Python's integers limited by machine memory
  

###FLOATS
  - Numbers with a fractional part are represented by the immutable type *float*
  - Single precision: a 32-bit float is represented by: 1 bit for sign (negative being 1, positive being 0), plus 23 bits for the significant digits (or mantissa), plus 8 bits for the exponent
  

###COMPLEX NUMBERS
  - A complex data type is an immutable number that holds a pair of floats
    - example, z = 3 + 4j
    - Examples:
      - z.real
      - z.imag
      - z.conjugate()
  - Imported from **cmath** module
    - Examples:
      - cmath.phase()
      - cmath.polar()
      - cmath.rect()
      - cmath.pi
      - cmath.e
 
     
###FRACTION MODULES
  - (Pass)


###DECIMAL MODULES
  - (Pass)


###OTHER
  - (Pass)



#BUILT-IN SEQUENCE TYPES
---
Sequence Types are defined to have the following properties:
  - **Membership Operator**
    - keyword *in*
  - **Size Method**
    - len(seq)
  - **Slicing Properties**
    - seq[:-1]
  - **Ierability**
    - looping


Python has five built-in sequence types:
  1. **Strings** (immutable)
    - s = ''
  2. **Tuples** (immutable)
    - t = ()
  3. **Lists** (mutable)
    - l = []
  4. **Byte Arrays** (mutable)
    - ba = bytearray(b'')
  5. **Bytes** (immuutable)
    - b = bytes([])
    

Immutable types are in general more efficient than mutable objects. In
addition, some collection data types (sets, dictionaries) can only be indexed by immutable data


All Python variables are object references, so copying mutable objects
can be tricky. When you say a = b you are actually pointing a to where b
points to. Thus, the need to understand *Deep Copying*:


###DEEP COPYING
  - **Deep Copies** duplicate everything. 
    - A deep copy of a collection is two collections with all of the elements in the original collection duplicated.
  - **Shallow Copies** duplicate as little as possible. 
    - A shallow copy of a collection is a copy of the collection structure, not the elements. 
    - With a shallow copy, two collections now share the individual elements.
    - >>> import copy
    - >>> newObj = copy.copy(myObj)      #Shallow Copy
    - >>> newObj = copy.deepcopy(myObj)  #Deep Copy


###SLICING
  - *indexing non-inclusive*
  - seq[start:]
  - seq[:end]
  - seq[start:end]
  - seq[start:end:step]
  - seq[-1:0:-1] *reverses list*
  

###STRINGS
  - **UNICODE**
    - Unicode encoding to include special characters in strings
    - From Python 3+, all strings are unicode, not just plain bytes
    - ASCII representations are given by 8-bits, Unicode representations usually use 16-bits
    - Unicode strings are prefixed w/ the 'u'
      - >>> u'Hello\u0020There'
        - 'Hello There'
   
  - **METHODS** *Some*
    - **str1.join(str2)** *or* **str1 + str2** *but, less efficient*
    - **rev_str = reversed(str)**
    - **str.rjust(n, c)** *conversely, ljust(n,c)*
      - >>> "example.rjust(10,'-')"
        - '----------example'
    - **format()**
      - >>> "{0} {1}".format("I’m the One!", "I’m not")
        - "I’m the One! I’m not"
      - >>> "The {who} was {0} last week".format(12, who="boy")
        - ’Buffy turned 17 this year!’
    - **[strs] = splitlines(str)** 
      - *Splits strings on line terminators, ex new line, and returns list of the splitted lines*
    - **str.split(d, n)** *splits left to right; conversely, str.rsplit vv.*
      - *Returns a list of strings splitting at most n times on delimiter string, d.* 
      - *If n is not given, it splits as many times as possible. If d is not given, it splits on whitespace*
    - **str.strip(substr)**
    - **str.swapcase()** 
      - *swaps lower for upper cases characters, vv.*
    - **str.capitalize()**
    - **str.lower()**
    - **str.upper()**
    - **str.index(substr)**
      - *Returns the index position of the substr or raises a ValueError exception on failure*
    - **str.find(substr)**
      - *Returns the index position of the substr or -1 on failure*
    - **str.count(substr, start, end)**
      - *Returns the number of occurences of substr in str*
    - **str.replace(substr, substr', n)**
      - *Returns a copy of the string with every (or a maximum of n if given) occur
rences of string substr replaced with string substr'*
      

###TUPLES
  - Immutable sequence type, seperated by commas
  - Where strings have a character at every position, tuples have an object reference at each position. For this reason, it is possible to create tuples that contain mutable objects, such as lists.
    - tl = ([1, 2, 3], [0,9,8])
  - Empty tuples are constructed by an empty pair of parentheses
    - >>> t0 = ()
  - Tuples with one item are constructed by following a value with a comma (it is not sufficient to enclose a single value in parentheses)
    - >>> t1 = ('e1', )
    
  - **METHODS** **Some*
    - **len(t)**
    - **t.count(e)**
      - *Returns a count of how many elements, e, are contained in tuple*
    - **t.index(e)**
      - *Returns the index of e*


  - **TUPLE UNPACKING**
    - In Python, any iterable object can be unpacked using the sequence unpackingoperator, *. 
    - This operator can be used with two or more variables.
    - In this case, the items in the left-hand side of the assignment (which are preceded by *) are assigned to the named variables, while the items left over are assigned to the starred variable
      - >>> x, y* = (1, 2, 3, 4)
      - >>> x
        - 1
      - >>> y
        - [2, 3, 4]


  - **NAMED TUPLES**
    - Named Tuples (part of *Collections*) behave just like the built-in tuple, with the same performance characteristics, but in addition they carry the ability to refer to items by name. This allows the creation of aggregates of data items
    - The first argument to collections.namedtuple is the name of the custom tuple data type to be created. The second argument is a string of space separated names, one for each item that the custom tuple will take.
      - **TupleX = collections.namedtuple("CustomID", "prop1 prop2 prop3")**
        - >>> employee = collections.namedtuple('id', ['job', 'age'])
        - >>> kid = employee('student', '22')
        - >>> kid.job
          - 'student'
        - >>> kid.age
          - '22'
          
          
          
###LISTS 
  - 0(1): **append()**, **pop()**
  - O(n): **remove()**, **index()**, ***in***

  - **METHODS** *Some*
    - **l.append(e)**
      - >>> ["jacob", "craig"].append("emy")
        - ["jacob", "craig", "emy"]
    - **l.extend(e)**
      - *Extends the list by appending all the iterable items in the given list. *
      - *Equivalent to a[len(a):]=L or using +=* 
        - ["jacob", "craig"].extend("emy") *or* ["jacob", "craig"] += ["emy"]
          - ["jacob", "craig", 'e', 'm', 'y']
    - **l.insert(i, e)**
      - *Inserts e into l one position before index i*
    - **l.remove(e)**
      - *Removes first element e found in l*
      - *Raises ValueError if e not found*
    - **l.pop()**
      - *Returns popped item*
    - **del l[i]**
      - *Deletes the object reference, not the content, i.e., it is a way to remove an item from a list given its index instead of its value.*
      - *When an object reference is deleted and no other object refers to its data, Python schedules the item to be garbage-collected*
    - **l.index(e)**
    - **l.count(e)**
    - **l.sort()**
    - **l.reverse()**

  - **LIST UNPACKING**
    - Similar to tuple unpacking
      - >>> h, *t = [0,1,2,3]
      - >>> h
        - 0
      - >>> t
        - [1, 2, 3]

  - **LIST COMPREHENSION**
    - *[item for item in iterable]*
    - *[expression for item in iterable]*
    - *[expression for item in iterable if condition]*
    - List comprehensions should only be used for simple cases, when each portion fits in one line (no multiple for clauses or filter expressions)
    
  - **LIST OPS TIME ANALYSIS**
    - O(1)
      - index[]
      - index assignment
      - append(e)
      - pop()
    - O(n)
      - pop(i)
      - insert(i, e)
      - del operator
      - iteration
      - contains (*in*)
      - reverse()
     
            
###BYTES / BYTE ARRAYS
  - Python provides two data types for handling raw bytes: 
    - **bytes** which is *immutable*, and **bytearray**, which is *mutable* 
    - Both types hold a sequence of zero or more unsigned 8-bits integers in the range 0 ... 255. The byte type is very similar to the string type and the bytearray provides mutating methods similar to lists.

