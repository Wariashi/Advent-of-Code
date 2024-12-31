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


def get_bigger_meshes(computers, meshes):
    bigger_meshes = []

    for mesh in meshes:
        # select a random computer inside the mesh
        first_name = mesh[0]
        connected_computers = computers[first_name]

        # create a new mesh for every computer that is connected to all other computers inside the mesh
        for connected_computer in connected_computers:
            if connected_computer not in mesh:
                is_completely_connected = True
                for member in mesh:
                    if member not in computers[connected_computer]:
                        is_completely_connected = False
                        break
                if is_completely_connected:
                    new_mesh = []
                    for node in mesh:
                        new_mesh.append(node)
                    new_mesh.append(connected_computer)
                    bigger_meshes.append(new_mesh)

    return bigger_meshes


def get_distinct_meshes(meshes):
    distinct_meshes = dict()

    for mesh in meshes:
        copy = mesh.copy()
        copy.sort()
        mesh_id = ",".join(copy)
        distinct_meshes[mesh_id] = copy

    return list(distinct_meshes.values())


def main():
    connections = get_connections()
    computers = create_computer_list(connections)

    meshes = []
    for computer in computers:
        meshes.append([computer])
    mesh_size = 1

    while 1 < len(meshes):
        print("Found {} meshes with size {}.".format(len(meshes), mesh_size))
        meshes = get_bigger_meshes(computers, meshes)
        meshes = get_distinct_meshes(meshes)
        mesh_size += 1
    print("Found {} mesh with size {}.".format(len(meshes), mesh_size))

    mesh = meshes[0]
    password = ",".join(mesh)
    print("The password is: {}".format(password))


if __name__ == '__main__':
    main()
