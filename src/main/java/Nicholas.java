import java.nio.file.Files;
import java.util.Objects;
import java.util.Scanner; //Import the Scanner Class
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Nicholas {

    public static final String line = "--------------------";
    public static final Scanner scanner = new Scanner(System.in); // Creating new Scanner Object
    public static final String exitCommand = "bye";
    public static final String NAME = "Nicholas";
    private static final String TASKLIST_FILENAME = "./tasks/Nicholas.txt";
    public static final TaskList task = new TaskList(TASKLIST_FILENAME);


    public static void main(String[] args) {
        System.out.println(line);
        greet();
        File file = new File(TASKLIST_FILENAME);

        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        while(true) {
            String userInput = scanner.nextLine();
            System.out.println(line);
            if (Objects.equals(userInput, exitCommand)) {
                exit();
                break;
            }
            try {
                task.instruction(userInput);
            } catch (NicholasException e){
                System.out.println(e);
            }
            System.out.println(line);
        }
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
