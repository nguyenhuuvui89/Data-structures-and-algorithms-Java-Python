import java.io.FileNotFoundException;
import net.datastructures.HeapAdaptablePriorityQueue;
import net.datastructures.Entry;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;



public class ProcessScheduling {
    public static void main(String[] args) throws IOException {
        ArrayList<Process> processes = new ArrayList<>();
        // create a new file to record the output
        FileWriter outputFile = new FileWriter("src/process_scheduling_output.txt");

        try {
            // create file instance of input file
            File file = new File("src/process_scheduling_input.txt");
            Scanner scan = new Scanner(file); // create scan of Scanner class to read input file

            while (scan.hasNextLine()) { // use while loop to read file
                String processLine = scan.nextLine();
                String[] processInfo = processLine.split(" "); // use split function to create string array

                if (processInfo.length == 4) {
                    int id = Integer.parseInt(processInfo[0]);
                    int priority = Integer.parseInt(processInfo[1]);
                    int duration = Integer.parseInt(processInfo[2]);
                    int arrivalTime = Integer.parseInt(processInfo[3]);

                    // Create a new process and add it to the ArrayList
                    Process process = new Process(id, priority, duration, arrivalTime);
                    processes.add(process);
                }
            }
            // Print all the processes.
            for (Process process : processes) {
                String message = String.format("Id = %s, priority = %s, duration = %s, arrival time = %s", process.getProcessId(),process.getPriority(),process.getDuration(),process.getArrivalTime());
                System.out.println(message);
                outputFile.write(message + "\n");
            }

            // Close the scanner when done
            scan.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        // Create a HeapAdaptablePriorityQueue
        HeapAdaptablePriorityQueue<Integer, Process> priorityQueue = new HeapAdaptablePriorityQueue<Integer, Process>();

        // Initialize other variables (currentTime, maxWaitTime, etc.)
        int currentTime = 0;
        int maxWaitTime = 30;
        int totalWaitTime = 0;
        int prevProcessId = 0;
        int executedProcesses = processes.size();

        // Print the maximum wait time
        System.out.println();
        System.out.println("Maximum wait time = " + maxWaitTime);
        System.out.println();
        outputFile.write("\nMaximum wait time = " + maxWaitTime + "\n"+ "\n");

        // Main loop run when priorityQueue or processes not empty
        while (!priorityQueue.isEmpty() || !processes.isEmpty()) {

            // Move processes from the list to the queue if their arrival time is less than or equal to currentTime
            if (!processes.isEmpty() && processes.get(0).getArrivalTime() <= currentTime) {
                // remove the item from the array and insert into priorityQueue
                Process process = processes.remove(0);
                priorityQueue.insert(process.getPriority(), process);

            }

            // Execute the top process in the queue for one time step
            if (!priorityQueue.isEmpty()) {
                Process currentProcess = priorityQueue.min().getValue();
                int timeRemain = currentProcess.getRemainingTime();
                int currentProcessId = currentProcess.getProcessId();
                currentProcess.setRemainingTime(timeRemain - 1);
            // Check if current process is same as previous process or not
                if (prevProcessId != currentProcess.getProcessId()) {
                    // Print process information
                    System.out.println("Now running Process id = " + currentProcess.getProcessId());
                    System.out.println("Arrival = " + currentProcess.getArrivalTime());
                    System.out.println("Duration = " + currentProcess.getDuration());
                    System.out.println("Run time left = " + timeRemain + "\n\t at time " + currentTime);

                    // Write to the output file
                    outputFile.write("Now running Process id = " + currentProcess.getProcessId() + "\n");
                    outputFile.write("Arrival = " + currentProcess.getArrivalTime() + "\n");
                    outputFile.write("Duration = " + currentProcess.getDuration() + "\n");
                    outputFile.write("Run time left = " + timeRemain + "\n\t at time " + currentTime + "\n");
                }


                System.out.println("Executed process ID:" + currentProcess.getProcessId() + ", at time " +
                        currentTime + " Remaining: " + currentProcess.getRemainingTime());

                outputFile.write("Executed process ID:" + currentProcess.getProcessId() + ", at time " +
                        currentTime + " Remaining: " + currentProcess.getRemainingTime() + "\n");

                // Update wait times of all processes in the queue.
                for (Entry<Integer, Process> pqEntry : priorityQueue) {
                    // get the process in the queue
                    Process processOfEntry = pqEntry.getValue();
                    // Check if the process in the queue is current process.
                    if (currentProcessId != processOfEntry.getProcessId()){
                        processOfEntry.incrementWaitTime();
                        totalWaitTime ++;
                    }
                // Check the process if it reaches maximum wait time and update it by decreasing priority.
                    if (processOfEntry.getWaitTime() == maxWaitTime) {
                        int newPriority = processOfEntry.getPriority() - 1;
                        // change the priority
                        priorityQueue.replaceKey(pqEntry, newPriority);

                        System.out.println("Process " + processOfEntry.getProcessId() +
                                " reached maximum wait time..." +
                                "decreasing priority to " +
                                (newPriority));

                        outputFile.write("Process " + processOfEntry.getProcessId() +
                                " reached maximum wait time..." +
                                "decreasing priority to " +
                                (newPriority)+"\n");

                // Update priority and reset the wait time
                        processOfEntry.setPriority(newPriority);
                        processOfEntry.setWaitTime(0);
                    }
                    priorityQueue.replaceValue(pqEntry,processOfEntry);
                }
                // If the current process remaining time = 0, print out the message and remove the process from queue
                if (currentProcess.getRemainingTime() == 0) {
                    System.out.println("Finish running Process id = " + currentProcess.getProcessId());
                    System.out.println("Arrival = " + currentProcess.getArrivalTime());
                    System.out.println("Duration = " + currentProcess.getDuration());
                    System.out.println("Run time left = " + (timeRemain - 1) + "\n\t at time " + currentTime);

                    // Write to output file
                    outputFile.write("Finish running Process id = " + currentProcess.getProcessId()+"\n");
                    outputFile.write("Arrival = " + currentProcess.getArrivalTime()+"\n");
                    outputFile.write("Duration = " + currentProcess.getDuration()+"\n");
                    outputFile.write("Run time left = " + (timeRemain - 1) + "\n\t at time " + currentTime+"\n");
                    priorityQueue.removeMin();
                }
            // Get the Id of the process which already ran
                prevProcessId = currentProcess.getProcessId();
            }

            // Increment currentTime
            currentTime++;
        }

        // Calculate and display the average wait time.
        double averageWaitTime = (double) totalWaitTime / executedProcesses;
        System.out.println("Finished running all processes at time " + (currentTime - 1));
        System.out.println("Average wait time: " + averageWaitTime);

        // Write to output file
        outputFile.write("Finished running all processes at time " + (currentTime - 1)+"\n");
        outputFile.write("Average wait time: " + averageWaitTime +"\n");
    outputFile.close();
    }
}
