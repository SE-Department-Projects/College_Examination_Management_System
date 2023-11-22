package models;

import java.util.ArrayList;

public class LecturerManagement {

    //    private Lecturer[] lecturersArr = new Lecturer[10];
    private ArrayList<Lecturer> lecturersArr1 = new ArrayList<>();


    public void addLecturer(Lecturer lecturer) {
        lecturersArr1.add(lecturer);
    }

    public boolean deleteLecturer(int ID) {

        int index = findLecIndex(ID);

        if (index != 0) {
            lecturersArr1.remove(lecturersArr1.get(index));

            return true;  // done
        }
        return false; // D.N.E
    }

    public int searchLecturer(int ID) {


        for (int i = 0; i < lecturersArr1.size(); i++) {
            if (ID == lecturersArr1.get(i).getID()) {

                return i;  // return the index of the lecture
            }
        }
        return 0; // D.N.E
    }


    public boolean updateLecUsername(int ID, String newUsername) {
        int index = findLecIndex(ID);
        if (index != 0) {

            lecturersArr1.get(index).setUserName(newUsername);
            return true;
        }
        return false;
    }

    public boolean updateLecPassword(int ID, String password) {
        int index = findLecIndex(ID);

        if (index != 0) {
            lecturersArr1.get(index).setPassword(password);
            return true;
        }
        return false;
    }

    public ArrayList<Lecturer> listLecturer() {
        return lecturersArr1;
    }

    public ArrayList<Lecturer> getLecturersArr1() {
        return lecturersArr1;
    }

    public void setLecturersArr1(ArrayList<Lecturer> lecturersArr1) {
        this.lecturersArr1 = lecturersArr1;
    }

//    private boolean isFound(int ID) {
//        for (Lecturer lecturer : lecturersArr1) {
//
//            if (ID == lecturer.getID()) {
//                return true;
//            }
//        }
//        return false;
//    }

    private int findLecIndex(int ID) {

        if (ID <= 0) {
            return 0;
        }

        for (int i = 0; i < lecturersArr1.size(); i++) {
            if (ID == lecturersArr1.get(i).getID()) {

                return i;  // return the index of the lecture
            }
        }

        return 0;
    }

}

