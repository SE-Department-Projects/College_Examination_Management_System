package roles;

import helpers.Functions;
import models.*;

import java.util.Scanner;

public class AdminRole {

    static public void adminRole(Admin admin1) {
        Scanner input = new Scanner(System.in);

        int op;
        int lecID;
        boolean isStillOperating = true;
        

        while(isStillOperating) {
            boolean isBackChosen = false; // to check if the user chose back option or not
            System.out.println("choose only one option to manage: ");
            System.out.println("1=> Lecturer\n2=> Student\n3=> Subject");
            System.out.print("Enter your answer: ");
            int optionsAnswer = Functions.readInt();


            while (optionsAnswer != 0 && isBackChosen == false) { // the isBackChosen mustt be false in order to enter the loop
                if (optionsAnswer == 1) {
                    do {

                
                        System.out.println("\nSelect operation");
                        System.out.println("1=> add\n2=> delete\n3=> search\n4=> list\n5=> update\n6=> Back\n0=> exit");
                        System.out.println("\n");
                        System.out.print("enter operation num: ");
                        op = Functions.readInt();
                        if (op == 6) {
                            isBackChosen = true; // set isBackChosen to true to not enter the outer loop, but we set it to false again when he choose an option so he can enter again
                            break; // to exit the inner do while loop
                        }

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
                                lecID = Functions.readInt();
                                // input.nextLine();

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


                            case 0:
                                System.out.println("logout successfully");
                                System.exit(0);

                            default:
                                System.out.println("can not find the operation");

                        }

                    } while (true);
                } else if (optionsAnswer == 2) {
                    System.out.println("manage student section coming soon...");
                } else if (optionsAnswer == 3) {
                    System.out.println("manage subject section coming soon...");
                } else {
                    System.out.print("enter valid option to manage or 0 to exit: ");
                    optionsAnswer = Functions.readInt();
                }
            }
        }




    }
}