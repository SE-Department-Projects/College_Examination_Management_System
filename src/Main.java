import helpers.Authentication;
import models.Admin;
import models.User;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String roleNum = "", username = "", password = "";
        boolean logout = true;  // howa lisa ma 3ml

        System.out.println("---- welcome to our system ---");

        System.out.println("1=> Admin");
        System.out.println("2=> Lecturer");
        System.out.println("3=> Student");


        do {

            System.out.print("enter the num of the role: ");
            roleNum = input.nextLine();

            if (roleNum.equals("1")) {

                System.out.print("Enter Username: ");
                username = input.nextLine().toLowerCase();

                System.out.println("Enter Password: ");
                password = input.nextLine().toLowerCase();

                if (Authentication.adminLogin("userName", "password"))
                {
                    System.out.println("login Success");
                    System.out.println("---------------------------\n");

                    System.out.println("choose only on option to manage: ");

                    System.out.println("1=> Lecturer\n2=> Student\n3=> Subject");
                    System.out.print("Enter your answer");
                    String optionsAnswer = input.nextLine()
                }



            }
        } while ();


        Admin admin = new Admin("test", "123", "admin");

        System.out.println(admin.getUserName());
    }
}