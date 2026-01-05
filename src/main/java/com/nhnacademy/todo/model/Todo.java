package com.nhnacademy.todo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Todo {
    private static int idCounter = 1;

    private int id;
    private String title;
    private Category category;
    private Priority priority;
    private int hours;
    private boolean done;
    private String createAt;

    public Todo(String title, Category category, Priority priority, int hours, boolean done) {
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
        this.hours = hours;
        this.done = done;
        this.createAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    /** 파일 로드용 */
    public Todo(int id, String title, Category category, Priority priority, int hours, boolean done, String createdAt) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.priority = priority;
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
    public int getHours() { return this.hours; }
    public boolean isDone() { return this.done; }
    public String createdAt() { return this.createAt; }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title;
    }
    public void setHours(int hours) {
        if (hours <= 0 || hours > 24) {
            throw new IllegalArgumentException("Hours must be between 1 and 24.");   
        }
        this.hours = hours;
    }
    public void setDone(boolean done) {
        this. done = done;
    }

    @Override
    public String toString() {
        // String status = done ? "[완료]" : "[미완료]";
        // return status + " " + this.title + " (" + this.hours + "시간)";
        String result = String.format("[ %s ] %3d | %-18s | %8s | %6s | %4dh | %s |",
            isDone() ? "O" : "X",
            getId(),
            getTitle(),
            getCategory(),
            getPriority(),
            getHours(),
            createdAt()
        );
        return result;
    }
}
