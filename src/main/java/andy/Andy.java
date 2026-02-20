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
        saveToFile(); // ensure latest state saved
        scanner.close();
        System.out.println(ui.bye());
    }

    /* =========================================================
       AI-ASSISTED ENHANCEMENT
       ---------------------------------------------------------
       ChatGPT helped refactor persistence logic so that:
       - Task list is always reloaded from file before use
       - Task list is always saved after modification
       This ensures the file remains the single source of truth
       and prevents stale in-memory state.
       ========================================================= */

    private void reloadFromFile() {
        this.array = new TaskList(parser.readFile());
    }

    private void saveToFile() {
        parser.writeFile(array);
    }

    private String executeCommand(InputParser parsedInput) {
        reloadFromFile(); // always read latest data before handling

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
        saveToFile(); // persist change
        return ui.mark(array, index);
    }

    private String handleUnmark(InputParser input) {
        int index = input.getIndex();
        array = array.change(index, false);
        saveToFile();
        return ui.unmark(array, index);
    }

    private String handleDelete(InputParser input) {
        int index = input.getIndex();
        Task removed = array.remove(index);
        saveToFile();
        return ui.remove(array, removed);
    }

    private String handleFind(InputParser input) {
        // read-only operation — no save needed
        String keyword = input.getArgument();
        TaskList result = array.find(keyword);
        return ui.listFind(result);
    }

    private String handleAddTask(InputParser input) {
        try {
            Task newTask = input.getTask();
            array = array.add(newTask);
            saveToFile();
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
            saveToFile();
            return ui.bye();
        }

        return executeCommand(parsedInput);
    }
}