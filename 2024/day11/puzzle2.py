filename = "input"


def get_initial_configuration():
    with open(filename) as file:
        lines = file.read().splitlines()

    stones = dict()
    for stone in lines[0].split():
        stones.setdefault(stone, 0)
        stones[stone] += 1

    return stones


def blink(stones):
    new_stones = dict()
    for stone in stones:
        count = stones[stone]
        if stone == "0":
            new_stones.setdefault("1", 0)
            new_stones["1"] += count
        elif len(stone) % 2 == 0:
            center = int(len(stone) / 2)
            end = len(stone)

            left = stone[0:center]
            new_stones.setdefault(left, 0)
            new_stones[left] += count

            right = str(int(stone[center:end]))
            new_stones.setdefault(right, 0)
            new_stones[right] += count
        else:
            result = str(int(stone) * 2024)
            new_stones.setdefault(result, 0)
            new_stones[result] += count
    return new_stones


def count_stones(stones):
    number_of_stones = 0
    for stone in stones:
        number_of_stones += stones[stone]
    return number_of_stones


def main():
    stones = get_initial_configuration()
    print("Initial arrangement: {} stones".format(count_stones(stones)))
    for i in range(1, 76):
        stones = blink(stones)
        print("After {} blink{}: {} stones".format(i, "" if i == 1 else "s", count_stones(stones)))


if __name__ == '__main__':
    main()
