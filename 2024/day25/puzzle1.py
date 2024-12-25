filename = "input"


def get_schematics():
    schematics = []

    with open(filename) as file:
        lines = file.read().splitlines()
    number_of_schematics = (len(lines) + 1) // 8

    for i in range(0, number_of_schematics):
        start = i * 8
        end = (i + 1) * 8 - 1
        schematics.append(lines[start:end])
    return schematics


def get_keys(schematics):
    keys = []

    for schematic in schematics:
        if schematic[0] != ".....":
            continue
        key = [0, 0, 0, 0, 0]
        for height in range(1, 6):
            for pin in range(0, 5):
                if schematic[height][pin] == "#":
                    key[pin] += 1
        keys.append(key)

    return keys


def get_locks(schematics):
    locks = []

    for schematic in schematics:
        if schematic[0] != "#####":
            continue
        lock = [0, 0, 0, 0, 0]
        for height in range(1, 6):
            for pin in range(0, 5):
                if schematic[height][pin] == "#":
                    lock[pin] += 1
        locks.append(lock)

    return locks


def overlaps(key, lock):
    for pin in range(0, 5):
        if 5 < key[pin] + lock[pin]:
            return True
    return False


def get_non_overlapping_combinations(keys, locks):
    non_overlapping_combinations = 0
    for key in keys:
        for lock in locks:
            if not overlaps(key, lock):
                non_overlapping_combinations += 1
    return non_overlapping_combinations


def main():
    schematics = get_schematics()
    keys = get_keys(schematics)
    locks = get_locks(schematics)
    print("Found {} keys and {} locks.".format(len(keys), len(locks)))
    non_overlapping_combinations = get_non_overlapping_combinations(keys, locks)
    print("Found {} combinations that don't overlap.".format(non_overlapping_combinations))


if __name__ == '__main__':
    main()
