from enum import Enum


class Area:
    tiles = []

    def __init__(self, filename):
        with open(filename) as file:
            lines = file.readlines()
        for line in lines:
            row = []
            for character in line:
                if not character.isspace():
                    row.append(character)
            self.tiles.append(row)

    def __str__(self):
        value = ""
        for row in self.tiles:
            for character in row:
                value += character
            value += "\n"
        return value[0:len(value) - 1]


class Direction(Enum):
    DOWN = "v"
    LEFT = '<'
    RIGHT = '>'
    UP = '^'

    def __str__(self):
        return self.name


class Guard:
    direction = Direction.UP
    position_x = 0
    position_y = 0

    def __init__(self, area):
        area_size = len(area.tiles)
        for x in range(0, area_size):
            for y in range(0, area_size):
                if area.tiles[y][x] == '^':
                    self.position_x = x
                    self.position_y = y

    def __str__(self):
        return "Guard[direction={},position={}:{}]".format(self.direction, self.position_x, self.position_y)

    def will_leave(self, area):
        if self.position_x == 0 or self.position_y == 0:
            return True
        area_size = len(area.tiles)
        if self.position_x == area_size - 1 or self.position_y == area_size - 1:
            return True
        return False
