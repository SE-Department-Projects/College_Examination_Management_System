/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javabroject;

import java.util.*;

/**
 *
 * @author DELL
 */
public class JavaBroject {
    
    static Scanner input = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Lecturer lec = new Lecturer();
        int examID;
        int op = 0;

    do {
        try {
            System.out.println("Select operation:");
            System.out.println("1=> Add Exam\n2=> Delete Exam\n3=> Update Exam\n4=> List Exams\n0=> Exit");
            System.out.print("Enter Number Of Operation: ");

            op = input.nextInt();

            switch(op){
                case 1:
                    Exam exam = createExam();
                    addQuestionsToExam(exam);
                    lec.addExam(exam);
                    System.out.println("Exam created successfully!");
                    break;
                case 2:
                    System.out.print("Enter Exam ID to delete: ");
                    examID = input.nextInt();
                    input.nextLine();
                    System.out.println(lec.deleteExam(examID) ? "\nExam Deleted successfully\n" : "\nExam Failure To Delete\n");
                    break;
                case 3:
                    System.out.print("Enter Exam ID to update: ");
                    examID = input.nextInt();
                    input.nextLine();
                    Exam examupdate = new Exam();
                    System.out.println(lec.updateExam(examID, examupdate) ? "\nExam updated successfully." : "\nExam not found.\n");
                    break;
                case 4:
                    examsList(lec);
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid operation. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            input.nextLine();
        }
    } while (op != 0);
    }
    
    public static Exam createExam(){
        
        System.out.println("Enter the subject of the exam: ");
        String subjectName = input.nextLine();
        input.nextLine();
        System.out.println("Enter the duration of the exam (in minutes): ");
        int duration = input.nextInt();             
        return new Exam(subjectName, duration);    
    }
    
    public static void addQuestionsToExam(Exam exam) {
        
        System.out.print("Enter the number of questions in the exam: ");
        int numQuestions = input.nextInt();
        input.nextLine();

        for (int i = 1; i <= numQuestions; i++) {
            System.out.println("Question " + i + ":");
            Question question = createQuestion();
            exam.addQuestion(question);
        }
    }
    
    public static Question createQuestion(){
        
        System.out.print("Enter question text: ");
        String questionText = input.nextLine();
        System.out.print("Enter question type: ");
        String questionType = input.nextLine();

        Question question = new Question(questionText, questionType);
        
        System.out.print("Enter the answer for the question: ");
        String answer = input.next();
        question.setQuestionAnswer(answer);

        return question;
    }
    
    public static void examsList(Lecturer lec){
        System.out.println("List of Exams:");
        System.out.format("%-10s%-16s%-25s\n", "ID", "Subject Name", "Duration");
        ArrayList<String> formattedExams = lec.listExams();

        for (String formattedExam : formattedExams) {
            System.out.println(formattedExam);
        }
        System.out.print("\n");
    }
    
}
