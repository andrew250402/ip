package andy;

public class Ui {
    private final static String HORIZONTAL = "";
    public Ui() {
    }

    public String start() {
        String result = (HORIZONTAL
            + "Hello! I'm Andy\n" 
            + "What can I do for you?\n" 
            + HORIZONTAL);
        return result;
    }

    public String listTasks(TaskList array) {
        String result = (HORIZONTAL
            + formatList(array)
            + HORIZONTAL);  
        return result;
    }

    public String mark(TaskList array, int index) {
        String result = (HORIZONTAL
            + formatResponse("Nice! I've marked this task as done:\n\t  " + array.get(index))
            + HORIZONTAL);
        return result;
    }

    public String unmark(TaskList array, int index) {
        String result = (HORIZONTAL
            + formatResponse("Ok, I've marked this task as not done yet:\n\t  " + array.get(index))
            + HORIZONTAL);
        return result;
    }

    public String remove(TaskList array, Task removed) {
        String result = (HORIZONTAL
            + formatResponse("Noted, I've removed this task:\n\t  "
                + removed
                + "\n\tNow you have "
                + array.size()
                + " task"
                + (array.size() == 1 ? "" : "s")
                + " in the list."
            )
            + HORIZONTAL
        );
        return result;
    }

    public String add(TaskList array, Task newTask) {
        String result = (HORIZONTAL 
            + formatResponse("Got it. I've added this task: \n\t  " 
                + newTask
                + "\n\tNow you have "
                + array.size()
                + " task"
                + (array.size() == 1 ? "" : "s")
                + " in the list."
            )
            + HORIZONTAL);
        return result;
    }

    public String error(Exception e) {
        String result = (HORIZONTAL
            + formatResponse(e.getMessage())
            + HORIZONTAL
        );
        return result;
    }

    public String custom(String message) {
        String result = (HORIZONTAL
            + formatResponse(message)
            + HORIZONTAL
        ); 
        return result;
    }

    public String bye() {
        String result = (HORIZONTAL
            + formatResponse("Bye. Hope to see you again soon!")
            + HORIZONTAL);
        return result;
    }

    public String listFind(TaskList array) {
        String result = (HORIZONTAL
            + formatFind(array)
            + HORIZONTAL);  
        return result;
    }
    private String formatResponse(String response) {
        return "\t" 
        + response 
        + "\n";
    }

    private String formatList(TaskList array) {
        if (array.size() == 0) {
            return "\tYou have no tasks in your list.";
        } else {
            String result = "\tHere are the tasks in your list:\n";
            return result + array.listTasks();
        }
    }

    private String formatFind(TaskList array) {
        if (array.size() == 0) {
            return "\tNo matching tasks found in your list.";
        } else {
            String result = "\tHere are the matching tasks in your list:\n";
            return result + array.listTasks();
        }
    }
}
