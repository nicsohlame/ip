import java.time.LocalDate;

public class EventTask extends Task{
    private LocalDate startTime;
    private LocalDate endTime;

    public EventTask(String description, LocalDate startTime, LocalDate endTime){
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + startTime + "to:" + endTime + ")";
    }
}
