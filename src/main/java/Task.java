import java.time.LocalDate;

public class Task {
    protected final String description;
    protected final String type;
    protected final boolean isDone;
    protected final LocalDate timestamp;

    protected Task(String description, String type, boolean isDone, LocalDate timestamp) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
        this.timestamp = timestamp;
    }


    public String getDescription() {
        return this.description;
    }

    public String getStatus() {
        return isDone ? "1" : "0";
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public LocalDate getDate() {
        return this.timestamp;
    }

    public Task markDone() {
        return new Task(this.description, this.type, true, this.timestamp);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + getStatusIcon() + "]" +
                " " + this.description;
    }
}
