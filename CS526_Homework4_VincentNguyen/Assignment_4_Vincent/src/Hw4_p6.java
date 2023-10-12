import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.*;

public class Hw4_p6 {
    public static void main(String[] args) {
        // Initialize the insertion time and search time for hashmap, arrayList, linkedList
        double mapInsertTime = 0.0;
        double arrayListInsertTime = 0.0;
        double linkedListInsertTime = 0.0;
        double mapSearchTime = 0.0;
        double arraySearchTime = 0.0;
        double linkedListSearchTime = 0.0;
        int iter = 10;
        double iterD = iter;
        // run for loop 10 times to calculate average total insertion time and average total search time for each data structures
        for (int i = 0; i < iter; i++) {
            // Create Random class
            Random r = new Random(System.currentTimeMillis());
            int[] insertKeys = new int[100000];

//            create a HashMap instance myMap
//            create an ArrayList instance myArrayList
//            create a LinkedList instance myLinkedList

            HashMap<Integer, Integer> myMap = new HashMap<Integer, Integer>();
            ArrayList<Integer> myArrayList = new ArrayList<Integer>();
            LinkedList<Integer> myLinkedList = new LinkedList<Integer>();

            // Initialize startTime, endTime, elapsedTime
            double startTime, endTime, elapsedTime;

            int insertKeysLength = insertKeys.length;
            // Generate and Insert 100000 distinct random numbers into insertKeys array
            for (int j = 0; j < insertKeysLength; j++) {
                // Generate random number
                int randomNumber = r.nextInt(1000000) + 1;
                boolean usedNumber = false;
                // Check if the random integer already exist in the insertKeys
                for (int number : insertKeys) {
                    if (randomNumber == number) {
                        usedNumber = true;
                        j--;
                        break;
                    }}
                if (!usedNumber) {
                    insertKeys[j] = randomNumber;
                }
            }
            // Using below make it faster to get results
//            Set<Integer> uNumbers = new HashSet<Integer>();
//            for (int j = 0; j < insertKeysLength; j++) {
//                int randomNumber;
//                do {
//                    randomNumber = r.nextInt(1000000) + 1;
//                } while (uNumbers.contains(randomNumber));
//
//                insertKeys[j] = randomNumber;
//                uNumbers.add(randomNumber);
//            }

            // INSERT ALL KEYS INTO myMap, myArrayList and myLinkedList from insertKeys

            // 1. Insert into myMap and calculate the insertion time
            startTime = System.currentTimeMillis();
            for (int k = 0; k < insertKeysLength; k++) {
                myMap.put(insertKeys[k], insertKeys[k]);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            mapInsertTime += elapsedTime;

            // 2. Insert into myArrayList and calculate the insertion time
            startTime = System.currentTimeMillis();
            for (int key: insertKeys) {
                myArrayList.add(key);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            arrayListInsertTime += elapsedTime;

            // 3. Insert into myLinkedList and calculate the insertion time
            startTime = System.currentTimeMillis();
            for (int key: insertKeys) {
                myLinkedList.add(key);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            linkedListInsertTime += elapsedTime;

            // reset seed
            r.setSeed(System.currentTimeMillis());


        //  SEARCH SECTION:

            int [] searchKeys = new int[100000];
            int searchKeysLength = searchKeys.length;

//            // Generate and Insert 100000 distinct random numbers into searchKeys array
            for (int j = 0; j < searchKeysLength; j++) {
                int randomNumber = r.nextInt(2000000) + 1;
                boolean usedNumber = false;
                // Check if the random integer already exist in the searchKeys
                for (int number : searchKeys) {
                    if (randomNumber == number) {
                        usedNumber = true;
                        j--;
                        break;
                    }}
                if (!usedNumber) {
                    searchKeys[j] = randomNumber;
                }
            }
//             Using below make it faster to get results
//            Set<Integer> uNumbersSet = new HashSet<Integer>();
//            for (int j = 0; j < searchKeysLength; j++) {
//                int randomNumber;
//                do {
//                    randomNumber = r.nextInt(2000000) + 1;
//                } while (uNumbersSet.contains(randomNumber));
//
//                searchKeys[j] = randomNumber;
//                uNumbersSet.add(randomNumber);
//            }

        // 1. Search key for myMap and calculate the search time
            startTime = System.currentTimeMillis();
            for (int key: searchKeys) {
                myMap.containsKey(key);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            mapSearchTime += elapsedTime;

        // 2. Search key for myArrayList and calculate the search time
            startTime = System.currentTimeMillis();
            for (int key: searchKeys) {
                myArrayList.contains(key);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            arraySearchTime += elapsedTime;

        // 3. Search key for myLinkedList and calculate the search time
            startTime = System.currentTimeMillis();
            for (int key: searchKeys) {
                myLinkedList.contains(key);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            linkedListSearchTime += elapsedTime;

            // reset seed.
            r.setSeed(System.currentTimeMillis());

        }

        // Print out results.
        System.out.println("Number of keys = "+ 100000 +  "\n");
        System.out.println("HashMap average total insert time = " + mapInsertTime/iterD);
        System.out.println("ArrayList average total insert time = " + arrayListInsertTime/iterD);
        System.out.println("LinkedList average total insert time = " + linkedListInsertTime/iterD + "\n");
        System.out.println("HashMap average total search time = " + mapSearchTime/iterD);
        System.out.println("ArrayList average total search time = " + arraySearchTime/iterD);
        System.out.println("LinkedList average total search time = " + linkedListSearchTime/iterD);

    }
}
