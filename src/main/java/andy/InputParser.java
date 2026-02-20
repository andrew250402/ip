package andy;

/**
 * The InputParser class interprets user input and determines
 * what command the user wants to execute.
 * <p>
 * It also converts valid task-creation commands into Task objects.
 */
public class InputParser {
    protected String input;

    /**
     * Creates an InputParser using the given user input.
     *
     * @param input The raw input entered by the user.
     */
    public InputParser(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        this.input = input.trim();
    }

    public boolean isBye() {
        return this.input.equals("bye");
    }

    public boolean isList() {
        return this.input.equals("list");
    }

    public boolean isMark() {
        return this.input.startsWith("mark ");
    }

    public boolean isUnmark() {
        return this.input.startsWith("unmark ");
    }

    public boolean isDelete() {
        return this.input.startsWith("delete ");
    }

    public boolean isFind() {
        return this.input.startsWith("find ");
    }

    public String getArgument() {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Command requires an argument");
        }
        return parts[1].trim();
    }

    /**
     * Extracts the task index from commands such as mark, unmark, or delete.
     */
    public int getIndex() {
        String argument = this.getArgument();
        if (!argument.matches("\\d+")) {
            throw new IllegalArgumentException("Task index should be a number");
        }
        return Integer.parseInt(argument);
    }

    /**
     * Creates a Task object based on the user's input.
     */
    public Task getTask() {
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        switch (command) {
        case "todo":
            return handleTodo(parts);

        case "deadline":
            return handleDeadline(parts);

        case "event":
            return handleEvent(parts);

        default:
            throw new IllegalArgumentException(
                    "Start with 'todo', 'deadline' or 'event' to add task.\n\t"
                    + "Use 'list' to view your tasks.\n\t"
                    + "Use 'bye' to stop Andy.");
        }
    }

    /**
     * Handles creation of Todo tasks.
     */
    private Task handleTodo(String[] parts) {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Description is not found. Please add a description for your new To do");
        }

        String description = parts[1].trim();
        return new Todo(description);
    }

    /**
     * Handles creation of Deadline tasks.
     */
    private Task handleDeadline(String[] parts) {
        if (parts.length < 2) {
            throw new IllegalArgumentException(
                    "Please create a new Deadline using this format: deadline [description] /by [yyyy-mm-dd]");
        }

        String body = parts[1];
        int byIndex = body.indexOf("/by");

        if (byIndex == -1) {
            throw new IllegalArgumentException(
                    "Deadline must contain /by. Format: deadline [description] /by [yyyy-mm-dd]");
        }

        String description = body.substring(0, byIndex).trim();
        String by = body.substring(byIndex + 3).trim();

        if (description.isEmpty()) {
            throw new IllegalArgumentException(
                    "Description is not found. Please add a description for your Deadline");
        }

        if (by.isEmpty()) {
            throw new IllegalArgumentException(
                    "Deadline is not found. Please add a deadline to your Deadline");
        }

        return new Deadline(description, by);
    }

    /**
     * Handles creation of Event tasks.
     */
    private Task handleEvent(String[] parts) {
        if (parts.length < 2) {
            throw new IllegalArgumentException(
                    "Please create a new Event using this format: event [description] /from [start] /to [end]");
        }

        String body = parts[1];

        int fromIndex = body.indexOf("/from");
        int toIndex = body.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
            throw new IllegalArgumentException(
                    "Event must follow format: event [description] /from [start] /to [end]");
        }

        String description = body.substring(0, fromIndex).trim();
        String from = body.substring(fromIndex + 5, toIndex).trim();
        String to = body.substring(toIndex + 3).trim();

        if (description.isEmpty()) {
            throw new IllegalArgumentException(
                    "Description is not found. Please add a description for your Event");
        }

        if (from.isEmpty()) {
            throw new IllegalArgumentException(
                    "Start time is not found. Please add a start time to your Event");
        }

        if (to.isEmpty()) {
            throw new IllegalArgumentException(
                    "End time is not found. Please add an end time to your Event");
        }

        return new Event(description, from, to);
    }
}