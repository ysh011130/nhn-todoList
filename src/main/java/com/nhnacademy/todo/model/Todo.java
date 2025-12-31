package com.nhnacademy.todo.model;

public class Todo {
    private static int idCounter = 1;

    private int id;
    private String title;
    private Category category;
    private Priority priority;
    private int hours;
    private boolean done;

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
    }

    public int getId() { return this.id; }
    public String getTitle() { return this.title; }
    public Category getCategory() { return this.category; }
    public Priority gePriority() {return this.priority; }
    public int getHours() { return this.hours; }
    public boolean isDone() { return this.done; }

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
        String status = done ? "[완료]" : "[미완료]";
        return status + " " + this.title + " (" + this.hours + "시간)";
    }

    public String printDetailTest() {
        return "id: " + id
           + ", title: " + title
           + ", category: " + category
           + ", priority: " + priority
           + ", hours: " + hours
           + ", done: " + done;
    }
}
