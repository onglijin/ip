package duke;

/**
 * Deadline is a subclass of Task.
 */
public class Deadline extends Task {
    // Deadlines: Tasks that need to be done before a specific time date/time
    // Example: Submit report by 11/10/2019 5pm
    protected String by;

    /**
     * Constructor of Deadline object.
     * 
     * @param description Takes in the description of the deadline task.
     * @param by Takes in the date in which the task needs to be completed by in YYYYY-MM-DD format..
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the task type of Deadline.
     * 
     * @return the task type of Deadline.
     */
    @Override
    public String getTaskType() {
        return "Deadline";
    }

    /**
     * Formats the string of a Deadline object.
     * 
     * @return a formatted string for a Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Formats the string of a Deadline object to be stored into hard drive.
     * 
     * @return a formatted string suitable for storage in hard drive for a Deadline object.
     */
    @Override
    public String toStringInFile() {
        return "D" + super.toStringInFile() + " | " + this.by;
    }
    
}
