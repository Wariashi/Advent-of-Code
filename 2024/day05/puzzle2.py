filename = "input"

# read input file
with open(filename) as file:
    lines = file.readlines()

# get rules and updates
rules = []
updates = []
for line in lines:
    if "|" in line:
        rule = []
        for part in line.replace("|", " ").split():
            rule.append(int(part))
        rules.append(rule)
    if "," in line:
        update = []
        for part in line.replace(",", " ").split():
            update.append(int(part))
        updates.append(update)
print("Found {} rules and {} updates.".format(len(rules), len(updates)))


def fix_order(update):
    while not is_in_right_order(update):
        for rule in rules:
            if rule[0] in update and rule[1] in update:
                index0 = update.index(rule[0])
                index1 = update.index(rule[1])
                if index1 < index0:
                    tmp = update[index0]
                    update[index0] = update[index1]
                    update[index1] = tmp
    return update


def is_in_right_order(update):
    for rule in rules:
        if rule[0] in update and rule[1] in update:
            index0 = update.index(rule[0])
            index1 = update.index(rule[1])
            if index1 < index0:
                return False
    return True


count = 0
sum_of_middle_pages = 0
for update in updates:
    if not is_in_right_order(update):
        count += 1
        page_numbers = fix_order(update)
        page_count = len(page_numbers)
        middle_page_number = int(page_numbers[page_count // 2])
        sum_of_middle_pages += middle_page_number

print("{} updates were in an incorrect order.".format(count))
print("The sum of the middle pages after ordering them is {}.".format(sum_of_middle_pages))
