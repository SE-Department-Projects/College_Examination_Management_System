package roles;

import helpers.Functions;
import models.*;

import java.util.ArrayList;
import java.util.Scanner;


public class LecturerRole {

    static public void lecturerRole(Lecturer lecturer){
        //TODO Handle lecturer param
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
                    if(lecturer.getLecturerSubjects().isEmpty()){
                        System.out.println("You don't have subjects to Manage exams");
                        break;
                        }
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
                                for (Subject subject : lecturer.getLecturerSubjects()) {
                                    System.out.println(subject.getSubjID() + "=>" + subject.getSubjectName());
                                }
                                System.out.println("Choose the subject you want to add an exam to or 0 to exit: ");
                                int subjID = Functions.readPositiveORZeroInt();
                                if (subjID == 0)
                                    break;
                                int index = SubjectManagement.findSubjIndex(subjID);
                                if (index == -1){
                                    System.out.println("Subject not found");
                                    break;
                                }
                                Subject subject = SubjectManagement.searchSubject(index);
                                System.out.println("You are now adding an exam to subject: " + subject.getSubjectName()); 
                                System.out.println("Enter the Exam duration in minutes: ");
                                int duration = Functions.readPositiveInt();

                                Exam exam1 = new Exam(subject, duration, subjID);

                                System.out.println("Enter number of questions: ");
                                int questionsNum = Functions.readPositiveInt();

                                for (int i = 1 ; i <= questionsNum ; i++){
                                    System.out.println("Question " + i + ":");
                                    System.out.println("Enter question text: ");
                                    String questionText = input.nextLine();
                                    boolean isAnswerValid = false;
                                    while(!isAnswerValid){
                                        System.out.println("Enter question Answer(true/false): ");
                                        String questionAnswer = input.nextLine();
                                        if (questionAnswer.equalsIgnoreCase("true") || questionAnswer.equalsIgnoreCase("false")){
                                            isAnswerValid = true;
                                            Question question = new Question(questionText, questionAnswer);
                                            exam1.addQuestion(question);
                                        }
                                        else{
                                            System.out.println("Answer must be true or false");
                                        }
                                    }
                                }
                                System.out.println("Exam added successfully");
                                lecturer.addExam(exam1,subject);
                                break;

                            case 2:
                                for (Subject subject1 : lecturer.getLecturerSubjects()) {
                                    System.out.println(subject1.getSubjID() + " " + subject1.getSubjectName());
                                }
                                System.out.println("Choose the subject you want to delete an exam or 0 to exit: ");
                                subjID = Functions.readPositiveORZeroInt();
                                if (subjID == 0)
                                    break;
                                index = SubjectManagement.findSubjIndex(subjID);
                                if (index == -1){
                                    System.out.println("Subject not found");
                                    break;
                                }
                                subject = SubjectManagement.searchSubject(index);
                                System.out.println("You are now deleting the exam from subject" + subject.getSubjectName());
                                if (lecturer.deleteExam(subject)){
                                    System.out.println("Exam deleted successfully");
                                }
                                else{
                                    System.out.println("Exam not found");
                                }

                            case 3:
                            System.out.println("List of Exams:");
                            System.out.printf("%-10s%-16s%-25s\n","ID","Subject Name","Duration");
                            for (Subject subject1 : lecturer.getLecturerSubjects()) {
                                System.out.println(subject1.getExam().getExamID() +subject1.getSubjectName()+subject1.getExam().getDuration());
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
                    if(lecturer.getLecturerSubjects().isEmpty()){
                        System.out.println("You don't have subjects to Make Reports");
                        break;
                    }
                    for (Subject subject : lecturer.getLecturerSubjects()) {
                        System.out.println(subject.getSubjID() + " " + subject.getSubjectName());
                    }
                    System.out.println("Choose the subject you want to make report on or 0 to exit: ");
                    int subjID = Functions.readPositiveORZeroInt();
                    if (subjID == 0)
                        break;
                    int index = SubjectManagement.findSubjIndex(subjID);
                    if (index == -1){
                        System.out.println("Subject not found");
                        break;
                    }
                    Subject subject = SubjectManagement.searchSubject(index);
                    // System.out.println("You are now adding an exam to subject" + subject.getSubjectName()); 
                    System.out.printf("%-10s%-16s%-25s\n","ID","Name","Degree");
                    System.out.println("\n");
                    ArrayList<Student> students = StudentManagement.getStudentArray();
                    for(Student std : students){
                        if (std.getSubjects().contains(subject)){
                            System.out.printf("%-10s%-16s%-25s",std.getID(),std.getUserName(),std.getDegree());
                            System.out.print("\n");
                        }
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
