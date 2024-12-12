filename = "input"


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

    def perimeter(self):
        map_size = len(self.plot_map)
        perimeter = 0
        for plot in self.plots:
            x = plot.x
            y = plot.y

            # check eastern plot
            if plot.x == map_size - 1 or self.plot_map[x + 1][y].plant_type is not self.plant_type:
                perimeter += 1

            # check northern plot
            if plot.y == 0 or self.plot_map[x][y - 1].plant_type is not self.plant_type:
                perimeter += 1

            # check southern plot
            if plot.y == map_size - 1 or self.plot_map[x][y + 1].plant_type is not self.plant_type:
                perimeter += 1

            # check western plot
            if plot.x == 0 or self.plot_map[x - 1][y].plant_type is not self.plant_type:
                perimeter += 1

        return perimeter

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
        perimeter = region.perimeter()
        plant_type = region.plant_type
        price = area * perimeter
        print("A region of {} plants with price {} * {} = {}.".format(plant_type, area, perimeter, price))
        total_prize += price
    print("The total price is {}.".format(total_prize))


if __name__ == '__main__':
    main()
