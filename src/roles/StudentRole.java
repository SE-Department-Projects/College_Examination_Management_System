package roles;

import helpers.Files;
import helpers.Functions;
import helpers.Menus;
import models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentRole {

    public static void studentRole(int studentID) {
        Scanner input = new Scanner(System.in);

        Files.subjectsFileReader();
        Files.studentIdSubjectFileReader();
        Files.subjectIdExamFileReader();
        Files.studentsFileReader();
        Files.lecturerIdSubjectFileReader();
        Files.lecturersFileReader();

        int stdIndex = StudentManagement.findStdIndex(studentID);       //get the current student index
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
                boolean correctSubjectID = false;

                System.out.println("Select the subject ID to enter the exam or 0 to back");

                System.out.println("The available Exams:\n" + student.getTheAvailableExams());


                do {

                    System.out.print("Enter your answer: ");
                    subjID = Functions.readPositiveORZeroInt();


                    subjectIndex = SubjectManagement.findSubjIndex(subjID);

                    if (subjectIndex != -1) { // this subject is found
                        subject = SubjectManagement.searchSubject(subjectIndex);

                        if (subjID == subject.getSubjID() && student.getExamGrade(subjID) == -1) { // not take the exam yet


                            Exam exam = subject.getExam();    // get the exam

                            ArrayList<Question> questions = exam.getQuestions(); // exam questions
                            int numberOfQuestions = questions.size();  // num of questions
                            int trueAnswers = 0;   // to count the correct answers

                            System.out.println("\n----------------------------------------------------------------------");
                            System.out.println("------- subject: " + subject.getSubjectName() +
                                    "\t\t" + "number of questions: " + numberOfQuestions);

                            System.out.println("------- for true answer =>  enter true or t\n------- for false Answer => enter false or f");
                            System.out.println("-----------------------------------------------------------------------");

                            for (int i = 1; i <= numberOfQuestions; i++) {
                                boolean correctAnswerFormat = false;
                                System.out.println("\nQ" + i + "- " + questions.get(i - 1).getQuestionText());


                                do {
                                    System.out.print("Answer: ");
                                    String studentAnswer = input.nextLine().toLowerCase().trim();
                                    String questionAnswer = questions.get(i - 1).getQuestionAnswer();

                                    if (studentAnswer.equals("true") || studentAnswer.equals("t") || studentAnswer.equals("false") || studentAnswer.equals("f")) {
                                        if (studentAnswer.equals(questionAnswer) || studentAnswer.equals(questionAnswer.charAt(0) + "")) {
                                            trueAnswers++;
                                        }

                                        correctAnswerFormat = true;
                                    }

                                    else
                                    {
                                        System.out.println("enter correct answer format (true / false)");
                                    }


                                } while (!correctAnswerFormat);


                            }

                            int gradeIndex = student.findSubjIndex(subjID);
                            student.getGrades().remove(gradeIndex);
                            student.getGrades().add(gradeIndex, trueAnswers);


                            break;

                        } else {
                            System.out.println("your already take this Exam");
                        }
                    } else {
                        System.out.println("enter a valid subject id");
                    }


                } while (subjID != 0);

            } else if (op == 3) {
                System.out.println("The Finished Exams: ");
                System.out.println("Select the subject ID to enter the exam or 0 to back");

                System.out.println("The Finished Exams:\n" + student.getTheFinishedExams());

                System.out.print("enter subject ID to see the exam details: ");
                subjID = Functions.readPositiveORZeroInt();
                int operation = Menus.studentFinishedExamMenu();

                subjectIndex = SubjectManagement.findSubjIndex(subjID);
                subject = SubjectManagement.searchSubject(subjectIndex);
                int numOfQues = subject.getExam().numOfQuestions();

                if (operation == 1)  // see exam grade
                {


                    System.out.println("the grade of  subject: " +
                            subject.getSubjectName() + " is " +
                            student.getExamGrade(subjID) + "out of" + numOfQues);
                } else if (operation == 2) // see corrected exam answer
                {
                    System.out.println("\nthe corrected exam answer: ");
//
                    for (int i = 1; i <= numOfQues; i++) {
                        String text = subject.getExam().getQuestions().get(i - 1).getQuestionText();
                        String answer = subject.getExam().getQuestions().get(i - 1).getQuestionAnswer();
                        System.out.println("\nQ" + i + "- " + text + " => " + answer);

                    }
                }


            } else if (op == 0) {
                System.out.println("log out from the system");

            }
        } while (op != 0);

        for (
                Student std : StudentManagement.getStudentArray()) {
            FileHandler stdIdSubjects = new FileHandler("std_" + std.getID() + "_subjects.txt");

            stdIdSubjects.emptyFile();
            for (int i = 0; i < std.getSubjects().size(); i++) {
                stdIdSubjects.writeFile(std.getSubjects().get(i).getSubjID() + "", true);
                stdIdSubjects.writeFile(std.getGrades().get(i) + "", true);
            }
        }

    }


}
