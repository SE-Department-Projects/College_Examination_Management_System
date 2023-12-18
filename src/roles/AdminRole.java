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
                            case 1: // add lecturer

                                System.out.print("Enter lecturer Username: ");
                                String LecUsername = input.nextLine().toLowerCase().trim();
                                
                                System.out.print("Enter lecturer password: ");
                                String LecPassword = input.nextLine().trim();
                                
                                if(LecUsername.equals("empty"))
                                {
                                    System.out.println("\nCan't add lecturer with username empty\n");
                                    break;
                                }
                                else if(LecUsername.contains("-") || LecPassword.contains("-")){
                                    System.out.println("\nCan't add lecturer with username or password contains '-' Character ");
                                    break;
                                }

                                int status = LecturerManagement.addLecturer(LecUsername, LecPassword);
                                if (status != -1) {
                                    System.out.println("\n---- lecturer added successfully ----\n");
                                } else {
                                    System.out.println("\n Username already exists \n");
                                }
                                break;

                            case 2: //delete lecturer
                                System.out.print("Enter lecturer id to delete: ");
                                lecID = Functions.readPositiveInt();

                                lecIndex = LecturerManagement.findLecIndex(lecID);
                                if (lecIndex == -1) {
                                    System.out.println("\n------ Lecturer not found ------\n");
                                }
                                else {
                                    Lecturer lecturer = LecturerManagement.searchLecturer(lecIndex);
                                    if(lecturer.getUserName().equals("empty")){
                                        System.out.println("\n------ Lecturer not found ------\n");
                                        break;
                                    }
                                        System.out.println(LecturerManagement.deleteLecturer(lecID) ? "\n-------- lecturer deleted success --------\n" : "\n------- failure to delete lecturer ------\n");
                                }
                                break;

                            case 3: //search lecturer
                                System.out.print("Enter lecturer id to search: ");
                                lecID = Functions.readPositiveInt();

                                lecIndex = LecturerManagement.findLecIndex(lecID);
                                if (lecIndex == -1) {
                                    System.out.println("\n------ Lecturer not found ------\n");
                                } else {
                                    Lecturer lecturer = LecturerManagement.searchLecturer(lecIndex);
                                    if (lecturer.getUserName().equals("empty") && lecturer.getPassword().equals("empty")) {
                                        System.out.println("\n------ the lecturer not found ----\n");
                                        break;
                                    }
                                    System.out.printf("%-10s%-16s%-25s%-30s\n", "ID", "username", "password", "subject");
                                    System.out.printf("%-10s%-16s%-25s", lecturer.getID(), lecturer.getUserName(), lecturer.getPassword());
                                    System.out.printf("%-30s\n", lecturer.getSubjectsAsString());
                                }
                                break;

                            case 4: // lecturer list

                                if (LecturerManagement.isLecturersListEmpty()) {
                                    System.out.println("\n------- there is no lecturers in the system -------");
                                } 
                                else {
                                    System.out.println("------- the lecturers in the system -------");
                                    System.out.printf("%-10s%-16s%-25s%-30s\n", "id", "name", "password", "subject");

                                    for (Lecturer lec : LecturerManagement.getLecturersArr()) {
                                        if (lec.getUserName().equals("empty") && lec.getPassword().equals("empty")) {
                                            continue;
                                        }
                                        System.out.printf("%-10s%-16s%-25s", lec.getID(), lec.getUserName(), lec.getPassword());
                                        System.out.printf("%-30s\n", lec.getSubjectsAsString());
                                    }
                                }

                                System.out.println();
                                break;

                            case 5: //update lecturer

                                lecIndex = -1;
                                int validInput = 1;
                                do {
                                    System.out.print("enter lecturer ID to update his info: ");
                                    lecID = Functions.readPositiveInt();
                                    lecIndex = LecturerManagement.findLecIndex(lecID);
                                    if (lecIndex != -1) {
                                        Lecturer lecturer = LecturerManagement.searchLecturer(lecIndex);
                                        if(lecturer.getUserName().equals("empty")){
                                            System.out.println("\n------ Lecturer not found ------\n");
                                            break;
                                        }
                                        int updateOP = Menus.adminUpdateUsersInfo();
                                        if (updateOP == 1) {
                                            System.out.print("enter the new Username: ");
                                            String newUsername = input.nextLine().toLowerCase().trim();
                                            if(newUsername.contains("-")){
                                                System.out.println("\nUsername can't contain '-' Character\n");
                                            }
                                            else{
                                                int update = lecturer.setUserName(newUsername);
                                                if (update == -1) {
                                                    System.out.println("\nCan't change username to empty");
                                                } else if (update == -2) {
                                                    System.out.println("\nThis username already exists");
                                                } else {
                                                    System.out.println("\nUsername updated successfully");
                                                }
                                            }
                                        } else if (updateOP == 2) {
                                            System.out.print("enter the new password: ");
                                            String newPassword = input.nextLine().trim();
                                            if(newPassword.contains("-")){
                                                System.out.println("\nPassword can't contain '-' Character\n");
                                            }
                                            else{
                                                lecturer.setPassword(newPassword);
                                                System.out.println("Password updated successfully");
                                            }
                                        } else if (updateOP == 0) {
                                            break;
                                        } else {
                                            System.out.println("this operation not exist");
                                        }
                                    
                                    } else {
                                        System.out.println("\nLecturer not found\n");
                                        do {
                                            System.out.println("1=> try again");
                                            System.out.println("0=> back");
                                            System.out.print("Enter your answer: ");
                                            validInput= Functions.readPositiveORZeroInt();
                                            if(validInput != 1 && validInput != 0)
                                            {
                                                System.out.println("enter a valid input");
                                            }
                                        }while (validInput != 1 && validInput != 0);
                                    }

                                } while (lecIndex == -1 && validInput == 1);


                                break;

                            case 6: // assign subject (lecturer)
                                if (LecturerManagement.isLecturersListEmpty()) {
                                    System.out.println("\nthere are no lecturers in the system, Please add a lecturer first!");
                                    break;
                                } else if (SubjectManagement.isSubjectListEmpty()) {
                                    System.out.println("\nthere are no subjects in the system, Please add a subject first!");
                                    break;
                                } else {
                                    System.out.print("Enter lecturer ID: ");
                                    lecID = Functions.readPositiveInt();
                                    lecIndex = LecturerManagement.findLecIndex(lecID);
                                    if (lecIndex == -1) {
                                        System.out.println("\nLecturer not found");
                                        break;
                                    } 
                                    Lecturer lecturer = LecturerManagement.searchLecturer(lecIndex);
                                    if(lecturer.getUserName().equals("empty") && lecturer.getPassword().equals("empty"))
                                    {
                                        System.out.println("\nLecturer not found");
                                        break;
                                    }
                                    else {
                                        System.out.println("You are now adding subject to " + lecturer.getUserName() + "\n");
                                        System.out.println("Here are the list of subjects");
                                        for (Subject subjects : SubjectManagement.getSubjectArrayList()) {
                                            if(subjects.getSubjectName().equals("empty") && subjects.getSubjectCode().equals("empty-0"))
                                            {
                                                continue;
                                            }
                                            System.out.println(subjects.getSubjID() + "=> " + subjects.getSubjectName());
                                        }
                                        System.out.print("Enter subject ID: ");
                                        subID = Functions.readPositiveInt();
                                        int subIndex = SubjectManagement.findSubjIndex(subID);
                                        if (subIndex == -1) {
                                            System.out.println("\nSubject not found");
                                            break;
                                        } else {
                                            Subject subject = SubjectManagement.searchSubject(subIndex);
                                            if(subject.getSubjectName().equals("empty") && subject.getSubjectCode().equals("empty-0"))
                                            {
                                                System.out.println("\nSubject not found");
                                                break;
                                            }
                                            else{
                                                if (lecturer.getLecturerSubjects().contains(subject)) {
                                                    System.out.println("\nthis subject is already assigned to this lecturer");
                                                    break;
                                                } else {
                                                    boolean isAssigned = SubjectManagement.assignSubjectToLecturer(subject, lecID);
                                                    System.out.println(isAssigned ? "\nsubject assigned successfully" : "\nfailure to assign subject");
                                                }
                                            }
                                        }
                                        break;
                                    }
                                }


                            case 7: // unassign subject (lecturer)
                                if (LecturerManagement.isLecturersListEmpty()) {
                                    System.out.println("\nthere are no lecturers in the system, Please add a lecturer first!");
                                    break;
                                } else {
                                    System.out.print("Enter lecturer ID: ");
                                    lecID = Functions.readPositiveInt();
                                    lecIndex = LecturerManagement.findLecIndex(lecID);
                                    if (lecIndex == -1) {
                                        System.out.println("\nLecturer not found");
                                        break;
                                    }
                                    Lecturer lecturer = LecturerManagement.searchLecturer(lecIndex);
                                    if(lecturer.getUserName().equals("empty") && lecturer.getPassword().equals("empty"))
                                    {
                                        System.out.println("\nLecturer not found");
                                        break;
                                    }
                                    else {
                                        if (lecturer.getLecturerSubjects().isEmpty()) {
                                            System.out.println("\nNo Subjects Assigned");
                                            break;
                                        } else {
                                            ArrayList<Subject> lecSubjects = lecturer.getLecturerSubjects(); // array list of subjects of the lecturer
                                            System.out.println("\nLecturer " + lecturer.getUserName() + " has Subjects: ");
                                            for (Subject subject : lecSubjects) {
                                                System.out.println(subject.getSubjID() + "=> " + subject.getSubjectName());
                                            }
                                            System.out.println("0=> back");
                                            System.out.print("\nEnter your Answer: ");
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
                                                System.out.println("\n\nSubject not found");
                                            break;
                                        }
                                    }
                                }

                            case 0:
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
                            case 1: //add student

                                System.out.print("Enter Student Username: ");
                                String stdUsername = input.nextLine().toLowerCase().trim();

                                System.out.print("Enter Student password: ");
                                String stdPassword = input.nextLine().trim();

                                if(stdUsername.equals("empty"))
                                {
                                    System.out.println("\nCan't add student with username empty\n");
                                    break;
                                }
                                else if(stdUsername.contains("-") || stdPassword.contains("-"))
                                {
                                    System.out.println("\nCan't add student with username or password contains '-' Character\n");
                                    break;
                                }

                                int status = StudentManagement.addStd(stdUsername, stdPassword);
                                if (status != -1) {
                                    System.out.println("\n---- student added successfully ----\n");
                                } else {
                                    System.out.println("\n Username already exists \n");
                                }
                                break;

                            case 2: //delete student
                                System.out.print("Enter student id to delete: ");
                                stdID = Functions.readPositiveInt();

                                int stdIndex = StudentManagement.findStdIndex(stdID);
                                if(stdIndex == -1)
                                {
                                    System.out.println("\n------ Student not found ------\n");
                                }
                                else {
                                    Student student = StudentManagement.searchStd(stdIndex);
                                    if(student.getUserName().equals("empty"))
                                    {
                                        System.out.println("\n------ Student not found ------\n");
                                        break;
                                    }
                                    System.out.println(StudentManagement.deleteStd(stdID) ? "\n-------- Student deleted successfully --------\n" : "\n------- Student not found ------\n");
                                }


                                break;

                            case 3: //search student
                                System.out.print("Enter Student id to search: ");
                                stdID = Functions.readPositiveInt();

                                int index = StudentManagement.findStdIndex(stdID);
                                if (index == -1) {
                                    System.out.println("\n------ Student not found ------\n");
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

                                if (StudentManagement.isStudentListEmpty()) {
                                    System.out.println("\nthere is no students in the system");
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

                            case 5: // update (student)

                                stdIndex = -1;
                                int validInput= 1;
                                do {
                                    System.out.print("enter student ID to update this info: ");
                                    stdID = Functions.readPositiveInt();
                                    stdIndex = StudentManagement.findStdIndex(stdID);
                                    if (stdIndex != -1) {
                                        Student student = StudentManagement.searchStd(stdIndex);
                                        if(student.getUserName().equals("empty")){
                                            System.out.println("\n------ Student not found ------\n");
                                            break;
                                        }
                                        int updateOP = Menus.adminUpdateUsersInfo();

                                        if (updateOP == 1) {

                                            System.out.print("enter the new Username: ");
                                            String newUsername = input.nextLine().toLowerCase().trim();
                                            if(newUsername.contains("-")){
                                                System.out.println("\nUsername can't contain '-' Character\n");
                                            }
                                            else{
                                                int update = student.setUserName(newUsername);
                                                if (update == -1) {
                                                    System.out.println("\nCan't change username to empty");
                                                } else if (update == -2) {
                                                    System.out.println("\nThis username already exists");
                                                } else {
                                                    System.out.println("\nUsername updated successfully");
                                                }
                                            }
                                        } else if (updateOP == 2) {

                                            System.out.print("enter the new password: ");
                                            String newPassword = input.nextLine().trim();
                                            if(newPassword.contains("-")){
                                                System.out.println("\nPassword can't contain '-' Character\n");
                                            }
                                            else{
                                                student.setPassword(newPassword);
                                                System.out.println("Password updated successfully");
                                            }
                                        } else if (updateOP == 0) {
                                            break;
                                        } else {
                                            System.out.println("this operation not exist");
                                        }
                                    } else {
                                        System.out.println("\nStudent not found\n");

                                        do {
                                            System.out.println("1=> try again");
                                            System.out.println("0=> back");
                                            System.out.print("Enter your answer: ");
                                            validInput= Functions.readPositiveORZeroInt();
                                            if(validInput != 1 && validInput != 0)
                                            {
                                                System.out.println("enter a valid input");
                                            }
                                        }while (validInput != 1 && validInput != 0);
                                    }

                                } while (stdIndex == -1 && validInput == 1);


                                break;


                            case 6: // assign subject (student)
                                if (StudentManagement.isStudentListEmpty()) {
                                    System.out.println("\nthere are no students in the system, Please add a student first!");
                                    break;
                                } else if (SubjectManagement.isSubjectListEmpty()) {
                                    System.out.println("\nthere are no subjects in the system, Please add a subject first!");
                                    break;
                                } else {
                                    System.out.print("enter student ID: ");
                                    stdID = Functions.readPositiveInt();
                                    stdIndex = StudentManagement.findStdIndex(stdID);
                                    if (stdIndex == -1) {
                                        System.out.println("\nStudent not found");
                                        break;
                                    }
                                    Student student = StudentManagement.searchStd(stdIndex);
                                    if(student.getUserName().equals("empty") && student.getPassword().equals("empty"))
                                    {
                                        System.out.println("\nStudent not found");
                                        break;
                                    }
                                    else {
                                        System.out.println("You are now adding subject to " + student.getUserName() + "\n");
                                        System.out.println("Here are the list of subjects");
                                        for (Subject subjects : SubjectManagement.getSubjectArrayList()) {
                                            if (subjects.getSubjectName().equals("empty") && subjects.getSubjectCode().equals("empty-0")) {
                                                continue;
                                            }
                                            System.out.println(subjects.getSubjID() + "=> " + subjects.getSubjectName());
                                        }
                                        System.out.print("enter subject ID: ");
                                        subID = Functions.readPositiveInt();
                                        int subIndex = SubjectManagement.findSubjIndex(subID);
                                        if (subIndex == -1) {
                                            System.out.println("\nSubject not found");
                                            break;
                                        } else {
                                            Subject subject = SubjectManagement.searchSubject(subIndex);
                                            if (subject.getSubjectName().equals("empty") && subject.getSubjectCode().equals("empty-0")){
                                                System.out.println("\nSubject not found");
                                                break;
                                            }
                                            else{
                                                if (student.getSubjects().contains(subject)) {
                                                    System.out.println("\nthis subject is already assigned to this student");
                                                    break;
                                                } else {
                                                    boolean isAssigned = SubjectManagement.assignSubjectToStudent(subject, stdID);
                                                    System.out.println(isAssigned ? "\nsubject assigned successfully" : "\nfailure to assign subject");
                                                }
                                            }
                                        }
                                        break;
                                    }
                                }


                            case 7: // unassign subject (student)
                                if (StudentManagement.isStudentListEmpty()) {
                                    System.out.println("\nthere are no students in the system, Please add a student first!");
                                    break;
                                } else {
                                    System.out.print("Enter student ID: ");
                                    stdID = Functions.readPositiveInt();
                                    stdIndex = StudentManagement.findStdIndex(stdID);
                                    if (stdIndex == -1) {
                                        System.out.println("\nStudent not found");
                                        break;
                                    }
                                    Student student = StudentManagement.searchStd(stdIndex);
                                    if(student.getUserName().equals("empty") && student.getPassword().equals("empty"))
                                    {
                                        System.out.println("\nStudent not found");
                                        break;
                                    }
                                    else {
                                        if (student.getSubjects().isEmpty()) {
                                            System.out.println("\nNo Subject Assigned");
                                            break;
                                        } else {
                                            ArrayList<Subject> stdSubjects = student.getSubjects();
                                            System.out.println("Student " + student.getUserName() + " has Subjects: ");
                                            for (Subject subject : stdSubjects) {
                                                System.out.println(subject.getSubjID() + "=> " + subject.getSubjectName());
                                            }
                                            System.out.println("0=> back");
                                            System.out.print("\nEnter your Answer: ");
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
                                                System.out.println("\ninvalid input\n");
                                            break;
                                        }
                                    }
                                }


                            case 0:
                                isBackChosen = true;
                                isStillOperating = false;
                                break;

                            default:
                                System.out.println("\nEnter a valid option\n");
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
                        Files.subjectIdExamFileReader();
                    }


                    do {


                        op = Menus.adminManageSubjects();
                        if (op == 6) {
                            isBackChosen = true; // set isBackChosen to true to not enter the outer loop, but we set it to false again when he choose an option so he can enter again
                            break; // to exit the inner do while loop
                        }

                        switch (op) {
                            case 1: // add subject

                                System.out.print("Enter subject name: ");
                                String subjectName = input.nextLine().toLowerCase().trim();
                                if(subjectName.contains(",")){
                                    System.out.println("\nSubject name can't contain ',' Character\n");
                                    break;
                                }

                                System.out.print("enter subject code: ");
                                int subjCode = Functions.readPositiveInt();
                                String subjectCode = subjectName + "-" + subjCode;
                                int status = SubjectManagement.addSubject(subjectName, subjectCode);
                                if (status != -1) {
                                    System.out.println("\n---- subject added successfully ----\n");
                                } else {
                                    System.out.println("\n Subject already exists \n");
                                }
                                break;

                            case 2: //delete subject
                                System.out.print("Enter subject id to delete: ");
                                subID = Functions.readPositiveInt();

                                int subIndex = SubjectManagement.findSubjIndex(subID);
                                if (subIndex == -1) {
                                    System.out.println("\n------ Subject not found ------\n");
                                } else {
                                    Subject subject = SubjectManagement.searchSubject(subIndex);
                                    if(subject.getSubjectName().equals("empty"))
                                    {
                                        System.out.println("\n------ Subject not found ------\n");
                                        break;
                                    }
                                    System.out.println(SubjectManagement.deleteSubject(subID) ? "\n-------- subject deleted success --------\n" : "\nSubject not found\n");
                                }

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
                                        System.out.println("\n------ Subject not found ------\n");
                                        break;
                                    }

                                    System.out.printf("%-10s%-16s%-25s%-30s\n", "ID", "name", "subject code", "lecturer ID");

                                    System.out.printf("%-10s%-16s%-25s", subject.getSubjID(), subject.getSubjectName(), subject.getSubjectCode());

                                    System.out.printf("%-30s\n", subject.getLecturersID().isEmpty() ?
                                            "No Lecturer Assigned" : subject.getLecturersIdAsString());
                                }
                                break;

                            case 4: // subject list
                                if (SubjectManagement.isSubjectListEmpty()) {
                                    System.out.println("\n------- there is no subjects in the system -------");
                                }
                                else{
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
                                }
                                break;

                            case 5: // update subject
                                System.out.print("enter subject ID to update the subject: ");
                                subID = Functions.readPositiveInt();
                                int subjectIndex =SubjectManagement.findSubjIndex(subID) ;

                                if(subjectIndex!= -1)
                                {
                                    Subject subject = SubjectManagement.searchSubject(subjectIndex);
                                    if(subject.getSubjectName().equals("empty"))
                                    {
                                        System.out.println("\nSubject not found\n");
                                        break;
                                    }
                                    System.out.print("enter the new subject name: ");
                                    String subNewName = input.nextLine().toLowerCase().trim();
                                    if(subNewName.contains(",")){
                                        System.out.println("\nSubject name can't contain ',' Character\n");
                                        continue;
                                    }
                                    System.out.print("enter the new subject code: ");
                                    int subjNewCode = Functions.readPositiveInt();
                                    String subNewCode = subNewName + "-" + subjNewCode;
                                    boolean isUpdated = SubjectManagement.updateSubject(subjectIndex, subNewName, subNewCode);
                                    System.out.println(isUpdated ? "\nSubject updated successfully\n" : "\nSubject already exists\n");
                                }
                                else
                                {
                                    System.out.println("\nSubject not found\n");
                                }

                                break;

                            case 0:
                                isBackChosen = true;
                                isStillOperating = false;
                                break;

                            default:
                                System.out.println("\nEnter a valid option\n");
                        }

                        if (op != 0) {
                            System.out.println("==========================================\n");
                        }

                    } while (!isBackChosen);

                    Files.subjectsFileWriter();
                    Files.allSubjectsIdExamFileWriter();
                    Files.studentIdSubjectFileWriter();
                    Files.lecturerIdSubjectFileWriter();
                } else if (optionsAnswer == 4) {// see personal info
                    System.out.println("\n" + admin.toString() + "\n");
                    break;
                } else if (optionsAnswer == 5) { //Update personal info
                    while (true) {
                        int updateChoice = Menus.updatePersonalInfo();
                        if (updateChoice == 1) {// update username
                            System.out.print("Enter new username: ");
                            String newUsername = input.nextLine().toLowerCase().trim();
                            if(newUsername.contains("-")){
                                System.out.println("\nUsername can't contain '-' Character\n");
                                continue;
                            }
                            admin.setUserName(newUsername);
                            System.out.println("\nUsername updated successfully\n");
                        } else if (updateChoice == 2) {  //update password
                            System.out.print("Enter new password: ");
                            String newPassword = input.nextLine().trim();
                            if(newPassword.contains("-")){
                                System.out.println("\nPassword can't contain '-' Character\n");
                                continue;
                            }
                            admin.setPassword(newPassword);
                            System.out.println("\nPassword updated successfully\n");
                        } else if (updateChoice == 3) { //update email
                            System.out.print("Enter new email: ");
                            String newEmail = input.nextLine().trim();
                            if(newEmail.contains("-")){
                                System.out.println("\nEmail can't contain '-' Character\n");
                                continue;
                            }
                            else if(!newEmail.contains("@") || !newEmail.contains(".")) {
                                System.out.println("\nInvalid email\n");
                                continue;
                            }
                            admin.setEmail(newEmail);
                            System.out.println("\nEmail updated successfully\n");
                        } else if (updateChoice == 4) { //update phone
                            System.out.print("enter new phone: ");
                            String newPhone = input.nextLine().trim();
                            if(newPhone.contains("-")){
                                System.out.println("\nPhone can't contain '-' Character\n");
                                continue;
                            }
                            
                            admin.setPhone(newPhone);
                            System.out.println("\nPhone updated successfully\n");
                        } else if (updateChoice == 0) {  // back
                            // isBackChosen = true;
                            break;
                        } else {
                            System.out.println("\nEnter a valid option\n");
                        }
                    }
                } else if (optionsAnswer == 6) { //Polymorphism
                    Admin.emptyAllUsers();
                    Admin.FillUsers(admin);
                    int usersCount = 0;
                    for (Person person : Admin.getAllUsers()) { // here polymorphism is used
                        if(!person.getUserName().equals("empty")){
                            System.out.println(person.getBriefInfo());
                            usersCount++;
                        }
                    }
                    System.out.println("\nSystem has a total of " + usersCount + " users \n");
                } 
                else {
                    System.out.println("\nEnter valid option\n");
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