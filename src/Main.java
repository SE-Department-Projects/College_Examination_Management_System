import helpers.Authentication;
import models.Admin;
import models.Lecturer;
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

                if (Authentication.adminLogin("userName", "password")) {
                    System.out.println("login Success");
                    System.out.println("---------------------------\n");

                    Admin admin1 = new Admin(username, password, "Admin");

                    System.out.println("choose only on option to manage: ");

                    System.out.println("1=> Lecturer\n2=> Student\n3=> Subject");
                    System.out.print("Enter your answer");
                    String optionsAnswer = input.nextLine();

                    if (optionsAnswer.equals("1")) {
                        int op;
                        int lecID;
                        do {

                            System.out.println("Select operation");
                            System.out.println("1=> add\n2=> delete\n3=>search\n=>list\n5=>update\n0=> exit");
                            System.out.print("enter operation num: ");
                            op = input.nextInt();
                            input.nextLine();

                            switch (op) {
                                case 1:
                                    System.out.print("Enter lecturer Username: ");
                                    String LecUsername = input.nextLine();

                                    System.out.print("Enter lecturer password: ");
                                    String LecPassword = input.nextLine();


                                    admin1.lectureManager.addLecturer(new Lecturer(LecUsername, LecPassword, "Lecturer"));
                                    System.out.println("---- lecturer added successfully ----");
                                    break;

                                case 2:
                                    System.out.print("Enter lecturer id to delete: ");
                                    lecID = input.nextInt();
                                    input.nextLine();

                                    System.out.println(admin1.lectureManager.deleteLecturer(lecID) ? "deleted success" : "failure");
                                    break;

                                case 3:
                                    System.out.print("Enter lecturer id to search: ");
                                    lecID = input.nextInt();

                                   if( admin1.lectureManager.searchLecturer(lecID) == 0)
                                   {
                                       System.out.println("the lecturer not found");
                                   }


                            }

                        } while (op != 0);
                    }
                }


            }
        } while ();


        Admin admin = new Admin("test", "123", "admin");

        System.out.println(admin.getUserName());
    }
}