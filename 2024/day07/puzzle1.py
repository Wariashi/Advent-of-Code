filename = "input"


def is_solvable(result, numbers):
    if len(numbers) == 1:
        return result == numbers[0]

    # test if addition works
    numbers_after_addition = [numbers[0] + numbers[1]]
    for index in range(2, len(numbers)):
        numbers_after_addition.append(numbers[index])
    if is_solvable(result, numbers_after_addition):
        return True

    # test if multiplication works
    numbers_after_multiplication = [numbers[0] * numbers[1]]
    for index in range(2, len(numbers)):
        numbers_after_multiplication.append(numbers[index])
    if is_solvable(result, numbers_after_multiplication):
        return True

    return False


# read input file
with open(filename) as file:
    lines = file.read().splitlines()

# get equations
equations = []
for line in lines:
    parts = line.replace(":", "").split()
    values = []
    for part in parts:
        values.append(int(part))
    equations.append(values)
print("Found {} equations.".format(len(equations)))

# test equations
total_calibration_result = 0
for equation in equations:
    result = equation[0]
    numbers = equation[1: len(equation)]
    if is_solvable(result, numbers):
        total_calibration_result += equation[0]
print("Calibration result: {}".format(total_calibration_result))
