package com.nhnacademy.todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.nhnacademy.todo.service.TodoService;

public class Main {
    public static void main(String[] args) {
        final String menu = "=== TODO 앱 ===\n"
                    + "1. 등록\n"
                    + "2. 조회\n"
                    + "3. 수정\n"
                    + "4. 삭제\n"
                    + "0. 종료\n"
                    + "선택 > ";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            TodoService service = new TodoService(reader);
            while (true) {
                System.out.print(menu);
                String choice = reader.readLine().trim();

                switch (choice) {
                    case "1":   // 삽입
                        try {
                            service.insert(service.newTodo());
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case "2":   // 조회
                        service.printAll();
                        break;
                    case "3":   // 수정
                        try {
                            service.updateTodo();
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case "4":   // 삭제
                        try {
                            service.delete();
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case "0":   // 종료
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}