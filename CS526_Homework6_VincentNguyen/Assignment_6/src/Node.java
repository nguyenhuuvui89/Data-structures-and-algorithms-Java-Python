import java.util.ArrayList;

public class Node {
    public String name;
    public ArrayList<Node> directFlows = new ArrayList<>();

    public Node(String name) {
        this.name = name;
    }
    public void insertDirectFollowee(Node node) {
        directFlows.add(node);
    }
    @Override
    public String toString() {
        // Customize the string representation for Node
        return name;
    }
}
