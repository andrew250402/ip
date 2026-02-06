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

        Ui ui = new Ui();

        FileParser parser = new FileParser("data/task.txt");
        TaskList array = new TaskList(parser.readFile());

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        InputParser parsedInput = new InputParser(input);

        System.out.println(ui.start());

        while (!parsedInput.isBye()) {
            if (parsedInput.isList()) {
                System.out.println(ui.listTasks(array));    
            } else if (parsedInput.isMark()) {
                int index = parsedInput.getIndex();
                array = array.change(index, true);
                System.out.println(ui.mark(array, index));
            } else if (parsedInput.isUnmark()) {
                int index = parsedInput.getIndex();
                array = array.change(index, false);
                System.out.println(ui.unmark(array, index));              
            } else if (parsedInput.isDelete()) {
                int index = parsedInput.getIndex();
                Task removed = array.remove(index);
                System.out.println(ui.remove(array, removed));
            } else if (parsedInput.isFind()) {
                String keyword = parsedInput.getArgument();
                TaskList desiredList = array.find(keyword);
                System.out.println(ui.listFind(desiredList));
            } else {
                try {
                    Task newTask = parsedInput.getTask();
                    array = array.add(newTask);
                    System.out.println(ui.add(array, newTask));
                } catch (IllegalArgumentException e) {
                    System.out.println(ui.error(e));
                } catch (DateTimeParseException e1) {
                    System.out.println(ui.custom("Please use a valid format after '/by' like yyyy-mm-dd"));
                }
            }

            input = scanner.nextLine();
            parsedInput = new InputParser(input);
        }
        
        parser.writeFile(array);
        scanner.close();
        System.out.println(ui.bye());
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        InputParser parsedInput = new InputParser(input);

        if (parsedInput.isBye()) {
            parser.writeFile(array);
            return ui.bye();
        } else if (parsedInput.isList()) {
            return ui.listTasks(array);    
        } else if (parsedInput.isMark()) {
            int index = parsedInput.getIndex();
            array = array.change(index, true);
            return ui.mark(array, index);
        } else if (parsedInput.isUnmark()) {
            int index = parsedInput.getIndex();
            array = array.change(index, false);
            return ui.unmark(array, index);              
        } else if (parsedInput.isDelete()) {
            int index = parsedInput.getIndex();
            Task removed = array.remove(index);
            return ui.remove(array, removed);
        } else if (parsedInput.isFind()) {
            String keyword = parsedInput.getArgument();
            TaskList desiredList = array.find(keyword);
            return ui.listFind(desiredList);
        } else {
            try {
                Task newTask = parsedInput.getTask();
                array = array.add(newTask);
                return ui.add(array, newTask);
            } catch (IllegalArgumentException e) {
                return ui.error(e);
            } catch (DateTimeParseException e1) {
                return ui.custom("Please use a valid format after '/by' like yyyy-mm-dd");
            }
        }
    }
}
