import sys

import numpy as np

filename = "input"

width = 7 if filename == "example" else 71
height = 7 if filename == "example" else 71

max_bytes = 12 if filename == "example" else 1024

UNREACHABLE = sys.maxsize


class UpdateInfo:
    x = 0
    y = 0
    distance = UNREACHABLE

    def __init__(self, x, y, distance):
        self.x = x
        self.y = y
        self.distance = distance


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


def create_distance_map(memory_space):
    distance_map = np.zeros([width, height], dtype=int)
    for y in range(0, height):
        for x in range(0, width):
            distance_map[x][y] = UNREACHABLE

    updates = [UpdateInfo(width - 1, height - 1, 0)]
    while 0 < len(updates):
        print("at least {} distance updates left".format(len(updates)))

        update = updates[len(updates) - 1]
        x = update.x
        y = update.y
        distance = update.distance

        # don't make it worse (the distance might have changed)
        if distance_map[x][y] < distance:
            updates.remove(update)
            continue

        # update
        distance_map[x][y] = distance

        # schedule an update to the east
        if x < width - 1 and not memory_space[x + 1][y] and distance + 1 < distance_map[x + 1][y]:
            updates.append(UpdateInfo(x + 1, y, distance + 1))

        # schedule an update to the north
        if 0 < y and not memory_space[x][y - 1] and distance + 1 < distance_map[x][y - 1]:
            updates.append(UpdateInfo(x, y - 1, distance + 1))

        # schedule an update to the south
        if y < height - 1 and not memory_space[x][y + 1] and distance + 1 < distance_map[x][y + 1]:
            updates.append(UpdateInfo(x, y + 1, distance + 1))

        # schedule an update to the west
        if 0 < x and not memory_space[x - 1][y] and distance + 1 < distance_map[x - 1][y]:
            updates.append(UpdateInfo(x - 1, y, distance + 1))

        updates.remove(update)

    return distance_map


def print_memory_space(memory_space):
    for y in range(0, height):
        for x in range(0, width):
            if memory_space[x][y]:
                print("#", end="")
            else:
                print(".", end="")
        print()


def main():
    memory_space = np.zeros([width, height], dtype=bool)

    falling_bytes = get_falling_bytes()
    drop_bytes(memory_space, falling_bytes[0:max_bytes])
    print_memory_space(memory_space)
    distance_map = create_distance_map(memory_space)
    steps = distance_map[0][0]
    print("{} steps needed to reach the exit.".format(steps))


if __name__ == '__main__':
    main()
