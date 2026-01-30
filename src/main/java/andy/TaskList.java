package andy;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of Task objects.
 * <p>
 * It provides methods to add, remove, retrieve, and update tasks.
 */
public class TaskList {
    protected ArrayList<Task> array;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.array = new ArrayList<Task>();
    }

    /**
     * Creates a TaskList using an existing list of tasks.
     *
     * @param array The ArrayList of tasks to store.
     */
    public TaskList(ArrayList<Task> array) {
        this.array = array;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The total number of tasks.
     */
    public int size() {
        return this.array.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     * @return A new TaskList containing the added task.
     */
    public TaskList add(Task task) {
        this.array.add(task);
        return new TaskList(array);
    }

    /**
     * Removes a task from the list using a 1-based index.
     *
     * @param index The index of the task to remove.
     * @return The task that was removed.
     */
    public Task remove(int index) {
        Task removed = this.array.remove(index -1);
        return removed;
    }

    /**
     * Retrieves a task using a 1-based index.
     *
     * @param index The index of the task.
     * @return The requested task.
     */
    public Task get(int index) {
        return array.get(index - 1);
    }

    /**
     * Returns a formatted string of all tasks in the list.
     *
     * @return A string representation of the task list.
     */
    public String listTasks() {
        int size = this.size();
        String result = "";
        for (int i = 1; i <= size; i ++) {
            result = result
            + "\t"
            + i
            + ". " 
            + this.array.get(i - 1)
            + "\n";
        }
        return result;
    }

    /**
     * Marks or unmarks a task as done.
     *
     * @param index The index of the task to update.
     * @param bool true to mark as done, false to unmark.
     * @return A new TaskList with the updated task.
     */
    public TaskList change(int index, boolean bool) {
        ArrayList<Task> result = new ArrayList<Task>();
        int size = array.size();

        for (int i = 0; i < size; i ++) {
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
