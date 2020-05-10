
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaExample {
    
    public static void main(String[] args) {
        int[] numbers = {14, 3, 27, 12, 7, 18, 57, 15, 8};
        List<Integer> values = new LinkedList<>();
        String[] names = {"gily", "ain", "Ann", "Bill",
            "Michael", "Kay", "Sam"};
        for (int n : numbers) {
            values.add(n);
        }
        List<Integer> sortedList = values.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println(sortedList);
//        reverse
//        List<String> sortedList = list.stream()
//			.sorted(Comparator.reverseOrder())
//			.collect(Collectors.toList());

//Dsiplay the sorted value  
// write your code
//Display names in ascending order after filtering the names strating with ‘g’or less
      List<String> sortedListName=  Arrays.stream(names)
                .filter(n -> n.startsWith("g") || n.compareTo("g") < 0
                ).sorted().collect(Collectors.toList());
        System.out.println(sortedListName);
        System.out.println("g".compareTo("hi"));

// write your code
    } //end main
} //end class
