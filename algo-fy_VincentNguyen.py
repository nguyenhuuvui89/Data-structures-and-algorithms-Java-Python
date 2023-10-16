import heapq

'''
You are working for a promising new music streaming service “Algo-fy”.
Algo-fy would like to offer a new ranking feature that will track the top k most streamed songs of the day.

You’ve been tasked with building a class “SongRank” that can track the most streamed songs
and return an accurate list top k list at any point in time.

Another team has already developed the song streaming feature: iStream and you will need to integrate with it.
iStream will send SongRank play data as it processes customer requests.
This data will be batched so the SongRank class will need to ingest lists of played songs multiple times

A song is identified with an integer id number.

The SongRank class will need to have the following methods:
Constructor(k): the number of songs to track in the top k
stream_songs(List of songIds) : Receive a batch of streamed songs => updates internal state
get_top_k(): return the top k most streamed songs in order

As an example the SongRank class may see the following usage
sr = new SongRank(2)
sr.stream_songs([1, 1, 2, 2, 3])
sr.stream_songs([3, 3, 3, 1, 2, 1, 3])
sr.get_top_k() => [3, 1]
sr.stream_songs([2, 2, 2, 2, 2])
sr.get_top_k() => [2, 3]

You must solve this problem by using either a Priority Queue or Heap

Vincent Nguyen
'''


class SongRank:
    def __init__(self, k):
        self.k = k
        self.songCount = {}  
        self.song_rev = {}
    """
    stream_songs have O(n) time complexity because in the worst case songIds has n element and the loop 
    has to run n time --> O(n). Inside the while loop, the searching is performed in the dictionary which time complexity is O(1)
    And the same for the for loop when we loop through all elements in the songCount dictionary --> O(n)
    --> The average time complexity of this method is O(n)

    """
    def stream_songs(self, songIds):
        songIds_length = len(songIds)
        i = 0
        # counting the number of song id
        while i < songIds_length:
            if songIds[i] in self.songCount:
                self.songCount[songIds[i]] += 1
            else:
                self.songCount[songIds[i]] = 1
            i += 1
        # Make counting number to be negative for the python heap (python heap implement the min heap)
        for songId in self.songCount:
            self.song_rev[songId] = -self.songCount[songId]
    """
    In the get_top_k method have 2 loops which are for loop and while loop
    1. for loop: in the wast case the song_rev has n element and we have to loop through n element which time complexity O(n). 
    In each element we perform heappush which time complexity O(logn)
    --> time complexity of this for loop is O(nlogn)
    2. the while loop. in the worst case k = n, the loop will run n time --> O(n).
    inside the loop, each iteration perform heappop which is O(logn)
    --> time complexity of this for while loop is O(nlogn)

    ---> time complexity for this method is O(nlogn)
    """
    def get_top_k(self):
        songIds_heap = []
        k_list = []
        # Pushing the counting song Id and song Id into heap using heapq.heappush
        for song_id in self.song_rev:
            heapq.heappush(songIds_heap, (self.song_rev[song_id], song_id))
        i = 0
        # pop the minimum node from the heap and exact the song id and append into k_list
        while i < self.k:
            heap_length = len(songIds_heap)
            if heap_length != 0:
                song_id = heapq.heappop(songIds_heap)[1]
                k_list.append(song_id)
            i += 1
        return k_list  


"""
DO NOT EDIT BELOW THIS
Below is the unit testing suite for this file.
It provides all the tests that your code must pass to get full credit.
"""


class TestSongRank:
    def run_unit_tests(self):
        self.test_example()
        self.test_example_2()
        self.test_many_batches()
        self.test_fewer_than_k()
        self.test_empty()

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
        sr = SongRank(2)
        sr.stream_songs([1, 1, 2, 2, 3])
        sr.stream_songs([3, 3, 3, 1, 2, 1, 3])
        result = sr.get_top_k()
        expected_answer = [3, 1]

        self.test_answer("test_example", result, expected_answer)

    def test_example_2(self):
        sr = SongRank(2)
        sr.stream_songs([1, 1, 2, 2, 3])
        sr.stream_songs([3, 3, 3, 1, 2, 1, 3])
        sr.get_top_k()
        sr.stream_songs([2, 2, 2, 2, 2])

        result = sr.get_top_k()
        expected_answer = [2, 3]

        self.test_answer("test_example_2", result, expected_answer)

    def test_many_batches(self):
        sr = SongRank(5)

        for i in range(1, 10):
            for j in range(1, 15):
                sr.stream_songs([i, j])
            sr.get_top_k()
        sr.stream_songs([3, 1, 1, 1, 2, 3])
        sr.stream_songs([5, 4, 4, 3, 2, 2, 2, 1, 1])
        result = sr.get_top_k()
        expected_answer = [1, 2, 3, 4, 5]

        self.test_answer("test_many_batches", result, expected_answer)

    def test_fewer_than_k(self):
        sr = SongRank(4)

        sr.stream_songs([1, 2, 3, 1, 2, 3, 1, 2, 1])
        result = sr.get_top_k()
        expected_answer = [1, 2, 3]

        self.test_answer("test_fewer_than_k", result, expected_answer)

    def test_empty(self):
        sr = SongRank(3)

        result = sr.get_top_k()
        expected_answer = []

        self.test_answer("test_fewer_than_k", result, expected_answer)


if __name__ == '__main__':
    test_runner = TestSongRank()
    test_runner.run_unit_tests()
