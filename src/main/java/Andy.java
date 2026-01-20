import java.util.Scanner;

public class Andy {
    public static void main(String[] args) {
        String horizontal = "_________________________________________________________\n";
        System.out.println(horizontal 
            + "Hello! I'm Andy\n" 
            + "What can I do for you?\n" 
            + horizontal);
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while(!input.equals("bye")) {
            System.out.println(horizontal 
                + formatResponse(input)
                + horizontal);
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
}
