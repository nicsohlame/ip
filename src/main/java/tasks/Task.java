package tasks;

/**
 * Default task class
 * <p>
 * This class is the default task class which contains all the methods that the different
 * tasks have in common. It is an abstract class which all tasks inherits from.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }



    public String markAsDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done: \n" + this;
    }

    public String markAsUndone() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet: \n" + this;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "]" + description;
    }
}
