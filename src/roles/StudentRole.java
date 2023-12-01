package roles;

import helpers.Functions;
import models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentRole {

    public static void studentRole(Student student) {
        Scanner input = new Scanner(System.in);

        ArrayList<Subject> subjectArrayList = student.getSubjects();


        int op = 0;
        int subjID;

        do {
            System.out.println("choose only one option to manage: ");
            System.out.println("1=> See Registered Subjects\n2=> get Exam(s)\n0=> exit");
            System.out.print("Enter your answer: ");
            op = Functions.readInt();

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
                            System.out.println("ssaaaaaaah");
                            correctSubjectID = true;
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
