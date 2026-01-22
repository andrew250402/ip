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
                System.out.println(horizontal 
                    + formatResponse("added: " + input)
                    + horizontal);
                array.add(new Task(input)); 
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
        String result = "\n";
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
}
