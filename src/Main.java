import helpers.*;
import models.*;
import roles.AdminRole;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String username, password;
        int roleNum;


        System.out.println("---- welcome to our system ----");

        System.out.println("1=> Admin\n2=> Lecturer\n3=> Student");


        System.out.print("enter the num of the role: ");
        roleNum = Functions.readInt();

        while (roleNum != 1 && roleNum != 2 && roleNum != 3) {
            System.out.print("this role not exist please enter a valid role num or 0 for exit: ");
            roleNum = Functions.readInt();
            if (roleNum == 0) {
                System.exit(0);
            }

        }

        System.out.print("Enter Username: ");
        username = input.nextLine().toLowerCase();

        System.out.print("Enter Password: ");

        password = input.nextLine().toLowerCase();


        if (roleNum == 1) {   //admin role


            if (Authentication.adminLogin(username, password)) {
                System.out.println("login Success");
                System.out.println("---------------------------\n");

                Admin admin1 = new Admin(username, password);  // will be the admin that returns from the file

                AdminRole.adminRole(admin1);

            } else {
                System.out.println("you are not Authenticated");
                System.exit(0);
            }


        } else if (roleNum == 2) { //lecturer role
            System.out.println("lecturer section coming soon...");
            System.exit(0);
        } else {  // student role
            System.out.println("student section coming soon...");
            System.exit(0);
        }

    }
}