package andy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific deadline date.
 *
 * <p>A Deadline extends the Task class by adding a due date (`by`) and a
 * human-readable formatted version of the date (`formattedBy`). The date
 * can be provided either as a String (ISO format: yyyy-MM-dd) or as a
 * LocalDate object.</p>
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
 * D | isDone | description | yyyy-MM-dd
 * </p>
 *
 * @author Your Name
 * @version 1.0
 */
public class Deadline extends Task {

    /** The deadline date of the task. */
    protected LocalDate by;

    /** Human-readable formatted version of the deadline date. */
    protected String formattedBy;

    /**
     * Constructs a Deadline task with a description and date string.
     * The task is initially marked as not done.
     *
     * @param description Description of the task
     * @param by Deadline date in ISO format (yyyy-MM-dd)
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
        this.formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Constructs a Deadline task with a description, date string,
     * and completion status.
     *
     * @param description Description of the task
     * @param by Deadline date in ISO format (yyyy-MM-dd)
     * @param isDone Whether the task is completed
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDate.parse(by);
        this.formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Constructs a Deadline task with a description and LocalDate.
     * The task is initially marked as not done.
     *
     * @param description Description of the task
     * @param by Deadline date as a LocalDate
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Constructs a Deadline task with a description, LocalDate,
     * and completion status.
     *
     * @param description Description of the task
     * @param by Deadline date as a LocalDate
     * @param isDone Whether the task is completed
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a new Deadline marked as completed.
     *
     * @return A new Deadline instance with completed status set to true
     */
    public Deadline markDone() {
        return new Deadline(this.description, this.by, true);
    }

    /**
     * Returns a new Deadline marked as not completed.
     *
     * @return A new Deadline instance with completed status set to false
     */
    public Deadline unmarkDone() {
        return new Deadline(this.description, this.by, false);
    }

    /**
     * Returns a string representation of the task formatted for storage.
     *
     * <p>Format: D | isDone | description | yyyy-MM-dd</p>
     *
     * @return Storage-friendly string representation
     */
    public String getString() {
        return "D|" + super.getString() + "|" + by;
    }

    /**
     * Returns a user-friendly string representation of the deadline task.
     *
     * <p>Example: [D][X] Submit report (by: Jan 1 2025)</p>
     *
     * @return Readable string for display
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}