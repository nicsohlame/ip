package tasks;

import exception.NicholasException;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList handles storing of multiple tasks
 * <p>
 * Acts like a List class but has other methods unique for the nicholas chatbot application.
 */
public class TaskList {
    private final List<Task> items;

    public TaskList() {
        this.items = new ArrayList<Task>();
    }

    public Task get(int idx) {
        return items.get(idx);
    }

    public int size() {
        return items.size();
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

    public void markTaskAsDone(int idx) throws NicholasException {
        validateIndex(idx, "marking");
        items.get(idx - 1).markAsDone();
    }

    public void markTaskAsUndone(int idx) throws NicholasException {
        validateIndex(idx, "marking");
        items.get(idx - 1).markAsUndone();
    }

    public void deleteTask(int idx) throws NicholasException {
        validateIndex(idx, "deleting");
        System.out.println("Noted. I've removed this task:");
        System.out.println(items.get(idx - 1));
        items.remove(idx - 1);
        System.out.println("Now you have " + items.size() + " tasks in the list.");
    }

    public void validateIndex(int idx, String action) throws NicholasException {
        if (items.isEmpty()) {
            throw new NicholasException("Please add tasks before " + action);
        }
        if (idx < 1 || idx > items.size()) {
            throw new NicholasException("Please enter a valid index from 1 to " + items.size());
        }
    }

    @Override
    public String toString() {
        String tasksDisplay = "";
        for (Task item : items) {
            tasksDisplay = tasksDisplay + item.toString() + "\n";
        }
        return tasksDisplay;
    }
}
