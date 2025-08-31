package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDate dueDate;

    public DeadlineTask(LocalDate dueDate, String description) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString(){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + "(by: " + dueDate.format(pattern) + ")";
    }
}
