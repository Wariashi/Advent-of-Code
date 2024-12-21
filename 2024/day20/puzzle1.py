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


def get_cheats(racetrack):
    height = len(racetrack[0])
    width = len(racetrack)

    cheats = []
    for y in range(0, height):
        for x in range(0, width):
            current_distance = racetrack[x][y]
            if current_distance == WALL:
                continue

            # check east
            if x < width - 2 and racetrack[x + 2][y] < current_distance:
                saved_ps = current_distance - racetrack[x + 2][y] - 2  # it would also take 2ps without the cheat
                if 0 < saved_ps:
                    cheats.append(Cheat(x, y, x + 2, y, saved_ps))

            # check north
            if 1 < y and racetrack[x][y - 2] < current_distance:
                saved_ps = current_distance - racetrack[x][y - 2] - 2  # it would also take 2ps without the cheat
                if 0 < saved_ps:
                    cheats.append(Cheat(x, y, x, y - 2, saved_ps))

            # check south
            if y < height - 2 and racetrack[x][y + 2] < current_distance:
                saved_ps = current_distance - racetrack[x][y + 2] - 2  # it would also take 2ps without the cheat
                if 0 < saved_ps:
                    cheats.append(Cheat(x, y, x, y + 2, saved_ps))

            # check west
            if 1 < x and racetrack[x - 2][y] < current_distance:
                saved_ps = current_distance - racetrack[x - 2][y] - 2  # it would also take 2ps without the cheat
                if 0 < saved_ps:
                    cheats.append(Cheat(x, y, x - 2, y, saved_ps))

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
