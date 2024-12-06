import lab

filename = "input"

OBSTACLE = '#'

default_area = lab.Area(filename)


def move_down(guard, area):
    if area.tiles[guard.position_y + 1][guard.position_x] is OBSTACLE:
        guard.direction = lab.Direction.LEFT
    else:
        guard.position_y += 1
        area.tiles[guard.position_y][guard.position_x] = guard.direction.value


def move_left(guard, area):
    if area.tiles[guard.position_y][guard.position_x - 1] is OBSTACLE:
        guard.direction = lab.Direction.UP
    else:
        guard.position_x -= 1
        area.tiles[guard.position_y][guard.position_x] = guard.direction.value


def move_right(guard, area):
    if area.tiles[guard.position_y][guard.position_x + 1] is OBSTACLE:
        guard.direction = lab.Direction.DOWN
    else:
        guard.position_x += 1
        area.tiles[guard.position_y][guard.position_x] = guard.direction.value


def move_up(guard, area):
    if area.tiles[guard.position_y - 1][guard.position_x] is OBSTACLE:
        guard.direction = lab.Direction.RIGHT
    else:
        guard.position_y -= 1
        area.tiles[guard.position_y][guard.position_x] = guard.direction.value


def move(guard, area):
    if guard.direction == lab.Direction.DOWN:
        move_down(guard, area)
    if guard.direction == lab.Direction.LEFT:
        move_left(guard, area)
    if guard.direction == lab.Direction.RIGHT:
        move_right(guard, area)
    if guard.direction == lab.Direction.UP:
        move_up(guard, area)


def loops(area):
    guard = lab.Guard(area)
    while not guard.will_leave(area):
        if guard.direction == lab.Direction.DOWN and area.tiles[guard.position_y + 1][
            guard.position_x] == lab.Direction.DOWN.value:
            return True
        elif guard.direction == lab.Direction.LEFT and area.tiles[guard.position_y][
            guard.position_x - 1] == lab.Direction.LEFT.value:
            return True
        elif guard.direction == lab.Direction.RIGHT and area.tiles[guard.position_y][
            guard.position_x + 1] == lab.Direction.RIGHT.value:
            return True
        elif guard.direction == lab.Direction.UP and area.tiles[guard.position_y - 1][
            guard.position_x] == lab.Direction.UP.value:
            return True
        else:
            move(guard, area)
    return False


looping_configurations = 0
area_size = len(default_area.tiles)
areas_to_test = area_size * area_size
for y in range(0, area_size):
    progress = int(((y * area_size) / areas_to_test) * 100)
    print("Progress: {} of {} ({}%), {} looping configurations found so far".format(y * area_size, areas_to_test,
                                                                                    progress, looping_configurations))
    for x in range(0, area_size):
        altered_area = lab.Area(filename)
        altered_area.tiles[y][x] = OBSTACLE
        if loops(default_area):
            looping_configurations += 1

print()
print("Distinct looping configurations: {}".format(looping_configurations))
