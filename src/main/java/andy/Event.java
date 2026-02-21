package andy;

/**
 * Represents a task that occurs over a specific time period.
 *
 * <p>An Event extends the Task class by adding a start time (`from`)
 * and an end time (`to`). Unlike Deadline, which has a single due date,
 * Event represents a duration.</p>
 *
 * <p>This class supports:
 * <ul>
 *     <li>Marking and unmarking completion</li>
 *     <li>Generating a storage-friendly string representation</li>
 *     <li>Producing a user-friendly display string</li>
 * </ul>
 * </p>
 *
 * <p><b>Storage format:</b><br>
 * E | isDone | description | from | to
 * </p>
 *
 * @author Your Name
 * @version 1.0
 */
public class Event extends Task {

    /** Start time or starting description of the event. */
    protected String from;

    /** End time or ending description of the event. */
    protected String to;

    /**
     * Constructs an Event task with description and time period.
     * The task is initially marked as not done.
     *
     * @param description Description of the event
     * @param from Start time or start description
     * @param to End time or end description
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task with description, time period,
     * and completion status.
     *
     * @param description Description of the event
     * @param from Start time or start description
     * @param to End time or end description
     * @param isDone Whether the task is completed
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a new Event marked as completed.
     *
     * @return A new Event instance with completed status set to true
     */
    public Event markDone() {
        return new Event(this.description, this.from, this.to, true);
    }

    /**
     * Returns a new Event marked as not completed.
     *
     * @return A new Event instance with completed status set to false
     */
    public Event unmarkDone() {
        return new Event(this.description, this.from, this.to, false);
    }

    /**
     * Returns a string representation of the task formatted for storage.
     *
     * <p>Format: E | isDone | description | from | to</p>
     *
     * @return Storage-friendly string representation
     */
    public String getString() {
        return "E|" + super.getString() + "|" + from + "|" + to;
    }

    /**
     * Returns a user-friendly string representation of the event.
     *
     * <p>Example: [E][ ] Team meeting (from: 2pm to: 4pm)</p>
     *
     * @return Readable string for display
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}