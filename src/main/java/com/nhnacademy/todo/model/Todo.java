package com.nhnacademy.todo.model;

public class Todo {
    private String title;
    private int hours;
    private boolean done;

    public Todo() {
        this.title = "제목 없음";
        this.hours = 0;
        this.done = false;
    }
    public Todo(String title, int hours, boolean done) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (hours <= 0 || hours > 24) {
            throw new IllegalArgumentException("Hours must be between 1 and 24.");   
        }
        
        this.title = title;
        this.hours = hours;
        this.done = done;
    }

    public String getTitle() { return this.title; }
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
}
