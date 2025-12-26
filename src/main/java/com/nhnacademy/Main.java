package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        boolean quit = false;
        String input = "";

        while (!quit) {
            System.out.println("=== TODO 앱===");
            System.out.println("1. 등록");
            System.out.println("2. 조회");
            System.out.println("0. 종료");
            System.out.print("선택 > ");

            try {
                input = reader.readLine();

                switch (input) {
                    case "1":
                        System.out.println("[등록] 메뉴 선택됨");
                        break;
                    case "2":
                        System.out.println("[조회] 메뉴 선택됨");
                        break;
                    case "0":
                        System.out.println("프로그램을 종료합니다.");
                        quit = true;
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println();
        }
        
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
