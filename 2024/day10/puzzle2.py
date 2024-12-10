filename = "input"


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
    reachable_peaks = 0

    # if this is a peak, return it
    if topographic_map[x][y] == 9:
        return 1

    # go east if possible
    if x < map_size - 1 and topographic_map[x + 1][y] == topographic_map[x][y] + 1:
        peaks = get_reachable_peaks(topographic_map, x + 1, y)
        reachable_peaks += peaks

    # go north if possible
    if 0 < y and topographic_map[x][y - 1] == topographic_map[x][y] + 1:
        peaks = get_reachable_peaks(topographic_map, x, y - 1)
        reachable_peaks += peaks

    # go south if possible
    if y < map_size - 1 and topographic_map[x][y + 1] == topographic_map[x][y] + 1:
        peaks = get_reachable_peaks(topographic_map, x, y + 1)
        reachable_peaks += peaks

    # go west if possible
    if 0 < x and topographic_map[x - 1][y] == topographic_map[x][y] + 1:
        peaks = get_reachable_peaks(topographic_map, x - 1, y)
        reachable_peaks += peaks

    return reachable_peaks


def main():
    topographic_map = get_topographic_map()
    map_size = len(topographic_map)
    sum_of_ratings = 0
    for y in range(0, map_size):
        for x in range(0, map_size):
            if topographic_map[x][y] == 0:
                rating = get_reachable_peaks(topographic_map, x, y)
                sum_of_ratings += rating
    print("Sum of ratings: {}".format(sum_of_ratings))


if __name__ == '__main__':
    main()
