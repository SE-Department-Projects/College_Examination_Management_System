package roles;

import helpers.*;
import models.*;

import java.util.Date;
import java.util.Scanner;

public class LecturerRole {

    static public void lecturerRole(int LecId) {
        int LecIndex = LecturerManagement.findLecIndex(LecId);
        Lecturer lecturer = LecturerManagement.searchLecturer(LecIndex);
        Scanner input = new Scanner(System.in);

        // read from files
        Files.lecturerIdSubjectFileReader(); // this is used in every choice so we read it here
        Files.subjectIdExamFileReader(); // this is used in every choice so we read it here
        Files.studentIdSubjectFileReader();
        Files.reportsHeadFileReader();

        int op;
        boolean isStillOperating = true;

        while (isStillOperating) {
            boolean isBackChosen = false;

            int optionsAnswer = Menus.lecturerManageMenu();

            while (optionsAnswer != 0 && isBackChosen == false) {
                if (optionsAnswer == 1) { //Manage Exams
                    if (lecturer.getLecturerSubjects().isEmpty()) {
                        System.out.println("\nYou don't have subjects to Manage exams\n");
                        break;
                    }
                    do {

                        op = Menus.lecturerManageExamMenu();
                        if (op == 4) {
                            isBackChosen = true; // set isBackChosen to true to not enter the outer loop, but we set it
                            // to false again when he choose an option so he can enter again
                            break; // to exit the inner do while loop
                        }

                        switch (op) {
                            case 1:
                                System.out.println(lecturer.getSubjectstoChooseFrom());
                                System.out.println("0=> Back");
                                System.out.print("Enter your answer: ");
                                int subjID = Functions.readPositiveORZeroInt();
                                if (subjID == 0)
                                    break;
                                int index = lecturer.findSubjIndex(subjID);
                                if (index == -1) {
                                    System.out.println("\nSubject not found");
                                    break;
                                }
                                Subject subject = lecturer.getSubject(index);
                                if (subject.isExamCreated()) {
                                    System.out.println("\nThis subject already has an exam");
                                    break;
                                }

                                System.out
                                        .println("\nYou are now adding an exam to subject: " + subject.getSubjectName());
                                Exam exam1 = new Exam(subject, subjID);

                                System.out.print("Enter number of questions: ");
                                int questionsNum = Functions.readPositiveInt();

                                for (int i = 1; i <= questionsNum; i++) {
                                    System.out.println("Question " + i + ":");
                                    System.out.print("Enter question text: ");
                                    String questionText = input.nextLine();
                                    boolean isAnswerValid = false;
                                    while (!isAnswerValid) {
                                        System.out.print("Enter question Answer(true/false): ");
                                        String questionAnswer = input.nextLine().toLowerCase().trim();
                                        if (questionAnswer.equals("true")||questionAnswer.equals("t")) {
                                            isAnswerValid = true;
                                            Question question = new Question(questionText, "true");
                                            exam1.addQuestion(question);
                                        }
                                        else if(questionAnswer.equals("false") || questionAnswer.equals("f")){
                                            isAnswerValid = true;
                                            Question question = new Question(questionText, "false");
                                            exam1.addQuestion(question);
                                        }
                                        else {
                                            System.out.println("Answer must be true or false");
                                        }
                                    }
                                }
                                System.out.println("Exam added successfully");
                                lecturer.addExam(exam1, subject);

                                break;

                            case 2:
                                System.out.println("\n" + lecturer.getSubjectsWithExams() + "\n");
                                System.out.println("0=> Exit");
                                System.out.print("Enter your answer: ");
                                subjID = Functions.readPositiveORZeroInt();
                                if (subjID == 0)
                                    break;
                                index = lecturer.findSubjIndex(subjID);
                                if (index == -1) {
                                    System.out.println("\nSubject not found");
                                    break;
                                }
                                subject = lecturer.getSubject(index);
                                if (lecturer.deleteExam(subject)) {
                                    System.out.println("Exam deleted successfully");
                                } else {
                                    System.out.println("Exam not found");
                                }
                                break;

                            case 3:
                                System.out.println("List of Exams:");
                                System.out.printf("%-10s%-16s\n", "ID", "Subject Name");
                                for (Subject subject1 : lecturer.getLecturerSubjects()) {
                                    if (subject1.isExamCreated())
                                        System.out.printf("%-10s%-16s\n", subject1.getSubjID(),
                                                subject1.getSubjectName());
                                }
                                break;

                            case 0:
                                isBackChosen = true;
                                isStillOperating = false;
                                break;

                            default:
                                System.out.println("can not find the operation");

                        }

                        if (op != 0)
                        {
                            System.out.println("==========================================\n");
                        }

                    } while (op != 0 && op != 6);
                    Files.lecturersFileWriter();
                    Files.subjectIdExamFileWriter(lecturer);


                } else if (optionsAnswer == 2) { // manage reports
                    while (true) {

                        op = Menus.lecturerManagerReports();
                        if (op == 0) {
                            isBackChosen = true;
                            break;
                        } else if (op == 1) { // generate report
                            System.out.println("\n");
                            System.out.println(lecturer.getSubjectstoChooseFrom());
                            System.out.println("0=> Back");
                            System.out.print("Enter your answer: ");
                            int subjID = Functions.readPositiveORZeroInt();
                            if (subjID == 0)
                                break;
                            int index = lecturer.findSubjIndex(subjID);
                            if (index == -1) {
                                System.out.println("\nSubject not found");
                                break;
                            }
                            Subject subject = lecturer.getSubject(index);

                            if (!subject.isExamCreated()) {
                                System.out.println("\nThis subject doesn't have an exam to make report on");
                                break;
                            }

                            Report report = new Report(subjID, lecturer.getUserName(), new Date().toString(), "");
                            System.out.println(report.makeReport(subject));
                            LecturerManagement.addReport(report);
                            Files.reportsFileWriter(report);
                            Files.reportsHeadsFileWriter(report);
                            break;
                        } else if (op == 2) { // view reports
                            System.out.println(lecturer.getSubjectstoChooseFrom());
                            System.out.println("0=> Back");
                            System.out.print("Enter your answer: ");
                            int subjID = Functions.readPositiveORZeroInt();
                            if (subjID == 0)
                                break;
                            int index = lecturer.findSubjIndex(subjID);
                            if (index == -1) {
                                System.out.println("\nSubject not found");
                                break;
                            } else {
                                System.out.println(LecturerManagement.getReportsOfSubject(subjID));
                                System.out.println("0=> Back");
                                System.out.print("Enter your answer: ");
                                int reportID = Functions.readPositiveORZeroInt();
                                if (reportID == 0)
                                    break;
                                index = LecturerManagement.findReportIndex(reportID);
                                if (index != -1) {
                                    if (LecturerManagement.searchReport(index).getSubjectID() == subjID) {
                                        reportID = LecturerManagement.searchReport(index).getReportID();
                                        Report report = Files.reportFileReader(reportID);
                                        String reportData = report.getReportData();
                                        if (reportData.equals("0") || reportData.equals("1")) {
                                            System.out.println("\nNo data in this report");
                                        } else {
                                            System.out.printf("%-10s%-16s%-25s\n", "ID", "Name", "Degree");
                                            System.out.println("\n");
                                            String[] students = reportData.split("/");
                                            for (String student : students) {
                                                String[] studentData = student.split(",");
                                                if (studentData[2].equals("-1")) {
                                                    studentData[2] = "Not taken yet";
                                                }
                                                System.out.printf("%-10s%-16s%-25s\n", studentData[0], studentData[1], studentData[2]);
                                            }
                                        }
                                    } else {
                                        System.out.println("\nReport not found");

                                    }
                                } else {
                                    System.out.println("\nReport not found");

                                }
                            }
                        } else {
                            System.out.println("\nInvalid input");
                        }
                    }
                } else if (optionsAnswer == 3) {
                    System.out.println("\n\n" + lecturer.toString() + "\n\n");
                    break;

                } else if (optionsAnswer == 4) { // update personal info

                    int updateAnswer = Menus.updatePersonalInfo();
                    if (updateAnswer == 1) { // update username
                        System.out.print("Enter new username: ");
                        String newUsername = input.nextLine().toLowerCase().trim();
                        int update = lecturer.setUserName(newUsername);
                        if (update == -1) {
                            System.out.println("\nCan't change username to empty");
                        } else if (update == -2) {
                            System.out.println("\nThis username already exists");
                        } else {
                            System.out.println("\nUsername updated successfully");
                        }
                    } else if (updateAnswer == 2) { // update password
                        System.out.print("Enter new password: ");
                        String newPassword = input.nextLine().trim();
                        lecturer.setPassword(newPassword);
                        System.out.println("Password updated successfully");
                    } else if (updateAnswer == 3) { // update email
                        System.out.print("Enter new email: ");
                        String newEmail = input.nextLine().trim();
                        lecturer.setEmail(newEmail);
                        System.out.println("Email updated successfully");
                    } else if (updateAnswer == 4) { // update phone
                        System.out.print("Enter new phone: ");
                        String newPhone = input.nextLine().trim();
                        lecturer.setPhone(newPhone);
                        System.out.println("Phone updated successfully");
                    } else if (updateAnswer == 0) {
                        isBackChosen = true;
                        break;
                    } else {
                        System.out.println("Invalid input");
                    }
                    Files.lecturersFileWriter();
                } else {
                    System.out.print("enter valid option to manage or 0 to exit: ");
                    optionsAnswer = Functions.readInt();
                }
                System.out.println("==========================================\n");
            }
            if (optionsAnswer == 0) {
                isStillOperating = false;
            }
        }
        input.close();
    }
}
