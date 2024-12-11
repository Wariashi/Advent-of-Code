filename = "input"


def get_initial_configuration():
    with open(filename) as file:
        lines = file.read().splitlines()
    stones = lines[0].split()
    return stones


def blink(stones):
    new_stones = []
    for stone in stones:
        if stone == "0":
            new_stones.append("1")
        elif len(stone) % 2 == 0:
            center = int(len(stone) / 2)
            end = len(stone)
            new_stones.append(stone[0:center])
            new_stones.append(str(int(stone[center:end])))
        else:
            new_stones.append(str(int(stone) * 2024))

    return new_stones


def main():
    stones = get_initial_configuration()
    print("Initial arrangement:")
    for stone in stones:
        print("{} ".format(stone), end="")
    print()
    for i in range(1, 26):
        stones = blink(stones)
        print("\nAfter {} blink{}:".format(i, "" if i == 1 else "s"))
        for stone in stones:
            print("{} ".format(stone), end="")
        print()
    print("\nResult: {} stones".format(len(stones)))


if __name__ == '__main__':
    main()
