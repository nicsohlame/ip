package parsers;

import exception.NicholasException;
import tasks.TaskList;
import tasks.ToDoTask;
import tasks.DeadlineTask;
import tasks.EventTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private static final String FIND = "find";

    /* Parse user input */
    public void parseCommand(String input, TaskList tasks) throws NicholasException {

        String command = input.split(" ")[0];

        switch (command) {
        case LIST -> {
            tasks.getList();
        }
        case MARK -> {
            int idx = prepareIndex(input);
            tasks.markTaskAsDone(idx);
        }
        case UNMARK -> {
            int idx = prepareIndex(input);
            tasks.markTaskAsUndone(idx);
        }
        case TODO -> {
            tasks.addItem(prepareToDo(input));
        }
        case DEADLINE -> {
            try {
                tasks.addItem(prepareDeadlineTask(input));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter a deadline. e.g. /by Sunday");
            }
        }
        case EVENT -> {
            try {
                tasks.addItem(prepareEventTask(input));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter a valid start and end time e.g. /from Mon 2pm /to 4pm");
            }
        }
        case DELETE -> {
            int idx = prepareIndex(input);
            tasks.deleteTask(idx);
        }
        case FIND -> {
            tasks.findTask(prepareKeyword(input));
        }
        default -> {
            throw new NicholasException("Please enter a valid task (todo, deadline, event)");
        }

        }
    }

    /* Prepare and create todo tasks */
    public ToDoTask prepareToDo(String input) {
        String description = input.replace(TODO, "");
        return new ToDoTask(description);
    }

    /* prepare and create deadline tasks */
    public DeadlineTask prepareDeadlineTask(String input) {
        String description = input.replace(DEADLINE, "");
        String[] temp = description.split("/by ");
        return new DeadlineTask(validateDate(temp[1]), temp[0]);
    }

    /* prepare and create eventtasks */
    public EventTask prepareEventTask(String input) {
        String description = input.replace(EVENT, "");
        String[] task = description.split("/from ");
        String[] time = task[1].split("/to ");
        return new EventTask(task[0],validateDate(time[0]), validateDate(time[1]));
    }

    /* prepare index for tasks */
    public int prepareIndex(String input) {
        int idx = Integer.parseInt(input.split(" ")[1]);
        return idx;
    }

    /* prepare keyword for find task */
    public String prepareKeyword(String input) throws NicholasException{
        String keyword = input.replace(FIND, "").trim();
        if (keyword.equals("")) {
            throw new NicholasException("Please enter a task to find:");
        }
        return keyword;
    }

    /* Validating date input */
    public LocalDate validateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDate dateTime = LocalDate.parse(date.trim(), formatter);
        return dateTime;
    }


}
