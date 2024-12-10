filename = "input"


class Peak:
    x = 0
    y = 0

    def __eq__(self, other):
        if not isinstance(other, type(self)):
            return NotImplemented
        return self.x == other.x and self.y == other.y

    def __hash__(self):
        return hash((self.x, self.y))

    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __str__(self):
        return "[{}:{}]".format(self.x, self.y)


def get_topographic_map():
    with open(filename) as file:
        lines = file.read().splitlines()
    size = len(lines)
    topographic_map = []
    for _ in range(0, size):
        topographic_map.append([])
    for y in range(0, size):
        line = lines[y]
        for x in range(0, size):
            character = line[x]
            topographic_map[x].append(int(character))
    return topographic_map


def get_reachable_peaks(topographic_map, x, y):
    map_size = len(topographic_map)
    reachable_peaks = set()

    # if this is a peak, return it
    if topographic_map[x][y] == 9:
        reachable_peaks.add(Peak(x, y))
        return reachable_peaks

    # go east if possible
    if x < map_size - 1 and topographic_map[x + 1][y] == topographic_map[x][y] + 1:
        peaks = get_reachable_peaks(topographic_map, x + 1, y)
        for peak in peaks:
            reachable_peaks.add(peak)

    # go north if possible
    if 0 < y and topographic_map[x][y - 1] == topographic_map[x][y] + 1:
        peaks = get_reachable_peaks(topographic_map, x, y - 1)
        for peak in peaks:
            reachable_peaks.add(peak)

    # go south if possible
    if y < map_size - 1 and topographic_map[x][y + 1] == topographic_map[x][y] + 1:
        peaks = get_reachable_peaks(topographic_map, x, y + 1)
        for peak in peaks:
            reachable_peaks.add(peak)

    # go west if possible
    if 0 < x and topographic_map[x - 1][y] == topographic_map[x][y] + 1:
        peaks = get_reachable_peaks(topographic_map, x - 1, y)
        for peak in peaks:
            reachable_peaks.add(peak)

    return reachable_peaks


def main():
    topographic_map = get_topographic_map()
    map_size = len(topographic_map)
    total_score = 0
    for y in range(0, map_size):
        for x in range(0, map_size):
            if topographic_map[x][y] == 0:
                reachable_peaks = get_reachable_peaks(topographic_map, x, y)
                score = len(reachable_peaks)
                total_score += score
    print("Total score: {}".format(total_score))


if __name__ == '__main__':
    main()
