package com.lvmp.utils;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

public class InputPrompt {
    public static String getInput(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(message);
        String inputVal = scanner.nextLine();
        scanner.close();

        return inputVal;
    }
}
