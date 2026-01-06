package com.nhnacademy.todo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Todo {
    private static int idCounter = 1;

    private int id;
    private String title;
    private Category category;
    private Priority priority;
    private LocalDate dueDate;
    private int hours;
    private boolean done;
    private LocalDateTime createAt;

    public Todo(String title, Category category, Priority priority, LocalDate dueDate, int hours, boolean done) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (hours <= 0 || hours > 24) {
            throw new IllegalArgumentException("Hours must be between 1 and 24.");   
        }
        
        this.id = idCounter++;
        this.title = title;
        this.category = category;
        this.priority = priority;
        this.dueDate = dueDate;
        this.hours = hours;
        this.done = done;
        this.createAt = LocalDateTime.now();
    }
    /** 파일 로드용 */
    public Todo(int id, String title, Category category, Priority priority, LocalDate dueDate, int hours, boolean done, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.priority = priority;
        this.dueDate = dueDate;
        this.hours = hours;
        this.done = done;
        this.createAt = createdAt;

        if (id >= idCounter) {
            idCounter = id + 1;
        }
    }

    public int getId() { return this.id; }
    public String getTitle() { return this.title; }
    public Category getCategory() { return this.category; }
    public Priority getPriority() { return this.priority; }
    public LocalDate getDueDate() { return this.dueDate; }
    public int getHours() { return this.hours; }
    public boolean isDone() { return this.done; }
    public LocalDateTime createdAt() { return this.createAt; }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public void setHours(int hours) {
        if (hours <= 0 || hours > 24) {
            throw new IllegalArgumentException("Hours must be between 1 and 24.");   
        }
        this.hours = hours;
    }
    
    @Override
    public String toString() {
        String result = String.format("[ %s ] %3d | %-18s | %8s | %6s | %4dh | 마감: %s |",
            isDone() ? "O" : "X",
            getId(),
            getTitle(),
            getCategory(),
            getPriority(),
            getHours(),
            (getDueDate() != null) ? getDueDate().toString() : "없음"
            // createdAt()
        );
        return result;
    }
}
