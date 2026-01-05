package com.nhnacademy.todo.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.nhnacademy.todo.exception.TodoNotFoundException;
import com.nhnacademy.todo.model.Category;
import com.nhnacademy.todo.model.Priority;
import com.nhnacademy.todo.model.Todo;

public class TodoService {
    private ArrayList<Todo> todoList;
    private BufferedReader reader;

    public TodoService(BufferedReader reader) {
        this.todoList = new ArrayList<>();
        this.reader = reader;
    }

    public void insert(Todo todo) {    // 삽입
        todoList.add(todo);
        System.out.println("등록 완료!");
    }

    public Todo findByID(int id) {
        for (Todo todo : todoList) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        throw new TodoNotFoundException(id);
    }
    public int idxFindById(int id) {
        for (int i = 0; i < todoList.size(); i++) {
            if(todoList.get(i).getId() == id) {
                return i;
            }
        }
        throw new TodoNotFoundException(id);
    }
    public void delete() throws IOException { // 삭제
        if (todoList.isEmpty()) {
            System.out.println("등록된 TODO가 없습니다.");
            return;
        }

        System.out.print("\n삭제할 TODO ID > ");
        int index = 0;
        try {
            index = Integer.parseInt(reader.readLine().trim());
        } catch (NumberFormatException e) {
            System.err.println("숫자를 입력하시오.");
            return;
        }
        
        try {
            todoList.remove(idxFindById(index));
            System.out.println("삭제 완료!");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("다음 ID는 존재하지 않습니다 : ID = " + index);
        } catch (TodoNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public boolean isExist(String title) {
        for (Todo todo : todoList) {
            if (todo.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
    public void updateTodo() throws IOException {
        if (todoList.isEmpty()) {
            System.out.println("등록된 TODO가 없습니다.");
            return;
        }

        System.out.print("수정할 TODO ID > ");
        int id = Integer.parseInt(reader.readLine().trim());
        try {
            Todo toUpdate = findByID(id);

            System.out.print("새 제목 > ");
            String title = reader.readLine().trim();
            if (isExist(title)) {
                throw new IllegalArgumentException("동일한 제목의 일정이 이미 존재합니다.");
            }

            System.out.print("새 예상 시간 > ");
            int hours = Integer.parseInt(reader.readLine().trim());

            toUpdate.setTitle(title);
            toUpdate.setHours(hours);
        } catch (TodoNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    public void printBy(ArrayList<Todo> todoList) {
        ArrayList<Todo> sorted = todoList;
        System.out.println("\n=== TODO 목록 ===");
        for (int i = 0; i < sorted.size(); i++) {
            Todo todo = sorted.get(i);
            // System.out.println("[" + todo.getId() + "] "
            //     + todo.getTitle() + " | "
            //     + todo.getHours() + "시간 | "
            //     + todo.getCategory() + " | "
            //     + todo.getPriority() + " | "
            //     + "[" + (todo.isDone() ? "O" : "X") + "]");
            System.out.println(todo);
        }
    }
    public void printByCategory() {
        ArrayList<Todo> byCategory = new ArrayList<>();
        for (Category category : Category.values()) {
            for (Todo todo : todoList) {
                if (category == todo.getCategory()) {
                    byCategory.add(todo);
                }
            }
        }
        printBy(byCategory);
    }
    public void printByPriority() {
        ArrayList<Todo> byPriority = new ArrayList<>();
        for (Priority priority : Priority.values()) {
            for (Todo todo : todoList) {
                if (priority == todo.getPriority()) {
                    byPriority.add(todo);
                }
            }
        }
        printBy(byPriority);
    }
    public void printAll() throws IOException {
        if (todoList.isEmpty()) {
            System.out.println("등록된 TODO가 없습니다.");
            return;
        }

        while (true) {
            System.out.println("\n=== 조회 메뉴 ===");
            System.out.println("1. 전체 조회");
            System.out.println("2. 구분별 조회");
            System.out.println("3. 중요도별 조회");
            System.out.println("0. 이전");
            System.out.print("선택 > ");
            String choice = reader.readLine().trim();
            
            switch (choice) {
                case "1":
                    printBy(todoList);
                    break;
                case "2":
                    printByCategory();
                    break;
                case "3":
                    printByPriority();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }

    public Todo newTodo() throws IOException {
        System.out.println("\n=== TODO 등록 ===");

        System.out.print("제목 > ");
        String title = reader.readLine().trim();
        if ("".equals(title)) {
            throw new IllegalArgumentException("Title cannot be null");
        } else if (isExist(title)) {
            throw new IllegalArgumentException("동일한 제목의 일정이 이미 존재합니다.");
        }

        int hours = 0;
        try {
            System.out.print("예상 시간 > ");
            hours = Integer.parseInt(reader.readLine().trim());            
        } catch (NumberFormatException e) {
            System.err.println("숫자를 입력하시오.");
        }

        Category category = Category.readCategory(reader);
        Priority priority = Priority.readPriority(reader);

        return new Todo(title, category, priority, hours, false);
    }

    public void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("파일이 없습니다. 빈 리스트로 시작합니다.");
            return;
        }

        try (BufferedReader read = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;
            while ((line = read.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                Category category = Category.valueOf(parts[2]);
                Priority priority = Priority.valueOf(parts[3]);
                int hours = Integer.parseInt(parts[4]);
                boolean done = Boolean.parseBoolean(parts[5]);
                String createAt = parts[6];
                todoList.add(new Todo(id, title, category, priority, hours, done, createAt));

                count++;
            }
            System.out.println("파일 로드 완료: " + filename + " ("+ count + "건)");
        } catch (Exception e) {
            System.err.println("파일 로드 실패: " + e.getMessage());
        }
    }
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Todo todo : todoList) {
                String line = todo.getId() + ","
                            + todo.getTitle() + ","
                            + todo.getCategory() + ","
                            + todo.getPriority() + ","
                            + todo.getHours() + ","
                            + todo.isDone() + ","
                            + todo.createdAt();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("파일 저장 완료: " + filename + " (" + todoList.size() + "건)");
        } catch (Exception e) {
            System.err.println("파일 저장 실패: " + e.getMessage());
        }
    }
}
