public class DeadlineTask extends Task{
    private String dueDate;

    public DeadlineTask(String dueDate, String description) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
