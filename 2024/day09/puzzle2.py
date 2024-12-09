filename = "input"


def get_initial_disk_map():
    with open(filename) as file:
        lines = file.read().splitlines()
    disk_map = []
    current_id_number = 0
    for character in lines[0]:
        size = int(character)
        for _ in range(0, size):
            if current_id_number % 2 == 0:
                disk_map.append(current_id_number // 2)
            else:
                disk_map.append(None)
        current_id_number += 1
    return disk_map


def move(disk_map, source_index, target_index, size):
    for offset in range(0, size):
        disk_map[target_index + offset] = disk_map[source_index + offset]
        disk_map[source_index + offset] = None


def has_space(disk_map, index, size):
    for offset in range(0, size):
        if disk_map[index + offset] is not None:
            return False
    return True


def compact_files(disk_map):
    index_right = len(disk_map) - 1

    while 0 < index_right:
        # find the next block to move
        while disk_map[index_right] is None and 0 < index_right:
            index_right -= 1
        block_size = 1
        while disk_map[index_right - 1] == disk_map[index_right]:
            block_size += 1
            index_right -= 1

        # find a free space to move to
        index_left = 0
        while not has_space(disk_map, index_left, block_size) and index_left < index_right:
            index_left += 1
        if index_left < index_right:
            move(disk_map, index_right, index_left, block_size)

        # move the right cursor left
        index_right -= 1

        # show progress
        progress = int(100 * (1 - (index_right / len(disk_map))))
        print("Compacting... ({}%)".format(progress))


def calculate_checksum(disk_map):
    checksum = 0
    for block_position in range(0, len(disk_map)):
        if disk_map[block_position] is not None:
            file_id_number = disk_map[block_position]
            checksum += block_position * file_id_number
    return checksum


def main():
    disk_map = get_initial_disk_map()
    print("Initial disk map: {}".format(disk_map))
    compact_files(disk_map)
    print("Compacted disk map: {}".format(disk_map))
    checksum = calculate_checksum(disk_map)
    print("Checksum: {}".format(checksum))


if __name__ == '__main__':
    main()
