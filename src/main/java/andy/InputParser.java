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
        this.input = input;
    }

    /**
     * Checks whether the input matches a specific command.
     *
     * @return true if the input matches the command, false otherwise.
     */
    public boolean isBye() {
        return this.input.equals("bye");
    }

    public boolean isList() {
        return this.input.equals("list");
    }

    public boolean isMark() {
        return this.input.split(" ")[0].equals("mark");      
    }

    public boolean isUnmark() {
        return this.input.split(" ")[0].equals("unmark");      
    }

    public boolean isDelete() {
        return this.input.split(" ")[0].equals("delete");      
    }

    /**
     * Extracts the task index from commands such as mark, unmark, or delete.
     *
     * @return The task index provided by the user.
     */
    public int getIndex() {
        return Integer.parseInt(input.split(" ")[1]);
    }

    /**
     * Creates a Task object based on the user's input.
     * <p>
     * Supported task types include todo, deadline, and event.
     *
     * @return A Task created from the user input.
     * @throws IllegalArgumentException If the input format is invalid.
     */
    public Task getTask() {
        Task task;
        String[] inputs = input.split(" ");
        String first = inputs[0];
        int firstSpace = input.indexOf(" ");
        if (first.equals("todo")) {
            String description = input.substring(firstSpace + 1);
            if (firstSpace == -1) {
                throw new IllegalArgumentException("Please create a new To do using this format: todo [description]");
            } else if (description.length() == 0) {
                throw new IllegalArgumentException("Description is not found. Please add a description for your new To do");
            }
            task = new Todo(description);
        } else if (first.equals("deadline")) {
            int byIndex = input.indexOf("/by");

            if (firstSpace == -1 | byIndex == -1) {
                throw new IllegalArgumentException("Please create a new Deadline using this format: deadline [description] /by [yyyy-mm-dd]");
            } 

            String description = input.substring(firstSpace + 1, byIndex);
            String by = input.substring(byIndex + 4);

            if (description.length() == 0) {
                throw new IllegalArgumentException("Description is not found. Please add a description for your Deadline");
            } else if (by.length() == 0) {
                throw new IllegalArgumentException("Deadline is not found. Please add a deadline to your Deadline");
            }
            task = new Deadline(description, by);
        } else if (first.equals("event")) {
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");

            if (firstSpace == -1 | fromIndex == -1 | toIndex == -1) {
                throw new IllegalArgumentException("Please create a new Event using this format: event [description] /from [a start time] /to [an end time]");
            } 

            String description = input.substring(firstSpace + 1, fromIndex);
            String from = input.substring(fromIndex + 5, toIndex);
            String to = input.substring(toIndex + 4);
            
            if (description.length() == 0) {
                throw new IllegalArgumentException("Description is not found. Please add a description for your Event");
            } else if (from.length() == 0) {
                throw new IllegalArgumentException("Start time is not found. Please add a start time to your Event");
            } else if (to.length() == 0) {
                throw new IllegalArgumentException("End time is not found. Please add an end time to your Event");
            }
            task = new Event(description, from, to);
        } else {
            throw new IllegalArgumentException("Start with 'todo', 'deadline' or 'event' to add task.\n\t"
            + "Use 'list' to view your tasks.\n\t" 
            + "Use 'bye' to stop Andy.");
        }
        return task;      
    }
}
