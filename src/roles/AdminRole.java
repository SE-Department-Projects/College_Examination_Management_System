package roles;

import helpers.*;
import models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminRole {

    static public void adminRole(Admin admin) {
        Scanner input = new Scanner(System.in);

        int op;
        int lecID, stdID, subID;
        boolean isStillOperating = true;
        boolean isLecturersRead = false;
        boolean isStudentsRead = false;
        boolean isSubjectsRead = false;

        while (isStillOperating) {
            boolean isBackChosen = false; // to check if the user chose back option or not

            int optionsAnswer = Menus.AdminManageMenu();


            while (optionsAnswer != 0 && !isBackChosen) { // the isBackChosen mustt be false in order to enter the loop
                if (optionsAnswer == 1) { // manage Lecturer
                    int lecIndex;
                    if (!isLecturersRead) {
                        isLecturersRead = true;
                        Files.lecturerIdSubjectFileReader();
                    }
                    do {
                        op = Menus.adminManagingLecturers();
                        if (op == 8) {
                            isBackChosen = true; // set isBackChosen to true to not enter the outer loop, but we set it to false again when he choose an option so he can enter again
                            System.out.println("==========================================\n");
                            break; // to exit the inner do while loop
                        }

                        switch (op) {
                            case 1: // add

                                System.out.print("Enter lecturer Username: ");
                                String LecUsername = input.nextLine();

                                System.out.print("Enter lecturer password: ");
                                String LecPassword = input.nextLine();

                                LecturerManagement.addLecturer(LecUsername, LecPassword);
                                System.out.println("\n---- lecturer added successfully ----\n");
                                break;

                            case 2: //delete
                                System.out.print("Enter lecturer id to delete: ");
                                lecID = Functions.readPositiveInt();

                                lecIndex = LecturerManagement.findLecIndex(lecID);
                                if (lecIndex == -1) {
                                    System.out.println("\n------ the lecturer not found ----\n");
                                } else {
                                    System.out.println(LecturerManagement.deleteLecturer(lecID) ? "\n-------- lecturer deleted success --------\n" : "\n------- failure to delete lecturer------\n");
                                }
                                break;

                            case 3: //search lecturer
                                System.out.print("Enter lecturer id to search: ");
                                lecID = Functions.readPositiveInt();

                                lecIndex = LecturerManagement.findLecIndex(lecID);
                                if (lecIndex == -1) {
                                    System.out.println("\n------ the lecturer not found ----\n");
                                } else {
                                    Lecturer lecturer = LecturerManagement.searchLecturer(lecIndex);
                                    if (lecturer.getUserName().equals("empty") && lecturer.getPassword().equals("empty")) {
                                        System.out.println("\n------ the lecturer not found ----\n");
                                        break;
                                    }
                                    System.out.printf("%-10s%-16s%-25s%-30s\n", "ID", "username", "password", "subject");
                                    System.out.printf("%-10s%-16s%-25s", lecturer.getID(), lecturer.getUserName(), lecturer.getPassword());
                                    if (lecturer.getLecturerSubjects().isEmpty())
                                        System.out.printf("%-30s\n", "No Subject Assigned");
//                                        System.out.printf("%-10s%-16s%-25s%-30s\n", lecturer.getID(), lecturer.getUserName(), lecturer.getPassword(), "No Subject Assigned");
                                    else
                                        System.out.printf("%-30s\n", lecturer.getSubjectsAsString());

//                                        System.out.printf("%-10s%-16s%-25s%-30s\n", lecturer.getID(), lecturer.getUserName(), lecturer.getPassword(), lecturer.getSubjectsAsString());
                                }
                                break;

                            case 4: // lecturer list

                                if (LecturerManagement.getLecturersArr().isEmpty()) {
                                    System.out.println("------- there is no lecturers in the system -------");
                                } else {
                                    System.out.println("------- the lecturers in the system -------");
                                    System.out.printf("%-10s%-16s%-25s%-30s\n", "id", "name", "password", "subject");

                                    for (Lecturer lec : LecturerManagement.getLecturersArr()) {
                                        if (lec.getUserName().equals("empty") && lec.getPassword().equals("empty")) {
                                            continue;
                                        }

                                        System.out.printf("%-10s%-16s%-25s", lec.getID(), lec.getUserName(), lec.getPassword());


                                        if (lec.getLecturerSubjects().isEmpty())
                                            System.out.printf("%-30s\n", "No Subject Assigned");

//                                        System.out.printf("%-10s%-16s%-25s%-30s", lec.getID(), lec.getUserName(), lec.getPassword(), "No Subject Assigned");
                                        else
//                                        System.out.printf("%-10s%-16s%-25s%-30s", lec.getID(), lec.getUserName(), lec.getPassword(), lec.getSubjectsAsString());
                                            System.out.printf("%-30s\n", lec.getSubjectsAsString());
                                    }

                                }

                                System.out.println();
                                break;

                            case 5: //update
                                System.out.println("1=> update username\n2=> password");
                                int updateOP = Functions.readInt();
                                if (updateOP == 1) {
                                    System.out.print("enter lecID to update username: ");
                                    lecID = Functions.readPositiveInt();
                                    System.out.print("enter the new Username: ");
                                    String newUsername = input.nextLine();
                                    boolean update = LecturerManagement.updateLecUsername(lecID, newUsername);
                                    System.out.println(update ? "updated username successfully" : "failure");
                                } else if (updateOP == 2) {
                                    System.out.print("enter lecID to update password: ");
                                    lecID = Functions.readPositiveInt();
                                    System.out.print("enter the new password: ");
                                    String newPassword = input.nextLine();
                                    boolean update = LecturerManagement.updateLecPassword(lecID, newPassword);
                                    System.out.println(update ? "updated password successfully" : "failure");
                                }
                                break;

                            case 6: // assign subject (lecturer)
                                if (LecturerManagement.getLecturersArr().isEmpty()) {
                                    System.out.println("\nthere are no lecturers in the system, Please add a lecturer first!");
                                    break;
                                } else if (SubjectManagement.getSubjectArrayList().isEmpty()) {
                                    System.out.println("\nthere are no subjects in the system, Please add a subject first!");
                                    break;
                                } else {
                                    System.out.print("enter lecturer ID: ");
                                    lecID = Functions.readPositiveInt();
                                    lecIndex = LecturerManagement.findLecIndex(lecID);
                                    if (lecIndex == -1) {
                                        System.out.println("\nlecturer not found");
                                        break;
                                    } else {
                                        System.out.println("Here are the list of subjects");
                                        for (Subject subjects : SubjectManagement.getSubjectArrayList()) {
                                            System.out.println(subjects.getSubjID() + "=> " + subjects.getSubjectName());
                                        }
                                        System.out.print("enter subject ID: ");
                                        subID = Functions.readPositiveInt();
                                        int subIndex = SubjectManagement.findSubjIndex(subID);
                                        if (subIndex == -1) {
                                            System.out.println("subject not found");
                                            break;
                                        } else {
                                            boolean isAssigned = SubjectManagement.assignSubjectToLecturer(SubjectManagement.searchSubject(subIndex), lecID);
                                            System.out.println(isAssigned ? "\nsubject assigned successfully" : "\nfailure to assign subject");
                                        }
                                        break;
                                    }
                                }


                            case 7: // unassign subject (lecturer)
                                if (LecturerManagement.getLecturersArr().isEmpty()) {
                                    System.out.println("\nthere are no lecturers in the system, Please add a lecturer first!");
                                    break;
                                } else {
                                    System.out.print("enter lecturer ID: ");
                                    lecID = Functions.readPositiveInt();
                                    lecIndex = LecturerManagement.findLecIndex(lecID);
                                    if (lecIndex == -1) {
                                        System.out.println("lecturer not found");
                                        break;
                                    } else {
                                        Lecturer lecturer = LecturerManagement.searchLecturer(lecIndex);
                                        if (lecturer.getLecturerSubjects().isEmpty()) {
                                            System.out.println("No Subjects Assigned");
                                            break;
                                        } else {
                                            ArrayList<Subject> lecSubjects = lecturer.getLecturerSubjects();
                                            System.out.println("\nlecturer " + lecturer.getUserName() + " has Subjects: ");
                                            for (Subject subject : lecSubjects) {
                                                System.out.println(subject.getSubjID() + "=> " + subject.getSubjectName());
                                            }
                                            System.out.println("\n Select subject to unassign or 0 to exit: ");
                                            int answer = Functions.readPositiveORZeroInt();
                                            int subIndex = lecturer.findSubjIndex(answer);
                                            if (subIndex != -1) {
                                                Subject subjToRemove = lecturer.getSubject(subIndex);
                                                boolean isUnassigned = SubjectManagement.unassignSubjOfLecturer(subjToRemove, lecID);
                                                System.out.println(isUnassigned ? "\nsubject unassigned successfully" : "\nfailure to unassign subject");
                                                break;
                                            } else if (answer == 0)
                                                break;
                                            else
                                                System.out.println("invalid input");
                                            break;
                                        }
                                    }
                                }

                            case 0:
                                System.out.println("logout successfully");
                                isBackChosen = true;
                                isStillOperating = false;
                                break;

                            default:
                                System.out.println("\ncan not find the operation");

                        }

                        if (op != 0) {
                            System.out.println("==========================================\n");
                        }

                    } while (!isBackChosen);
                    Files.lecturersFileWriter();
                    Files.lecturerIdSubjectFileWriter();
                }

                // manage student section

                else if (optionsAnswer == 2) {
                    if (!isStudentsRead) {
                        isStudentsRead = true;
                        Files.studentIdSubjectFileReader();
                    }


                    do {

                        op = Menus.adminManageStudents();
                        if (op == 8) {
                            isBackChosen = true; // set isBackChosen to true to not enter the outer loop, but we set it to false again when he choose an option so he can enter again
                            System.out.println("==========================================\n");
                            break; // to exit the inner do while loop
                        }

                        switch (op) {
                            case 1: //add

                                System.out.print("Enter Student Username: ");
                                String stdUsername = input.nextLine();

                                System.out.print("Enter Student password: ");
                                String stdPassword = input.nextLine();

                                StudentManagement.addStd(stdUsername, stdPassword);
                                System.out.println("\n---- student added successfully ----\n");
                                break;

                            case 2: //delete
                                System.out.print("Enter student id to delete: ");
                                stdID = Functions.readPositiveInt();

                                System.out.println(StudentManagement.deleteStd(stdID) ? "\n-------- student deleted success --------\n" : "\n-------the Student not found ------\n");

                                break;

                            case 3: //search student
                                System.out.print("Enter Student id to search: ");
                                stdID = Functions.readPositiveInt();

                                int index = StudentManagement.findStdIndex(stdID);
                                if (index == -1) {
                                    System.out.println("\n------ the Student not found ------\n");
                                } else {
                                    Student student = StudentManagement.searchStd(index);
                                    if (student.getUserName().equals("empty") && student.getPassword().equals("empty")) {
                                        System.out.println("\n------the student not found ------\n");
                                        break;
                                    }
                                    System.out.printf("%-10s%-16s%-25s%-30s\n", "ID", "username", "password", "subject(s)");
                                    System.out.printf("%-10s%-16s%-25s", student.getID(), student.getUserName(), student.getPassword());

                                    System.out.printf("%-30s\n", student.getSubjects().isEmpty() ?
                                            "No Subject Assigned" :
                                            student.getSubjectsAsString());

                                }
                                break;


                            case 4: // students list

                                if (StudentManagement.getStudentArray().isEmpty()) {
                                    System.out.println("there is no students in the system");
                                } else {
                                    System.out.println("--- students in the system --- ");
                                    System.out.printf("%-10s%-16s%-25s%-30s\n", "id", "name", "password", "subject(s)");
                                    for (Student std : StudentManagement.getStudentArray()) {
                                        if (std.getUserName().equals("empty") && std.getPassword().equals("empty")) {
                                            continue;
                                        }
                                        System.out.printf("%-10s%-16s%-25s", std.getID(), std.getUserName(), std.getPassword());

                                        System.out.printf("%-30s\n", std.getSubjects().isEmpty() ?
                                                "No Subject Assigned" :
                                                std.getSubjectsAsString());
                                    } // end for loop
                                }
                                break;

                            case 5: // update
                                System.out.println("1=> update username\n2=> password");
                                int updateOP = Functions.readInt();
                                if (updateOP == 1) {
                                    System.out.print("enter StdID to update username: ");
                                    stdID = Functions.readPositiveInt();
                                    System.out.print("enter the new Username: ");
                                    String newUsername = input.nextLine();
                                    boolean update = StudentManagement.updateStdUsername(stdID, newUsername);
                                    System.out.println(update ? "updated username successfully" : "failure to update the username");
                                } else if (updateOP == 2) {
                                    System.out.print("enter stdID to update password: ");
                                    stdID = Functions.readPositiveInt();
                                    System.out.print("enter the new password: ");
                                    String newPassword = input.nextLine();
                                    boolean update = LecturerManagement.updateLecPassword(stdID, newPassword);
                                    System.out.println(update ? "updated password successfully" : "failure");
                                } else {
                                    System.out.println("this operation not exist");
                                }
                                break;


                            case 6: // assign subject (student)
                                if (StudentManagement.getStudentArray().isEmpty()) {
                                    System.out.println("\nthere are no students in the system, Please add a student first!");
                                    break;
                                } else if (SubjectManagement.getSubjectArrayList().isEmpty()) {
                                    System.out.println("\nthere are no subjects in the system, Please add a subject first!");
                                    break;
                                } else {
                                    System.out.print("enter student ID: ");
                                    stdID = Functions.readPositiveInt();
                                    int stdIndex = StudentManagement.findStdIndex(stdID);
                                    if (stdIndex == -1) {
                                        System.out.println("\nstudent not found");
                                        break;
                                    } else {
                                        System.out.println("Here are the list of subjects");
                                        for (Subject subjects : SubjectManagement.getSubjectArrayList()) {
                                            System.out.println(subjects.getSubjID() + "=> " + subjects.getSubjectName());
                                        }
                                        System.out.print("enter subject ID: ");
                                        subID = Functions.readPositiveInt();
                                        int subIndex = SubjectManagement.findSubjIndex(subID);
                                        if (subIndex == -1) {
                                            System.out.println("subject not found");
                                            break;
                                        } else {
                                            boolean isAssigned = SubjectManagement.assignSubjectToStudent(SubjectManagement.searchSubject(subIndex), stdID);
                                            System.out.println(isAssigned ? "\nsubject assigned successfully" : "\nfailure to assign subject");
                                        }
                                        break;
                                    }
                                }


                            case 7: // unassign subject (student)
                                if (StudentManagement.getStudentArray().isEmpty()) {
                                    System.out.println("\nthere are no students in the system, Please add a student first!");
                                    break;
                                } else {
                                    System.out.println("enter student ID: ");
                                    stdID = Functions.readPositiveInt();
                                    int stdIndex = StudentManagement.findStdIndex(stdID);
                                    if (stdIndex == -1) {
                                        System.out.println("Student not found");
                                        break;
                                    } else {
                                        Student student = StudentManagement.searchStd(stdIndex);
                                        if (student.getSubjects().isEmpty()) {
                                            System.out.println("No Subject Assigned");
                                            break;
                                        } else {
                                            ArrayList<Subject> stdSubjects = student.getSubjects();
                                            System.out.println("student " + student.getUserName() + " has Subjects: ");
                                            for (Subject subject : stdSubjects) {
                                                System.out.println(subject.getSubjID() + "=> " + subject.getSubjectName());
                                            }
                                            System.out.println("\n Select subject to unassign or 0 to exit: ");
                                            int answer = Functions.readPositiveORZeroInt();
                                            int subIndex = student.findSubjIndex(answer);
                                            if (subIndex != -1) {
                                                Subject subjToRemove = student.getSubject(subIndex);
                                                boolean isUnassigned = SubjectManagement.unassignSubjOfStudent(subjToRemove, stdID);
                                                System.out.println(isUnassigned ? "\nsubject unassigned successfully" : "\nfailure to unassign subject");
                                                break;
                                            } else if (answer == 0)
                                                break;
                                            else
                                                System.out.println("invalid input");
                                            break;
                                        }
                                    }
                                }


                            case 0:
                                System.out.println("logout successfully");
                                isBackChosen = true;
                                isStillOperating = false;
                                break;

                            default:
                                System.out.println("\ncan not find the operation");
                        }
                        if (op != 0) {
                            System.out.println("==========================================\n");
                        }

                    } while (!isBackChosen);


                    Files.studentsFileWriter();
                    Files.studentIdSubjectFileWriter();
                } else if (optionsAnswer == 3) {  // subject management
                    if (!isSubjectsRead && !isLecturersRead) {
                        isSubjectsRead = true;
                        Files.lecturerIdSubjectFileReader();
                    }


                    do {


                        op = Menus.adminManageSubjects();
                        if (op == 6) {
                            isBackChosen = true; // set isBackChosen to true to not enter the outer loop, but we set it to false again when he choose an option so he can enter again
//                            System.out.println("==========================================\n");
                            break; // to exit the inner do while loop
                        }

                        switch (op) {
                            case 1: // add

                                System.out.print("Enter subject name: ");
                                String subjectName = input.nextLine();

                                System.out.print("enter subject code: ");
                                String subjectCode = input.nextLine();

                                SubjectManagement.addSubject(subjectName, subjectCode);
                                System.out.println("\n---- subject added successfully ----\n");
                                break;

                            case 2: //delete
                                System.out.print("Enter subject id to delete: ");
                                subID = Functions.readPositiveInt();

                                System.out.println(SubjectManagement.deleteSubject(subID) ? "\n-------- subject deleted success --------\n" : " the subject not exist");
                                break;

                            case 3: //search subject
                                System.out.print("Enter Subject id to search: ");
                                subID = Functions.readPositiveInt();

                                int index = SubjectManagement.findSubjIndex(subID);
                                if (index == -1) {
                                    System.out.println("\n------ the Subject not found ----\n");
                                } else {
                                    Subject subject = SubjectManagement.searchSubject(index);
                                    if (subject.getSubjectName().equals("empty") && subject.getSubjectCode().equals("empty-0")) {
                                        System.out.println("\n------the subject not found----\n");
                                        break;
                                    }

                                    System.out.printf("%-10s%-16s%-25s%-30s\n", "ID", "name", "subject code", "lecturer ID");

                                    System.out.printf("%-10s%-16s%-25s", subject.getSubjID(), subject.getSubjectName(), subject.getSubjectCode());

                                    System.out.printf("%-30s\n", subject.getLecturersID().isEmpty() ?
                                            "No Lecturer Assigned" : subject.getLecturersIdAsString());
                                }
                                break;

                            case 4: // subject list
                                System.out.println("--- the subjects in the system ");
                                System.out.printf("%-10s%-16s%-25s%-30s\n", "id", "subject name", "subject code", "lecturer ID");
                                for (Subject sub : SubjectManagement.getSubjectArrayList()) {
                                    if (sub.getSubjectName().equals("empty") && sub.getSubjectCode().equals("empty-0")) {
                                        continue;
                                    }

                                    System.out.printf("%-10s%-16s%-25s", sub.getSubjID(), sub.getSubjectName(), sub.getSubjectCode());

                                    System.out.printf("%-30s\n", sub.getLecturersID().isEmpty() ?
                                            "No Lecturer Assigned" : sub.getLecturersIdAsString());
                                }
                                break;

                            case 5: // update
                                System.out.print("enter subject ID to update the subject: ");
                                subID = Functions.readPositiveInt();
                                System.out.print("enter the new subject name: ");
                                String subNewName = input.nextLine();
                                System.out.print("enter the new subject code: ");
                                String subNewCode = input.nextLine();

                                boolean isUpdated = SubjectManagement.updateSubject(subID, subNewName, subNewCode);
                                System.out.println(isUpdated ? "\nsubject updated successfully" : "\nsubject not exist");
                                break;

                            case 0:
                                System.out.println("logout successfully");
                                isBackChosen = true;
                                isStillOperating = false;
                                break;

                            default:
                                System.out.println("can not find the operation");
                        }

                        if (op != 0) {
                            System.out.println("==========================================\n");
                        }

                    } while (!isBackChosen);

                    Files.subjectsFileWriter();
                } else if (optionsAnswer == 4) {// see personal info
                    System.out.println("\n" + admin.toString() + "\n");
                    break;
                } else if (optionsAnswer == 5) { //Update personal info
                    //TODO while loop here if he entered invalid option
                    int updateChoice = Menus.updatePersonalInfo();
                    if (updateChoice == 1) {// update username
                        System.out.print("enter new username: ");
                        String newUsername = input.nextLine();
                        System.out.println(admin.setPassword(newUsername) ? "username updated successfully" : "some thing went wrong");
                    } else if (updateChoice == 2) {  //update password
                        System.out.print("enter new password: ");
                        String newPassword = input.nextLine();
                        System.out.println(admin.setPassword(newPassword) ? "password updated successfully" : "some thing went wrong");
                    } else if (updateChoice == 3) { //update email
                        System.out.print("enter new email: ");
                        String newEmail = input.nextLine();
                        System.out.println(admin.setEmail(newEmail) ? "email updated successfully" : "some thing went wrong");
                    } else if (updateChoice == 4) { //update phone
                        System.out.print("enter new phone: ");
                        String newPhone = input.nextLine();
                        System.out.println(admin.setPhone(newPhone) ? "phone updated successfully" : "some thing went wrong");
                    } else if (updateChoice == 0) {  // back
//                        System.out.println("logout successfully");
                        isBackChosen = true;
//                        isStillOperating = false;
                        break;
                    } else {
                        System.out.println("Enter a valid option");
                    }
                } else {
                    System.out.print("enter valid option to manage or 0 to exit: ");
                    optionsAnswer = Functions.readPositiveORZeroInt();
                }

                break;
            }
            if (optionsAnswer == 0)
                isStillOperating = false;

            if (optionsAnswer != 0)
                System.out.println("==========================================\n");
        }
        input.close();
        Files.adminFileWriter(admin);
    }
}