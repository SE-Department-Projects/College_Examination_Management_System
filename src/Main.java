import helpers.*;
import models.*;
import roles.*;

import java.io.File;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    public static boolean isBack = true;

    public static void main(String[] args) {
        LecturerManagement lec = new LecturerManagement();

        // File handeling (filling the arrays with the data from the file)

        FileHandler lecturerFileHandler = new FileHandler("Files/lecturers.txt");  // lecturer file handler

        lecturerFileHandler.createFile(); // create the file if it doesn't exist

        String lecturersData = lecturerFileHandler.readFile(); // read the file

        if (lecturersData != "") { // if the file is not empty

            String[] lecturers = lecturersData.split("\n"); // split the data into lines
            for (String line : lecturers) { // loop through the lines
                if(line.matches("\\d+-[A-z]+-\\w+")){ // if the line matches the pattern (id-username-password)
                    String[] data = line.split("-"); // split the line into id, username, password
                    Lecturer lecturer1 = new Lecturer(data[1],data[2]); // create a new lecturer object (username, password)
                    LecturerManagement.addLecturer(lecturer1); // add the lecturer to the array
                }
            }
        }






        FileHandler studentFileHandler = new FileHandler("Files/students.txt"); // student file handler

        studentFileHandler.createFile(); // create the file if it doesn't exist

        String studentsData = studentFileHandler.readFile(); // read the file

        if(studentsData != ""){ // if the file is not empty

            String[] students = studentsData.split("\n"); // split the data into lines

            for (String line : students) { // loop through the lines

                if(line.matches("\\d+-[A-z]+-\\w+")){ // if the line matches the pattern (id-username-password)

                    String[] data = line.split("-"); // split the line into id, username, password
                    Student student1 = new Student(data[1],data[2]); // create a new student object (username, password)
                    StudentManagement.addStd(student1); // add the student to the array

                }
            }
        }





        FileHandler subjectsFileHandler = new FileHandler("Files/subjects.txt"); // subjects file handler

        subjectsFileHandler.createFile(); // create the file if it doesn't exist

        String subjectsData = subjectsFileHandler.readFile(); // read the file

        if(subjectsData != ""){ // if the file is not empty

            String[] subjects = subjectsData.split("\n"); // split the data into lines

            for (String line : subjects) { // loop through the lines

                if(line.matches("\\d+,[A-z]+,\\w+-\\d+")){ // if the line matches the pattern (id-subjectName)

                    String[] data = line.split(","); // split the line into id, subjectName
                    Subject subject1 = new Subject(data[1],data[2]); // create a new subject object (subjectName)
                    SubjectManagement.addSubject(subject1); // add the subject to the array

                }
            }
        }








        String username, password;
        int roleNum;
        boolean isLoggedin = false;
        boolean isAuth = false;

        System.out.println("---- welcome to our system ----");
        
        while (!isLoggedin) {

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

                } else if (roleNum == 2) { // lecturer role
                    if (Authentication.lecturerLogin(username, password)) {
                        isAuth = true;
                        isLoggedin = true;
                        System.out.println("login Success");
                        System.out.println("---------------------------\n");

                        Lecturer lecturer = new Lecturer(username, password); 

                        LecturerRole.lecturerRolee(lecturer);
                        isLoggedin = true;

                    }
                    else{
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
                    
                } else { // student role
                    System.out.println("student section coming soon...");
                    System.exit(0);
                }
            }

        }

    }
}