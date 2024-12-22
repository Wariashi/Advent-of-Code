filename = "input"


def get_numbers():
    numbers = []
    with open(filename) as file:
        lines = file.read().splitlines()
    for line in lines:
        numbers.append(int(line))
    return numbers


def get_next(number):
    number = ((number * 64) ^ number) % 16777216
    number = ((number // 32) ^ number) % 16777216
    number = ((number * 2048) ^ number) % 16777216

    return number


def main():
    numbers = get_numbers()
    results = []
    for number in numbers:
        result = number
        for _ in range(0, 2000):
            result = get_next(result)
        results.append(result)

    sum_of_secret_numbers = 0
    for result in results:
        sum_of_secret_numbers += result

    print("Sum of the 2000th secret numbers: {}".format(sum_of_secret_numbers))


if __name__ == '__main__':
    main()
