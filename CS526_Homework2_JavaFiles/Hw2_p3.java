package LinkedList;

import LinkedList.DoublyLinkedList;

public class Hw2_p3 {

	// implement reverse method
	// you may want to write a separate method with additional parameters, which is recursive
	
	public static void reverse(DoublyLinkedList<Integer> intList) {
		reverseHelper(intList, intList.size() - 1);
	}

	/**
	 *  reverseHelper is a method which use to reverse DoublyLinkedList
	 * @param intList is the integer DoublyLinkedList
	 * @param i is the size of DoublyLinkedList
	 */
	public static void reverseHelper(DoublyLinkedList<Integer> intList, int i) {
		if (i <=0) {   // This is base case
			return;
		}
		Integer temp = intList.removeFirst(); // Remove the first element in the DoublyLinkList
		reverseHelper(intList, i - 1); // Recursion with the size decrease 1
		intList.addLast(temp); // add the most recently removed element to the DoublyLinkList
	}
	// use the main method for testing
	// test with arrays of different lenghts
	public static void main(String[] args) {

		
		DoublyLinkedList<Integer> intList = new DoublyLinkedList<>();
		
		int[] a = {10, 20, 30, 40, 50};
		for (int i=0; i<a.length; i++) {
			intList.addLast(a[i]);
		}
		System.out.println("Initial list: size = " + intList.size() + ", " + intList.toString());
		
		// Here, invoke the reverse method you implemented above
		reverse(intList);
		
		System.out.println("After reverse: " + intList.toString());
		
		intList = new DoublyLinkedList<>();
		int[] b = {10, 20, 30, 40, 50, 60};
		for (int i=0; i<b.length; i++) {
			intList.addLast(b[i]);
		}
		System.out.println();
		System.out.println("Initial list: size = " + intList.size() + ", " + intList.toString());
		
		// Here, invoke the reverse method you implemented above
		reverse(intList);
		
		System.out.println("After reverse: " + intList.toString());

	}

}
