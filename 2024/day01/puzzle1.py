filename = "input"

# read input file
with open(filename) as file:
    content = file.readlines()
locationIdsLeft = []
locationIdsRight = []
for line in content:
    locationIds = line.split()
    locationIdsLeft.append(int(locationIds[0]))
    locationIdsRight.append(int(locationIds[1]))

# sort location ID lists
locationIdsLeft.sort()
locationIdsRight.sort()

# get distances
totalDistance = 0
for i in range(len(content)):
    distance = locationIdsRight[i] - locationIdsLeft[i]
    absoluteDistance = abs(distance)
    totalDistance += absoluteDistance

print("Total distance: {}".format(totalDistance))
