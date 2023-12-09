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

    public static int addSubject(String subjectName, String subjectCode) { // adds new subject to the list
        for (Subject subject : subjectArrayList) {
            if(subject.getSubjectName().equals(subjectName)){
                return -1; // subjName already exists
            }
        }
        for (int i = 0; i < subjectArrayList.size(); i++) { // loop through the array of subjects
            if (subjectArrayList.get(i).getSubjectName().equals("empty") && subjectArrayList.get(i).getSubjectCode().equals("empty-0")) { // if the subject name and code are empty
                int subID = subjectArrayList.get(i).getSubjID(); // get the id of the subject
                subjectArrayList.remove(i); // remove the subject
                subjectArrayList.add(i,new Subject(subID,subjectName,subjectCode));  // add the subject with the new subject name and code with the same id as the old subject (the empty one)
                return 1; // return
            }
        }
        subjectArrayList.add(new Subject(subjectName,subjectCode)); // if there is no empty subjects, add a new subject with the subject name and code
        return 1;
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
            Subject sub = searchSubject(index); 
            int subID = sub.getSubjID();
            subjectArrayList.remove(sub);
            subjectArrayList.add(index,new Subject(subID,"empty","empty-0"));
            return true;
        } 
        return false;
    }


    //--------------- ASSIGN SUBJECT TO STUDENT ----------------------------------------------

    public static boolean assignSubjectToStudent(Subject sub, int idOfUser) { // takes the subject object and the id of the user
            int index = StudentManagement.findStdIndex(idOfUser); // we find the index of the student ( method from class StudentManagement)
            if (index != -1) { // if it exists
                Student student = StudentManagement.searchStd(index); // we get the student object
                boolean status = student.addSubject(sub); // and assign the subject
                if (!status)
                    return false;
                else{
                    student.addGrade(-1);
                }
            return true; // true if the subject assigned successfully
        }
        return false; // false if the subject didn't assign successfully
    }



    //--------------- ASSIGN SUBJECT TO LECTURER  ----------------------------------------------
    public static boolean assignSubjectToLecturer(Subject sub, int idOfUser){

        int index = LecturerManagement.findLecIndex(idOfUser); // we find the index of the lecturer ( method from class LecturerManagement)
        if (index != -1) { // if it exists
            Lecturer lecturer = LecturerManagement.searchLecturer(index); // we get the lecturer object
            boolean status = lecturer.addSubject(sub); // and assign the subject
            if (!status)
                return false;
            sub.addLecturerID(idOfUser);
        }
        return true; // true if the subject assigned successfully
    }



    //--------------- UNASSIGN SUBJECT OF STUDENT ----------------------------------------------

    public static boolean unassignSubjOfStudent(Subject sub, int idOfUser) { // SAME AS assignSubj method but instead it deletes the subject
            int index = StudentManagement.findStdIndex(idOfUser);
            if (index != -1) {
                Student student = StudentManagement.searchStd(index);
                int indexOfSub = student.findSubjIndex(sub.getSubjID());
                boolean status = student.delSubject(sub);
                if (!status)
                    return false;
                else{
                    student.delGrade(indexOfSub);
                }
            }
            return true;
        }
    

    //--------------- UNASSIGN SUBJECT OF LECTURER ----------------------------------------------
    
    public static boolean unassignSubjOfLecturer(Subject sub, int idOfUser){
            int index = LecturerManagement.findLecIndex(idOfUser);
            if (index != -1) {
                Lecturer lecturer = LecturerManagement.searchLecturer(index);
                boolean status = lecturer.delSubject(sub);
                if (!status)
                    return false;
                sub.removeLecturerID(idOfUser);
            }
            return true;
        
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


