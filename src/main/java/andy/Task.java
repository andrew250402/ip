package andy;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task markDone() {
        return new Task(this.description, true);
    }

    public Task unmarkDone() {
        return new Task(this.description, false);
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }

    public String getString() {
        return (isDone ? "1|" : "0|") + description;
    }

    public String toString() {
        return "["
        + this.getStatusIcon()
        + "] "
        + description;
    }
}
