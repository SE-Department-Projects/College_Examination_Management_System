package roles;

import helpers.Functions;
import models.*;

import java.util.ArrayList;
import java.util.Scanner;


public class LecturerRole {

    static public void lecturerRolee(Lecturer lecturer){
        Scanner input = new Scanner(System.in);

        int op;
        boolean isStillOperating = true;

        while(isStillOperating){
            boolean isBackChosen = false;
            System.out.println("choose only one option to manage: ");
            System.out.println("1=> Manage Exams\n2=> Make Report\n0=> exit");
            System.out.print("Enter your answer: ");
            int optionsAnswer = Functions.readInt();


            while (optionsAnswer != 0 && isBackChosen == false) {
                if (optionsAnswer == 1) {
                    do {
                        System.out.println("\nSelect operation");
                        System.out.println("1=> add\n2=> delete\n3=> list\n4=> Back\n0=> exit");
                        System.out.println("\n");
                        System.out.print("enter operation num: ");
                        op = Functions.readInt();
                        if (op == 4) {
                            isBackChosen = true; // set isBackChosen to true to not enter the outer loop, but we set it to false again when he choose an option so he can enter again
                            break; // to exit the inner do while loop
                        }

                        switch (op){
                            case 1:
                            Subject sub = new Subject("IS","IS-203"); //TODO remove this later
                            lecturer.setSubject(sub);
                                System.out.println("You are now adding an exam to subject" + lecturer.getSubject().getSubjectName()); 
                                System.out.println("Enter the Exam duration in minutes: ");
                                int duration = Functions.readPositiveInt();

                                Exam exam1 = new Exam(lecturer.getSubject(), duration);

                                System.out.println("Enter number of questions: ");
                                int questionsNum = Functions.readPositiveInt();

                                for (int i = 1 ; i <= questionsNum ; i++){
                                    System.out.println("Question " + i + ":");
                                    System.out.println("Enter question text: ");
                                    String questionText = input.nextLine();
                                    System.out.println("Enter question Answer(true/false): ");
                                    String questionAnswer = input.nextLine();
                                    Question question = new Question(questionText, questionAnswer);
                                    exam1.addQuestion(question);
                                }
                                System.out.println("Exam added successfully");
                                lecturer.setExam(exam1);
                                break;
                            case 2:
                                boolean isDeleted = false;
                                while(!isDeleted){
                                    System.out.println("Enter the ID of the exam you want to delete or 0 to exit: ");
                                    int examID = Functions.readPositiveORZeroInt();
                                    if (examID == 0)
                                        break;
                                    isDeleted = lecturer.deleteExam(examID);
                                    if (isDeleted)
                                        System.out.println("Exam deleted successfully");
                                    else{
                                        System.out.println("Exam not found");
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("List of Exams:");
                                System.out.printf("%-10s%-16s%-25s\n","ID","Subject Name","Duration");
                                ArrayList<Exam> exams = lecturer.getExams();
                                for ( Exam exam : exams) {
                                    
                                    System.out.printf("%-10s%-16s%-25s",exam.getExamID(),exam.getSubjectName(),exam.getDuration());
                                    System.out.print("\n");
                                }
                                break;
                            case 0:
                                System.out.println("logout successfully");
                                isBackChosen = true;
                                isStillOperating = false;
                                break;

                            default:
                                System.out.println("can not find the operation");

                        }

                        
                    }while (op != 0 && op != 6);

                }
                else if (optionsAnswer == 2) { // TODO change REPORT COMPLETELY
                    System.out.printf("%-10s%-16s%-25s\n","ID","Name","Degree");
                    System.out.println("\n");
                    //! this works, but the array is empty try running the comment underneath and it will list them
                    ArrayList<Student> students = StudentManagement.getStudentArray();
                    // ArrayList<Student> students = new ArrayList<>();
                    // students.add(new Student("ss", "ss"));
                    // students.add(new Student("sssssssss", "ss"));
                    for(Student std : students){
                        System.out.printf("%-10s%-16s%-25s",std.getID(),std.getUserName(),std.getDegree());
                        System.out.print("\n");
                    }
                    break;

                }
                else {
                    System.out.print("enter valid option to manage or 0 to exit: ");
                    optionsAnswer = Functions.readInt();
                }
            }
            if (optionsAnswer == 0) {
                isStillOperating = false;
            }
        }


    }
}
