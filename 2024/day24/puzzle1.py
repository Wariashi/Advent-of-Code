filename = "input"


class Gate:
    input_x = None
    input_y = None
    operation = None
    output = None

    def __init__(self, input_x, input_y, operation, output):
        self.input_x = input_x
        self.input_y = input_y
        self.operation = operation
        self.output = output

    def __str__(self):
        return "{} {} {} -> {}".format(self.input_x, self.operation, self.input_y, self.output)

    def update(self):
        x = self.input_x.get_value()
        y = self.input_y.get_value()
        if x is not None and y is not None:
            match self.operation:
                case "AND":
                    result = x & y
                    self.output.set_value(result)
                case "OR":
                    result = x | y
                    self.output.set_value(result)
                case "XOR":
                    result = x ^ y
                    self.output.set_value(result)


class Wire:
    __output_listeners = []
    __value = None

    def __init__(self):
        self.__output_listeners = []
        self.__value = None

    def __str__(self):
        return "Wire[listeners={},value={}]".format(len(self.__output_listeners), self.__value)

    def add_output_listener(self, output_listener):
        self.__output_listeners.append(output_listener)

    def get_value(self):
        return self.__value

    def set_value(self, value):
        self.__value = value
        for listener in self.__output_listeners:
            listener.update()


def get_wires(lines):
    wires = dict()
    for line in lines:
        if line.__contains__(": "):
            parts = line.split(": ")
            name = parts[0]
            wires[name] = (Wire())
        elif line.__contains__("-> "):
            parts = line.split("-> ")
            name = parts[1]
            wires[name] = (Wire())
    return wires


def add_gates(lines, wires):
    for line in lines:
        if line.__contains__(" -> "):
            # parse input
            line = line.replace(" -> ", " ")
            parts = line.split(" ")
            input_x = wires[parts[0]]
            operation = parts[1]
            input_y = wires[parts[2]]
            output = wires[parts[3]]

            # add gate
            gate = Gate(input_x, input_y, operation, output)

            # connect wires
            input_x.add_output_listener(gate)
            input_y.add_output_listener(gate)


def update_wires(wires, updates):
    for update in updates:
        if update.__contains__(": "):
            parts = update.split(": ")
            name = parts[0]
            value = int(parts[1])
            wire = wires[name]
            wire.set_value(value)


def get_result(wires):
    result = 0
    for name in wires:
        if name[0] == "z":
            significance = int(name[1:len(name)])
            bit_factor = pow(2, significance)
            wire = wires[name]
            value = wire.get_value()
            if value == 1:
                result += bit_factor
    return result


def main():
    with open(filename) as file:
        lines = file.read().splitlines()

    wires = get_wires(lines)
    add_gates(lines, wires)
    update_wires(wires, lines)
    result = get_result(wires)
    print("Result: {}".format(result))


if __name__ == '__main__':
    main()
