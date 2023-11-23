import helpers.Authentication;
import helpers.Functions;
import models.Admin;
import models.Lecturer;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String roleNum = "", username = "", password = "";
        boolean systemIsOpen = true;  // howa lisa ma 3ml

        System.out.println("---- welcome to our system ---");

        System.out.println("1=> Admin");
        System.out.println("2=> Lecturer");
        System.out.println("3=> Student");


//        while (systemIsOpen) {

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

                    if (optionsAnswer.equals("1")) {
                        int op;
                        int lecID;
                        do {

                            System.out.println("\nSelect operation");
                            System.out.println("1=> add\n2=> delete\n3=>search\n4=>list\n5=>update\n0=> exit");
                            System.out.println("\n");
                            System.out.print("enter operation num: ");
                            op = input.nextInt();
                            input.nextLine();

                            switch (op) {
                                case 1:

                                    System.out.print("Enter lecturer Username: ");
                                    String LecUsername = input.nextLine();

                                    System.out.print("Enter lecturer password: ");
                                    String LecPassword = input.nextLine();


                                    admin1.lectureManager.addLecturer(new Lecturer(LecUsername, LecPassword));
                                    System.out.println("\n---- lecturer added successfully ----\n");
                                    break;

                                case 2:
                                    System.out.print("Enter lecturer id to delete: ");
                                    lecID = input.nextInt();
                                    input.nextLine();

                                    System.out.println(admin1.lectureManager.deleteLecturer(lecID) ? "\n--------deleted success\n" : "\n-------failure------\n");
                                    break;

                                case 3:
                                    System.out.print("Enter lecturer id to search: ");
                                    lecID = Functions.readInt();

                                    int index = admin1.lectureManager.findLecIndex(lecID);
                                    if (index == -1) {
                                        System.out.println("\n------the lecturer not found----\n");
                                    } else {
                                        Lecturer lecturer = admin1.lectureManager.searchLecturer(index);
                                        System.out.println(lecturer.getID() + "   " + lecturer.getUserName() + "   " + lecturer.getPassword());
                                        System.out.println("\n-------------------");

                                    }
                                    break;

                                case 4:
                                    System.out.printf("%-10s%-16s%-25s\n", "id", "name", "password");
                                    for (Lecturer lec : admin1.lectureManager.getLecturersArr()) {
                                        System.out.printf("\n%-10s%-16s%-25s", lec.getID(), lec.getUserName(), lec.getPassword());

                                    }
                                    break;

                                default:
                                    System.out.println("can not find the operation");


                            }

                        } while (op != 0);
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