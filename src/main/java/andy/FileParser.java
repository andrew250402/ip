package andy;

import java.io.File;                  // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;             // Import the Scanner class to read text files
import java.util.ArrayList;

/**
 * The FileParser class handles reading tasks from a file and
 * writing tasks back to a file.
 * <p>
 * Tasks are stored in a text file using a specific format, and
 * this class converts each line into the appropriate Task object
 * (Todo, Deadline, or Event).
 */
public class FileParser {
    private String path;
    private ArrayList<Task> tasks;

    /**
     * Creates a FileParser using the given file path.
     *
     * @param path The file path used to read and write task data.
     */
    public FileParser(String path) {
        this.path = path;
        this.tasks = null;
    }

    /**
     * Reads tasks from the file and converts each line into a Task object.
     * <p>
     * If the file does not exist, a new file will be created.
     *
     * @return An ArrayList of tasks read from the file.
     */
    public ArrayList<Task> readFile() {
        File file = new File(this.path);
        this.tasks = new ArrayList<>();

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Failed to create data file.");
                e.printStackTrace();
                return this.tasks;
            }
        }

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String data = reader.nextLine();

                if (data.trim().isEmpty()) {
                    continue;
                }

                String[] line = data.split("\\|");

                try {
                    parseLine(line);
                } catch (Exception e) {
                    System.out.println("Skipping corrupted line: " + data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unexpected error reading file.");
            e.printStackTrace();
        }

        return this.tasks;
    }

    private void parseLine(String[] line) {
        String type = line[0];
        String done = line[1];
        String description = line[2];

        if (type.equals("T")) {
            if (done.equals("1")) {
                this.tasks.add(new Todo(description, true));
            } else {
                this.tasks.add(new Todo(description));
            }
        } else if (type.equals("D")) {
            String by = line[3];

            if (done.equals("1")) {
                this.tasks.add(new Deadline(description, by, true));
            } else {
                this.tasks.add(new Deadline(description, by));
            }
        } else {
            String from = line[3];
            String to = line[4];

            if (done.equals("1")) {
                this.tasks.add(new Event(description, from, to, true));
            } else {
                this.tasks.add(new Event(description, from, to));
            }
        }
    }
    
    /**
     * Writes all tasks in the given TaskList to the file.
     *
     * @param array The TaskList containing tasks to be saved.
     */
    public void writeFile(TaskList array) {
        try (FileWriter myWriter = new FileWriter(path)) {
            for (int i = 1; i <= array.size(); i ++) {
                myWriter.write(array.get(i).getString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
