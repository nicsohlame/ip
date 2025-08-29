import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class TaskList {
    private final List<Task> items;
    private final String filePath;
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";

    public TaskList(String filePath) {
        this.items = new ArrayList<Task>();
        this.filePath = filePath;
    }

    public void addItem(Task task){
        items.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + items.size() + " tasks in the list.");
    }

    public void getList() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < items.size(); i++) {
            System.out.println(i + 1 + "." + items.get(i).toString());
        }
    }

    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter(this.filePath, false);

            for (Task task : items) {
                System.out.println(task);
                writer.write(task + System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void instruction(String command) throws NicholasException{
        if (command.equals(LIST)) getList();
        else if (command.startsWith(MARK)) {
            markTaskAsDone(command);
            saveToFile();
        }
        else if (command.startsWith(UNMARK)){
            markTaskAsUndone(command);
            saveToFile();
        }
        else if (command.startsWith(TODO)){
            addItem(createToDoTask(command));
            saveToFile();
        }
        else if (command.startsWith(DEADLINE)){
            try {
                addItem(createDeadlineTask(command));
                saveToFile();
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Please enter a deadline. e.g. /by Sunday");
            }
        }
        else if (command.startsWith(EVENT)){
            try {
                addItem(createEventTask(command));
                saveToFile();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter a valid start and end time e.g. /from Mon 2pm /to 4pm");
            }
        }
        else if (command.startsWith(DELETE)){
            deleteTask(command);
            saveToFile();
        }
        else throw new NicholasException("Please enter a valid task (todo, deadline, event)");
    }

    public ToDoTask createToDoTask(String command){
        String description = command.replace(TODO, "");
        return new ToDoTask(description);
    }
    public EventTask createEventTask(String command){
        String description = command.replace(EVENT, "");
        String[] task = description.split("/from ");
        String[] time = task[1].split("/to ");
        return new EventTask(task[0],validateDate(time[0]), validateDate(time[1]));
    }

    public DeadlineTask createDeadlineTask(String command){
        String description = command.replace(DEADLINE, "");
        String[] temp = description.split("/by ");
        return new DeadlineTask(validateDate(temp[1]), temp[0]);
    }

    public void markTaskAsDone(String command) throws NicholasException{
        try{
            int idx = Integer.parseInt(command.split(" ")[1]);
            validateIndex(idx, "marking");
            items.get(idx - 1).markAsDone();
        } catch (NumberFormatException e) {
            System.out.println("Invalid number! Please enter in a valid number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please indicate the task number to mark.");
        }
    }

    public void markTaskAsUndone(String command) throws NicholasException{
        try {
            int idx = Integer.parseInt(command.split(" ")[1]);
            validateIndex(idx, "marking");
            items.get(idx - 1).markAsUndone();
        } catch (NumberFormatException e) {
            System.out.println("Invalid number! Please enter in a valid number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please indicate the task number to unmark.");
        }
    }

    public void deleteTask(String command) throws NicholasException{
        try {
            int idx = Integer.parseInt(command.split( " ")[1]);
            validateIndex(idx, "deleting");
            System.out.println("Noted. I've removed this task:");
            System.out.println(items.get(idx - 1));
            items.remove(idx - 1);
            System.out.println("Now you have " + items.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Please indicate the task number to delete");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number! Pleas eenter in a valid number");
        }

    }

    public void validateIndex(int idx, String action) throws NicholasException{
        if (items.isEmpty()) {
            throw new NicholasException("Please add tasks before " + action);
        }
        if (idx < 1 || idx > items.size()) {
            throw new NicholasException("Please enter a valid index from 1 to " + items.size());
        }
    }

    public LocalDate validateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDate dateTime = LocalDate.parse(date, formatter);
        return dateTime;
    }
}
