package com.nhnacademy.todo.model;

import java.io.BufferedReader;
import java.io.IOException;

public enum Category {
    WORK("업무"),
    STUDY("학습"),
    PERSONAL("개인"),
    HEALTH("건강"),
    OTHER("기타");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Category fromNumber(int number) {
        if (number < 1 || number > values().length) {
            throw new IllegalArgumentException("잘못된 구분 번호: " + number);
        }
        return values()[number - 1];
    }
    public static Category readCategory(BufferedReader reader) throws IOException {
        while (true) {
            System.out.print("구분 (1:WORK, 2:STUDY, 3:PERSONAL, 4:HEALTH, 5:OTHER) > ");

            try {
                int idx = Integer.parseInt(reader.readLine());
                return Category.fromNumber(idx);
            } catch (NumberFormatException e) {
                System.err.println("숫자를 입력하시오.");
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
