import helpers.*;
import models.*;
import roles.*;

import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    public static boolean isBack = true;

    public static void main(String[] args) {

        //!  read from files

        // lecturer file reader

        Files.lecturersFileReader();




        // student file handler

        Files.studentsFileReader();




        // subjects file handler


        Files.subjectsFileReader();


        // std_ID_subject read


        // Files.studentIdSubjectFileReader();



        // lec_ID_subject read


        // Files.lecturerIdSubjectFileReader();





        // sub_ID_exam read


        // Files.subjectIdExamFileReader();




        String username, password;
        int roleNum;
        boolean isLoggedin = false;
        boolean isAuth = false;
        boolean worngAuth = false;

        System.out.println("---- welcome to our system ----");
        
        while (!isLoggedin) { //!isLoggedin

            System.out.println("1=> Admin\n2=> Lecturer\n3=> Student\n0=> exit");

            System.out.print("enter the num of the role: ");
            roleNum = Functions.readInt();
            
            if (roleNum == 0) {
                System.exit(0);
            }

            while (roleNum != 1 && roleNum != 2 && roleNum != 3 && roleNum != 0) {
                System.out.print("this role doesn't exist please enter a valid role num or 0 for exit: ");
                roleNum = Functions.readInt();
                if (roleNum == 0) {
                    System.exit(0);
                }

            }
            while (!isAuth) {
                System.out.print("Enter Username: ");
                username = input.nextLine().toLowerCase();

                System.out.print("Enter Password: ");

                password = input.nextLine().toLowerCase();

                if (roleNum == 1) { // admin role
                    if (Authentication.adminLogin(username, password)) {
                        isAuth = true;
                        isLoggedin = true;
                        System.out.println("login Success");
                        System.out.println("---------------------------\n");

                        Admin admin1 = new Admin(username, password); // will be the admin that returns from the file

                        AdminRole.adminRole();
                        isLoggedin = true;


                    } else {
                        worngAuth = true;
                    }

                } else if (roleNum == 2) { // lecturer role
                    
                    int lecturerID = Authentication.lecturerLogin(username, password);
                    if (lecturerID != -1) {
                        isAuth = true;
                        isLoggedin = true;
                        System.out.println("login Success");
                        System.out.println("---------------------------\n");

                        LecturerRole.lecturerRole(lecturerID);
                        isLoggedin = true;

                    } else {
                        worngAuth = true;
                    }

                } else if (roleNum == 3) { // student role
                    
                    int studentID = Authentication.studentLogin(username, password);
                    if (studentID!= -1) {
                        isAuth = true;
                        isLoggedin = true;
                        System.out.println("login Success");
                        System.out.println("---------------------------\n");

                        StudentRole.studentRole(studentID);
                        isLoggedin = true;
                    } else {
                        worngAuth = true;
                    }
                }

                if (worngAuth) {
                        System.out.println("\nyou are not Authenticated\n 1=> try again\n 0=> logout");
                        int answer = Functions.readInt();
                        if (answer == 0)
                            break;
                        else if (answer == 1)
                            isAuth = false;
                        else {
                            System.out.println("\ninvalid input\n");
                            break;
                        }
                    }
                    
                            }

        }



    }
}