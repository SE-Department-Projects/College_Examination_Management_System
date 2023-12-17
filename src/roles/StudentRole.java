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

                    if (student.getTheAvailableExamsAsString().isEmpty()) {
                        System.out.println("\nNo available exams\n");
                        break;
                    } else {
                        System.out.println("Select the subject ID to enter the exam");

                        System.out.println("The available Exams:\n" + student.getTheAvailableExamsAsString());
                        System.out.println("0=> back");
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
                                System.out.println("\nEnter a valid subject ID\n");
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
                        } else {
                            System.out.println("\nEnter a valid subject id\n");
                        }
                    }

                }
                while (subjID != 0 && !isSuccessfullyExamed);

            } else if (op == 3) { // Finished exams
                if ((student.getTheFinishedExamsAsString().isEmpty())) {
                    System.out.println("\nNo finished exams\n");

                } else {

                    System.out.println("The Finished Exams:\n" + student.getTheFinishedExamsAsString());
                    System.out.println("0=> back");
                    System.out.print("Enter your answer: ");
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

                                int operation;
                                do {
                                    operation = Menus.studentFinishedExamMenu();
                                    if (operation == 1) // see exam grade
                                    {
                                        System.out.println("\nthe grade of  subject: " +
                                                subject.getSubjectName() + " is " +
                                                student.getGrades().get(stdSubjIndex) + "/" + numOfQues+"\n");

                                    } else if (operation == 2) // see corrected exam answer
                                    {
                                        System.out.println("\nthe corrected exam answer: ");
                                        for (int i = 1; i <= numOfQues; i++) {
                                            String text = subject.getExam().getQuestions().get(i - 1).getQuestionText();
                                            String answer = subject.getExam().getQuestions().get(i - 1).getQuestionAnswer();
                                            System.out.println("\nQ" + i + "- " + text + " => " + answer);
                                        }
                                        System.out.println("\n");

                                    } else if (operation == 0) {

                                    } else {
                                        System.out.println("\ninvalid operation number\n");
                                    }
                                } while (operation != 0);
                            }

                        } else {
                            System.out.println("\nSubject not found\n");
                            continue;
                        }
                    }

                }
            } else if (op == 4) { // personal info
                System.out.println(student.toString());
            } else if (op == 5) { // update personal info
                while (true) {
                    int updateOp = Menus.updatePersonalInfo();
                    if (updateOp == 1) {
                        System.out.print("Enter new username: ");
                        String newUsername = input.nextLine().toLowerCase().trim();
                        if(newUsername.equals("-")) {
                            System.out.println("\nUsername can't contain '-' character\n");
                            continue;
                        }
                        int update = student.setUserName(newUsername);
                        if (update == -1) {
                            System.out.println("\nCan't change username to empty");
                        } else if (update == -2) {
                            System.out.println("\nThis username already exists");
                        } else {
                            System.out.println("\nUsername updated successfully");
                        }
                    } else if (updateOp == 2) {
                        System.out.print("Enter new password: ");
                        String newPassword = input.nextLine().trim();
                        if(newPassword.equals("-")) {
                            System.out.println("\nPassword can't contain '-' character\n");
                            continue;
                        }
                        student.setPassword(newPassword);
                        System.out.println("Password updated successfully");
                    } else if (updateOp == 3) {
                        System.out.print("Enter new email: ");
                        String newEmail = input.nextLine().trim();
                        if(newEmail.equals("-")) {
                            System.out.println("\nEmail can't contain '-' character\n");
                            continue;
                        }
                        else if(!newEmail.contains("@") || !newEmail.contains(".")) {
                            System.out.println("\nInvalid email\n");
                            continue;
                        }
                        student.setEmail(newEmail);
                        System.out.println("Email updated successfully");
                    } else if (updateOp == 4) {
                        System.out.print("Enter new phone: ");
                        String newPhone = input.nextLine().trim();
                        if(newPhone.equals("-")) {
                            System.out.println("\nPhone can't contain '-' character\n");
                            continue;
                        }
                        student.setPhone(newPhone);
                        System.out.println("Phone updated successfully");
                    } else if (updateOp == 0) {
                        System.out.println("back to the main menu");
                        break;
                    } else {
                        System.out.println("\n Enter a valid option \n");
                    }
                }
            }

            if (op != 0) {
                System.out.println("==========================================\n");

            }


        } while (op != 0);

        Files.studentIdSubjectFileWriter();
        Files.studentsFileWriter();
        input.close();
    }

}
