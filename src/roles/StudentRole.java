package roles;

import models.*;
import helpers.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentRole {

    public static void studentRole(int studentID) {
        Scanner input = new Scanner(System.in);

        Files.studentIdSubjectFileReader();
        Files.subjectIdExamFileReader();

        int stdIndex = StudentManagement.findStdIndex(studentID); // get the current student index
        Student student = StudentManagement.searchStd(stdIndex);

        int op = 0;
        int subjID;
        Subject subject;
        int subjectIndex;

        do {
            op = Menus.studentMenu();

            if (op == 1) { // see the Registered
                System.out.println("\nThe Registered Subjects\n");
                System.out.println(student.getSubjectsAsString());

            } else if (op == 2) { // get Exam
                // boolean correctSubjectID = false;

                boolean isSuccessfullyExamed = false;
                do {
                    System.out.println("Select the subject ID to enter the exam");

                    System.out.println("The available Exams:\n" + student.getTheAvailableExamsAsString());
                    System.out.println("0 => Back");

                    System.out.print("Enter your answer: ");
                    subjID = Functions.readPositiveORZeroInt();

                    if (subjID == 0) {
                        break;
                    }
                    subjectIndex = student.findSubjIndex(subjID);

                    if (subjectIndex != -1) { // this subject is found
                        boolean didStudentTakeExam = student.getGrades().get(subjectIndex) != -1;
                        boolean doesSubjecthaveExam = student.getSubjects().get(subjectIndex).isExamCreated();
                        if (!doesSubjecthaveExam || didStudentTakeExam) {
                            isSuccessfullyExamed = false;
                            System.out.println("exam doesn't exist or already taken");
                            continue;
                        }
                        subject = student.getSubjects().get(subjectIndex);

                        Exam exam = subject.getExam(); // get the exam

                        ArrayList<Question> questions = exam.getQuestions(); // exam questions
                        int numberOfQuestions = questions.size(); // num of questions
                        int trueAnswers = 0; // to count the correct answers

                        System.out.println(
                                "\n----------------------------------------------------------------------");
                        System.out.println("------- subject: " + subject.getSubjectName() +
                                "\t\t" + "number of questions: " + numberOfQuestions);

                        System.out.println(
                                "------- for true answer =>  enter true or t\n------- for false Answer => enter false or f");
                        System.out
                                .println("-----------------------------------------------------------------------");

                        for (int i = 1; i <= numberOfQuestions; i++) {
                            boolean correctAnswerFormat = false;
                            System.out.println("\nQ" + i + "- " + questions.get(i - 1).getQuestionText());

                            do {
                                System.out.print("Answer: ");
                                String studentAnswer = input.nextLine().toLowerCase().trim();
                                String questionAnswer = questions.get(i - 1).getQuestionAnswer();

                                if (studentAnswer.equals("true") || studentAnswer.equals("t")
                                        || studentAnswer.equals("false") || studentAnswer.equals("f")) {
                                    if (studentAnswer.equals(questionAnswer)
                                            || studentAnswer.equals(questionAnswer.charAt(0) + "")) {
                                        trueAnswers++;
                                    }

                                    correctAnswerFormat = true;
                                } else {
                                    System.out.println("enter correct answer format (true / false)");
                                }

                            } while (!correctAnswerFormat);

                        }

                        int gradeIndex = student.findSubjIndex(subjID);
                        student.getGrades().remove(gradeIndex);
                        student.getGrades().add(gradeIndex, trueAnswers);
                        isSuccessfullyExamed = true;
                        // break;
                    } else {
                        System.out.println("enter a valid subject id");
                    }

                } while (subjID != 0 && !isSuccessfullyExamed);

            } else if (op == 3) {
                boolean isFinishedExamsDone = false;
                while (!isFinishedExamsDone) {
                    System.out.println("The Finished Exams:\n" + student.getTheFinishedExamsAsString());
                    System.out.println("0 => Back");
                    System.out.print("enter subject ID to see the exam details: ");
                    subjID = Functions.readPositiveORZeroInt();
                    if (subjID != 0) {
                        int stdSubjIndex = student.findSubjIndex(subjID);
                        if (stdSubjIndex != -1) {
                            subject = student.getSubject(stdSubjIndex); 
                            if (student.getGrades().get(stdSubjIndex) == -1) {
                                System.out.println("invalid input");
                                continue;
                            } else {
                                int numOfQues = subject.getExam().getQuestions().size();
                                int operation = Menus.studentFinishedExamMenu();
                                if (operation == 1) // see exam grade
                                {
                                    System.out.println("the grade of  subject: " +
                                            subject.getSubjectName() + " is " +
                                            student.getGrades().get(stdSubjIndex) + "/" + numOfQues);
                                } else if (operation == 2) // see corrected exam answer
                                {
                                    System.out.println("\nthe corrected exam answer: ");
                                    for (int i = 1; i <= numOfQues; i++) {
                                        String text = subject.getExam().getQuestions().get(i - 1).getQuestionText();
                                        String answer = subject.getExam().getQuestions().get(i - 1).getQuestionAnswer();
                                        System.out.println("\nQ" + i + "- " + text + " => " + answer);
                                    }
                                }
                            }
                        } else {
                            System.out.println("\nSubject not found\n");
                            isFinishedExamsDone = false;
                            continue;
                        }
                    } else { // subjID = 0
                        break;
                    }
                }
            } 
            else if (op == 4) {
                System.out.println(student.toString());
            } 
            else if (op == 5) {
                while(true){
                    int updateOp = Menus.updatePersonalInfo();
                    if (updateOp == 1) {
                        System.out.print("Enter new username: ");
                        String newUsername = input.nextLine().toLowerCase().trim();
                        student.setUserName(newUsername);
                        System.out.println("Username updated successfully");
                    } else if (updateOp == 2) {
                        System.out.print("Enter new password: ");
                        String newPassword = input.nextLine().trim();
                        student.setPassword(newPassword);
                        System.out.println("Password updated successfully");
                    } else if (updateOp == 3) {
                        System.out.print("Enter new email: ");
                        String newEmail = input.nextLine().trim();
                        student.setEmail(newEmail);
                        System.out.println("Email updated successfully");
                    } else if (updateOp == 4) {
                        System.out.print("Enter new phone: ");
                        String newPhone = input.nextLine().trim();
                        student.setPhone(newPhone);
                        System.out.println("Phone updated successfully");
                    } else if (updateOp == 0) {
                        System.out.println("back to the main menu");
                        break;
                    }
                    else{
                        System.out.println("\n Enter a valid option \n");
                    }
                }
            }
            else if (op == 0) {
                System.out.println("log out from the system");

            }

            if(op != 0)
            {
                System.out.println("==========================================\n");

            }


        } while (op != 0);

        Files.studentIdSubjectFileWriter();
        Files.studentsFileWriter();
    input.close();
    }

}
