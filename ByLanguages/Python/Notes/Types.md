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
  - *Deep Copies* duplicate everything. 
    - A deep copy of a collection is two collections with all of the elements in the original collection duplicated.
  - *Shallow Copies* duplicate as little as possible. 
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
    - **"example".rjust(10, '-')** *conversely, ljust(#,delim)*
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
      

