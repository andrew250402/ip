import java.io.File;                  // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;             // Import the Scanner class to read text files
import java.util.ArrayList;

public class FileParser {
    private String path;
    private ArrayList<Task> tasks;

    public FileParser(String path) {
        this.path = path;
        this.tasks = null;
    }

    public ArrayList<Task> readFile() {
        File myObj = new File(this.path);
        this.tasks = new ArrayList<Task>();

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] line = data.split("\\|");
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
        } catch (FileNotFoundException e) {
            try {
                File newFile = new File(path);
                newFile.createNewFile();
            } catch (IOException e1) {
                System.out.println("An error occurred.");
                e1.printStackTrace();
            }
        }
        return this.tasks;
    }

    public void writeFile(ArrayList<Task> array) {
        try (FileWriter myWriter = new FileWriter(path)) {
            for (int i = 0; i < array.size(); i ++) {
                myWriter.write(array.get(i).getString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
