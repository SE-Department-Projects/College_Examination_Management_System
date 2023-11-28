package models;
// test

import java.util.ArrayList;

public class SubjectManagement {


    private static  ArrayList<Subject> subjectArrayList = new ArrayList<>(); // array of subjects

    //---------------CONSTRUCTOR----------------------------------------------
    // public SubjectManagement() {
    //     Subject initialSubject = new Subject("0", "0"); // initial subject
    //     subjectArrayList.add(initialSubject);
    // }


    // private StudentManagement std = new StudentManagement(); // object from student management class to use its methods
    // private LecturerManagement lec = new LecturerManagement(); // object from lecturer management class to use its methods


    //--------------- ADD SUBJECT----------------------------------------------

    public static void addSubject(Subject sub) { // adds new subject to the list
        subjectArrayList.add(sub);
    }

    //---------------FIND SUBJECT INDEX----------------------------------------------

    public static int findSubjIndex(int id) {

        for (int i = 0; i < subjectArrayList.size(); i++) { // loop on the list of subjects
            if (subjectArrayList.get(i).getSubjID() == id) // if the id of the subject equals the id we entered
                return i; // returns the index of the subject (index not id)
        }
        return -1; // else it returns -1
    }


    //-------------------SEARCH SUBJECT--------------------------------------------

    public static Subject searchSubject(int index) { //we will enter the index we got from findSubjIndex method in case it didn't return -1

        return subjectArrayList.get(index);
    }


    //-----------------DELETE SUBJECT------------------------------------------

    public static boolean deleteSubject(int id) {
        int index = findSubjIndex(id); // returns the index of the subject if it exist , else returns -1
        if (index != -1) {
            Subject sub = searchSubject(index); // if the subejct exists it returns the subject object
            subjectArrayList.remove(sub); // remove the subject
            return true;
        } else // if it doesn't exist it returns false
            return false;
    }


    //---------------ASSIGN SUBJECT----------------------------------------------

    public static boolean assignSubj(Subject sub, String role, int idOfUser) { // takes the subject object , the role of the user and the id of the user
        if (role.equals("student")) { //if the role is student
            int index = StudentManagement.findStdIndex(idOfUser); // we find the index of the student ( method from class StudentManagement)
            if (index != -1) { // if it exists
                Student student = StudentManagement.searchStd(index); // we get the student object
                boolean status = student.addSubject(sub); // and assign the subject
                if (!status)
                    return false;
            }
            return true; // true if the subject assigned successfully
        } else if (role.equals("lecturer")) { // if the role is lecturer
            int index = LecturerManagement.findLecIndex(idOfUser); // we find the index of the lecturer ( method from class LecturerManagement)
            if (index != -1) { // if it exists
                Lecturer lecturer = LecturerManagement.searchLecturer(index); // we get the lecturer object
                boolean status = lecturer.setSubject(sub); // and assign the subject
                if (!status)
                    return false;
            }
            return true; // true if the subject assigned successfully
        } else { // if the role is not student nor lecturer
            return false;  // returns false
        }
    }


    //---------------UNASSIGNED SUBJECT----------------------------------------------

    public static boolean unassignSubj(Subject sub, String role, int idOfUser) { // SAME AS assignSubj method but instead it deletes the subject
        if (role.equals("student")) {
            int index = StudentManagement.findStdIndex(idOfUser);
            if (index != -1) {
                Student student = StudentManagement.searchStd(index);
                boolean status = student.delSubject(sub);
                if (!status)
                    return false;
            }
            return true;
        } else if (role.equals("lecturer")) {
            int index = LecturerManagement.findLecIndex(idOfUser);
            if (index != -1) {
                Lecturer lecturer = LecturerManagement.searchLecturer(index);
                boolean status = lecturer.delSubject(sub);
                if (!status)
                    return false;
            }
            return true;
        } else {
            return false;
        }
    }
    public static ArrayList<Subject> getSubjectArrayList() {
        return subjectArrayList;
    }

    public static boolean updateSubject(int ID, String newSubjectName, String newSubjectcode) {
        int index = findSubjIndex(ID);
        if (index != -1) {

            subjectArrayList.get(index).setSubjectName(newSubjectName);
            subjectArrayList.get(index).setSubjectCode(newSubjectcode);
            return true;
        }
        return false;
    }

}


