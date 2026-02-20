package andy;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public Event markDone() {
        return new Event(this.description, this.from, this.to, true);
    }

    public Event unmarkDone() {
        return new Event(this.description, this.from, this.to, false);
    }

    public String getString() {
        return "E|" + super.getString() + "|" + from + "|" + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: "+ to +")";
    }
}

