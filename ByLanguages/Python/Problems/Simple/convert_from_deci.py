### File: convert_from_deci.py
### -----
### Desc: Converts an decimal based number into any other base
### Note, if the base value is larger than 10, we must use non-numeric chars to represent 
### larger digits (i.e A=10, ...)



# Simple Conversion converts decimal base to any other base up to 10
def simple_from_deci(num, base):
    mult, res = 1, 0
    while num > 0:
        res += num % (10 * mult)
        mult *= base
        num = num//10
    return res

def test_simple_from_deci():
    num, base = 1001, 2
    assert to_deci(num, base) == 9, "Uh-Oh!"




# Convert a decimal based number to any other base up to 20
def from_deci(num, base):
    strs = "0123456789ABCDEFGHIJ"
    res = ""
    while num > 0:
        digit = num % base
        res = strs[digit] + res
        num = num // base
    return res

def test_from_deci():
    num, base = 31, 16
    assert(from_deci(num, base) == '1F'), "Uh-Oh!"


def rec_from_deci(num, base):
    strs = "0123456789ABCDEFGHIJ"
    if num < base: 
        return strs[num]
    else:
        return rec_from_deci(num//base, base) + strs[num % base]

def test_rec_from_deci():
        num, base = 9, 2
        assert(rec_from_deci(num, base) == '1001'), "Uh-Oh!"


#test_simple_from_deci()
#test_from_deci()
test_rec_from_deci()
