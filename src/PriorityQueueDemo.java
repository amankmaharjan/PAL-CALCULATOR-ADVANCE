
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue<Integer> q1 = new PriorityQueue<>();
        Integer[] values = {9, 2, 12, 7, 15};
        for (Integer value : values) {
            q1.offer(value);
        }
        //fill the queue q1 with elements from the array values.
// Write code to display the contents of queue q1.
// write code to replace the element ‘2’ with ‘5’ in q1.
        LinkedList<Integer> templist=new LinkedList<>(q1);
        int index=templist.indexOf(2);
        templist.set(index, 5);
        q1.clear();
        q1.addAll(templist);
        System.out.println(q1);
        
        
          

    } // end main
} //end class
