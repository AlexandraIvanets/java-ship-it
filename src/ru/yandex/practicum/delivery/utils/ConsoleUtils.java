package ru.yandex.practicum.delivery.utils;

import java.util.Scanner;

public class ConsoleUtils {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String SEPARATOR_LINE = "-".repeat(48);
    public static final String INPUT_PROMPT = "--> ";

    public static int readInt(String message) {
        while (true) {
            System.out.println(message);
            System.out.print(INPUT_PROMPT);

            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Повторите ввод, ожидается целое число");
            }
        }
    }

    public static String readString(String message) {
        System.out.println(message);
        System.out.print(INPUT_PROMPT);
        return scanner.nextLine();
    }
}
