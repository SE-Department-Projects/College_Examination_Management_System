package roles;

import helpers.*;
import models.*;

import java.util.ArrayList;
import java.util.Scanner;


public class LecturerRole {

    static public void lecturerRole(int LecId){
        int LecIndex = LecturerManagement.findLecIndex(LecId);
        Lecturer lecturer = LecturerManagement.searchLecturer(LecIndex);
        Scanner input = new Scanner(System.in);
        boolean isMakeReportChosenBefore = false;

        // read from files
        Files.lecturerIdSubjectFileReader(); // this is used in every choice so we read it here
        Files.subjectIdExamFileReader(); // this is used in every choice so we read it here

        int op;
        boolean isStillOperating = true;

        while(isStillOperating){
            boolean isBackChosen = false;
            System.out.println("choose only one option to manage: ");
            System.out.println("1=> Manage Exams\n2=> Make Report\n3=> Show personal Info\n4=> Update personal Info\n0=> exit");
            System.out.print("Enter your answer: ");
            int optionsAnswer = Functions.readInt();


            while (optionsAnswer != 0 && isBackChosen == false) {
                if (optionsAnswer == 1) {
                    if(lecturer.getLecturerSubjects().isEmpty()){
                        System.out.println("\nYou don't have subjects to Manage exams\n");
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

                                //TODO would you like to delete the existing exam and add a new one?
                                if (subject.isExamCreated()){
                                    System.out.println("This subject already has an exam, you should delete it first");
                                    break;
                                }

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
                                // System.out.println("You are now deleting the exam from subject" + subject.getSubjectName());
                                if (lecturer.deleteExam(subject)){
                                    System.out.println("Exam deleted successfully");
                                }
                                else{
                                    System.out.println("Exam not found");
                                }
                                break;

                            case 3:
                            System.out.println("List of Exams:");
                            System.out.printf("%-10s%-16s%-25s\n","ID","Subject Name","Duration");
                            for (Subject subject1 : lecturer.getLecturerSubjects()) {
                                if(subject1.isExamCreated())
                                    System.out.printf("%-10s%-16s%-25s\n",subject1.getExam().getExamID(), subject1.getSubjectName(), subject1.getExam().getDuration());
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

                    Files.lecturersFileWriter();
                    Files.subjectIdExamFileWriter(lecturer);
                }
                else if (optionsAnswer == 2) {
                    if(isMakeReportChosenBefore == false){
                        isMakeReportChosenBefore = true;
                        Files.studentIdSubjectFileReader();
                    }
                    if(lecturer.getLecturerSubjects().isEmpty()){
                        System.out.println("\nYou don't have subjects to Make Reports\n");
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
                    System.out.printf("%-10s%-16s%-25s\n","ID","Name","Degree");
                    System.out.println("\n");
                    ArrayList<Student> students = StudentManagement.getStudentArray();
                    for(int i = 0 ; i < students.size() ; i++){
                        if (students.get(i).getSubjects().contains(subject)){
                            index = students.get(i).getSubjects().indexOf(subject);
                            if (students.get(i).getGrades().get(index) == -1){
                            System.out.printf("%-10s%-16s%-25s",students.get(i).getID(),students.get(i).getUserName()," Not Taken Yet");
                            System.out.print("\n");
                            }
                            else{
                            System.out.printf("%-10s%-16s%-25s",students.get(i).getID(),students.get(i).getUserName(),students.get(i).getGrades().get(index));
                            System.out.print("\n");
                            }
                        }
                    }
                    break;
                }
                else if (optionsAnswer == 3){
                    // TODO show personal info with toString
                    break;

                }
                else if (optionsAnswer == 4){ // update personal info 
                    System.out.println("1=> Update username\n2=> Update password\n3=> Back");
                    System.out.print("Enter your answer: ");
                    int updateAnswer = Functions.readPositiveInt();
                    if(updateAnswer == 1){
                        System.out.print("Enter new username: ");
                        String newUsername = input.nextLine();
                        lecturer.setUserName(newUsername);
                        System.out.println("Username updated successfully");
                    }
                    else if(updateAnswer == 2){
                        System.out.print("Enter new password: ");
                        String newPassword = input.nextLine();
                        lecturer.setPassword(newPassword);
                        System.out.println("Password updated successfully");
                    }
                    else if(updateAnswer == 3){
                        isBackChosen = true;
                        break;
                    }
                    else{
                        System.out.println("Invalid input");
                    }
                    Files.lecturersFileWriter();
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
