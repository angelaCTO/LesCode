import math
import random

# Note, n = a*b - if n were not prime, it could be factored into a and b. 
# However, if a and b were greater than the square root of n then a*b > n; so,
# at least either a or b must be less than the square root of n, and to check 
# if n is prime, we need only test for factors less than or equal to the square
# root of n.

# Checks wheter an integer is prime using square root of n
def is_prime(n):
    for i in range(2, int(math.sqrt(n))):
        if (n % i) == 0:
            return False
    return True

# Finds the prime factors of a non-prime integer 
def find_prime_factors(n):
    divisors = [d for d in range(2, n//2 + 1) if (n % d == 0)]
    primes = [d for d in divisors if is_prime(d)]
    return primes

# Generates some prime integer
def gen_prime(n):
    while True:
        x = random.randint(pow(2, n - 2), pow(2, n - 1) - 1)
        x = 2 * x + 1
        if find_prime_factors(x):
            return x

# TEST ----------
print is_prime(5)
print is_prime(6)
print find_prime_factors(6)
print gen_prime(6)
