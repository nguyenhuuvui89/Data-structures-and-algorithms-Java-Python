
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Hw1_p2 {
	/**
	 * findByMake method is used to find and print all cars in the car array that have the same make as the given make
	 * and print out message if there is no car with the given make. Method has no return value
	 * @param cars array of Car objects
	 * @param make the make of the car make
	 */
	public static void findByMake(Car[] cars, String make) {
		// implement this method
		int count = 0; // initial count for number of car have same make
		for (Car car: cars) { // use extended for loop to loop through car array
			if (car.getMake().equals(make)) {		// get the make of each car and compare with the given make, if equal, print out the car.
				System.out.println(car);
				count++;
			}
		}
		if (count == 0) {		// count == 0 means no car in the array is the same make with given make
			System.out.println("There is no cars with make " + make);
		}
	}

	/**
	 * olderThan method is used to find and prints all cars in the array that are older than the given year. Method has no return value.
	 * print out message if there is no older car than the given year
	 * @param cars array of Car objects
	 * @param year year of car made
	 */
	public static void olderThan(Car[] cars, int year) {
		// implement this method
		int counter = 0;		// initial counter for number of older car
		for (Car car: cars) {	// use extended loop to loop through car array
			if (car.getYear() < year) {  // check if the year of the car in the array is older or not
				System.out.println(car); // print out older car
				counter++;		// increase the counter
			}
		}
		if (counter == 0) {			// if counter is 0, it means that there is no older car than given year --> print message
			System.out.println("There is no older cars than given year " + year);
		}
	}

	public static void main(String[] args) throws IOException {

		Car [] cars = new Car[10];					// initial car array
		File file = new File("src/CS526_car_input.txt");			// create file instance of input files
		Scanner scan = new Scanner(file);					// create scan of Scanner class to read input file
		int index = 0;
		int len = cars.length;
		while(scan.hasNextLine() && index < len) {			// use while loop to read file
			String carData = scan.nextLine();				// read current line and store it into carData variable
			String [] arrCarData = carData.split(", ");	// use split function to create string array
			// get the make, price, year from arrCarData and convert price year into integer
			if (arrCarData.length == 3) {
				String make = arrCarData[0];
				int price = Integer.parseInt(arrCarData[1]);
				int year = Integer.parseInt(arrCarData[2]);
				cars[index] = new Car(make, year, price);	// create new car object based on the Car class
			}
			index ++; // increase index
		}
		scan.close(); // Close the file
		System.out.println("\nAll cars:");
		for (int i=0; i<cars.length; i++) {
			System.out.println(cars[i]);
		}

		String make = "Honda";
		int year = 2017;

		System.out.println("\nAll cars made by " + make);
		findByMake(cars, make);
		System.out.println("\nAll cars made before " + year);
		olderThan(cars, year);
	}

}
