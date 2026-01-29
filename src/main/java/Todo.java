public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public Todo markDone() {
        return new Todo(this.description, true);
    }

    public Todo unmarkDone() {
        return new Todo(this.description, false);
    }

    public String getString() {
        return "T|" + super.getString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
