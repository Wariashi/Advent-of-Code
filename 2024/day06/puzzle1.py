import lab

filename = "input"

GUARD = 'G'
OBSTACLE = '#'
VISITED = 'X'

# create the initial situation
area = lab.Area(filename)
guard = lab.Guard(area)
print("Initial situation:")
print(area)
print(guard)


def move_down():
    if area.tiles[guard.position_y + 1][guard.position_x] is OBSTACLE:
        guard.direction = lab.Direction.LEFT
    else:
        area.tiles[guard.position_y][guard.position_x] = VISITED
        guard.position_y += 1
        area.tiles[guard.position_y][guard.position_x] = GUARD


def move_left():
    if area.tiles[guard.position_y][guard.position_x - 1] is OBSTACLE:
        guard.direction = lab.Direction.UP
    else:
        area.tiles[guard.position_y][guard.position_x] = VISITED
        guard.position_x -= 1
        area.tiles[guard.position_y][guard.position_x] = GUARD


def move_right():
    if area.tiles[guard.position_y][guard.position_x + 1] is OBSTACLE:
        guard.direction = lab.Direction.DOWN
    else:
        area.tiles[guard.position_y][guard.position_x] = VISITED
        guard.position_x += 1
        area.tiles[guard.position_y][guard.position_x] = GUARD


def move_up():
    if area.tiles[guard.position_y - 1][guard.position_x] is OBSTACLE:
        guard.direction = lab.Direction.RIGHT
    else:
        area.tiles[guard.position_y][guard.position_x] = VISITED
        guard.position_y -= 1
        area.tiles[guard.position_y][guard.position_x] = GUARD


def move():
    if guard.direction == lab.Direction.DOWN:
        move_down()
    if guard.direction == lab.Direction.LEFT:
        move_left()
    if guard.direction == lab.Direction.RIGHT:
        move_right()
    if guard.direction == lab.Direction.UP:
        move_up()


# move the guard until she leaves the area
while not guard.will_leave(area):
    move()

print()
print("Final situation:")
print(area)
print(guard)

# count the visited positions
visited_positions = 0
for row in area.tiles:
    for tile in row:
        if tile == VISITED or tile == GUARD:
            visited_positions += 1
print()
print("Distinct visited positions: {}".format(visited_positions))
