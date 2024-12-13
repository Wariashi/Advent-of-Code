filename = "input"


class ClawMachine:
    a_x = 0
    a_y = 0
    b_x = 0
    b_y = 0
    price_x = 0
    price_y = 0

    def __str__(self):
        return "[A:{}/{}, B:{}/{}, Prize:{}/{}]".format(
            self.a_x,
            self.a_y,
            self.b_x,
            self.b_y,
            self.price_x,
            self.price_y
        )


def get_claw_machines():
    with open(filename) as file:
        lines = file.read().splitlines()
    number_of_machines = len(lines) // 4 + 1
    claw_machines = []
    for i in range(0, number_of_machines):
        claw_machine = ClawMachine()

        # get button A configuration
        a_line = lines[4 * i]
        a_coordinates = a_line[11:len(a_line)].split(", Y+")
        claw_machine.a_x = int(a_coordinates[0])
        claw_machine.a_y = int(a_coordinates[1])

        # get button B configuration
        b_line = lines[4 * i + 1]
        b_coordinates = b_line[11:len(b_line)].split(", Y+")
        claw_machine.b_x = int(b_coordinates[0])
        claw_machine.b_y = int(b_coordinates[1])

        # get the prize location
        prize_line = lines[4 * i + 2]
        prize_coordinates = prize_line[9:len(prize_line)].split(", Y=")
        claw_machine.price_x = 10000000000000 + int(prize_coordinates[0])
        claw_machine.price_y = 10000000000000 + int(prize_coordinates[1])

        claw_machines.append(claw_machine)

    return claw_machines


def get_required_tokens(claw_machine):
    # Let 'a' be the number of times we pressed button A and 'b' be the number of times we pressed button B.
    # Then this should be true: a * a_x + b * b_x = prize_x
    a_x = claw_machine.a_x
    b_x = claw_machine.b_x
    price_x = claw_machine.price_x

    # Also this should be true: a * a_y + b * b_y = prize_y
    a_y = claw_machine.a_y
    b_y = claw_machine.b_y
    price_y = claw_machine.price_y

    # We can rewrite the second equation in a way that a_y = -a_x.
    rewrite_factor = -a_x / a_y
    a_y *= rewrite_factor
    b_y *= rewrite_factor
    price_y *= rewrite_factor

    # Now we can add the equations to eliminate the 'a' part and get b * b_combined = price_combined.
    b_combined = b_x + b_y
    price_combined = price_x + price_y

    # Solve for b (and round to the nearest possible number of button presses).
    b = round(price_combined / b_combined)

    # Calculate 'a' using the first equation
    a = round((price_x - (b * b_x)) / a_x)

    # Double check
    if (a * claw_machine.a_x + b * claw_machine.b_x == claw_machine.price_x
            and a * claw_machine.a_y + b * claw_machine.b_y == claw_machine.price_y):
        return 3 * a + b
    else:
        return None


def main():
    claw_machines = get_claw_machines()
    total_required_tokens = 0
    get_required_tokens(claw_machines[0])
    for claw_machine in claw_machines:
        required_tokens = get_required_tokens(claw_machine)
        if required_tokens is not None:
            total_required_tokens += required_tokens
    print("Minimum tokens required: {}".format(total_required_tokens))


if __name__ == '__main__':
    main()
