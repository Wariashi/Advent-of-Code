filename = "input"

# read input file
with open(filename) as file:
    lines = file.readlines()

# copy into a two-dimensional array
word_search = []
for line in lines:
    row = []
    for character in line:
        if character == 'X' or character == 'M' or character == 'A' or character == 'S':
            row.append(character)
    word_search.append(row)
size = len(word_search)


# returns the element or "." if the element is outside the matrix
def get_element(x, y):
    if x < 0 or size <= x:
        return "."
    if y < 0 or size <= y:
        return "."
    return word_search[y][x]


def is_valid_down_left(x, y):
    if get_element(x + 1, y - 1) != "M":
        return False
    if get_element(x, y) != "A":
        return False
    if get_element(x - 1, y + 1) != "S":
        return False
    return True


def is_valid_down_right(x, y):
    if get_element(x - 1, y - 1) != "M":
        return False
    if get_element(x, y) != "A":
        return False
    if get_element(x + 1, y + 1) != "S":
        return False
    return True


def is_valid_up_left(x, y):
    if get_element(x + 1, y + 1) != "M":
        return False
    if get_element(x, y) != "A":
        return False
    if get_element(x - 1, y - 1) != "S":
        return False
    return True


def is_valid_up_right(x, y):
    if get_element(x - 1, y + 1) != "M":
        return False
    if get_element(x, y) != "A":
        return False
    if get_element(x + 1, y - 1) != "S":
        return False
    return True


def is_valid(x, y):
    if not is_valid_down_left(x, y) and not is_valid_up_right(x, y):
        return False
    if not is_valid_down_right(x, y) and not is_valid_up_left(x, y):
        return False
    return True


# count X-MAS occurrences
count = 0
for y in range(0, size):
    for x in range(0, size):
        if is_valid(x, y):
            count += 1

print("Result: {}".format(count))
