package roles;

import helpers.Functions;
import models.*;

import java.util.Scanner;

public class AdminRole {

    static public void adminRole(Admin admin1) {
        Scanner input = new Scanner(System.in);

        int op;
        int lecID, stdID, subID;
        boolean isStillOperating = true;


        while (isStillOperating) {
            boolean isBackChosen = false; // to check if the user chose back option or not
            System.out.println("choose only one option to manage: ");
            System.out.println("1=> Lecturer\n2=> Student\n3=> Subject\n0=> Exit");
            System.out.print("Enter your answer: ");
            int optionsAnswer = Functions.readInt();


            while (optionsAnswer != 0 && !isBackChosen) { // the isBackChosen mustt be false in order to enter the loop
                if (optionsAnswer == 1) {
                    do {


                        System.out.println("\n\nSelect operation");
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

                                System.out.println(admin1.lectureManager.deleteLecturer(lecID) ? "\n--------deleted success--------\n" : "\n-------failure------\n");
                                break;

                            case 3:
                                System.out.print("Enter lecturer id to search: ");
                                lecID = Functions.readInt();

                                int index = admin1.lectureManager.findLecIndex(lecID);
                                if (index == -1) {
                                    System.out.println("\n------the lecturer not found----\n");
                                } else {
                                    Lecturer lecturer = admin1.lectureManager.searchLecturer(index);
                                    // System.out.println(lecturer.getID() + "   " + lecturer.getUserName() + "   " + lecturer.getPassword());
                                    // System.out.println("\n-------------------");
                                    System.out.printf("%-10s%-16s%-25s%-30s\n", "ID", "username", "password", "subject");
                                    System.out.printf("%-10s%-16s%-25s%-30s\n", lecturer.getID(), lecturer.getUserName(), lecturer.getPassword(),"Put SUBJECT HERE");//TODO set subject name
                                    
                                }
                                break;

                            case 4:
                                System.out.printf("%-10s%-16s%-25s\n", "id", "name", "password");
                                for (Lecturer lec : admin1.lectureManager.getLecturersArr()) {
                                    System.out.printf("\n%-10s%-16s%-25s", lec.getID(), lec.getUserName(), lec.getPassword());

                                }
                                break;

                            case 5: //update
                                System.out.println("1 => update username\n2=> password");
                                int updateOP = Functions.readInt();
                                if (updateOP == 1) {
                                    System.out.print("enter lecID to update username: ");
                                    lecID = Functions.readInt();
                                    System.out.print("enter the new Username: ");
                                    String newUsername = input.nextLine();
                                    boolean update = admin1.lectureManager.updateLecUsername(lecID, newUsername);
                                    System.out.println(update ? "updated username successfully" : "failure");
                                    // System.out.println(admin1.lectureManager.getLecturersArr().get(0).getUserName());
                                } else if (updateOP == 2) {
                                    System.out.print("enter lecID tp update password: ");
                                    lecID = Functions.readInt();
                                    System.out.print("enter the new password: ");
                                    String newpassword = input.nextLine();
                                    boolean update = admin1.lectureManager.updateLecPassword(lecID, newpassword);
                                    System.out.println(update ? "updated password successfully" : "failure");
                                }
                                break;

                            case 0:
                                System.out.println("logout successfully");
                                System.exit(0);

                            default:
                                System.out.println("can not find the operation");

                        }

                    } while (true);
                } else if (optionsAnswer == 2) { //manage student section
                    System.out.println("\n\nSelect operation");
                    System.out.println("1=> add\n2=> delete\n3=> search\n4=> list\n5=> update\n6=> Back\n0=> exit");
                    System.out.println("\n");
                    System.out.print("enter operation num: ");
                    op = Functions.readInt();
                    if (op == 6) {
                        isBackChosen = true; // set isBackChosen to true to not enter the outer loop, but we set it to false again when he choose an option so he can enter again
                        break; // to exit the inner do while loop
                    }

                    switch (op) {
                        case 1: //add

                            System.out.print("Enter Student Username: ");
                            String stdUsername = input.nextLine();

                            System.out.print("Enter Student password: ");
                            String stdPassword = input.nextLine();


                            admin1.studentManager.addStd(new Student(stdUsername, stdPassword));
                            System.out.println("\n---- student added successfully ----\n");
                            break;

                        case 2: //delete
                            System.out.print("Enter student id to delete: ");
                            stdID = Functions.readInt();

                            System.out.println(admin1.studentManager.deleteStd(stdID) ? "\n-------- student deleted success --------\n" : "\n------- failure to add Student------\n");
                            break;

                        case 3: //search student
                            System.out.print("Enter lecturer id to search: ");
                            stdID = Functions.readInt();

                            int index = admin1.studentManager.findStdIndex(stdID);
                            if (index == -1) {
                                System.out.println("\n------the Student not found----\n");
                            } else {
                                Student student = admin1.studentManager.searchStd(index);
                                System.out.printf("%-10s%-16s%-25s%-30s\n", "ID", "username", "password", "subject(s)");
                                System.out.printf("%-10s%-16s%-25s%-30s\n", student.getID(), student.getUserName(), student.getPassword(),"put subjects here"); //TODO put subject here

                                System.out.println("\n-------------------");

                            }
                            break;

                        case 4: // students list
                            System.out.println("--- students in the system --- ");
                            System.out.printf("%-10s%-16s%-25s\n", "id", "name", "password");
                            for (Student std : admin1.studentManager.getStudentArray()) {
                                System.out.printf("\n%-10s%-16s%-25s", std.getID(), std.getUserName(), std.getPassword());

                            }
                            break;

                        case 5: // update
                            System.out.println("1 => update username\n2=> password");
                            int updateOP = Functions.readInt();
                            if (updateOP == 1) {
                                System.out.print("enter StdID to update username: ");
                                stdID = Functions.readInt();
                                System.out.print("enter the new Username: ");
                                String newUsername = input.nextLine();
                                boolean update = admin1.studentManager.updateStdUsername(stdID, newUsername);
                                System.out.println(update ? "updated username successfully" : "failure to update the username");
                            } else if (updateOP == 2) {
                                System.out.print("enter stdID to update password: ");
                                stdID = Functions.readInt();
                                System.out.print("enter the new password: ");
                                String newpassword = input.nextLine();
                                boolean update = admin1.lectureManager.updateLecPassword(stdID, newpassword);
                                System.out.println(update ? "updated password successfully" : "failure");
                            }
                            break;

                        case 0:
                            System.out.println("logout successfully");
                            System.exit(0);

                        default:
                            System.out.println("can not find the operation");

                    }


                } else if (optionsAnswer == 3) {  // subject management

                    System.out.println("\n\nSelect operation");
                    System.out.println("1=> add\n2=> delete\n3=> search\n4=> list\n5=> update\n6=> Back\n0=> exit");
                    System.out.println("\n");
                    System.out.print("enter operation num: ");
                    op = Functions.readInt();
                    if (op == 6) {
                        isBackChosen = true; // set isBackChosen to true to not enter the outer loop, but we set it to false again when he choose an option so he can enter again
                        break; // to exit the inner do while loop
                    }

                    switch (op) {
                        case 1: // add

                            System.out.print("Enter subject name: ");
                            String subjectName = input.nextLine();

                            System.out.print("enter subject code: ");
                            String subjectCode = input.nextLine();

                            admin1.subjectManager.addSubject(new Subject(subjectName, subjectCode));
                            System.out.println("\n---- subject added successfully ----\n");
                            break;

                        case 2: //delete
                            System.out.print("Enter subject id to delete: ");
                            subID = Functions.readInt();

                            System.out.println(admin1.subjectManager.deleteSubject(subID) ? "\n-------- subject deleted success --------\n" : "\n------- failure to add subject ------\n");
                            break;

                        case 3: //search subject
                            System.out.print("Enter Subject id to search: ");
                            subID = Functions.readInt();

                            int index = admin1.subjectManager.findSubjIndex(subID);
                            if (index == -1) {
                                System.out.println("\n------ the Subject not found ----\n");
                            } else {
                                Subject subject = admin1.subjectManager.searchSubject(index);
                                System.out.printf("%-10s%-16s%-25s%-30s\n", "ID", "name", "subject code", "lecturer ID");
                                System.out.printf("%-10s%-16s%-25s%-30s\n", subject.getSubjID(), subject.getSubjectName(), subject.getSubjectCode(), "1");

                                System.out.println("\n-------------------");

                            }
                            break;

                        case 4: // subject list
                            System.out.println("--- the count of subjects in the system is: " + Subject.getSubjectCounter()+ " ---");
                            System.out.printf("%-10s%-16s%-25s%-30s\n", "id", "subject name", "subject code", "lecturer ID");
                            for (Subject sub : admin1.subjectManager.getSubjectArrayList()) {
                                System.out.printf("%-10s%-16s%-25s%-30s\n",sub.getSubjID(), sub.getSubjectName(), sub.getSubjectCode(),sub.getLecturerID() );
                            }
                            break;

                        case 5: // update
                            System.out.print("enter subject ID to update the subject: ");
                            subID = Functions.readInt();
                            System.out.print("enter the new subject name: ");
                            String subNewName = input.nextLine();
                            System.out.print("enter the new subject code: ");
                            String subNewCode = input.nextLine();

                            boolean isUpdated =   admin1.subjectManager.updateSubject(subID,subNewName,subNewCode);
                            System.out.println(isUpdated? "subject updated successfully" : "failure to update subject ");
                            break;

                        case 0:
                            System.out.println("logout successfully");
                            System.exit(0);

                        default:
                            System.out.println("can not find the operation");

                    }


                } else {
                    System.out.print("enter valid option to manage or 0 to exit: ");
                    optionsAnswer = Functions.readInt();
                }
            }
            if (optionsAnswer == 0)
                isStillOperating = false;
        }


    }
}