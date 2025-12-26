package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
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
                    System.out.println("[등록] 메뉴 선택됨");
                    break;
                case "2":
                    System.out.println("[조회] 메뉴 선택됨");
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
