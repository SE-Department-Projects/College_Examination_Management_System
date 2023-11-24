import helpers.Authentication;
import models.Admin;
import roles.AdminRole;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String roleNum, username, password;
//        boolean systemIsOpen = true;  // howa lisa ma 3ml

        System.out.println("---- welcome to our system ---");

        System.out.println("1=> Admin");
        System.out.println("2=> Lecturer");
        System.out.println("3=> Student");


        System.out.print("enter the num of the role: ");
        roleNum = input.nextLine();

        if (roleNum.equals("1")) {

            System.out.print("Enter Username: ");
            username = input.nextLine().toLowerCase();

            System.out.print("Enter Password: ");
            password = input.nextLine().toLowerCase();

            if (Authentication.adminLogin(username, password)) {
                System.out.println("login Success");
                System.out.println("---------------------------\n");

                Admin admin1 = new Admin(username, password);

                System.out.println("choose only on option to manage: ");

                System.out.println("1=> Lecturer\n2=> Student\n3=> Subject");
                System.out.print("Enter your answer");
                String optionsAnswer = input.nextLine();

                if (optionsAnswer.equals("1")) {  //admin role
                    AdminRole.adminRole(admin1);
                } else {
                    System.out.println("else de bta3et 3aiz y3mil manage lehh bezabt");
                }
            } else {
                System.out.println("you are not Authenticated");
                System.exit(0);
            }


        } else {
            System.out.println("esbor lma nkhlas aly fo2");
            System.exit(0);
        }


    }
}