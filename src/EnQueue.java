
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.LinkedList;

public class EnQueue {

    private double values[] = {2.5, 1.2, 1.5, 5.6, 1.5, 8.7, 9.5, 2.5};
    private LinkedList<Double> queue;
    private SortedSet<Double> ordered;

    public EnQueue() {
        queue = new LinkedList<Double>(); // LinkedList to be used as a queue
        ordered = new TreeSet<>(); //creates a set
        enQueue();
    }

    public void enQueue() {
        for (double value : values) {
            queue.offer(value);

        }
        //Student to complete Use appropriate Linked list method to fill the queue
        // with Array elements of values[] given above.

    }

    public void displayQueueFillSet() {
        System.out.println("Queue contains:");
        while (queue.size() > 0) { // Student to complete display queue elements and remove these
            
            System.out.println(queue.peek());
            ordered.add(queue.poll());
            //elements from queue and add to the set, ordered given above.

        }
        System.out.println(ordered);
    }

    public void displaySet() {
        Iterator<Double> pointer = ordered.iterator();
        System.out.println("Set contains:");
        while (pointer.hasNext()) {
            System.out.printf("%.2f, ", pointer.next());
        }
    }

    public static void main(String[] args) {
        EnQueue set = new EnQueue();
        set.displayQueueFillSet();
        set.displaySet();
    }
}
