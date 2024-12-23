filename = "input"


def get_connections():
    connections = []
    with open(filename) as file:
        lines = file.read().splitlines()
    for line in lines:
        connections.append([line[0:2], line[3:5]])
    return connections


def create_computer_list(connections):
    computers = dict()
    for connection in connections:
        computer1 = connection[0]
        computer2 = connection[1]
        computers.setdefault(computer1, [])
        computers.setdefault(computer2, [])
        computers[computer1].append(computer2)
        computers[computer2].append(computer1)
    return computers


def get_loops_for_route(computers, route, ttl):
    if ttl == 0:
        is_loop = route[0] == route[-1]
        return [route] if is_loop else []

    last_computer_in_route = route[-1]
    reachable_computers = computers[last_computer_in_route]
    loops = []
    for reachable_computer in reachable_computers:
        if ttl == 1 or reachable_computer not in route:
            route_copy = route.copy()
            route_copy.append(reachable_computer)
            for loop in get_loops_for_route(computers, route_copy, ttl - 1):
                loops.append(loop)
    return loops


def get_loops(computers):
    loops = []
    for computer in computers.keys():
        loops_for_computer = get_loops_for_route(computers, [computer], 3)
        for loop in loops_for_computer:
            loops.append(loop)
    return loops


def get_distinct_loops(loops):
    distinct_loops = set()

    for loop in loops:
        copy = loop.copy()
        copy.remove(loop[0])
        copy.sort()
        loop_as_string = ",".join(copy)
        distinct_loops.add(loop_as_string)

    return distinct_loops


def main():
    connections = get_connections()
    computers = create_computer_list(connections)
    all_loops = get_loops(computers)
    distinct_loops = get_distinct_loops(all_loops)

    count = 0
    for loop in distinct_loops:
        if loop[0] == "t" or loop[3] == "t" or loop[6] == "t":
            count += 1
    print("{} loops contain a computer with a name that starts with t.".format(count))


if __name__ == '__main__':
    main()
