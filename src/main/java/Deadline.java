public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline markDone() {
        return new Deadline(this.description, this.by, true);
    }

    public Deadline unmarkDone() {
        return new Deadline(this.description, this.by, false);
    }

    public String getString() {
        return "D|" + super.getString() + "|" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
