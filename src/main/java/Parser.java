import java.util.ArrayList;
import java.util.List;
/**
 * Interpreting user command.
 */
public class Parser {
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";

    /* Parse user input */
    public void parseCommand(String input, TaskList tasks) throws NicholasException {

        String command = input.split(" ")[0];

        switch (command) {
            case LIST -> {
                tasks.getList();
            }
            case MARK -> {
                tasks.markTaskAsDone(input);
            }
            case UNMARK -> {
                tasks.markTaskAsUndone(input);
            }
            case TODO -> {
                String instruction = prepareToDo(input);
                tasks.addItem(tasks.createToDoTask(input));
            }
            case DEADLINE -> {
                try {
                    tasks.addItem(tasks.createDeadlineTask(input));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please enter a deadline. e.g. /by Sunday");
                }
            }
            case EVENT -> {
                try {
                    tasks.addItem(tasks.createEventTask(command));
                    tasks.saveToFile();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please enter a valid start and end time e.g. /from Mon 2pm /to 4pm");
                }
            }
            case DELETE -> {
                tasks.deleteTask(command);
                tasks.saveToFile();
            }
            default -> {
                throw new NicholasException("Please enter a valid task (todo, deadline, event)");
            }

        }
    }

    public String prepareToDo(String input) {
        return input.replace(TODO, "");
    }
}
