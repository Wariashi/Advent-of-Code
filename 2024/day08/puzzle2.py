filename = "input"


class Point:
    position_x = 0
    position_y = 0

    def __eq__(self, other):
        if not isinstance(other, type(self)):
            return NotImplemented
        return self.position_x == other.position_x and self.position_y == other.position_y

    def __hash__(self):
        return hash((self.position_x, self.position_y))

    def __init__(self, x, y):
        self.position_x = x
        self.position_y = y

    def __str__(self):
        return "[{}:{}]".format(self.position_x, self.position_y)


def read_input():
    with open(filename) as file:
        return file.read().splitlines()


def create_frequency_map(input_file):
    frequency_map = dict()
    for y in range(0, len(input_file)):
        for x in range(0, len(input_file[y])):
            frequency = input_file[y][x]
            if frequency != '.':
                frequency_map.setdefault(frequency, [])
                frequency_map[frequency].append(Point(x, y))
    return frequency_map


def is_within_mapped_area(antinode, map_size):
    x = antinode.position_x
    y = antinode.position_y
    return 0 <= x < map_size and 0 <= y < map_size


def calculate_antinodes(frequency_map, map_size):
    antinodes = set()
    for frequency in frequency_map:
        antennas = frequency_map[frequency]
        for first_antenna in antennas:
            for second_antenna in antennas:
                if first_antenna == second_antenna:
                    continue
                distance_x = second_antenna.position_x - first_antenna.position_x
                distance_y = second_antenna.position_y - first_antenna.position_y
                x = second_antenna.position_x
                y = second_antenna.position_y
                antinode = Point(x, y)
                while is_within_mapped_area(antinode, map_size):
                    antinodes.add(Point(x, y))
                    x += distance_x
                    y += distance_y
                    antinode = Point(x, y)
    return antinodes


def main():
    input_file = read_input()
    map_size = len(input_file)
    frequency_map = create_frequency_map(input_file)
    print("Found {} frequencies.".format(len(frequency_map)))
    antinodes = calculate_antinodes(frequency_map, map_size)
    print("Found {} antinodes.".format(len(antinodes)))


if __name__ == '__main__':
    main()
