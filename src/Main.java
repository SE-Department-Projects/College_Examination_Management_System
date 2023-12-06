import helpers.*;
import models.*;
import roles.*;

import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    public static boolean isBack = true;

    public static void main(String[] args) {

        //!  read from files

        // lecturer file handler

        FileHandler lecturerFileHandler = new FileHandler(Paths.lecturersPath);

        lecturerFileHandler.createFile(); // create the file if it doesn't exist

        String lecturersData = lecturerFileHandler.readFile(); // read the file

        if (lecturersData != "") { // if the file is not empty

            String[] lecturers = lecturersData.split("\n"); // split the data into lines
            for (String line : lecturers) { // loop through the lines
                if(line.matches("\\d+-[A-z]+-\\w+")){ // if the line matches the pattern (id-username-password)
                    String[] data = line.split("-"); // split the line into id, username, password
                     Lecturer lecturer1 = new Lecturer(Integer.parseInt(data[0]),data[1],data[2]); // create a new lecturer object (username, password)
                    LecturerManagement.addLecturer(lecturer1); // add the lecturer to the array
                    Lecturer.setNumOfLecturer(Integer.parseInt(data[0]));
                }
            }
        }




        // student file handler

        FileHandler studentFileHandler = new FileHandler(Paths.studentsPath);

        studentFileHandler.createFile(); // create the file if it doesn't exist

        String studentsData = studentFileHandler.readFile(); // read the file

        if(studentsData != ""){ // if the file is not empty

            String[] students = studentsData.split("\n"); // split the data into lines

            for (String line : students) { // loop through the lines

                if(line.matches("\\d+-[A-z]+-\\w+")){ // if the line matches the pattern (id-username-password)

                    String[] data = line.split("-"); // split the line into id, username, password
                    Student student1 = new Student(Integer.parseInt(data[0]),data[1],data[2]); // create a new student object (username, password)
                    StudentManagement.addStd(student1); // add the student to the array
                    Student.setNumOfStudents(Integer.parseInt(data[0]));
                }
            }
        }




        // subjects file handler

        FileHandler subjectsFileHandler = new FileHandler(Paths.subjectsPath);

        subjectsFileHandler.createFile(); // create the file if it doesn't exist

        String subjectsData = subjectsFileHandler.readFile(); // read the file

        if(subjectsData != ""){ // if the file is not empty

            String[] subjects = subjectsData.split("\n"); // split the data into lines

            for (String line : subjects) { // loop through the lines

                if(line.matches("\\d+,[A-z]+,\\w+-\\d+")){ // if the line matches the pattern (id-subjectName)

                    String[] data = line.split(","); // split the line into id, subjectName
                    Subject subject1 = new Subject(Integer.parseInt(data[0]),data[1],data[2]); // create a new subject object (subjectName)
                    SubjectManagement.addSubject(subject1); // add the subject to the array
                    Subject.setNumOfSubjects(Integer.parseInt(data[0]));
                }
            }
        }



        // std_ID_subject read

        for (Student student : StudentManagement.getStudentArray()) { // loop through the students

            FileHandler stdSubjFileHandler = new FileHandler(Paths.studentCoursesPath +student.getID()+"_subjects.txt");

            stdSubjFileHandler.createFile(); // create the file if it doesn't exist

            String stdSubjData = stdSubjFileHandler.readFile(); // read the file

            if(stdSubjData != ""){ // if the file is not empty

                String[] stdSubjs = stdSubjData.split("\n"); // split the data into lines

                boolean skip = false;
                for (int i = 0 ; i < stdSubjs.length ; i++ ) { // loop through the lines
                    
                    if (skip){
                        skip = false;
                        continue;
                    }
                    else{
                        if(stdSubjs[i].matches("-*\\d+")){ // if the line matches the pattern (subjectID)
                            if(i%2 == 0){
                                int index = SubjectManagement.findSubjIndex(Integer.parseInt(stdSubjs[i])); // find the index of the subject
                                if (index != -1){ // if the subject exists
                                    student.addSubject(SubjectManagement.searchSubject(index)); // add the subject to the student
                                }
                                else{
                                    
                                    skip = true;
                                }
                            }
                            else{
                                student.addGrade(Integer.parseInt(stdSubjs[i])); // add the grade to the student
                            }
                        }
                        else if (i %2 == 0){
                            skip = true;
                        }
                    }
                }
                
            }
        }




        // lec_ID_subject read

        for (Lecturer lecturer : LecturerManagement.getLecturersArr()) { // loop through the lecturers

            FileHandler lecSubjFileHandler = new FileHandler(Paths.lecturerCoursesPath +lecturer.getID()+"_subjects.txt");

            lecSubjFileHandler.createFile(); // create the file if it doesn't exist

            String lecSubjData = lecSubjFileHandler.readFile(); // read the file

            if(lecSubjData != ""){ // if the file is not empty

                String[] lecSubjs = lecSubjData.split("\n"); // split the data into lines

                for (String line : lecSubjs) { // loop through the lines

                    if(line.matches("\\d+")){ // if the line matches the pattern (subjectID)

                        int index = SubjectManagement.findSubjIndex(Integer.parseInt(line)); // find the index of the subject

                        if (index != -1){ // if the subject exists
                            Subject subject = SubjectManagement.searchSubject(index);
                            lecturer.addSubject(subject); // add the subject to the lecturer
                            subject.addLecturerID(lecturer.getID());

                        }
                    }
                }
            }
        }






        // sub_ID_exam read

        //TODO validation 
        for (Subject subject : SubjectManagement.getSubjectArrayList()) { // loop through the subjects

            FileHandler subExamFileHandler = new FileHandler(Paths.examPath+subject.getSubjID()+"_exam.txt");

            subExamFileHandler.createFile(); // create the file if it doesn't exist

            String subExamData = subExamFileHandler.readFile(); // read the file

            if(subExamData != ""){ // if the file is not empty

                String[] subExams = subExamData.split("\n"); // split the data into lines
                if (subExams[0].equals("-1")){
                    // System.out.println("empty");
                    break;
                }
                subject.setExam(new Exam());
                for(int i = 1 ; i < subExams.length ; i+=2){
                    Question question = new Question(subExams[i],subExams[i+1]);
                    subject.getExam().addQuestion(question);
                }
                subject.setIsExamCreated(true);
            }
        }





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


        for(Student std : StudentManagement.getStudentArray()){
            FileHandler stdIdSubjects = new FileHandler("src/Files/StudentsCourses/std_"+std.getID()+"_subjects.txt");

            stdIdSubjects.emptyFile();
            for(int i = 0 ; i < std.getSubjects().size() ; i++){
                stdIdSubjects.writeFile(std.getSubjects().get(i).getSubjID()+"",true);
                stdIdSubjects.writeFile(std.getGrades().get(i)+"",true);
            }
        }
    }
}