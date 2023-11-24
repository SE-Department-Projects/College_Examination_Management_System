package models;
// test

import java.util.ArrayList;

public class SubjectManagement  {


    private ArrayList<Subject> ListSubj = new ArrayList<>(); // array of subjects

    private StudentManagement std; // object from student management class to use its methods
    private LecturerManagement lec; // object from lecturer management class to use its methods



    //--------------- ADD SUBJECT----------------------------------------------

    public void addSubject(Subject sub){ // adds new subject to the list
        ListSubj.add(sub);
    }

    //---------------FIND SUBJECT INDEX----------------------------------------------

    public int findSubjIndex(int id){
        
        for(int i = 0 ; i<ListSubj.size();i++){ // loop on the list of subjects
            if(ListSubj.get(i).getSubjID() == id) // if the id of the subject equals the id we entered
            return i; // returns the index of the subject (index not id)
        }
        return -1; // else it returns -1
    }


    //-------------------SEARCH SUBJECT--------------------------------------------

    public Subject searchSubject(int index){ //we will enter the index we got from findSubjIndex method in case it didn't return -1
        
        return ListSubj.get(index);
    }



    //-----------------DELETE SUBJECT------------------------------------------

    public boolean deleteSubject(int id){  
        int index = findSubjIndex(id); // returns the index of the subject if it exist , else returns -1
        if(index != -1){ 
            Subject sub = searchSubject(index); // if the subejct exists it returns the subject object
            ListSubj.remove(sub); // remove the subject
            return true;
        }
        else // if it doesn't exist it returns false
            return false;
    }


    //---------------ASSIGN SUBJECT----------------------------------------------

    public boolean assignSubj(Subject sub, String role, int idOfUser){ // takes the subject object , the role of the user and the id of the user
        if (role.equals("student")){ //if the role is student
            int index = std.findStdIndex(idOfUser); // we find the index of the student ( method from class StudentManagment)
            if (index != -1){ // if it exists
                Student student = std.searchStd(index); // we get the student object
                student.addSubject(sub); // and assign the subject
            }
            return true; // true if the subject assigned successfully
        }
            else if (role.equals("lecturer")){ // if the role is lecturer
            int index = lec.findLecIndex(idOfUser); // we find the index of the lecturer ( method from class LecturerManagment)
            if (index != -1){ // if it exists
                Lecturer lecturer = lec.searchLecturer(index); // we get the lecturer object
                lecturer.addSubject(sub); // and assign the subject
            }
            return true; // true if the subject assigned successfully
        }
        else { // if the role is not student nor lecturer
            return false;  // returns false
        }
    }


    //---------------UNASSIGN SUBJECT----------------------------------------------
    
    public boolean unassignSubj(Subject sub, String role, int idOfUser){ // SAME AS assignSubj method but instead it deletes the subject
        if (role.equals("student")){ 
            int index = std.findStdIndex(idOfUser); 
            if (index != -1){
                Student student = std.searchStd(index);
                student.delSubject(sub);
            }
            return true;
        }
            else if (role.equals("lecturer")){
            int index = lec.findLecIndex(idOfUser);
            if (index != -1){
                Lecturer lecturer = lec.searchLecturer(index);
                lecturer.delSubject(sub);
            }
            return true;
        }
        else{
            return false;
        }
    }

}


