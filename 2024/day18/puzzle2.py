import numpy as np

filename = "input"

width = 7 if filename == "example" else 71
height = 7 if filename == "example" else 71


def get_falling_bytes():
    falling_bytes = []

    with open(filename) as file:
        lines = file.read().splitlines()

    for line in lines:
        coordinates = line.split(",")
        x = int(coordinates[0])
        y = int(coordinates[1])
        falling_bytes.append([x, y])

    return falling_bytes


def drop_bytes(memory_space, falling_bytes):
    for falling_byte in falling_bytes:
        x = falling_byte[0]
        y = falling_byte[1]
        memory_space[x][y] = True


def is_reachable(memory_space):
    # the distance doesn't matter anymore so we can flood the whole area
    flood_map = np.zeros([width, height], dtype=bool)

    # set a water source
    tiles_to_check = [[0, 0]]

    # flood the map
    while 0 < len(tiles_to_check):
        tile = tiles_to_check[len(tiles_to_check) - 1]
        x = tile[0]
        y = tile[1]

        if x == width - 1 and y == height - 1:
            # water reached the exit
            return True

        # don't flood it twice
        if flood_map[x][y]:
            tiles_to_check.remove(tile)
            continue

        # flood
        flood_map[x][y] = True

        # go east
        if x < width - 1 and not memory_space[x + 1][y]:
            tiles_to_check.append([x + 1, y])

        # go north
        if 0 < y and not memory_space[x][y - 1]:
            tiles_to_check.append([x, y - 1])

        # go south
        if y < height - 1 and not memory_space[x][y + 1]:
            tiles_to_check.append([x, y + 1])

        # go west
        if 0 < x and not memory_space[x - 1][y]:
            tiles_to_check.append([x - 1, y])

        tiles_to_check.remove(tile)

    return False


def print_memory_space(memory_space):
    for y in range(0, height):
        for x in range(0, width):
            if memory_space[x][y]:
                print("#", end="")
            else:
                print(".", end="")
        print()


def main():
    falling_bytes = get_falling_bytes()

    lower_bound = 0  # highest index of non-blocking byte
    upper_bound = len(falling_bytes) - 1  # lowest index of blocking byte

    while 1 < upper_bound - lower_bound:
        print(
            "I'm not done yet, but the first blocking byte's index is somewhere between {} and {}.".format(
                lower_bound,
                upper_bound
            )
        )
        pivot = (lower_bound + upper_bound) // 2  # byte to test
        memory_space = np.zeros([width, height], dtype=bool)
        drop_bytes(memory_space, falling_bytes[0:pivot + 1])

        if is_reachable(memory_space):
            lower_bound = pivot
        else:
            upper_bound = pivot

    blocking_byte = falling_bytes[upper_bound]
    print("The first blocking byte is {},{} at index {}.".format(blocking_byte[0], blocking_byte[1], upper_bound))


if __name__ == '__main__':
    main()
