import sys

import numpy as np

filename = "input"

END = 0
START = sys.maxsize - 1
TRACK = -1
WALL = sys.maxsize


class Cheat:
    start_x = 0
    start_y = 0
    end_x = 0
    end_y = 0
    saved_ps = 0

    def __eq__(self, other):
        return (self.start_x == other.start_x and
                self.start_y == other.start_y and
                self.end_x == other.end_x and
                self.end_y == other.end_y)

    def __hash__(self):
        return hash((self.start_x, self.start_y, self.end_x, self.end_y))

    def __init__(self, start_x, start_y, end_x, end_y, saved_ps):
        self.start_x = start_x
        self.start_y = start_y
        self.end_x = end_x
        self.end_y = end_y
        self.saved_ps = saved_ps

    def __str__(self):
        return "Cheat[from=[{}:{}],to=[{}:{}],saved_ps={}]".format(
            self.start_x,
            self.start_y,
            self.end_x,
            self.end_y,
            self.saved_ps
        )


def get_racetrack():
    # read input
    with open(filename) as file:
        lines = file.read().splitlines()

    # create basic map
    height = len(lines)
    width = len(lines[0])
    racetrack = np.zeros([width, height], dtype=int)
    end_x = 0
    end_y = 0
    for y in range(0, height):
        for x in range(0, width):
            if lines[y][x] == "#":
                racetrack[x][y] = WALL
            elif lines[y][x] == ".":
                racetrack[x][y] = TRACK
            elif lines[y][x] == "S":
                racetrack[x][y] = START
            elif lines[y][x] == "E":
                racetrack[x][y] = END
                end_x = x
                end_y = y

    # update the remaining distance for every track tile
    x = end_x
    y = end_y
    current_distance = racetrack[x][y]
    while racetrack[x][y] == TRACK or racetrack[x][y] == START or racetrack[x][y] == END:
        racetrack[x][y] = current_distance
        if racetrack[x + 1][y] == TRACK or racetrack[x + 1][y] == START:
            x += 1
        elif racetrack[x][y - 1] == TRACK or racetrack[x][y - 1] == START:
            y -= 1
        elif racetrack[x][y + 1] == TRACK or racetrack[x][y + 1] == START:
            y += 1
        elif racetrack[x - 1][y] == TRACK or racetrack[x - 1][y] == START:
            x -= 1
        current_distance += 1

    return racetrack


def add_cheats_for_position(cheats, racetrack, center_x, center_y):
    height = len(racetrack[0])
    width = len(racetrack)
    current_distance = racetrack[center_x][center_y]

    for offset_y in range(0, 21):
        for offset_x in range(0, 21 - offset_y):
            cheat_duration = offset_x + offset_y

            # test north-east
            target_x = center_x + offset_x
            target_y = center_y - offset_y
            if (0 <= target_x < width and 0 <= target_y < height
                    and racetrack[target_x][target_y] < current_distance):
                saved_ps = current_distance - racetrack[target_x][target_y] - cheat_duration
                cheats.add(Cheat(center_x, center_y, target_x, target_y, saved_ps))

            # test north-west
            target_x = center_x - offset_x
            target_y = center_y - offset_y
            if (0 <= target_x < width and 0 <= target_y < height
                    and racetrack[target_x][target_y] < current_distance):
                saved_ps = current_distance - racetrack[target_x][target_y] - cheat_duration
                cheats.add(Cheat(center_x, center_y, target_x, target_y, saved_ps))

            # test south-east
            target_x = center_x + offset_x
            target_y = center_y + offset_y
            if (0 <= target_x < width and 0 <= target_y < height
                    and racetrack[target_x][target_y] < current_distance):
                saved_ps = current_distance - racetrack[target_x][target_y] - cheat_duration
                cheats.add(Cheat(center_x, center_y, target_x, target_y, saved_ps))

            # test south-west
            target_x = center_x - offset_x
            target_y = center_y + offset_y
            if (0 <= target_x < width and 0 <= target_y < height
                    and racetrack[target_x][target_y] < current_distance):
                saved_ps = current_distance - racetrack[target_x][target_y] - cheat_duration
                cheats.add(Cheat(center_x, center_y, target_x, target_y, saved_ps))


def get_cheats(racetrack):
    height = len(racetrack[0])
    width = len(racetrack)

    cheats = set()
    for y in range(0, height):
        for x in range(0, width):
            if racetrack[x][y] != WALL:
                add_cheats_for_position(cheats, racetrack, x, y)

    return cheats


def print_racetrack(racetrack):
    height = len(racetrack[0])
    width = len(racetrack)
    for y in range(0, height):
        for x in range(0, width):
            if racetrack[x][y] == WALL:
                print("#", end="")
            elif racetrack[x][y] == START:
                print("S", end="")
            elif racetrack[x][y] == END:
                print("E", end="")
            else:
                print(".", end="")
        print()


def main():
    racetrack = get_racetrack()
    cheats = get_cheats(racetrack)

    number_of_major_cheats = 0
    for cheat in cheats:
        if 100 <= cheat.saved_ps:
            number_of_major_cheats += 1
    print("{} cheats save at least 100 ps.".format(number_of_major_cheats))


if __name__ == '__main__':
    main()
