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
        assert this.array != null : "Internal task list should be initialized";
    }

    /**
     * Creates a TaskList using an existing list of tasks.
     *
     * @param array The ArrayList of tasks to store.
     */
    public TaskList(ArrayList<Task> array) {
        assert array != null : "Task list cannot be null";
        this.array = array;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The total number of tasks.
     */
    public int size() {
        assert this.array != null : "Internal task list should exist";
        return this.array.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     * @return A new TaskList containing the added task.
     */
    public TaskList add(Task task) {
        assert task != null : "Cannot add null task";
        this.array.add(task);
        assert this.array.contains(task) : "Task should be added successfully";
        return new TaskList(array);
    }

    /**
     * Removes a task from the list using a 1-based index.
     *
     * @param index The index of the task to remove.
     * @return The task that was removed.
     */
    public Task remove(int index) {
        assert index >= 1 : "Index must be 1-based and positive";
        assert index <= this.array.size() : "Index must be within list size";

        Task removed = this.array.remove(index - 1);
        assert removed != null : "Removed task should exist";
        return removed;
    }

    /**
     * Retrieves a task using a 1-based index.
     *
     * @param index The index of the task.
     * @return The requested task.
     */
    public Task get(int index) {
        assert index >= 1 : "Index must be 1-based and positive";
        assert index <= this.array.size() : "Index must be within list size";

        Task task = array.get(index - 1);
        assert task != null : "Retrieved task should exist";
        return task;
    }

    /**
     * Returns a formatted string of all tasks in the list.
     *
     * @return A string representation of the task list.
     */
    public String listTasks() {
        assert array != null : "Internal task list should exist";

        int size = this.size();
        String result = "";

        for (int i = 1; i <= size; i++) {
            assert i - 1 < array.size() : "Loop index must remain valid";

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
        assert index >= 1 : "Index must be 1-based and positive";
        assert index <= array.size() : "Index must be within list size";

        ArrayList<Task> result = new ArrayList<Task>();
        int size = array.size();

        for (int i = 0; i < size; i++) {
            assert array.get(i) != null : "Task entries should never be null";

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

        assert result.size() == array.size() : "Change operation must preserve list size";
        return new TaskList(result);
    }

public TaskList find(String keyword) {
    assert keyword != null : "Search keyword cannot be null";
    keyword = keyword.trim().toLowerCase();

    ArrayList<Task> result = new ArrayList<>();
    for (Task task : array) {
        if (task.getDescription().toLowerCase().contains(keyword)) {
            result.add(task);
        }
    }
    return new TaskList(result);
}

}
