package com.nhnacademy.todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.nhnacademy.todo.model.Todo;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Todo> todoList = new ArrayList<>();

        String sel = "";
        while (true) {
            System.out.println("=== TODO 앱===");
            System.out.println("1. 등록");
            System.out.println("2. 조회");
            System.out.println("0. 종료");
            System.out.print("선택 > ");
            sel = reader.readLine();

            switch (sel) {
                case "1":
                    // "[등록] 메뉴 선택됨"
                    System.out.println();

                    System.out.println("Todo 작성");
                    System.out.println("입력 예시 : Java,3,false");
                    System.out.print("> ");
                    String input = reader.readLine();
                    input = input.replaceAll("\\s+", "");
                    String[] tmp = input.split(",");

                    todoList.add(
                        new Todo(
                            tmp[0],
                            Integer.parseInt(tmp[1]),
                            Boolean.parseBoolean(tmp[2])
                        )
                    );

                    break;
                case "2":
                    // "[조회] 메뉴 선택됨"
                    System.out.println();
                    
                    if (todoList.size() == 0) {
                        System.out.println("목록이 비어있습니다.");
                        break;
                    }

                    System.out.println("=== TODO 목록 ===");
                    for (int i = 0; i < todoList.size(); i++) {
                        System.out.println((i+1) + ". " + todoList.get(i));
                    }
                    break;
                case "0":
                    System.out.println("프로그램을 종료합니다.");
                    reader.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }

            System.out.println();
        }
    }
}
