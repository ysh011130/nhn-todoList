package com.nhnacademy.todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.nhnacademy.todo.model.Category;
import com.nhnacademy.todo.model.Priority;
import com.nhnacademy.todo.model.Todo;
import com.nhnacademy.todo.service.TodoService;

public class Main {
    private static TodoService service = new TodoService();

    private static final String menu = "=== TODO 앱 ===\n"
                + "1. 등록\n"
                + "2. 조회\n"
                + "3. 수정\n"
                + "4. 삭제\n"
                + "0. 종료\n"
                + "선택 > ";

    private static void addTodo(BufferedReader reader) throws IOException { // 삽입
        System.out.println("\n=== TODO 등록 ===");

        System.out.print("제목 > ");
        String title = reader.readLine().trim();
        if ("".equals(title)) {
            throw new IllegalArgumentException("제목은 반드시 포함되어야 합니다.");
        } else if (service.isExist(title)) {
            throw new IllegalArgumentException("동일한 제목의 일정이 이미 존재합니다.");
        }
        
        Category category = Category.readCategory(reader);
        Priority priority = Priority.readPriority(reader);
        
        System.out.print("마감일 (yyyy-MM-dd, 없으면 Enter): ");
        LocalDate dueDate = readDate(reader);

        int hours = 0;
        try {
            System.out.print("예상 시간 > ");
            hours = Integer.parseInt(reader.readLine().trim());            
        } catch (NumberFormatException e) {
            System.err.println("숫자를 입력하시오.");
        }

        service.add(new Todo(title, category, priority, dueDate, hours, false));
    }
    private static LocalDate readDate(BufferedReader reader) throws IOException {
        try {
            String input = reader.readLine();
            if (input.isBlank()) return null;
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            System.out.println("날짜 형식이 올바르지 않습니다.");
            return null;
        }
    }

    private static void printAll(BufferedReader reader) throws IOException {    // 전체 출력
        if (service.isEmpty()) {
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
                    service.printBy(null);
                    break;
                case "2":
                    selectCategory(reader);
                    break;
                case "3":
                    selectPriority(reader);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }
    private static void selectCategory(BufferedReader reader) throws IOException {
        System.out.println("\n구분 (1:WORK, 2:STUDY, 3:PERSONAL, 4:HEALTH, 5:OTHER)");
        System.out.print("> ");
        Category category = Category.fromNumber(Integer.parseInt(reader.readLine().trim()));
        
        service.printBy(service.filterByCategory(category));
    }
    private static void selectPriority(BufferedReader reader) throws IOException {
        System.out.println("\n중요도 (1:LOW, 2:MEDIUM, 3:HIGH)");
        System.out.print("> ");
        Priority priority = Priority.fromLevel(Integer.parseInt(reader.readLine().trim()));
        
        service.printBy(service.filterByPriority(priority));
    }

    private static void updateTodo(BufferedReader reader) {  // 수정
        System.out.println("추후 구현 예정");
    }

    private static void deleteTodo(BufferedReader reader) throws IOException {  // 삭제
        if (service.isEmpty()) {
            System.out.println("등록된 TODO가 없습니다.");
            return;
        }

        System.out.print("\n삭제할 TODO ID > ");
        int id = -1;
        try {
            id = Integer.parseInt(reader.readLine().trim());
        } catch (NumberFormatException e) {
            System.err.println("숫자를 입력하시오.");
            return;
        }
        
        service.remove(id);
    }

    public static void main(String[] args) {
        service.loadFromFile();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            while (true) {
                System.out.print(menu);
                String choice = reader.readLine().trim();

                switch (choice) {
                    case "1":   // 삽입
                        addTodo(reader);
                        break;
                    case "2":   // 조회
                        printAll(reader);
                        break;
                    case "3":   // 수정
                        updateTodo(reader);
                        break;
                    case "4":   // 삭제
                        deleteTodo(reader);
                        break;
                    case "0":   // 종료
                        service.saveToFile();
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("[오류]: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("[오류]: " + e.getMessage());
        }
    }
}