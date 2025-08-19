import java.util.Objects;
import java.util.Scanner; //Import the Scanner Class

public class Nicholas {

    public static final String line = "--------------------";
    public static final Scanner scanner = new Scanner(System.in); // Creating new Scanner Object
    public static final String exitCommand = "bye";
    public static final String NAME = "Nicholas";

    public static void main(String[] args) {
        System.out.println(line);
        greet();
        echo();
    }

    public static void greet() {
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void echo() {
        while (true) {
            String userInput = scanner.nextLine();
            System.out.println(line);

            if (Objects.equals(userInput, exitCommand)) {
                exit();
                break;
            }

            System.out.println(userInput);
            System.out.println(line);
        }
    }
}
