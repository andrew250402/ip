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

    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }

    public String toString() {
        return "["
        + this.getStatusIcon()
        + "] "
        + description;
    }
}
