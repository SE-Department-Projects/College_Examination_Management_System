import helpers.*;
import models.*;
import roles.*;

import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        //!  read from files

        Files.lecturersFileReader();

        Files.studentsFileReader();

        Files.subjectsFileReader();

        Admin admin = Files.adminFileReader();

        String username, password;
        int roleNum;
        boolean isLoggedin = false;
        boolean isAuth = false;
        boolean worngAuth = false;
        System.out.println("========================================");
        System.out.println("College Examination Management System");
        System.out.println("========================================");

        System.out.println("---- welcome to our system ----");

        while (!isLoggedin) {

            roleNum = Menus.mainMenu();

            if (roleNum == 0) {
                System.exit(0);
            }

            while (roleNum != 1 && roleNum != 2 && roleNum != 3) {
                System.out.print("Enter a valid role or 0 to exit: ");
                roleNum = Functions.readInt();
                if (roleNum == 0) {
                    System.out.println("Exit Success");
                    System.exit(0);
                }

            }
            while (!isAuth) {
                System.out.print("Enter Username: ");
                username = input.nextLine().toLowerCase().trim();

                System.out.print("Enter Password: ");

                password = input.nextLine().trim();

                if(username.equals("empty")) {
                    worngAuth = true;
                }
                else if (roleNum == 1) { // admin role
                    
                    
                    // if the file was empty (or didn't satisfy the pattern) the method will return an admin object with userName = "empty" 
                    if (admin.getUserName().equals("empty")) { 
                        worngAuth = true;
                    }
                    else if (Authentication.adminLogin(username, password,admin)) {
                        worngAuth = false;
                        isAuth = true;
                        isLoggedin = true;
                        System.out.println("login Success");
                        System.out.println("==========================================\n");
                        AdminRole.adminRole(admin);
                    } else {
                        worngAuth = true;
                    }
                } else if (roleNum == 2) { // lecturer role
                    int lecturerID = Authentication.lecturerLogin(username, password);
                    if (lecturerID != -1) {
                        worngAuth = false;
                        isAuth = true;
                        isLoggedin = true;
                        System.out.println("login Success");
                        System.out.println("==========================================\n");
                        LecturerRole.lecturerRole(lecturerID);
                    } else {
                        worngAuth = true;
                    }

                } else  { // student role
                    int studentID = Authentication.studentLogin(username, password);
                    if (studentID != -1) {
                        worngAuth = false;
                        isAuth = true;
                        isLoggedin = true;
                        System.out.println("login Success");
                        System.out.println("==========================================\n");
                        StudentRole.studentRole(studentID);
                    } else {
                        worngAuth = true;
                    }
                }

                if (worngAuth) {
                    int answer = Menus.notAuthenticatedMenu();
                    if (answer == 0)
                        break;
                    else if (answer == 1)
                        isAuth = false;
                    else {
                        System.out.println("\ninvalid input\n");
                        break;
                    }
                }
            }
        }
    System.out.println("Log out successfully");
    }
}