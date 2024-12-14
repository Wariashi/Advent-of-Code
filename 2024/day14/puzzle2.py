filename = "input"

width = 11 if filename == "example" else 101
height = 7 if filename == "example" else 103
center_x = width // 2
center_y = height // 2


class Robot:
    position_x = 0
    position_y = 0
    velocity_x = 0
    velocity_y = 0

    def step(self):
        self.position_x = (self.position_x + self.velocity_x) % width
        self.position_y = (self.position_y + self.velocity_y) % height

    def __init__(self, position_x, position_y, velocity_x, velocity_y):
        self.position_x = position_x
        self.position_y = position_y
        self.velocity_x = velocity_x
        self.velocity_y = velocity_y

    def __str__(self):
        return "Robot[position={}:{},velocity={}:{}]".format(
            self.position_x,
            self.position_y,
            self.velocity_x,
            self.velocity_y
        )


def get_robots():
    with open(filename) as file:
        lines = file.read().splitlines()
    robots = []
    for line in lines:
        values = line[2:len(line)].split(" v=")
        position = values[0].split(",")
        velocity = values[1].split(",")
        robots.append(Robot(int(position[0]), int(position[1]), int(velocity[0]), int(velocity[1])))
    return robots


def calculate_safety_factor(robots):
    robots_in_ne = 0
    robots_in_nw = 0
    robots_in_se = 0
    robots_in_sw = 0
    for robot in robots:
        x = robot.position_x
        y = robot.position_y
        if x < center_x:
            if y < center_y:
                robots_in_nw += 1
            elif center_y < y:
                robots_in_sw += 1
        elif center_x < x:
            if y < center_y:
                robots_in_ne += 1
            elif center_y < y:
                robots_in_se += 1
    return robots_in_ne * robots_in_nw * robots_in_se * robots_in_sw


def create_lobby_map(robots):
    lobby_map = [[False for _ in range(height)] for _ in range(width)]
    for robot in robots:
        lobby_map[robot.position_x][robot.position_y] = True
    return lobby_map


def might_display_christmas_tree(lobby_map, confidence):
    # if the robots display anything, most of them probably have a neighbor
    robots_with_neighbor = 0
    robots_without_neighbor = 0
    # ignore edge cases, should work either way
    for y in range(1, height - 1):
        for x in range(1, width - 1):
            if not lobby_map[x][y]:
                # no robot here
                continue
            if lobby_map[x + 1][y] or lobby_map[x][y - 1] or lobby_map[x][y + 1] or lobby_map[x - 1][y]:
                robots_with_neighbor += 1
            else:
                robots_without_neighbor += 1
    return confidence <= robots_with_neighbor / robots_without_neighbor


def print_lobby_map(lobby_map):
    for y in range(0, height):
        for x in range(0, width):
            if lobby_map[x][y]:
                print("O", end="")
            else:
                print(".", end="")
        print()


def main():
    robots = get_robots()
    lobby_map = create_lobby_map(robots)
    steps = 0
    while not might_display_christmas_tree(lobby_map, 0.75):
        for robot in robots:
            robot.step()
        steps += 1
        lobby_map = create_lobby_map(robots)
        if steps % 1000 == 0:
            print("Calculated {} steps.".format(steps))
    print_lobby_map(lobby_map)
    print("It took {} steps to create this.".format(steps))


if __name__ == '__main__':
    main()
