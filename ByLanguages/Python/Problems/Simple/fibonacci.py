### Fibonacci Calculator

# Generator
def fib_generator():
    a, b = 0, 1
    while True:
        yield b
        a, b = b, (a + b)


# Iterative yields O(n^2) 
def fib(n):
    if n < 3:
        return 1
    a, b = 0, 1
    count = 1
    while count < n:
        count += 1
        a, b = b, (a + b)
    return b


# Recursive yieds O(2^n)
def fib_rec(n):
    if n < 3:
        return 1
    return fib_rec(n - 1) + fib_rec(n - 2)


# TEST -----
for i in range(10):
    print 'For n = ' + str(i) + ':'
    print 'FIB: ', fib(i)
    print 'REC: ', fib_rec(i)
    print 

