package Game;

import java.util.Scanner;

public class ErrorCheck {

    public static int getInt(Scanner sc) {
        try {
            return sc.nextInt();
        } catch (java.util.InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input. Please enter a number.");
            return getInt(sc);
        }
    }
}