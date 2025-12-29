package com.nhnacademy;

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
        this.title = title;
        this.hours = hours;
        this.done = done;
    }

    public String getTitle() { return this.title; }
    public int getHours() { return this.hours; }
    public boolean isDone() { return this.done; }

    public void setTitle(String title) {
        if ("".equals(title) != true)
            this.title = title;
    }
    public void setHours(int hours) {
        if (hours > 0 && hours <= 24)
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

    public static void main(String[] args) {
        Todo todo = new Todo("Java Study", 3, false);
        System.out.println(todo);
    }
}
