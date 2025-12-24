package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println( "=== 입력 테스트 ===");
        
        String name = "";
        while (true) {
            System.out.print( "입력 (종료: quit) > ");
            name = reader.readLine();

            if("quit".equals(name)) break;
            else System.out.println( "입력한 값: " + name);
        }
        
        System.out.println( "프로그램을 종료합니다.");

        reader.close();
    }
}
