import java.io.File;
import java.io.IOException;
import java.util.*;

public class Hw6_p5 {

    public static void main(String[] args) throws IOException {
        ArrayList<Node> adjList = new ArrayList<>();
        File file = new File("src/follows_input.txt");            // create file instance of input files
        Scanner scan = new Scanner(file);                    // create scan of Scanner class to read input file
        while (scan.hasNextLine()) {            // use while loop to read file
            String nodeData = scan.nextLine();                // read current line and store it into carData variable
            String[] arrNodeData = nodeData.split(", ");    // use split function to create string array
            Node newNode = new Node(arrNodeData[0]);
            // add nodes to directFlows
            for (int i = 1; i < arrNodeData.length; i++) {
                Node followee = new Node(arrNodeData[i]);
                newNode.insertDirectFollowee(followee);
            }
            // add nodes into adjacency list
            adjList.add(newNode);
        }
        scan.close(); // Close the file

        allFollows("D", adjList);
        System.out.println();
        allFollows("A", adjList);
        System.out.println();
        allFollows("B", adjList);
        System.out.println();
        allFollows("C", adjList);
        System.out.println();
        allFollows("E", adjList);
        System.out.println();
        allFollows("F", adjList);
        System.out.println();
        allFollows("G", adjList);

    }

    /**
     * This function get the list of people X directly follows and all people X indirectly follows using DFS.
     * @param person
     * @param adjList
     */
    public static void allFollows(String person, ArrayList<Node> adjList) {
        // Initial indirectFollow and directFlows arrayList
        ArrayList<String> indirectFollows = new ArrayList<>();
        ArrayList<String> directFlows = new ArrayList<>();
        // Create the stack
        Stack<Node> stack = new Stack<>();
        // Create the set to track if node is visited or not
        Set<String> visited = new HashSet<>();
        Node personNode = new Node(person);
        ArrayList<Node> directFlowsNode = new ArrayList<>();
        // check in to get the start node (person)
        for (Node a: adjList) {
            if (a.name.equals(person)) {
                personNode = a;
                directFlowsNode.addAll(a.directFlows);
                break;
            }
        }
        // Add the direct follows into the list and print out
        for (Node followee: directFlowsNode) {
            directFlows.add(followee.name);
        }
        System.out.println(person+" directly follows " + "{"+String.join(",", directFlows)+"}");
        // Push the person node into stack
        stack.push(personNode);
        // Run the while loop (start DFS traversal)
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            // Check if the node is already visited or not.
            if (!visited.contains(currentNode.name)) {
                // if the current node is not visited --> add name into visited set
                visited.add(currentNode.name);
                Node currentNodeObject = null;
                // get current full node from the adjList
                for (Node node: adjList) {
                    String nodeName =  node.name;
                    if(nodeName.equals(currentNode.name)) {
                        currentNodeObject = node;
                    }
                }
                if (currentNodeObject != null) {
                    // loop through the direct follows of current full node
                    for (Node follow: currentNodeObject.directFlows){
                        // only add the node name into indirectFollows list if it is not in indirectFollow and directFlows lists
                        if (!indirectFollows.contains(follow.name) && (!directFlows.contains(follow.name))) {
                            indirectFollows.add(follow.name);
                        }
                        stack.push(follow);
                    }
                }

            }

        }
        System.out.println(person + " indirectly follows " + "{"+String.join(",", indirectFollows)+"}");
    }
}
