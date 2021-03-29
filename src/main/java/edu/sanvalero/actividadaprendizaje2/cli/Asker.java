package edu.sanvalero.actividadaprendizaje2.cli;

import java.util.Scanner;

public class Asker {
    public static final Scanner scanner = new Scanner(System.in);

    public static String text(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    public static int number(String question) {
        int parsedResponse = Integer.MIN_VALUE;
        do {
            try {
                String rawResponse = text(question);
                parsedResponse = Integer.parseInt(rawResponse);
            } catch (NumberFormatException ex) {
                System.out.println("Debe introducir un número válido");
            }
        } while (parsedResponse == Integer.MIN_VALUE);
        return parsedResponse;
    }
}
