filename = "input"


class Computer:
    register_a = 0
    register_b = 0
    register_c = 0

    program = []

    instruction_pointer = 0

    output = []

    def run_program(self):
        self.instruction_pointer = 0
        self.output = []
        while self.instruction_pointer + 1 < len(self.program):
            opcode = self.program[self.instruction_pointer]
            operand = self.program[self.instruction_pointer + 1]
            self.__execute(opcode, operand)
            self.instruction_pointer += 2

    def __execute(self, opcode, operand):
        match opcode:
            case 0:
                self.__adv(operand)
            case 1:
                self.__bxl(operand)
            case 2:
                self.__bst(operand)
            case 3:
                self.__jnz(operand)
            case 4:
                self.__bxc()
            case 5:
                self.__out(operand)
            case 6:
                self.__bdv(operand)
            case 7:
                self.__cdv(operand)

    def __get_combo_operand(self, operand):
        match operand:
            case 0:
                return 0
            case 1:
                return 1
            case 2:
                return 2
            case 3:
                return 3
            case 4:
                return self.register_a
            case 5:
                return self.register_b
            case 6:
                return self.register_c

    def __adv(self, operand):
        combo_operand = self.__get_combo_operand(operand)
        numerator = self.register_a
        denominator = pow(2, combo_operand)
        self.register_a = numerator // denominator

    def __bdv(self, operand):
        combo_operand = self.__get_combo_operand(operand)
        numerator = self.register_a
        denominator = pow(2, combo_operand)
        self.register_b = numerator // denominator

    def __bst(self, operand):
        combo_operand = self.__get_combo_operand(operand)
        value = combo_operand % 8
        self.register_b = value

    def __bxc(self):
        value = self.register_b ^ self.register_c
        self.register_b = value

    def __bxl(self, operand):
        value = self.register_b ^ operand
        self.register_b = value

    def __cdv(self, operand):
        combo_operand = self.__get_combo_operand(operand)
        numerator = self.register_a
        denominator = pow(2, combo_operand)
        self.register_c = numerator // denominator

    def __jnz(self, operand):
        if self.register_a != 0:
            self.instruction_pointer = operand - 2

    def __out(self, operand):
        combo_operand = self.__get_combo_operand(operand)
        value = combo_operand % 8
        self.output.append(value)


def get_computer():
    computer = Computer()

    with open(filename) as file:
        lines = file.read().splitlines()

    computer.register_a = int(lines[0][12:len(lines[0])])
    computer.register_b = int(lines[1][12:len(lines[1])])
    computer.register_c = int(lines[2][12:len(lines[2])])
    computer.program = []
    for code in lines[4][9:len(lines[4])].split(","):
        computer.program.append(int(code))

    return computer


def main():
    computer = get_computer()
    computer.run_program()
    output = ",".join(str(value) for value in computer.output)
    print("Output: {}".format(output))


if __name__ == '__main__':
    main()
