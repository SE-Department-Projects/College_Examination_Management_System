package roles;

import helpers.Functions;
import helpers.Menus;
import models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentRole {

    public static void studentRole(int studentID) {
        Scanner input = new Scanner(System.in);

        Student student = StudentManagement.searchStd(studentID);//the current student

        ArrayList<Subject> subjectArrayList = student.getSubjects();


        int op = 0;
        int subjID;

        do {
          op =   Menus.studentMenu();

            if (op == 1) {
                System.out.println("\nThe Registered Subjects\n");
                System.out.println(student.getSubjectsAsString());
            } else if (op == 2) {
                boolean correctSubjectID = false;

                System.out.println("Select the subject ID to enter the exam or 0 to back: ");

                for (Subject subject : subjectArrayList) {
                    System.out.println(subject.getSubjID() + "=> " + subject.getSubjectName());
                }
                do {
                    System.out.print("Enter your answer: ");
                    subjID = Functions.readInt();

                    for (Subject subj : subjectArrayList) {


                        if (subjID == subj.getSubjID()) {
                            correctSubjectID = true;


//                            subj.setExam(new Exam(subj, 30, 5));
                            Exam exam = subj.getExam();



                            ArrayList<Question> questions = exam.getQuestions();
                            int numberOfQuestions = questions.size();
                            int trueAnswers = 0;

                            System.out.println("\n----------------------------------------------------------------------");
                            System.out.println("------- subject: " + exam.getSubjectName() +
                                    "\t\tduration: " + exam.getDuration() + "min" +
                                    "\t\t" + "number of questions: " + numberOfQuestions);

                            System.out.println("------- for true answer =>  enter true or t\n------- for false Answer => enter false or f");
                            System.out.println("-----------------------------------------------------------------------");

                            for (int i = 1; i <= numberOfQuestions; i++) {
                                System.out.println("\nQ" + i + "- " + questions.get(i - 1).getQuestionText());
                                System.out.print("Answer: ");
                                String studentAnswer = input.nextLine().toLowerCase().trim();
                                String questionAnswer = questions.get(i - 1).getQuestionAnswer();

                                if (studentAnswer.equals(questionAnswer) || studentAnswer.equals(questionAnswer.charAt(0) + "")) {
                                    trueAnswers++;
                                }
                            }

                            int gradeIndex = student.findSubjIndex(subjID);
                            student.getGrades().remove(gradeIndex);
                            student.getGrades().add(gradeIndex,trueAnswers);

                            System.out.println("\nyou got: " + trueAnswers + " out of " + numberOfQuestions);

                            System.out.println("\nthe corrected exam answer: ");

                            for (int i = 1; i <= numberOfQuestions; i++) {
                                System.out.println("\nQ" + i + "- " + questions.get(i - 1).getQuestionText() + " => " + questions.get(i-1).getQuestionAnswer());

                            }
                            break;

                        }

                    }

                    if (!correctSubjectID ) {
                        System.out.println("this ID is wrong enter a valid ID or 0 to back");

                    }

                } while (!correctSubjectID && subjID != 0);

            } else if (op == 0) {
                System.out.println("log out from the system");

            }
        } while (op != 0);

    }

}
