package andy;

import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class Andy {
    protected Ui ui;
    protected TaskList array;
    protected FileParser parser;

    public Andy() {
        this.ui = new Ui();
        this.parser = new FileParser("data/task.txt");
        this.array = new TaskList(parser.readFile());
    }

    public static void main(String[] args) {
        Andy andy = new Andy();
        andy.run();
    }

    /* =========================
       HIGH LEVEL PROGRAM FLOW
       ========================= */

    private void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ui.start());

        String input = scanner.nextLine();
        InputParser parsedInput = new InputParser(input);

        while (!parsedInput.isBye()) {
            System.out.println(executeCommand(parsedInput));
            input = scanner.nextLine();
            parsedInput = new InputParser(input);
        }

        shutdown(scanner);
    }

    private void shutdown(Scanner scanner) {
        parser.writeFile(array);
        scanner.close();
        System.out.println(ui.bye());
    }

    private String executeCommand(InputParser parsedInput) {
        if (parsedInput.isList()) {
            return ui.listTasks(array);
        }

        if (parsedInput.isMark()) {
            return handleMark(parsedInput);
        }

        if (parsedInput.isUnmark()) {
            return handleUnmark(parsedInput);
        }

        if (parsedInput.isDelete()) {
            return handleDelete(parsedInput);
        }

        if (parsedInput.isFind()) {
            return handleFind(parsedInput);
        }

        return handleAddTask(parsedInput);
    }

    private String handleMark(InputParser input) {
        int index = input.getIndex();
        array = array.change(index, true);
        return ui.mark(array, index);
    }

    private String handleUnmark(InputParser input) {
        int index = input.getIndex();
        array = array.change(index, false);
        return ui.unmark(array, index);
    }

    private String handleDelete(InputParser input) {
        int index = input.getIndex();
        Task removed = array.remove(index);
        return ui.remove(array, removed);
    }

    private String handleFind(InputParser input) {
        String keyword = input.getArgument();
        TaskList result = array.find(keyword);
        return ui.listFind(result);
    }

    private String handleAddTask(InputParser input) {
        try {
            Task newTask = input.getTask();
            array = array.add(newTask);
            return ui.add(array, newTask);
        } catch (IllegalArgumentException e) {
            return ui.error(e);
        } catch (DateTimeParseException e) {
            return ui.custom("Please use a valid format after '/by' like yyyy-mm-dd");
        }
    }

    public String getResponse(String input) {
        InputParser parsedInput = new InputParser(input);

        if (parsedInput.isBye()) {
            parser.writeFile(array);
            return ui.bye();
        }

        return executeCommand(parsedInput);
    }
}
