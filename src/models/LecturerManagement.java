package models;

import java.util.ArrayList;

public class LecturerManagement {

    //    private Lecturer[] lecturersArr = new Lecturer[10];
    private static ArrayList<Lecturer> lecturersArr = new ArrayList<>();

    //---------------CONSTRUCTOR----------------------------------------------
    // public LecturerManagement() {
    //     Lecturer initialLecturer = new Lecturer("0", "0"); // initial lecturer
    //     lecturersArr.add(initialLecturer);
    // }


//--------------- ADD LECTURER----------------------------------------------

    public static void addLecturer(Lecturer lecturer) {  // Method explaination in SubjectManagement.java
        lecturersArr.add(lecturer);
}

    public static void addLecturer(String userName, String password) {  // Method explaination in SubjectManagement.java
        for (int i = 0; i < lecturersArr.size(); i++) {
            if (lecturersArr.get(i).getUserName().equals("empty") && lecturersArr.get(i).getPassword().equals("empty")) {
                int lecID = lecturersArr.get(i).getID();
                lecturersArr.remove(i);
                lecturersArr.add(i,new Lecturer(lecID,userName,password));
                return;
            }
        }
        lecturersArr.add(new Lecturer(userName,password));
}


    //-----------------FIND LECTURER INDEX------------------------------------------

    public static int findLecIndex(int ID) {  // Method explaination in SubjectManagement.java

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
    public static Lecturer searchLecturer(int index) {  // Method explaination in SubjectManagement.java

        return  lecturersArr.get(index);

    }


    //----------------- DELETE LECTURER------------------------------------------

    public static boolean deleteLecturer(int ID) {  // Method explaination in SubjectManagement.java

        int index = findLecIndex(ID);

        if (index != -1) {
            Lecturer lecturer = lecturersArr.get(index);
            for(Subject subject : SubjectManagement.getSubjectArrayList()){
                int removeIndex = -1;
                for(int i = 0 ; i < subject.getLecturersID().size() ; i++){
                    if(subject.getLecturersID().get(i) == ID){
                        removeIndex = i;
                    }
                }
                if(removeIndex != -1){
                    subject.getLecturersID().remove(removeIndex);
                }
            }
                int lecID = lecturer.getID();
                lecturersArr.remove(lecturer);
                lecturersArr.add(index,new Lecturer(lecID,"empty","empty"));
            return true;  // done
        }
        return false; // D.N.E
    }




    //---------------UPDATE LECTURER----------------------------------------------

    public static boolean updateLecUsername(int ID, String newUsername) {
        int index = findLecIndex(ID);
        if (index != -1) {

            lecturersArr.get(index).setUserName(newUsername);
            return true;
        }
        return false;
    }

    public static boolean updateLecPassword(int ID, String password) {
        int index = findLecIndex(ID);

        if (index != -1) {
            lecturersArr.get(index).setPassword(password);
            return true;
        }
        return false;
    }


    //---------------LIST LECTURER----------------------------------------------
    public static ArrayList<Lecturer> getLecturersArr() {
        return lecturersArr;
    }

    public static void setLecturersArr(ArrayList<Lecturer> lecturersArray) {
        lecturersArr = lecturersArray;
    }




}

