import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class Andy {
    public static void main(String[] args) {
        String horizontal = "_________________________________________________________\n";
        System.out.println(horizontal 
            + "Hello! I'm Andy\n" 
            + "What can I do for you?\n" 
            + horizontal);
        Scanner scanner = new Scanner(System.in);

        FileParser parser = new FileParser("../../../data/task.txt");
        TaskList array = new TaskList(parser.readFile());

        String input = scanner.nextLine();
        InputParser parsedInput = new InputParser(input);


        while (!parsedInput.isBye()) {
            if (parsedInput.isList()) {
                System.out.println(horizontal 
                    + formatList(array)
                    + horizontal);     
            } else if (parsedInput.isMark()) {
                int index = parsedInput.getIndex();
                array = array.change(index, true);
                System.out.println(horizontal
                    + formatResponse("Nice! I've marked this task as done:\n\t  " + array.get(index))
                    + horizontal);
            } else if (parsedInput.isUnmark()) {
                int index = parsedInput.getIndex();
                array = array.change(index, false);
                System.out.println(horizontal
                    + formatResponse("Ok, I've marked this task as not done yet:\n\t  " + array.get(index))
                    + horizontal);                
            } else if (parsedInput.isDelete()) {
                int index = parsedInput.getIndex();
                Task removed = array.remove(index);
                System.out.println(horizontal
                    + formatResponse("Noted, I've removed this task:\n\t  "
                        + removed
                        + "\n\tNow you have "
                        + array.size()
                        + " task"
                        + (array.size() == 1 ? "" : "s")
                        + " in the list."
                    )
                    + horizontal
                );

            } else {
                try {
                    Task newTask = parsedInput.getTask();
                    array = array.add(newTask);
                    System.out.println(horizontal 
                        + formatResponse("Got it. I've added this task: \n\t  " 
                            + newTask
                            + "\n\tNow you have "
                            + array.size()
                            + " task"
                            + (array.size() == 1 ? "" : "s")
                            + " in the list."
                        )
                        + horizontal);
                } catch (IllegalArgumentException e) {
                    System.out.println(horizontal
                        + formatResponse(e.getMessage())
                        + horizontal
                    );
                } catch (DateTimeParseException e1) {
                    System.out.println(horizontal
                        + formatResponse("Please use a valid format after '/by' like yyyy-mm-dd")
                        + horizontal
                    ); 
                }

 
            }
            input = scanner.nextLine();
            parsedInput = new InputParser(input);
        }

        System.out.println(horizontal
            + formatResponse("Bye. Hope to see you again soon!")
            + horizontal);

        parser.writeFile(array);
        scanner.close();
    }

    static String formatResponse(String response) {
        return "\n" 
        + "\t" 
        + response 
        + "\n";
    }

    static String formatList(TaskList array) {
        String result = "\n\tHere are the tasks in your list:\n";
        return result + array.listTasks();
    }
}
