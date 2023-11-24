package models;

import java.util.ArrayList;

public class LecturerManagement {

    //    private Lecturer[] lecturersArr = new Lecturer[10];
    private ArrayList<Lecturer> lecturersArr = new ArrayList<>();


//--------------- ADD LECTURER----------------------------------------------

    public void addLecturer(Lecturer lecturer) {  // Method explaination in SubjectManagement.java
        lecturersArr.add(lecturer);
}


    //-----------------FIND LECTURER INDEX------------------------------------------

    public int findLecIndex(int ID) {  // Method explaination in SubjectManagement.java

        if (ID <= 0) {
            return -1;
        }

        for (int i = 0; i < lecturersArr.size(); i++) {
            if (ID == lecturersArr.get(i).getID()) {

                return i;  // return the index of the lecture
            }
        }

        return -1;
    }


    //-------------------SEARCH LECTURER--------------------------------------------

    // before running check if the index is not -1
    public Lecturer searchLecturer(int index) {  // Method explaination in SubjectManagement.java

        return  lecturersArr.get(index);

    }


    //----------------- DELETE LECTURER------------------------------------------

    public boolean deleteLecturer(int ID) {  // Method explaination in SubjectManagement.java

        int index = findLecIndex(ID);

        if (index != 0) {
            lecturersArr.remove(lecturersArr.get(index));

            return true;  // done
        }
        return false; // D.N.E
    }


    //---------------UPDATE LECTURER----------------------------------------------

    public boolean updateLecUsername(int ID, String newUsername) {
        int index = findLecIndex(ID);
        if (index != 0) {

            lecturersArr.get(index).setUserName(newUsername);
            return true;
        }
        return false;
    }

    public boolean updateLecPassword(int ID, String password) {
        int index = findLecIndex(ID);

        if (index != 0) {
            lecturersArr.get(index).setPassword(password);
            return true;
        }
        return false;
    }


    //---------------LIST LECTURER----------------------------------------------
    public ArrayList<Lecturer> getLecturersArr() {
        return lecturersArr;
    }

    public void setLecturersArr(ArrayList<Lecturer> lecturersArr) {
        this.lecturersArr = lecturersArr;
    }




}

