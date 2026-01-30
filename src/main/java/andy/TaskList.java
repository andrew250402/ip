package andy;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> array;

    public TaskList() {
        this.array = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> array) {
        this.array = array;
    }

    public int size() {
        return this.array.size();
    }

    public TaskList add(Task task) {
        this.array.add(task);
        return new TaskList(array);
    }

    public Task remove(int index) {
        Task removed = this.array.remove(index -1);
        return removed;
    }

    public Task get(int index) {
        return array.get(index - 1);
    }

    public String listTasks() {
        int size = this.size();
        String result = "";
        for (int i=1; i <= size; i ++) {
            result = result
            + "\t"
            + i
            + ". " 
            + this.array.get(i-1)
            + "\n";
        }
        return result;
    }

    public TaskList change(int index, boolean bool) {
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
        return new TaskList(result);
    }

    public TaskList find(String keyword) {
        ArrayList<Task> result = new ArrayList<Task>();
        int size = array.size();
        for (int i = 0; i < size; i ++) {
            Task task = array.get(i);
            if (task.getDescription().indexOf(keyword) != -1) {
                result.add(task);
            }
        }
        return new TaskList(result);
    }
}
