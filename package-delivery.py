"""
You are working for a new company trying to improve the package delivery process "Algo-zon".
The company wants to detect if a driver has taken a circular root while delivering packages:
 at some point in the trip they returned to somewhere they had been before.

The monitoring system currently in use at Algo-zon tracks routes taken by drivers as a Linked List where each node has:
id (integer) and a timestamp (in unix format).

Each node represents a package pickup zone where a driver gets packages.

To check for inefficiency Algo-zon has tasked you with writing a function that will detect whether a driver returned
to the same pickup zone

Write a function that takes in the first node in the monitoring linked list
and returns the total time for the cycle or None if there isn't one.

You are given the code for a node
"""


class PickupSnapshotNode:
    def __init__(self, location_id: int, timestamp: int, next_node: 'PickupSnapshotNode'):
        self.id = location_id
        self.timestamp = timestamp
        self.next = next_node

    def get_id(self):
        return self.id

    def get_timestamp(self):
        return self.timestamp

    def get_next(self):
        return self.next


def detect_cyclic_route(start: PickupSnapshotNode):
    head_node = start
    zones_dict = {} # initial dictionary to collect zone id and timestamp of each zone driver visited
    current_node = start
    while head_node:
        zone_id = current_node.get_id()
        current_timestamp = current_node.get_timestamp()
        if zone_id in zones_dict: # check if zone_id exist as key in zones_dict, it mean driver return to same pickup zones
            return (current_timestamp - zones_dict[zone_id])
        zones_dict[zone_id] = current_timestamp  
        current_node = current_node.get_next()
        if current_node is None: # mean when there is end linked list
            return None
    else: # if the head is None, not present
        return None
    
class TestingBase:
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


# Unit Testing Class
class TestRideSnapshotNode(TestingBase):
    def run_unit_tests(self):
        self.test_detect_cyclic_ride()
        self.test_detect_cyclic_ride_no_cycle()

    def test_detect_cyclic_ride(self):
        # Create nodes for a cyclic ride
        node4 = PickupSnapshotNode(12345, 1685288860, None)
        node3 = PickupSnapshotNode(21341, 1685288560, node4)
        node2 = PickupSnapshotNode(12345, 1685288260, node3)
        node1 = PickupSnapshotNode(32144, 1685287960, node2)
        
        # Call the detect_cyclic_ride function
        result = detect_cyclic_route(node1)
        # Cycle is node 2 -> node 3 -> node 4
        expected_answer = 1685288860 - 1685288260

        self.test_answer("test_detect_cyclic_ride_cycle", result, expected_answer)

    def test_detect_cyclic_ride_no_cycle(self):
        # Create nodes for a non-cyclic ride
        node4 = PickupSnapshotNode(12345, 1685288860, None)
        node3 = PickupSnapshotNode(21341, 1685288560, node4)
        node2 = PickupSnapshotNode(32144, 1685288260, node3)
        node1 = PickupSnapshotNode(12237, 1685287960, node2)
        
        # Call the detect_cyclic_ride function
        result = detect_cyclic_route(node1)

        # Assert that no cycle is detected (result should be None or False)
        self.test_answer("test_detect_cyclic_ride_no_cycle", result, None)


if __name__ == '__main__':
    test_runner = TestRideSnapshotNode()
    test_runner.run_unit_tests()
