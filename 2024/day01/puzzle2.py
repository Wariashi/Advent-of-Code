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

# count occurrences in right list
occurrences = dict()
for i in range(len(locationIdsRight)):
    locationId = locationIdsRight[i]
    if locationId not in occurrences:
        occurrences[locationId] = 0
    occurrences[locationId] += 1

# calculate scores
totalScore = 0
for i in range(len(locationIdsLeft)):
    locationId = locationIdsLeft[i]
    if locationId not in occurrences:
        occurrences[locationId] = 0
    count = occurrences[locationId]
    similarityScore = count * locationId
    totalScore += similarityScore

print("similarity score: {}".format(totalScore))
