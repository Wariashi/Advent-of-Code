import re

filename = "input"

# read input file
with open(filename) as file:
    lines = file.readlines()

# get rules and updates
rules = []
updates = []
for line in lines:
    if "|" in line:
        rules.append(line)
    if "," in line:
        updates.append(line)
print("Found {} rules and {} updates.".format(len(rules), len(updates)))


def is_in_right_order(update):
    for rule in rules:
        pages = rule.replace("|", " ").split()
        regex = pages[1] + ".*" + pages[0]
        result = re.search(regex, update)
        if result:
            return False
    return True


count = 0
sum_of_middle_pages = 0
for update in updates:
    if is_in_right_order(update):
        count += 1
        page_numbers = update.split(",")
        page_count = len(page_numbers)
        middle_page_number = int(page_numbers[page_count // 2])
        sum_of_middle_pages += middle_page_number

print("{} updates are in right order.".format(count))
print("The sum of the middle pages is {}.".format(sum_of_middle_pages))
