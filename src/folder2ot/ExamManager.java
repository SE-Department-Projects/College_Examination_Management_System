/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package folder2ot;

import java.util.*;

/**
 *
 * @author DELL
 */
public class ExamManager {
    static Scanner input = new Scanner(System.in);

    //Others Methods to Add Exam.
    public static Exam createExam(){
        
        System.out.println("Enter the subject of the exam: ");
        String subjectName = input.nextLine();
        input.nextLine();
        System.out.println("Enter the duration of the exam (in minutes): ");
        int duration = input.nextInt();             
        return new Exam(subjectName, duration);    
    }
    
    public static Question createQuestion(){
        
        System.out.print("Enter question text: ");
        String questionText = input.nextLine();
//        System.out.print("Enter question type: ");
//        String questionType = input.nextLine();

        Question question = new Question(questionText);
        
        System.out.print("Enter the answer for the question: ");
        String answer = input.nextLine();
        question.setQuestionAnswer(answer);

        return question;
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
    
    //Others Methods to List Exams.
    public static void examsList(LecturerRole lec){
        System.out.println("List of Exams:");
        System.out.format("%-10s%-16s%-25s\n","ID","Subject Name","Duration");
        ArrayList<String> formattedExams = lec.listExams();

        for (String formattedExam : formattedExams) {
            System.out.println(formattedExam);
        }
        System.out.print("\n");
    }
    
//    public static void reportGenerate(LecturerRole lec){
//        System.out.println("Report Of Degrees");
//        System.out.format("%-20s%-20s%-20s%-20s\n","Student ID","Student Name","Exam ID","Grade");
//        ArrayList<String> formattedReports = lec.generateReport();
//
//        for (String formattedReport : formattedReports) {
//            System.out.println(formattedReport);
//        }
//        System.out.print("\n");
//    }
}
