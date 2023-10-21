"""
You are working for a new cybersecurity company “AlgoWare Defender” on a decoding tool.
The decoding tool is meant to help reverse a nefarious one way encoding scheme of letters to digits.

The encoding scheme coverts letter strings into numeric strings by
converting each letter to a digit based on its place in the alphabet.

Here is the encoding table
A : 1
B : 2
…
Z : 26

For example the string ZAB maps to 2612.

The nefarious part of this scheme is that it is not reversible!

However, if you are given 2612 this could be decoded in multiple ways

26 1 2 = ZAB
2 6 12 = BFL
2 6 1 2 = BFAB
26 12 = ZL

AlgoWare Defender’s decoding tool will have a few components; you are working on the first piece:
determining how many decodings are possible. For example, there are 4 decodings for 2612

You must write a function: findNumDecodings
which when given a string of digits returns the number of possible decodings.

Your program must be efficient and must use dynamic programming.

You must also provide an explanation of the running time

Code Author: <Vincent Nguyen>

Running Time Analysis
--------------------
The running time of this method is O(n) because we loop and check all element in the array which construct by the length of given string.
"""


def find_num_decodings(string: str):
    # check if string is empty or not
    if not string:
        return 1
    else:
        string_len = len(string)
        # initial array
        decode_Arr = [0] * (string_len + 1)
        # initial the base case for dynamic programming
        decode_Arr[0] = 1
        first_letter = int(string[0])
        i = 1
        while i <= string_len:
            # check if first letter of string if it valid or not
            if i == 1:
                if first_letter != 0:      
                    decode_Arr[1] = 1
                else:
                    decode_Arr[1] = 0
            else: 
                if 1<= int(string[i - 1]) <10:
                    decode_Arr[i] += decode_Arr[i - 1]
                slice_string = string[i - 2:i]
                if 10 <= int(slice_string) <= 26:
                    decode_Arr[i] += decode_Arr[i - 2]
            i +=1
        # return last element
        return decode_Arr[-1]


class TestFindNumDecodings:
    def run_unit_tests(self):
        self.test_example()
        self.test_empty()
        self.test_single()
        self.test_invalid_1()
        self.test_invalid_2()
        self.test_normal_1()
        self.test_normal_2()
        self.test_normal_3()
        self.test_many_ones()

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
        result = find_num_decodings("2612")
        expected_answer = 4

        self.test_answer("test_example", result, expected_answer)

    def test_empty(self):
        result = find_num_decodings("")
        expected_answer = 1

        self.test_answer("test_empty", result, expected_answer)

    def test_single(self):
        result = find_num_decodings("8")
        expected_answer = 1

        self.test_answer("test_single", result, expected_answer)

    def test_double(self):
        result = find_num_decodings("25")
        expected_answer = 2

        self.test_answer("test_double", result, expected_answer)

    def test_invalid_1(self):
        result = find_num_decodings("0")
        expected_answer = 0

        self.test_answer("test_invalid_1", result, expected_answer)

    def test_invalid_2(self):
        result = find_num_decodings("1200")
        expected_answer = 0

        self.test_answer("test_invalid_2", result, expected_answer)

    def test_normal_1(self):
        result = find_num_decodings("123456789")
        expected_answer = 3

        self.test_answer("test_normal_1", result, expected_answer)

    def test_normal_2(self):
        result = find_num_decodings("1011121314151617181920212223242526")
        expected_answer = 86528

        self.test_answer("test_normal_2", result, expected_answer)

    def test_normal_3(self):
        result = find_num_decodings("1232020410105")
        expected_answer = 3

        self.test_answer("test_normal_3", result, expected_answer)

    def test_many_ones(self):
        result = find_num_decodings("1111111111111111111111111111111111111111")
        expected_answer = 165580141

        self.test_answer("test_many_ones", result, expected_answer)


if __name__ == '__main__':
    test_runner = TestFindNumDecodings()
    test_runner.run_unit_tests()
