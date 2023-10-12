import java.io.*;
import java.util.Random;

public class Hw5_P7 {
    /*Function to sort array using insertion sort*/
    //    https://www.geeksforgeeks.org/insertion-sort/
    public static void insertionSort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
    //    https://www.geeksforgeeks.org/merge-sort/
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]

    public static void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    // Main function that sorts arr[l..r] using
    // merge()

    public static void mergesort(int arr[], int l, int r)
    {
        if (l < r) {

            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergesort(arr, l, m);
            mergesort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
    //    https://www.geeksforgeeks.org/quick-sort/
    // A utility function to swap two elements

    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    // This function takes last element as pivot,
    // places the pivot element at its correct position
    // in sorted array, and places all smaller to left
    // of pivot and all greater elements to right of pivot

    static int partition(int[] arr, int low, int high)
    {
        // Choosing the pivot
        int pivot = arr[high];

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller than the pivot
            if (arr[j] < pivot) {

                // Increment index of smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }
    // The main function that implements QuickSort
    // arr[] --> Array to be sorted,
    // low --> Starting index,
    // high --> Ending index

    public static void quickSort(int[] arr, int low, int high)
    {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    //    https://www.geeksforgeeks.org/heap-sort/
    // The main function that implements heap sort
    public static void heapsort(int arr[])
    {
        int N = arr.length;

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--)
            heapify(arr, N, i);

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap

    public static void heapify(int arr[], int N, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < N && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest);
        }
    }
    public static void main(String[] args) {
        Random r = new Random();
        long startTime;
        long endTime;
        long elapsedTime;
        int i = 10000;
        while (i <= 100000) {


            int [] arraySort = new int[i];
            // generate random integer with each loop with each size of array
            int arraySortLength = arraySort.length;
            // Generate and Insert 100000 distinct random numbers into insertKeys array
            for (int j = 0; j < arraySortLength; j++) {
                // Generate random number
                int randomNumber = r.nextInt(1000000) + 1;
                boolean usedNumber = false;
                // Check if the random integer already exist in the insertKeys
                for (int number : arraySort) {
                    if (randomNumber == number) {
                        usedNumber = true;
                        j--;
                        break;
                    }}
                if (!usedNumber) {
                    arraySort[j] = randomNumber;
                }
            }
            System.out.println();
            System.out.println("---Sorting time of different algorithms with array size: " + i + " elements--");
            System.out.println();

            // Calculate elapse time for insertion sort
            // Initiate the arrays
            int [] insertionArr = new int[arraySortLength];
            int [] mergeArr = new int[arraySortLength];
            int [] quickArr = new int[arraySortLength];
            int [] heapArr = new int[arraySortLength];
            // Copy arraySort elements into above arrays
            for (int j = 0; j < arraySortLength; j++) {
                insertionArr[j] = arraySort[j];
                mergeArr[j] = arraySort[j];
                quickArr[j] = arraySort[j];
                heapArr[j] = arraySort[j];
            }
            //Calculating elapsed time for insertion sort
            startTime = System.currentTimeMillis(); // Starting time of insertionSort
            insertionSort(insertionArr); // Calling insertionSort
            endTime = System.currentTimeMillis(); // Ending time of insertionSort
            elapsedTime = endTime - startTime; // Calculating elapsed time for insertionSort
            System.out.print("Insertion Sort's Elapsed Time: "+ elapsedTime +" mils");
            System.out.println();

            //Calculating elapsed time for merge sort
            startTime = System.currentTimeMillis(); // Starting time of merge sort
            mergesort(mergeArr, 0, arraySortLength -1); // Calling mergeSort
            endTime = System.currentTimeMillis(); // Ending time of merge sort
            elapsedTime = endTime - startTime; // Calculating elapsed time for merge sort
            System.out.print("Merge Sort's Elapsed Time: "+ elapsedTime +" mils");
            System.out.println();

            //Calculating elapsed time for quickSort
            startTime = System.currentTimeMillis(); // Starting time of quick sort
            quickSort(quickArr, 0, arraySortLength -1); // Calling quick sort
            endTime = System.currentTimeMillis(); // Ending time of quickSort
            elapsedTime = endTime - startTime; // Calculating elapsed time for quick sort
            System.out.print("Quick Sort's Elapsed Time: "+ elapsedTime +" mils");
            System.out.println();

            //Calculating elapsed time for heapSort
            startTime = System.currentTimeMillis(); // Starting time of heap sort
            heapsort(heapArr); // Calling heap sort
            endTime = System.currentTimeMillis(); // Ending time of heap sort
            elapsedTime = endTime - startTime; // Calculating elapsed time for heap sort
            System.out.print("Heap Sort's Elapsed Time: "+ elapsedTime +" mils");
            System.out.println();


        i += 10000;
        }

    }

}
