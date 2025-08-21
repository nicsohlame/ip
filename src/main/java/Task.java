import java.util.ArrayList;
import java.util.List;

public class Task {
    private final List<String> items;
    private static final String LIST = "list";

    public Task() {
        this.items = new ArrayList<String>();
    }

    public void addItem(String task){
        items.add(task);
        System.out.println("added: " + task);
    }

    public void getList() {
        for(int i = 0; i < items.size(); i++) {
            System.out.println(i + 1 + ". " + items.get(i));
        }
    }

    public void instruction(String command) {
        switch(command) {
            case LIST:
                getList();
                break;
            default:
                addItem(command);
        }
    }


}
