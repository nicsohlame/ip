import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> items;
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";

    public TaskList() {
        this.items = new ArrayList<Task>();
    }

    public void addItem(Task task){
        items.add(task);
        System.out.println("added: " + task);
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
        else addItem(new Task(command));
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
