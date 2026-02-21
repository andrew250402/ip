# Andy User Guide

Andy is a desktop chatbot application that helps you manage tasks.
It supports both **Command Line Interface (CLI)** usage and a **Graphical User Interface (GUI)**.

If you prefer fast keyboard interactions, Andy lets you manage tasks efficiently using simple text commands.

---

# Quick Start

1. **Ensure you have Java 17 or above installed.**

2. **Download the latest `.jar` file** of Andy.

3. **Place the `.jar` file** in a folder that you want to use as Andy’s home directory.

4. **Open a command terminal**, navigate to that folder, and run:

```
java -jar andy.jar
```

5. The GUI should appear.
   You can type commands in the input box and press **Enter** or click **Send**.

---

# Features

## Notes about command format

* Words in UPPER_CASE are parameters supplied by the user.
  Example: `todo DESCRIPTION`

* Items in square brackets are optional.

* Commands are **case-sensitive** unless stated otherwise.

* Index values refer to the numbering shown in the task list.

---

# Viewing all tasks — `list`

Shows all tasks currently stored.

**Format**

```
list
```

---

# Adding a todo task — `todo`

Adds a simple task with a description.

**Format**

```
todo DESCRIPTION
```

**Example**

```
todo buy groceries
```

---

# Adding a deadline — `deadline`

Adds a task with a deadline.

**Format**

```
deadline DESCRIPTION /by DATE
```

**Example**

```
deadline submit report /by 2025-03-01
```

---

# Adding an event — `event`

Adds a task with a start and end time.

**Format**

```
event DESCRIPTION /from START_TIME /to END_TIME
```

**Example**

```
event project meeting /from Monday 2pm /to Monday 4pm
```

---

# Marking a task as done — `mark`

Marks a task as completed.

**Format**

```
mark INDEX
```

**Example**

```
mark 2
```

---

# Unmarking a task — `unmark`

Marks a completed task as not done.

**Format**

```
unmark INDEX
```

**Example**

```
unmark 2
```

---

# Deleting a task — `delete`

Removes a task from the list.

**Format**

```
delete INDEX
```

**Example**

```
delete 3
```

---

# Finding tasks — `find`

Searches for tasks containing a keyword in the description.

Partial matches are supported.

**Format**

```
find KEYWORD
```

**Example**

```
find meeting
```

---

# Exiting the program — `bye`

Closes Andy safely.

**Format**

```
bye
```

---

# Saving the data

All task data is saved automatically whenever changes are made.
There is no need to manually save.

---

# Data storage location

Tasks are stored in a text file:

```
data/task.txt
```

Andy reads from this file when starting and writes to it when tasks change.

---

# Editing the data file manually

Advanced users may edit the task file directly.

⚠ Caution:

* Invalid formatting may cause errors.
* Always create a backup before editing.

---

# FAQ

### Q: How do I move my tasks to another computer?

Copy the `data/task.txt` file into the same folder as the jar file.

---

### Q: Why is my command not working?

Ensure the command format is correct and required parameters are provided.

---

# Known Issues

* Invalid command formats may produce error messages.
* Editing the data file incorrectly may break task loading.

---

# Command Summary

| Action       | Command                                 |
| ------------ | --------------------------------------- |
| List tasks   | `list`                                  |
| Add todo     | `todo DESCRIPTION`                      |
| Add deadline | `deadline DESCRIPTION /by DATE`         |
| Add event    | `event DESCRIPTION /from START /to END` |
| Mark task    | `mark INDEX`                            |
| Unmark task  | `unmark INDEX`                          |
| Delete task  | `delete INDEX`                          |
| Find task    | `find KEYWORD`                          |
| Exit         | `bye`                                   |

---

End of User Guide.
