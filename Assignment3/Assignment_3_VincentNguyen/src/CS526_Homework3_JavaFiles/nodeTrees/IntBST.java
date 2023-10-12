package CS526_Homework3_JavaFiles.nodeTrees;

import java.util.ArrayList;
import java.util.List;

// binary search tree storing integers
public class IntBST extends NodeBinaryTree<Integer> {

	//private int size = 0;

	public IntBST() {	}

	public int size() {
		return size;
	}

	public void setSize(int s) { size = s; }
	
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Places element e at the root of an empty tree and returns its new Node.
	 *
	 * @param e the new element
	 * @return the Node of the new element
	 * @throws IllegalStateException if the tree is not empty
	 */

	public Node<Integer> addRoot(Integer e) throws IllegalStateException {
		if (size != 0)
			throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}

	/**
	 * Print a binary tree horizontally Modified version of
	 * https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
	 * Modified by Keith Gutfreund
	 * 
	 * @param n Node in tree to start printing from
	 */
	
	  public void print(Node<Integer> n){ print ("", n); }
	  
	  public void print(String prefix, Node<Integer> n){
		  if (n != null){
			  print(prefix + "       ", right(n));
			  System.out.println (prefix + ("|-- ") + n.getElement());
			  print(prefix + "       ", left(n));
		  }
	  }
	  
	  
	  public void inorderPrint(Node<Integer> n) {
		if (n == null)
			return;
		inorderPrint(n.getLeft());
		System.out.print(n.getElement() + "  ");
		inorderPrint(n.getRight());
	}

	
	public Iterable<Node<Integer>> children(Node<Integer> n) {
		List<Node<Integer>> snapshot = new ArrayList<>(2); // max capacity of 2 
		if (left(n) != null) 
			snapshot.add(left(n)); 
		if (right(n) != null)
			snapshot.add(right(n)); return snapshot; 
	}
	
	public int height(Node<Integer> n) throws IllegalArgumentException { 
		if (isExternal(n)) { return 0; } 
		int h = 0; // base case if p is external
		for (Node<Integer> c : children(n)) h = Math.max(h, height(c)); return h + 1; 
	}


	public static IntBST makeBinaryTree(int[] a){
		return binaryTreeBuilder(a, 0, a.length - 1);
	}

	/**
	 * This method build a complete binary search tree that contains all integers in the array
	 * @param a the input array
	 * @param startIndex start index to build binary Tree
	 * @param endIndex end index to build binary Tree
	 * @return return binary Tree
	 */
	public static IntBST binaryTreeBuilder(int a[], int startIndex, int endIndex) {
		// create a new tree
		IntBST newTree = new IntBST();
		// base case
		if (startIndex > endIndex) {
			return null;
		}
		// find the middle element of the array
		int midI = (startIndex + endIndex) / 2;
		int midVal = a[midI];

		//add root note using the middle element to tree - middle becomes the root
		Node<Integer> rootNode = newTree.addRoot(midVal);

		// build the binary tree for left sub array using recursion
		IntBST lt = binaryTreeBuilder(a, startIndex, midI -1);;

		// build the binary tree for right sub array using recursion
		IntBST rt = binaryTreeBuilder(a, midI + 1, endIndex);

		// attach the left and right tree to the middle root note using attach method in NodeBinaryTre
		// because it is complete binary search tree
		if (lt != null && rt != null) {
			newTree.attach(rootNode, lt, rt);
		}
		// add two more condition to avoid error even though we are assuming this binary Tree is complete
		else if (lt != null) {
			newTree.attach(rootNode, lt, null);
		} else if (rt != null) {
			newTree.attach(rootNode, null, rt);
		}
		// set the size for binary tree
		int size = a.length;
		newTree.setSize(size);
		return newTree;
	}

}
