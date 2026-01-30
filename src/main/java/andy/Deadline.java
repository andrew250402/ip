package andy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    protected String formattedBy;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
        this.formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDate.parse(by);
        this.formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public Deadline markDone() {
        return new Deadline(this.description, this.by, true);
    }

    public Deadline unmarkDone() {
        return new Deadline(this.description, this.by, false);
    }

    public String getString() {
        return "D|" + super.getString() + "|" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + formattedBy + ")";
    }
}
