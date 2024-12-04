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


def is_valid_diagonal_down_left(x, y):
    if get_element(x, y) != "X":
        return False
    if get_element(x - 1, y + 1) != "M":
        return False
    if get_element(x - 2, y + 2) != "A":
        return False
    if get_element(x - 3, y + 3) != "S":
        return False
    return True


def is_valid_diagonal_down_right(x, y):
    if get_element(x, y) != "X":
        return False
    if get_element(x + 1, y + 1) != "M":
        return False
    if get_element(x + 2, y + 2) != "A":
        return False
    if get_element(x + 3, y + 3) != "S":
        return False
    return True


def is_valid_diagonal_up_left(x, y):
    if get_element(x, y) != "X":
        return False
    if get_element(x - 1, y - 1) != "M":
        return False
    if get_element(x - 2, y - 2) != "A":
        return False
    if get_element(x - 3, y - 3) != "S":
        return False
    return True


def is_valid_diagonal_up_right(x, y):
    if get_element(x, y) != "X":
        return False
    if get_element(x + 1, y - 1) != "M":
        return False
    if get_element(x + 2, y - 2) != "A":
        return False
    if get_element(x + 3, y - 3) != "S":
        return False
    return True


def is_valid_horizontal_backwards(x, y):
    if get_element(x, y) != "X":
        return False
    if get_element(x - 1, y) != "M":
        return False
    if get_element(x - 2, y) != "A":
        return False
    if get_element(x - 3, y) != "S":
        return False
    return True


def is_valid_horizontal_forward(x, y):
    if get_element(x, y) != "X":
        return False
    if get_element(x + 1, y) != "M":
        return False
    if get_element(x + 2, y) != "A":
        return False
    if get_element(x + 3, y) != "S":
        return False
    return True


def is_valid_vertical_backwards(x, y):
    if get_element(x, y) != "X":
        return False
    if get_element(x, y - 1) != "M":
        return False
    if get_element(x, y - 2) != "A":
        return False
    if get_element(x, y - 3) != "S":
        return False
    return True


def is_valid_vertical_forward(x, y):
    if get_element(x, y) != "X":
        return False
    if get_element(x, y + 1) != "M":
        return False
    if get_element(x, y + 2) != "A":
        return False
    if get_element(x, y + 3) != "S":
        return False
    return True


# count XMAS occurrences
count = 0
for y in range(0, size):
    for x in range(0, size):
        if is_valid_diagonal_down_left(x, y):
            count += 1
        if is_valid_diagonal_down_right(x, y):
            count += 1
        if is_valid_diagonal_up_left(x, y):
            count += 1
        if is_valid_diagonal_up_right(x, y):
            count += 1
        if is_valid_horizontal_backwards(x, y):
            count += 1
        if is_valid_horizontal_forward(x, y):
            count += 1
        if is_valid_vertical_backwards(x, y):
            count += 1
        if is_valid_vertical_forward(x, y):
            count += 1

print("Result: {}".format(count))
