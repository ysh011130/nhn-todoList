package com.nhnacademy.todo.model;

import java.io.BufferedReader;
import java.io.IOException;

public enum Priority {
    LOW(1, "낮음"),
    MEDIUM(2, "보통"),
    HIGH(3, "높음");

    private final int level;
    private final String displayName;

    Priority(int level, String displayName) {
        this.level = level;
        this.displayName = displayName;
    }

    public int getLevel() { return level; }
    public String getDisplayName() { return displayName; }

    public static Priority fromLevel(int level) {
        for (Priority p : values()) {
            if (p.level == level) {
                return p;
            }
        }
        throw new IllegalArgumentException("잘못된 중요도: " + level);
    }
    public static Priority readPriority(BufferedReader reader) throws IOException {
        while (true) {
            System.out.print("중요도 (1:LOW, 2:MEDIUM, 3:HIGH) > ");
            
            try {
                int idx = Integer.parseInt(reader.readLine().trim());
                return Priority.fromLevel(idx);
            } catch (NumberFormatException e) {
                System.err.println("숫자를 입력하시오.");
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
