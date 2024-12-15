filename = "input"


class Fence:
    line = 0  # can be the row or column depending on the direction
    start = 0
    end = 0

    def __init__(self, line, start, end):
        self.line = line
        self.start = start
        self.end = end

    def __lt__(self, other):
        return self.line < other.line or self.line == other.line and self.start < other.start


class Region:
    plant_type = '.'
    plot_map = []
    plots = []

    def __init__(self, plot_map, x, y):
        self.plot_map = plot_map
        self.plots = []
        self.plant_type = plot_map[x][y].plant_type
        self.__add_plot_and_neighbors(plot_map, x, y)

    def area(self):
        return len(self.plots)

    def fence_sections(self):
        map_size = len(self.plot_map)

        # collect fences
        eastern_fences = []
        northern_fences = []
        southern_fences = []
        western_fences = []
        for plot in self.plots:
            x = plot.x
            y = plot.y
            # east
            if x == map_size - 1 or self.plot_map[x + 1][y].plant_type is not self.plant_type:
                eastern_fences.append(Fence(x, y, y))
            # north
            if y == 0 or self.plot_map[x][y - 1].plant_type is not self.plant_type:
                northern_fences.append(Fence(y, x, x))
            # south
            if y == map_size - 1 or self.plot_map[x][y + 1].plant_type is not self.plant_type:
                southern_fences.append(Fence(y, x, x))
            # west
            if x == 0 or self.plot_map[x - 1][y].plant_type is not self.plant_type:
                western_fences.append(Fence(x, y, y))

        # combine eastern fences into sections
        self.__combine_fences(eastern_fences)
        self.__combine_fences(northern_fences)
        self.__combine_fences(southern_fences)
        self.__combine_fences(western_fences)

        return len(eastern_fences) + len(northern_fences) + len(southern_fences) + len(western_fences)

    def __add_plot_and_neighbors(self, plot_map, x, y):
        size = len(plot_map)

        # append plot
        plot = plot_map[x][y]
        self.plots.append(plot)
        plot.region = self

        # check eastern plot
        if x < size - 1 and plot_map[x + 1][y].region is None and plot_map[x + 1][y].plant_type is self.plant_type:
            self.__add_plot_and_neighbors(plot_map, x + 1, y)

        # check northern plot
        if 0 < y and plot_map[x][y - 1].region is None and plot_map[x][y - 1].plant_type is self.plant_type:
            self.__add_plot_and_neighbors(plot_map, x, y - 1)

        # check southern plot
        if y < size - 1 and plot_map[x][y + 1].region is None and plot_map[x][y + 1].plant_type is self.plant_type:
            self.__add_plot_and_neighbors(plot_map, x, y + 1)

        # check western plot
        if 0 < x and plot_map[x - 1][y].region is None and plot_map[x - 1][y].plant_type is self.plant_type:
            self.__add_plot_and_neighbors(plot_map, x - 1, y)

    def __combine_fences(self, fences):
        fences.sort()
        i = 1
        while i < len(fences):
            previous = fences[i - 1]
            current = fences[i]
            if previous.line == current.line and previous.end == current.start - 1:
                previous.end = current.end
                fences.remove(current)
            else:
                i += 1
        return len(fences)


class Plot:
    plant_type = '.'
    region = None
    x = 0
    y = 0

    def __eq__(self, other):
        if not isinstance(other, type(self)):
            return NotImplemented
        return self.x == other.x and self.y == other.y

    def __hash__(self):
        return hash((self.x, self.y))

    def __init__(self, x, y, plant_type):
        self.x = x
        self.y = y
        self.plant_type = plant_type

    def __str__(self):
        return self.plant_type


def get_plot_map():
    with open(filename) as file:
        lines = file.read().splitlines()
    size = len(lines)
    plot_map = []
    for _ in range(0, size):
        plot_map.append([])
    for y in range(0, size):
        line = lines[y]
        for x in range(0, size):
            character = line[x]
            plot_map[x].append(Plot(x, y, character))
    return plot_map


def get_regions(plot_map):
    size = len(plot_map)
    regions = []
    for y in range(0, size):
        for x in range(0, size):
            plot = plot_map[x][y]
            if plot.region is None:
                region = Region(plot_map, x, y)
                regions.append(region)
    return regions


def main():
    plot_map = get_plot_map()
    regions = get_regions(plot_map)
    print("Found {} regions:".format(len(regions)))
    total_prize = 0
    for region in regions:
        area = region.area()
        fence_sections = region.fence_sections()
        plant_type = region.plant_type
        price = area * fence_sections
        print("A region of {} plants with price {} * {} = {}.".format(plant_type, area, fence_sections, price))
        total_prize += price
    print("The total price is {}.".format(total_prize))


if __name__ == '__main__':
    main()
