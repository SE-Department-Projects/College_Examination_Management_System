/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package java_project;

import java.util.*;
/**
 *
 * @author DELL
 */
public class Java_Project {
    
    static Scanner input = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LecturerRole lec = new LecturerRole();
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
                    lec.addExam();
                    System.out.println("Exam created successfully!");
                    break;
                case 2:
                    System.out.print("Enter Exam ID to delete: ");
                    examID = input.nextInt();
                    System.out.println(lec.deleteExam(examID) ? "\nThe Exam in ID " +examID+ " Deleted successfully\n" : "\n---The Exam failure to Delete---\n");
                    System.out.println("---------------");
                    break;
                case 3:
                    System.out.print("Enter Exam ID to update: ");
                    examID = input.nextInt();
                    input.nextLine();
                    Exam examupdate = new Exam();
                    System.out.println(lec.updateExam(examID, examupdate) ? "\nExam updated successfully." : "\nExam not found.\n");
                    break;
                case 4:
                    ExamManager.examsList(lec);
                    break;
//                case 5:
//                    ExamManager.reportGenerate(lec);
//                    break;
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
    
}
