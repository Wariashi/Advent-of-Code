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

    def step(self, number_of_steps):
        distance_x = number_of_steps * self.velocity_x
        self.position_x = (self.position_x + distance_x) % width

        distance_y = number_of_steps * self.velocity_y
        self.position_y = (self.position_y + distance_y) % height

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


def main():
    robots = get_robots()
    for robot in robots:
        robot.step(100)
    safety_factor = calculate_safety_factor(robots)
    print("Safety factor: {}".format(safety_factor))


if __name__ == '__main__':
    main()
