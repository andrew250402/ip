import java.util.Scanner;
import java.util.ArrayList;

public class Andy {
    public static void main(String[] args) {
        String horizontal = "_________________________________________________________\n";
        System.out.println(horizontal 
            + "Hello! I'm Andy\n" 
            + "What can I do for you?\n" 
            + horizontal);
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> array = new ArrayList<Task>();

        String input = scanner.nextLine();


        while (!input.equals("bye")) {
            String[] inputs = input.split(" ");
            if (input.equals("list")) {
                System.out.println(horizontal 
                    + formatList(array)
                    + horizontal);     
            } else if (inputs[0].equals("mark")) {
                int index = Integer.parseInt(inputs[1]);
                array = change(array, index, true);
                System.out.println(horizontal
                    + formatResponse("Nice! I've marked this task as done:\n\t  " + array.get(index - 1))
                    + horizontal);
            } else if (inputs[0].equals("unmark")) {
                int index = Integer.parseInt(inputs[1]);
                array = change(array, index, false);
                System.out.println(horizontal
                    + formatResponse("Ok, I've marked this task as not done yet:\n\t  " + array.get(index - 1))
                    + horizontal);                
            } else {
                try {
                    Task newTask = parseInput(input);
                    array.add(newTask);
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
                }

 
            }
            input = scanner.nextLine();
        }

        System.out.println(horizontal
            + formatResponse("Bye. Hope to see you again soon!")
            + horizontal);


        scanner.close();
    }

    static String formatResponse(String response) {
        return "\n" 
        + "\t" 
        + response 
        + "\n";
    }

    static String formatList(ArrayList<Task> list) {
        int size = list.size();
        String result = "\n\tHere are the tasks in your list:\n";
        for (int i=1; i <= size; i ++) {
            result = result
            + "\t"
            + i
            + ". " 
            + list.get(i-1)
            + "\n";
        }
        return result;
    }

    static ArrayList<Task> change(ArrayList<Task> array, int index, boolean bool) {
        ArrayList<Task> result = new ArrayList<Task>();
        int size = array.size();
        for (int i=0; i < size; i ++) {
            if (i == index - 1) {
                if (bool) {
                    result.add(array.get(i).markDone());
                } else {
                    result.add(array.get(i).unmarkDone());
                }
            } else {
                result.add(array.get(i));
            }
        }
        return result;
    }

    static Task parseInput(String input) {
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
                throw new IllegalArgumentException("Please create a new Deadline using this format: deadline [description] /by [a deadline]");
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
