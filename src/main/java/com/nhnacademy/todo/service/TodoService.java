package com.nhnacademy.todo.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nhnacademy.todo.exception.TodoNotFoundException;
import com.nhnacademy.todo.model.Category;
import com.nhnacademy.todo.model.Priority;
import com.nhnacademy.todo.model.Todo;

public class TodoService {
    private ArrayList<Todo> todoList;
    private static final String FILENAME = "todos.csv";

    public TodoService() {
        todoList = new ArrayList<>();
    }

    public void add(Todo todo) {    // 삽입
        todoList.add(todo);
        System.out.println("등록 완료!");
    }
    public boolean isExist(String title) {
        for (Todo todo : todoList) {
            if (todo.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
    /** <p>아무것도 선택되지 않아 바로 printBy(null)로 호출</p>
     *  <p>또는 getByCategory(Category category) or getByPriority(Priority priority)를 매개변수로 호출</p>
     * */
    public void printBy(ArrayList<Todo> selected) { // 전체 출력
        ArrayList<Todo> choice;
        if (selected == null) {
            choice = todoList;
        } else {
            choice = selected;
        }

        if (choice.isEmpty()) {
            System.out.println("등록된 TODO가 없습니다.");
            return;
        }
        for (Todo todo : choice) {
            System.out.println(todo);
        }
    }
    public ArrayList<Todo> filterByCategory(Category category) {
        ArrayList<Todo> retArr = new ArrayList<>();
        
        for (Todo todo : todoList) {
            if (category == todo.getCategory()) {
                retArr.add(todo);
            }
        }
        return retArr;
    }
    public ArrayList<Todo> filterByPriority(Priority priority) {
        ArrayList<Todo> retArr = new ArrayList<>();
        
        for (Todo todo : todoList) {
            if (priority == todo.getPriority()) {
                retArr.add(todo);
            }
        }
        return retArr;
    }

    public Todo findById(int id) {
        for (Todo todo : todoList) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        throw new TodoNotFoundException(id);
    }
    public void remove(int id) {    // 삭제
        if (todoList.isEmpty()) {
            System.out.println("등록된 TODO가 없습니다.");
            return;
        }
        
        try {
            todoList.remove(findById(id));
            System.out.println("삭제 완료!");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("다음 ID는 존재하지 않습니다 : ID = " + id);
        } catch (TodoNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean isEmpty() {
        return todoList.isEmpty();
    }

    public List<Todo> searchByTitle(String keyword) {
        List<Todo> result = new ArrayList<>();

        String lowerKeyword = keyword.toLowerCase();
        for (Todo todo : todoList) {
            if (todo.getTitle().toLowerCase().contains(lowerKeyword)) {
                result.add(todo);
            }
        }
        return result;
    }

    public void loadFromFile() {    // 파일 로드
        File file = new File(FILENAME);
        if (!file.exists()) {
            System.out.println("파일이 없습니다. 빈 리스트로 시작합니다.");
            return;
        }

        try (BufferedReader read = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            int count = 0;
            while ((line = read.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                Category category = Category.valueOf(parts[2]);
                Priority priority = Priority.valueOf(parts[3]);
                LocalDate dueDate = parts[4].isEmpty() ? null : LocalDate.parse(parts[4]);
                int hours = Integer.parseInt(parts[5]);
                boolean done = Boolean.parseBoolean(parts[6]);
                LocalDateTime createAt = LocalDateTime.parse(parts[7]);
                todoList.add(new Todo(id, title, category, priority, dueDate, hours, done, createAt));

                count++;
            }
            System.out.println("파일 로드 완료: " + FILENAME + " ("+ count + "건)");
        } catch (Exception e) {
            System.err.println("파일 로드 실패: " + e.getMessage());
        }
    }
    public void saveToFile() {  // 파일 저장
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Todo todo : todoList) {
                String line = todo.getId() + ","
                            + todo.getTitle() + ","
                            + todo.getCategory() + ","
                            + todo.getPriority() + ","
                            + ((todo.getDueDate() == null) ? "" : todo.getDueDate().toString()) + ","
                            + todo.getHours() + ","
                            + todo.isDone() + ","
                            + todo.createdAt().toString();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("파일 저장 완료: " + FILENAME + " (" + todoList.size() + "건)");
        } catch (Exception e) {
            System.err.println("파일 저장 실패: " + e.getMessage());
        }
    }
}
