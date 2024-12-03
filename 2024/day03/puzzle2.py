import re

filename = "input"

# read input file
with open(filename) as file:
    lines = file.readlines()

# get instructions
instructions = list()
for line in lines:
    for instruction in re.findall("do\\(\\)|don't\\(\\)|mul\\(\\d*,\\d*\\)", line):
        instructions.append(instruction)

# multiply and add results
enabled = True
result = 0
for instruction in instructions:
    if instruction == "do()":
        enabled = True
    elif instruction == "don't()":
        enabled = False
    elif enabled:
        factors = instruction[4:len(instruction) - 1].split(",")
        factor_left = int(factors[0])
        factor_right = int(factors[1])
        result += (factor_left * factor_right)

# print the result
print("Result: {}".format(result))
