
import java.sql.SQLOutput;
import java.util.Arrays;

public class Hw1_p1 {
	/**
	 * find method use to search for an integer x in the integer array a and print out the message with the indices of x. There is no return in the method.
	 * @param a is the array of integers
	 * @param x is an integer which is used to searched on a;
	 */
	public static void find(int[] a, int x) {
		// implement this method
		int count = 0;
		int leng = a.length; 							// get the length of array
		for (int i = 0; i < leng; i++) { 				// loop through all elements in array
			if (a[i] == x){ 							// print out message when find the element.
				count += 1; 							// increase 1 for the count anytime x is founded
				String messageFound = String.format("%s is in a[%s]",x,i);
				System.out.println(messageFound);
			}
		}
		if (count == 0) {  								// If x is not found in array
			String messageNotFound = String.format("%s does not exist",x);
			System.out.println(messageNotFound);
		}
	}

	/**
	 * isPrefix method uses to check if string s1 is prefix of string s2, assume that length of s1 <=length of s2. This method only use loop.
	 * @param s1
	 * @param s2
	 * @return boolean, false if s1 is prefix of s2 otherwise true.
	 */
	public static boolean isPrefix(String s1, String s2) {
		int lens = s1.length(); // length of s1
		for (int i = 0; i < lens; i++){ // using for loop with length (iteration times) of s1;
			if (s1.charAt(i) != s2.charAt(i)) { // if any of character of s1 is different to character if s2, return false
				return false;
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		
		int[] a = {5, 3, 5, 6, 1, 2, 12, 5, 6, 1};
		
		find(a, 5);
		find(a, 10);
		System.out.println();
		
		String s1 = "abc";
		String s2 = "abcde";
		String s3 = "abdef";
		
		if (isPrefix(s1,s2)) {
			System.out.println(s1 + " is a prefix of " + s2);
		}
		else {
			System.out.println(s1 + " is not a prefix of " + s2);
		}
		
		if (isPrefix(s1,s3)) {
			System.out.println(s1 + " is a prefix of " + s3);
		}
		else {
			System.out.println(s1 + " is not a prefix of " + s3);
		}
	}
}
