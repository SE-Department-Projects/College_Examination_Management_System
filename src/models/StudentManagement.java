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
    public void addStd(Student student) {
        studentArray.add(student);
    }


//----------------- DELETE STUDENT------------------------------------------

    public boolean deleteStd(int id) {
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


//-------------------SEARCH STUDENT--------------------------------------------

    public Student searchStd(int index) {
        return studentArray.get(index);
    }

    //----------------------LIST STUDENT---------------------------------------------
    public ArrayList<Student> listStd() {
        return studentArray;
    }


    public int findStdIndex(int id) {

        if (id <= -1) {
            return 0;
        }
        for (int i = 0; i < studentArray.size(); i++) {
            if (id == (studentArray.get(i)).getId()) {

                return i;  // return the index of the student
            }
        }
        return -1;
    }


}
