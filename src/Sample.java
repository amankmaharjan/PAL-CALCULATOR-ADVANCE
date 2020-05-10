
public class Sample {

    public static int guessProg(int num1, int num2) {
        if (num2 > 0) {
            return guessProg(num1, num2 - 1) * num1;
        } else {
            return 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(guessProg(2, 3));
        System.out.println(guessProg(4, 2));
    }
}
