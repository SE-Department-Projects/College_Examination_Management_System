package helpers;

import java.util.Scanner;

public class Functions {
    static Scanner scanner = new Scanner(System.in);

    public static int readInt() {
        int num = 0;

        while (true) {
            try {
                num = scanner.nextInt();
                return num;
            } catch (Exception exception) {
                System.out.print("enter a valid int num: ");
                scanner.nextLine();
            }
        }
    }

    public static   int readPositiveInt() {
        int num = 0;

        while (true) {
            num = readInt();
            if (num > 0) {
                return num;
            } else {
                System.out.print("enter a positive int num: ");
            }
        }
    }

    public static   int readPositiveORZeroInt() {
        int num = 0;
        while (true) {
            num = readInt();
            if (num >= 0) {
                return num;
            } else {
                System.out.print("enter a positive int num: ");
            }
        }
    }

}
