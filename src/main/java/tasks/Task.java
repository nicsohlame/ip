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
    private String tag;


    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        String trueStatusIcon = "X";
        String falseStatusIcon = " ";
        return (this.isDone ? trueStatusIcon : falseStatusIcon);
    }

    public String tagTask(String tag) {
        this.tag = tag;
        String returnMessage = this + " has been tagged " + this.tag;

        return returnMessage;
    }



    public String markAsDone() {
        String markAsDoneDisplay = "Nice! I've marked this task as done: \n" + this;

        this.isDone = true;
        return markAsDoneDisplay;
    }

    public String markAsUndone() {
        String markAsUndoneDisplay = "OK, I've marked this task as not done yet: \n" + this;

        this.isDone = false;
        return markAsUndoneDisplay;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "]" + description;
    }
}
