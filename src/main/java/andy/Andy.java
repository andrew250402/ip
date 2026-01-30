package andy;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class Andy {
    public static void main(String[] args) {

        Ui ui = new Ui();
        ui.start();
        Scanner scanner = new Scanner(System.in);
        FileParser parser = new FileParser("data/task.txt");
        TaskList array = new TaskList(parser.readFile());
        String input = scanner.nextLine();
        InputParser parsedInput = new InputParser(input);

        while (!parsedInput.isBye()) {
            if (parsedInput.isList()) {
                ui.listTasks(array);    
            } else if (parsedInput.isMark()) {
                int index = parsedInput.getIndex();
                array = array.change(index, true);
                ui.mark(array, index);
            } else if (parsedInput.isUnmark()) {
                int index = parsedInput.getIndex();
                array = array.change(index, false);
                ui.unmark(array, index);              
            } else if (parsedInput.isDelete()) {
                int index = parsedInput.getIndex();
                Task removed = array.remove(index);
                ui.remove(array, removed);
            } else {
                try {
                    Task newTask = parsedInput.getTask();
                    array = array.add(newTask);
                    ui.add(array, newTask);
                } catch (IllegalArgumentException e) {
                    ui.error(e);
                } catch (DateTimeParseException e1) {
                    ui.custom("Please use a valid format after '/by' like yyyy-mm-dd");
                }
            }
            input = scanner.nextLine();
            parsedInput = new InputParser(input);
        }
        ui.bye();
        parser.writeFile(array);
        scanner.close();
    }
}
