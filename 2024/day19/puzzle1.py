import re

filename = "input"


def get_regex(towels):
    print("We found {} types of towels.".format(len(towels)))

    relevant_towels = []
    for towel in towels:
        others = towels.copy()
        others.remove(towel)

        possible_combinations = "^(" + "|".join(others) + ")*$"

        if not re.match(possible_combinations, towel):
            relevant_towels.append(towel)

    print("{} of them can not be made from other towels.".format(len(relevant_towels)))

    return "^(" + "|".join(relevant_towels) + ")*$"


def main():
    with open(filename) as file:
        lines = file.read().splitlines()

    towels = lines[0].split(", ")
    patterns = lines[2:len(lines)]

    possible_combinations = get_regex(towels)

    possible_designs = 0
    for pattern in patterns:
        if re.match(possible_combinations, pattern):
            possible_designs += 1

    print("{} designs are possible.".format(possible_designs))


if __name__ == '__main__':
    main()
