package models;

import java.util.ArrayList;

public class StudentManagement {


//----------DATA FIELDS------------------------------------------------------------------------------------

    private static ArrayList<Student> studentArray = new ArrayList<>();



    //--------------- ADD STUDENT----------------------------------------------

    public static void addStd(Student student) { 
        studentArray.add(student);
    }

    public static int addStd(String userName, String password) { // takes username and pass
        for (Student student : studentArray) {
            if(student.getUserName().equals(userName)){
                return -1; // username already exists
            }
        }
        for (int i = 0; i < studentArray.size(); i++) { // loop through the array of students
            if (studentArray.get(i).getUserName().equals("empty") && studentArray.get(i).getPassword().equals("empty")) { // if the username and password are empty
                int stdID = studentArray.get(i).getID(); // get the id of the student
                studentArray.remove(i); // remove the student
                studentArray.add(i,new Student(stdID,userName,password,"@","0"));  // add the student with the new username and password with the same id as the old student (the empty one)
                return 1; // return
            }
        }
        studentArray.add(new Student(userName,password,"@","0")); // if there is no empty students, add a new student with the username and password
        return 1;
    }





    //-----------------FIND STUDENT INDEX------------------------------------------

    public static int findStdIndex(int id) { // Method explaination in SubjectManagement.java

        if (id <= 0) {
            return -1;
        }
        for (int i = 0; i < studentArray.size(); i++) {
            if (id == studentArray.get(i).getID()) {

                return i;  // return the index of the student
            }
        }
        return -1;
    }



//-------------------SEARCH STUDENT--------------------------------------------
    // before running check if the index is not -1
    public static Student searchStd(int index) {  // Method explaination in SubjectManagement.java
        return studentArray.get(index);
    }



//----------------- DELETE STUDENT------------------------------------------

    public static boolean deleteStd(int id) {   // Method explaination in SubjectManagement.java
        int index = findStdIndex(id);
        if (index != -1) {
            Student student = studentArray.get(index);
            int stdID = student.getID();
            studentArray.remove(student);
            studentArray.add(index,new Student(stdID,"empty","empty","empty","empty"));
            return true;
        }
        return false;
    }



    


    //----------------------LIST STUDENT---------------------------------------------

    public static ArrayList<Student> getStudentArray() {
        return studentArray;
    }



    public static boolean isStudentListEmpty() {
        for (Student student : studentArray) {
            if (!student.getUserName().equals("empty")) {
                return false;
            }
        }
        return true;
    }
}
