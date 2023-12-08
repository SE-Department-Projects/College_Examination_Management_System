package models;

import java.util.ArrayList;

public class StudentManagement {

// JUST TEST

//----------DATA FIELDS------------------------------------------------------------------------------------

    private static ArrayList<Student> studentArray = new ArrayList<>();


//-------------CONSTRUCTOR--------------------------------------------------------------------------------

    // public StudentManagement() {
    //     Student initialStudent = new Student("0", "0"); // initial student
    //     studentArray.add(initialStudent);
    // }


//-------------METHODS--------------------------------------------------------------------------------------



    //--------------- ADD STUDENT----------------------------------------------

    public static void addStd(Student student) { 
        studentArray.add(student);
    }

    public static void addStd(String userName, String password) { // takes username and pass
        for (int i = 0; i < studentArray.size(); i++) { // loop through the array of students
            if (studentArray.get(i).getUserName().equals("empty") && studentArray.get(i).getPassword().equals("empty")) { // if the username and password are empty
                int stdID = studentArray.get(i).getID(); // get the id of the student
                studentArray.remove(i); // remove the student
                studentArray.add(i,new Student(stdID,userName,password,"@","0"));  // add the student with the new username and password with the same id as the old student (the empty one)
                return; // return
            }
        }
        studentArray.add(new Student(userName,password,"@","0")); // if there is no empty students, add a new student with the username and password
        
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


    /*    public static boolean deleteLecturer(int ID) {  // Method explaination in SubjectManagement.java

        int index = findLecIndex(ID);

        if (index != -1) {
            Lecturer lecturer = lecturersArr.get(index);
            int lecID = lecturer.getID();
            lecturersArr.remove(lecturer);
            lecturersArr.add(index,new Lecturer(lecID,"empty","empty"));
            return true;  // done
        }
        return false; // D.N.E
    } */



    //-------------------UPDATE STUDENT USERNAME---------------------------------

    public static boolean updateStdUsername(int id, String newUsername) {
        int index = findStdIndex(id);

        if (index != -1) {
            studentArray.get(index).setUserName(newUsername);
            return true;
        }
        return false;
    }


    //------------------UPDATE STUDENT PASSWORD-----------------------------------

    public static boolean updateStdPassword(int id, String newPass) {
        int index = findStdIndex(id);
        if (index != -1) {
            studentArray.get(index).setPassword(newPass);
            return true;
        }
        return false;
    }


    


    //----------------------LIST STUDENT---------------------------------------------

    public static ArrayList<Student> getStudentArray() {
        return studentArray;
    }

    public static void setStudentArray(ArrayList<Student> studentArr) {
        studentArray = studentArr;
    }
}
