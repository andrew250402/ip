package andy;
public class Ui {
    private final static String HORIZONTAL = "_________________________________________________________\n";
    public Ui() {
    }

    public void start() {
        System.out.println(HORIZONTAL
            + "Hello! I'm Andy\n" 
            + "What can I do for you?\n" 
            + HORIZONTAL);
    }

    public void listTasks(TaskList array) {
        System.out.println(HORIZONTAL
            + formatList(array)
            + HORIZONTAL);  
    }

    public void mark(TaskList array, int index) {
        System.out.println(HORIZONTAL
            + formatResponse("Nice! I've marked this task as done:\n\t  " + array.get(index))
            + HORIZONTAL);
    }

    public void unmark(TaskList array, int index) {
        System.out.println(HORIZONTAL
            + formatResponse("Ok, I've marked this task as not done yet:\n\t  " + array.get(index))
            + HORIZONTAL);
    }

    public void remove(TaskList array, Task removed) {
        System.out.println(HORIZONTAL
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
    }

    public void add(TaskList array, Task newTask) {
        System.out.println(HORIZONTAL 
            + formatResponse("Got it. I've added this task: \n\t  " 
                + newTask
                + "\n\tNow you have "
                + array.size()
                + " task"
                + (array.size() == 1 ? "" : "s")
                + " in the list."
            )
            + HORIZONTAL);
    }

    public void error(Exception e) {
        System.out.println(HORIZONTAL
            + formatResponse(e.getMessage())
            + HORIZONTAL
        );
    }

    public void custom(String message) {
        System.out.println(HORIZONTAL
            + formatResponse(message)
            + HORIZONTAL
        ); 
    }

    public void bye() {
        System.out.println(HORIZONTAL
            + formatResponse("Bye. Hope to see you again soon!")
            + HORIZONTAL);
    }


    private String formatResponse(String response) {
        return "\n" 
        + "\t" 
        + response 
        + "\n";
    }

    private String formatList(TaskList array) {
        String result = "\n\tHere are the tasks in your list:\n";
        return result + array.listTasks();
    }
}
