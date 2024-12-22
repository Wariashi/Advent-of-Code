filename = "input"


class Buyer:
    numbers = []
    prices = []
    changes = []
    sequences = dict()

    def __init__(self, first_number):
        numbers = [first_number]
        prices = [first_number % 10]
        changes = [None]
        sequences = dict()
        for i in range(1, 2000 + 1):
            # numbers
            previous_number = numbers[i - 1]
            next_number = get_next(previous_number)
            numbers.append(next_number)

            # prices
            previous_price = prices[i - 1]
            next_price = next_number % 10
            prices.append(next_price)

            # changes
            next_change = next_price - previous_price
            changes.append(str(next_change))

            # sequences
            if 3 < i:
                sequence = ",".join(changes[i - 3:i + 1])
                if sequence not in sequences:
                    sequences[sequence] = prices[i]

        self.numbers = numbers
        self.prices = prices
        self.changes = changes
        self.sequences = sequences


def get_buyers():
    buyers = []
    with open(filename) as file:
        lines = file.read().splitlines()
    for line in lines:
        buyers.append(Buyer(int(line)))
    return buyers


def get_next(number):
    number = ((number * 64) ^ number) % 16777216
    number = ((number // 32) ^ number) % 16777216
    number = ((number * 2048) ^ number) % 16777216

    return number


def main():
    buyers = get_buyers()
    sequences = dict()
    for buyer in buyers:
        for sequence in buyer.sequences:
            price = buyer.sequences[sequence]
            sequences.setdefault(sequence, 0)
            sequences[sequence] += price

    max_price = 0
    for sequence in sequences:
        price = sequences[sequence]
        if max_price < price:
            max_price = price
    print("You can get {} bananas.".format(max_price))


if __name__ == '__main__':
    main()
