package pl.lodz.eksoc.task_domain.models;

import java.time.LocalDate;

public class Task {
    private int id;
    private String name;
    private TaskType type;
    private int priority;
    private LocalDate creationDate;
    private TaskStatus status;

    // Konstruktor
    public Task(int id, String name, TaskType type, int priority, LocalDate creationDate, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.priority = priority;
        this.creationDate = creationDate;
        this.status = status;
    }

    // Gettery
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TaskType getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    // Settery
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}

