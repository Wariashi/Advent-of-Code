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


def calculate_antinodes(frequency_map):
    antinodes = set()
    for frequency in frequency_map:
        antennas = frequency_map[frequency]
        for first_antenna in antennas:
            for second_antenna in antennas:
                if first_antenna != second_antenna:
                    distance_x = second_antenna.position_x - first_antenna.position_x
                    distance_y = second_antenna.position_y - first_antenna.position_y
                    x = second_antenna.position_x + distance_x
                    y = second_antenna.position_y + distance_y
                    antinodes.add(Point(x, y))
    return antinodes


def crop_antinodes(antinodes, map_size):
    cropped_antinodes = []
    for antinode in antinodes:
        x = antinode.position_x
        y = antinode.position_y
        if 0 <= x < map_size and 0 <= y < map_size:
            cropped_antinodes.append(antinode)
    return cropped_antinodes


def main():
    input_file = read_input()
    frequency_map = create_frequency_map(input_file)
    print("Found {} frequencies.".format(len(frequency_map)))
    antinodes = calculate_antinodes(frequency_map)
    print("Found {} antinodes in total.".format(len(antinodes)))
    cropped_antinodes = crop_antinodes(antinodes, len(input_file))
    print("Found {} antinodes within the mapped area.".format(len(cropped_antinodes)))


if __name__ == '__main__':
    main()
