### Finds the Greatest Common Divisor of 2 integers

def find_gcd(a, b):
    while(b != 0):
        res = b
        a, b = b, (a % b)
    print res
    return res


#TEST--------
assert(find_gcd(8,12) == 4), "Uh-Oh!"

