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
        assert input != null : "Input should never be null";
        this.input = input;
    }

    public boolean isBye() {
        assert input != null;
        return this.input.equals("bye");
    }

    public boolean isList() {
        assert input != null;
        return this.input.equals("list");
    }

    public boolean isMark() {
        assert input != null;
        return this.input.split(" ")[0].equals("mark");      
    }

    public boolean isUnmark() {
        assert input != null;
        return this.input.split(" ")[0].equals("unmark");      
    }

    public boolean isDelete() {
        assert input != null;
        return this.input.split(" ")[0].equals("delete");      
    }

    public boolean isFind() {
        assert input != null;
        return this.input.split(" ")[0].equals("find");
    }

    public String getArgument() {
        String[] parts = input.split(" ");
        assert parts.length > 1 : "Command expected to have an argument";
        return parts[1];
    }

    /**
     * Extracts the task index from commands such as mark, unmark, or delete.
     *
     * @return The task index provided by the user.
     */
    public int getIndex() {
        String argument = this.getArgument();
        assert argument.matches("\\d+") : "Task index should be numeric";
        return Integer.parseInt(argument);
    }

    /**
     * Creates a Task object based on the user's input.
     */
    public Task getTask() {
        assert input != null : "Input must exist before parsing task";

        Task task;
        String[] inputs = input.split(" ");
        assert inputs.length > 0 : "Input should contain at least a command keyword";

        String first = inputs[0];
        int firstSpace = input.indexOf(" ");

        if (first.equals("todo")) {

            assert firstSpace != -1 : "Todo command must contain description";

            String description = input.substring(firstSpace + 1);
            assert description != null;

            if (description.length() == 0) {
                throw new IllegalArgumentException(
                    "Description is not found. Please add a description for your new To do");
            }

            task = new Todo(description);

        } else if (first.equals("deadline")) {

            int byIndex = input.indexOf("/by");
            assert byIndex != -1 : "Deadline must contain /by marker";

            if (firstSpace == -1 | byIndex == -1) {
                throw new IllegalArgumentException(
                    "Please create a new Deadline using this format: deadline [description] /by [yyyy-mm-dd]");
            } 

            String description = input.substring(firstSpace + 1, byIndex);
            String by = input.substring(byIndex + 4);

            assert description != null;
            assert by != null;

            if (description.length() == 0) {
                throw new IllegalArgumentException(
                    "Description is not found. Please add a description for your Deadline");
            } else if (by.length() == 0) {
                throw new IllegalArgumentException(
                    "Deadline is not found. Please add a deadline to your Deadline");
            }

            task = new Deadline(description, by);

        } else if (first.equals("event")) {

            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");

            assert fromIndex != -1 : "Event must contain /from marker";
            assert toIndex != -1 : "Event must contain /to marker";
            assert fromIndex < toIndex : "/from must appear before /to";

            if (firstSpace == -1 | fromIndex == -1 | toIndex == -1) {
                throw new IllegalArgumentException(
                    "Please create a new Event using this format: event [description] /from [a start time] /to [an end time]");
            } 

            String description = input.substring(firstSpace + 1, fromIndex);
            String from = input.substring(fromIndex + 5, toIndex);
            String to = input.substring(toIndex + 4);

            assert description != null;
            assert from != null;
            assert to != null;

            if (description.length() == 0) {
                throw new IllegalArgumentException(
                    "Description is not found. Please add a description for your Event");
            } else if (from.length() == 0) {
                throw new IllegalArgumentException(
                    "Start time is not found. Please add a start time to your Event");
            } else if (to.length() == 0) {
                throw new IllegalArgumentException(
                    "End time is not found. Please add an end time to your Event");
            }

            task = new Event(description, from, to);

        } else {
            throw new IllegalArgumentException(
                "Start with 'todo', 'deadline' or 'event' to add task.\n\t"
                + "Use 'list' to view your tasks.\n\t" 
                + "Use 'bye' to stop Andy.");
        }
        
        assert task != null : "Task should be created before returning";
        return task;      
    }
}
