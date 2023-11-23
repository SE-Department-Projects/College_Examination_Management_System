package models;

import java.util.ArrayList;

public class LecturerManagement {

    //    private Lecturer[] lecturersArr = new Lecturer[10];
    private ArrayList<Lecturer> lecturersArr = new ArrayList<>();


    //add lecturer
    public void addLecturer(Lecturer lecturer) {
        lecturersArr.add(lecturer);
    }

    //delete lecturer
    public boolean deleteLecturer(int ID) {

        int index = findLecIndex(ID);

        if (index != -1) {
            lecturersArr.remove(lecturersArr.get(index));

            return true;  // done
        }
        return false; // D.N.E
    }

    //search lecturer
    public Lecturer searchLecturer(int index) {

     return  lecturersArr.get(index);

    }


    //update lecturer username
    public boolean updateLecUsername(int ID, String newUsername) {
        int index = findLecIndex(ID);
        if (index != -1) {

            lecturersArr.get(index).setUserName(newUsername);
            return true;
        }
        return false;
    }


    //update lecturer password
    public boolean updateLecPassword(int ID, String password) {
        int index = findLecIndex(ID);

        if (index != -1) {
            lecturersArr.get(index).setPassword(password);
            return true;
        }
        return false;
    }


    //list all student
    public ArrayList<Lecturer> getLecturersArr() {
        return lecturersArr;
    }

    public void setLecturersArr(ArrayList<Lecturer> lecturersArr) {
        this.lecturersArr = lecturersArr;
    }


    //find the index if the student
    public int findLecIndex(int ID) {

        if (ID <= 0) {
            return -1;   // D.N.E
        }

        for (int i = 0; i < lecturersArr.size(); i++) {
            if (ID == lecturersArr.get(i).getID()) {

                return i;  // return the index of the lecture
            }
        }

        return -1;
    }

}

