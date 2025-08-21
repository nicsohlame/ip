import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> items;
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo ";
    private static final String DEADLINE = "deadline ";
    private static final String EVENT = "event ";

    public TaskList() {
        this.items = new ArrayList<Task>();
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

    public void instruction(String command) {
        if (command.equals(LIST)) getList();
        else if (command.startsWith(MARK)) {markTaskAsDone(command);}
        else if (command.startsWith(UNMARK)){markTaskAsUndone(command);}
        else if (command.startsWith(TODO)){addItem(createToDoTask(command));}
        else if (command.startsWith(DEADLINE)){
            addItem(createDeadlineTask(command));
        }
        else if (command.startsWith(EVENT)){
            addItem(createEventTask(command));
        }
        else addItem(new Task(command));
    }

    public ToDoTask createToDoTask(String command){
        String description = command.replace(TODO, "");
        return new ToDoTask(description);
    }
    public EventTask createEventTask(String command){
        String description = command.replace(EVENT, "");
        String[] task = description.split(" /from ");
        String[] time = task[1].split(" /to ");
        return new EventTask(task[0],time[0], time[1]);
    }

    public DeadlineTask createDeadlineTask(String command){
        String description = command.replace(DEADLINE, "");
        String[] temp = description.split(" /by ");
        return new DeadlineTask(temp[1], temp[0]);
    }

    public void markTaskAsDone(String command) {
        int idx = Integer.parseInt(command.split(" ")[1]);
        items.get(idx - 1).markAsDone();
    }

    public void markTaskAsUndone(String command) {
        int idx = Integer.parseInt(command.split(" ")[1]);
        items.get(idx - 1).markAsUndone();
    }


}
