
import java.util.LinkedList;

public class GenericStack<T> {

    public LinkedList<T> elements;

    public GenericStack() {
        elements = new LinkedList<T>();
    }

    public void push(T value) {
// Student to complete the code to push value to the stack
elements.push(value);

//Use appropriate LinkedList method
    }

    public static void main(String[] args) {
        String[] words = {"push", "pop", "pull", "put", "post"};
//Student to complete: create a Stack <String>
        GenericStack<String> stackString = new GenericStack<>();
//Student to complete: push values from the words Array into the
for (String word : words) {
            stackString.push(word);
        }
//Stack <String>

        System.out.println(stackString.elements);
    }
}
