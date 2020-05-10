
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import org.w3c.dom.NameList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class DataStructures {

    // File separator and  file name constants
    private final String SEPERATOR = ",";
    private final String FILENAME = "COIT20256Ass2Data.csv";

    // Scannner object to read from file
    Scanner inputScanner;
    // Data structures objects
    PriorityQueue<String> priorityQueue;
    Stack<String> nameStack = new Stack<>();
    Set<String> nameSet = new HashSet<>();
    List<String> studentIDList = new ArrayList<>();
    List<String> studentNamesList = new ArrayList<>();
    HashMap<String, String> nameHashMap = new HashMap<>();
    DatabaseUtility databaseUtility;

    // Constructor
    public DataStructures() throws ClassNotFoundException {
        databaseUtility = new DatabaseUtility();
        databaseUtility.generateNameIDFile();
        //  Opening file
        openFile();
        // Reading file
        readFile();
        // Closing file
        closeFile();
    }

    // method for queue operation
    public void priorityQueueOperation() {
        priorityQueue = new PriorityQueue<>();
        // loading in priority queue
        System.out.println("Loading data in priority Queue");
        studentNamesList.forEach((name) -> priorityQueue.offer(name));
        // displayingl list names
        System.out.println("List names:");
        System.out.println(studentNamesList);
        // displaying values
        System.out.println("\nPriority Queue:");
        while (priorityQueue.size() > 0) {
            // display names
            System.out.print(priorityQueue.peek() + ",");
            // pushing in stack list
            nameStack.push(priorityQueue.peek());
            // removing name the priority queue
            priorityQueue.poll();
        }
    }

    // method for  opening file
    public void openFile() {
        try {
            // reading file
            inputScanner = new Scanner(Paths.get(FILENAME));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Method for  reading file
    public void readFile() {
        // Skip first line
        inputScanner.nextLine();
        while (inputScanner.hasNext()) {
            // read each line
            String row = inputScanner.next();
            // splitting the row by ,
            String rowdata[] = row.split(SEPERATOR);
            //  add name and id data into list
            studentIDList.add(rowdata[0]);
            studentNamesList.add(rowdata[1]);
        }
    }

    // Method for  closing file
    public void closeFile() {
        if (inputScanner != null) {
            inputScanner.close();
        }
    }

    // Method for  stack operation
    private void stackOperation() {
        // displaying values
        System.out.println("\nStack list:");
        while (nameStack.size() > 0) {
            // poping up from stack
            String name = nameStack.pop();
            // printing name
            System.out.print(name + ",");
            // adding into set
            nameSet.add(name);
        }
    }

    // Method for set operation
    private void setOperation() {
        // displaying names set vaues
        System.out.println("\nSet list:");
        System.out.print(nameSet);
    }

    // Method for  hashMap Operation
    private void hashMapOperation() {
        System.out.println("\nHash Map list:");
        // insert key value pair  in the  hash map
        for (int i = 0; i < studentNamesList.size(); i++) {
            nameHashMap.put(studentIDList.get(i), studentNamesList.get(i));
        }
        // displaying hash map content
        for (String i : nameHashMap.keySet()) {
            System.out.println("key: " + i + " value: " + nameHashMap.get(i));
        }
    }
// Main function

    public static void main(String args[]) throws ClassNotFoundException {
        // Creating data structure object
        DataStructures data = new DataStructures();
        // Applying queue operation
        data.priorityQueueOperation();
        // Applying stack operation
        data.stackOperation();
        // Applying set operation
        data.setOperation();
        // Applying hash map operation
        data.hashMapOperation();
    }

}
