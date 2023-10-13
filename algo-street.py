"""

You are working for a promising new stock trading startup “Algo-Street”.

You have been tasked with developing a new trading signal that will be incorporated into the automatic trading strategy.
The new metric has been called “stock pressure” which aims to measure the tendency for stocks to regress to previous prices.

The positive stock pressure measures:
how many consecutive days before today (not including today) have an equal or higher price.

The negative stock pressure measures:
how many consecutive days before today (not including today) have an equal or lower price.
Notice that only one of the two should be greater than 1.

The Stock Pressure is the positive stock pressure - the negative stock pressure.

So if the positive stock pressure is 0 and the negative stock pressure is 3 the stock pressure is -3.

You will be given daily stock prices for the last N days and must return the list of daily stock pressures for each day.
Below is an example for 1 week of data.

        |   Price   | +Pressure | -Pressure |   Pressure  |
-----------------------------------------------------------------
| Day 1 |   100     |     1     |     1     |    0      |
-----------------------------------------------------------------
| Day 2 |   90      |     2     |     1     |    1      |
-----------------------------------------------------------------
| Day 3 |   95      |     1     |     2     |   -1      |
-----------------------------------------------------------------
| Day 4 |   100     |     1     |     4     |   -3      |
-----------------------------------------------------------------
| Day 5 |   105     |     1     |     5     |   -4      |
-----------------------------------------------------------------
| Day 6 |   110     |     1     |     6     |   -5      |
-----------------------------------------------------------------
| Day 7 |   80      |     7     |     1     |    6      |
-----------------------------------------------------------------

Implement the function to compute stock pressure.

In order to compute positive/negative stock pressure for each stock price
you must find the last day with a lower/higher price

You must use two stacks to solve the problem in O(N) time
"""


def compute_pressure(stock_history: list):
    stock_ArrayLength = len(stock_history)
    ProsPressure = []
    NegPressure = []
    stock_pressure = []
    stack_indexLowerPrice = []
    stack_indexHigherPrice = []
    i =  0
    while i < stock_ArrayLength:
        # Calculate negative stock pressure
        while stack_indexLowerPrice and  stock_history[stack_indexLowerPrice[-1]] <= stock_history[i]:
            stack_indexLowerPrice.pop() # Remove last element from the stack
        if len(stack_indexLowerPrice) != 0: # If stack is not empty, calculate negative pressure and append into NegPressure array
            NegPressure.append(i - stack_indexLowerPrice[-1])
        else: # If stack is empty
            NegPressure.append(i + 1)
        stack_indexLowerPrice.append(i)

        # Calculate positive stock pressure
        while stack_indexHigherPrice and stock_history[i] <= stock_history[stack_indexHigherPrice[-1]]:
            stack_indexHigherPrice.pop() # Remove last element from the stack
        if len(stack_indexHigherPrice) != 0: # If stack is not empty, calculate positive pressure and append into ProPressure array
            ProsPressure.append(i - stack_indexHigherPrice[-1])
        else: # If stack is empty
            ProsPressure.append (i + 1)
        stack_indexHigherPrice.append(i)

        # Calculate Stock Pressure and append into stock_pressure array
        stock_pressure.append(ProsPressure[i] - NegPressure[i])
        i += 1

    return stock_pressure


class TestGeneratePressure:
    def run_unit_tests(self):
        self.test_example()
        self.test_2()
        self.test_3()
        self.test_no_days_provided()
        self.test_large_list()

    def print_test_result(self, test_name, result):
        color = "\033[92m" if result else "\033[91m"
        reset = "\033[0m"
        print(f"{color}[{result}] {test_name}{reset}")

    def test_answer(self, test_name, result, expected):
        if result == expected:
            self.print_test_result(test_name, True)
        else:
            self.print_test_result(test_name, False)
            print(f"Expected: {expected} \nGot:      {result}")

    def test_example(self):
        stock_history = [100, 90, 95, 100, 105, 110, 80]

        result = compute_pressure(stock_history)
        expected_answer = [0, 1, -1, -3, -4, -5, 6]

        self.test_answer("test_example", result, expected_answer)

    def test_2(self):
        stock_history = [80, 74, 75, 90, 120, 81]

        result = compute_pressure(stock_history)
        expected_answer = [0, 1, -1, -3, -4, 2]

        self.test_answer("test_2", result, expected_answer)

    def test_3(self):
        stock_history = [1, 2, 5, 10, 12, 20]

        result = compute_pressure(stock_history)
        expected_answer = [0, -1, -2, -3, -4, -5]

        self.test_answer("test_3", result, expected_answer)

    def test_no_days_provided(self):
        stock_history = []

        result = compute_pressure(stock_history)
        expected_answer = []

        self.test_answer("test_no_days_provided", result, expected_answer)

    def test_large_list(self):
        stock_history = [100, 90, 80, 85, 90, 95, 100, 105, 110, 120, 140, 120, 100, 80]

        result = compute_pressure(stock_history)
        expected_answer = [0, 1, 2, -1, -3, -4, -6, -7, -8, -9, -10, 2, 6, 13]

        self.test_answer("test_large_list", result, expected_answer)


if __name__ == '__main__':
    test_runner = TestGeneratePressure()
    test_runner.run_unit_tests()
