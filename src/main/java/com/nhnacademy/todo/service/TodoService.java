package com.nhnacademy.todo.service;

import com.nhnacademy.todo.model.Todo;
import java.util.ArrayList;

public class TodoService {
    private ArrayList<Todo> todoList = new ArrayList<>();

    public void add(Todo todo) {    // 등록
        todoList.add(todo);
        System.out.println("등록 완료: " + todo.getTitle());
    }

    public void delete(int index) { // 삭제
        todoList.remove(index);
    }

    public Todo get(int index) {    // 조회
        return todoList.get(index);
    }

    public ArrayList<Todo> getAll() {   // 전체 조회
        return todoList;
    }

    public void printAll() {
        if (todoList.isEmpty()) {
            System.out.println("등록된 TODO가 없습니다.");
            return;
        }

        System.out.println("=== TODO 목록 ===");
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println((i+1) + ". " + todoList.get(i));            
        }
    }
}
