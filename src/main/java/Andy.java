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
        ArrayList<String> array = new ArrayList<String>();

        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(horizontal 
                    + formatList(array)
                    + horizontal);     
            } else {
                System.out.println(horizontal 
                    + formatResponse("added: " + input)
                    + horizontal);
                array.add(input); 
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

    static String formatList(ArrayList<String> list) {
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
}
