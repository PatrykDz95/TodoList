package TodoList.DataModel;

import java.time.LocalDate;

public class TodoItem {

    private String Description;
    private String details;
    private LocalDate deadline;

    public TodoItem(String description, String details, LocalDate deadline) {
        this.Description = description;
        this.details = details;
        this.deadline = deadline;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
