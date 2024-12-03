import re

filename = "input"

# read input file
with open(filename) as file:
    lines = file.readlines()

# get multiplications
multiplications = list()
for line in lines:
    for multiplication in re.findall("mul\\(\\d*,\\d*\\)", line):
        multiplications.append(multiplication)

# multiply and add results
result = 0
for multiplication in multiplications:
    factors = multiplication[4:len(multiplication) - 1].split(",")
    factor_left = int(factors[0])
    factor_right = int(factors[1])
    result += (factor_left * factor_right)

# print the result
print("Result: {}".format(result))
