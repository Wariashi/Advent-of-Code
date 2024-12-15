filename = "input"


class Warehouse:
    robot_x = 0
    robot_y = 0
    tiles = [[]]

    def __init__(self, tiles):
        self.tiles = tiles
        width = len(tiles)
        height = len(tiles[0])
        for y in range(0, height):
            for x in range(0, width):
                if tiles[x][y] == "@":
                    self.robot_x = x
                    self.robot_y = y
                    return

    def get_sum_of_gps_coordinates(self):
        tiles = self.tiles
        width = len(tiles)
        height = len(tiles[0])

        sum_of_gps_coordinates = 0
        for y in range(0, height):
            for x in range(0, width):
                if tiles[x][y] == "O":
                    gps_coordinate = x + 100 * y
                    sum_of_gps_coordinates += gps_coordinate
        return sum_of_gps_coordinates

    def move_robot(self, movements):
        for movement in movements:
            if movement == ">":
                if self.__move_east(self.robot_x, self.robot_y):
                    self.robot_x += 1
            elif movement == "^":
                if self.__move_north(self.robot_x, self.robot_y):
                    self.robot_y -= 1
            elif movement == "v":
                if self.__move_south(self.robot_x, self.robot_y):
                    self.robot_y += 1
            elif movement == "<":
                if self.__move_west(self.robot_x, self.robot_y):
                    self.robot_x -= 1

    def print_tiles(self):
        width = len(self.tiles)
        height = len(self.tiles[0])
        for y in range(0, height):
            for x in range(0, width):
                print(self.tiles[x][y], end="")
            print()

    def __move_east(self, x, y):
        if self.tiles[x][y] == ".":
            return True
        if self.tiles[x + 1][y] == "#":
            return False
        elif self.__move_east(x + 1, y):
            self.tiles[x + 1][y] = self.tiles[x][y]
            self.tiles[x][y] = "."
            return True
        else:
            return False

    def __move_north(self, x, y):
        if self.tiles[x][y] == ".":
            return True
        if self.tiles[x][y - 1] == "#":
            return False
        elif self.__move_north(x, y - 1):
            self.tiles[x][y - 1] = self.tiles[x][y]
            self.tiles[x][y] = "."
            return True
        else:
            return False

    def __move_south(self, x, y):
        if self.tiles[x][y] == ".":
            return True
        if self.tiles[x][y + 1] == "#":
            return False
        elif self.__move_south(x, y + 1):
            self.tiles[x][y + 1] = self.tiles[x][y]
            self.tiles[x][y] = "."
            return True
        else:
            return False

    def __move_west(self, x, y):
        if self.tiles[x][y] == ".":
            return True
        if self.tiles[x - 1][y] == "#":
            return False
        elif self.__move_west(x - 1, y):
            self.tiles[x - 1][y] = self.tiles[x][y]
            self.tiles[x][y] = "."
            return True
        else:
            return False


def get_warehouse(lines):
    width = len(lines[0])
    height = len(lines)
    tiles = []
    for x in range(0, width):
        tiles.append([])
        for y in range(0, height):
            tiles[x].append(lines[y][x])
    return Warehouse(tiles)


def get_movements(lines):
    movements = ""
    for line in lines:
        movements += line
    return movements


def main():
    with open(filename) as file:
        lines = file.read().splitlines()

    separator_line = 0
    for i in range(0, len(lines)):
        if len(lines[i]) == 0:
            separator_line = i

    warehouse = get_warehouse(lines[0:separator_line])
    movements = get_movements(lines[separator_line:len(lines)])

    warehouse.move_robot(movements)
    print("Final state:")
    warehouse.print_tiles()
    sum_of_gps_coordinates = warehouse.get_sum_of_gps_coordinates()
    print("Sum of GPS coordinates: {}".format(sum_of_gps_coordinates))


if __name__ == '__main__':
    main()
