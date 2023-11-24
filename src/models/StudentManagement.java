package models;

import java.util.ArrayList;

public class StudentManagement {

// JUST TEST

//----------DATA FIELDS------------------------------------------------------------------------------------

    private ArrayList<Student> studentArray = new ArrayList<>();


//-------------METHODS--------------------------------------------------------------------------------------


    //------------STUDENT MANAGER  NOT IMPORTANT----------------------------------------------
    public StudentManagement() {

    }


    //--------------- ADD STUDENT----------------------------------------------

    public void addStd(Student student) { // Method explaination in SubjectManagement.java
        studentArray.add(student);
    }


    //-----------------FIND STUDENT INDEX------------------------------------------

    public int findStdIndex(int id) { // Method explaination in SubjectManagement.java

        if (id <= -1) {
            return -1;
        }
        for (int i = 0; i < studentArray.size(); i++) {
            if (id == (studentArray.get(i)).getId()) {

                return i;  // return the index of the student
            }
        }
        return -1;
    }


//-------------------SEARCH STUDENT--------------------------------------------
    // before running check if the index is not -1
    public Student searchStd(int index) {  // Method explaination in SubjectManagement.java
        return studentArray.get(index);
    }



//----------------- DELETE STUDENT------------------------------------------

    public boolean deleteStd(int id) {   // Method explaination in SubjectManagement.java
        int index = findStdIndex(id);
        if (index != -1) {
            studentArray.remove(studentArray.get(index));
            return true;
        }
        return false;
    }


    //-------------------UPDATE STUDENT USERNAME---------------------------------

    public boolean updateStdUsername(int id, String newUsername) {
        int index = findStdIndex(id);

        if (index != -1) {
            studentArray.get(index).setUserName(newUsername);
            return true;
        }
        return false;
    }


    //------------------UPDATE STUDENT PASSWORD-----------------------------------

    public boolean updateStdPassword(int id, String newPass) {
        int index = findStdIndex(id);
        if (index != -1) {
            studentArray.get(index).setPassword(newPass);
            return true;
        }
        return false;
    }


    


    //----------------------LIST STUDENT---------------------------------------------
    public ArrayList<Student> listStd() {
        return studentArray;
    }




}
