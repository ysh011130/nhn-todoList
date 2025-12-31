package com.nhnacademy.todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.nhnacademy.todo.model.Category;
import com.nhnacademy.todo.model.Priority;
import com.nhnacademy.todo.model.Todo;
import com.nhnacademy.todo.service.TodoService;

public class Main {
    public static void printMenu() {
        System.out.println("=== TODO 앱===");
        System.out.println("1. 등록  2. 조회  0. 종료");
        System.out.println("99. 테스트용"); // 테스트용 전체 출력
        System.out.print("선택 > ");
    }

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TodoService service = new TodoService();

        while (true) {
            printMenu();
            String choice = reader.readLine();

            switch (choice) {
                case "1":   // "[등록] 메뉴 선택됨"
                    System.out.print("할 일: ");
                    String title = reader.readLine();
                    System.out.print("시간: ");
                    int hours = Integer.parseInt(reader.readLine());
                    Category category = Category.readCategory(reader);
                    Priority priority = Priority.readPriority(reader);

                    try {
                        service.add(new Todo(title, category, priority, hours, false));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                    break;
                case "2":   // "[조회] 메뉴 선택됨"
                    service.printAll();
                    break;
                case "0":
                    System.out.println("프로그램을 종료합니다.");
                    reader.close();
                    return;
                case "99":  // 테스트용 전체 출력
                    System.out.println("\n테스트용 전체 출력");
                    service.printAllTest();
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }

            System.out.println();
        }
    }
}
