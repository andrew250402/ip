package andy;

/**
 * Represents a Todo task in the task manager.
 * <p>
 * A Todo is a basic task that only contains a description and a completion status.
 * Unlike deadlines or events, it does not store any date or time information.
 * </p>
 *
 * <p>
 * Todo objects are treated as immutable when their completion status changes.
 * Calling {@code markDone()} or {@code unmarkDone()} returns a new Todo instance
 * instead of modifying the current one.
 * </p>
 */
public class Todo extends Task {

    /**
     * Creates a Todo task with the given description.
     * The task is initially not marked as done.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a Todo task with the given description and completion status.
     *
     * @param description The description of the todo task.
     * @param isDone Whether the task is completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a new Todo marked as completed.
     *
     * @return A new Todo instance with {@code isDone = true}.
     */
    public Todo markDone() {
        return new Todo(this.description, true);
    }

    /**
     * Returns a new Todo marked as not completed.
     *
     * @return A new Todo instance with {@code isDone = false}.
     */
    public Todo unmarkDone() {
        return new Todo(this.description, false);
    }

    /**
     * Returns the file storage representation of this Todo.
     * <p>
     * Format: {@code T|<task data>}
     *
     * @return A string suitable for saving to the data file.
     */
    public String getString() {
        return "T|" + super.getString();
    }

    /**
     * Returns the user-friendly string representation of the Todo.
     * <p>
     * Format: {@code [T]<task info>}
     *
     * @return A formatted string representing the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}