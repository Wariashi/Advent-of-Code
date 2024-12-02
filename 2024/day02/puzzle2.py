filename = "input"


def is_safe_if_dampened(levels):
    for i in range(len(levels)):
        copy = levels.copy()
        copy.pop(i)
        if is_safe(copy):
            return True
    return False


def is_safe(levels):
    increasing = int(levels[0]) < int(levels[1])
    for i in range(1, len(levels)):
        previous_level = levels[i - 1]
        this_level = levels[i]
        difference = int(this_level) - int(previous_level)

        # if one level is increasing, all have to be
        if increasing and difference < 0:
            return False

        # if one level is decreasing, all have to be
        if not increasing and 0 < difference:
            return False

        # the difference must be between 1 and 3
        if abs(difference) < 1 or 3 < abs(difference):
            return False

    return True


# read input file
with open(filename) as file:
    reports = file.readlines()

# count safe reports
safeReports = 0
for report in reports:
    if is_safe(report.split()) or is_safe_if_dampened(report.split()):
        print("Safe")
        safeReports += 1
    else:
        print("Unsafe")

print("Number of safe reports: {}".format(safeReports))
