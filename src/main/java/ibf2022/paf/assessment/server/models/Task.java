package ibf2022.paf.assessment.server.models;

import java.util.Date;

// TODO: Task 4

public class Task {

    private Integer taskId;
    private String description;
    private Integer priority;
    private Date dueDate;
    public Task() {
    }
    public Task(Integer taskId, String description, Integer priority, Date dueDate) {
        this.taskId = taskId;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }
    public Integer getTaskId() {
        return taskId;
    }
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    @Override
    public String toString() {
        return "Task [taskId=" + taskId + ", description=" + description + ", priority=" + priority + ", dueDate="
                + dueDate + "]";
    }
    
    
    

    
}
