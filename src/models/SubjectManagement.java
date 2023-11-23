package models;

import java.util.ArrayList;

public class SubjectManagement  {


    private ArrayList<Subject> ListSubj = new ArrayList<>();

    private Student std;
    private LecturerManagement lec;



    public void addSubject(Subject sub){
        ListSubj.add(sub);
    }


    public boolean deleteSubject(int id){ 
        int index = findSubjIndex(id);
        if(index != -1){
            Subject sub = searchSubject(index);
            ListSubj.remove(sub);
            return true;
        }
        else
            return false;
    }


    public Subject searchSubject(int index){ //we will enter the index we got from findSubjIndex method in case it didn't return -1
        
        return ListSubj.get(index);
    }


    public int findSubjIndex(int id){
        
        for(int i = 0 ; i<ListSubj.size();i++){
            if(ListSubj.get(i).getSubjID() == id)
            return i;
        }
        return 0;
    }
        
    


    public boolean assignSubj(Subject sub, String role, int idOfUser){
        if (role == "student"){
            int index = std.findStdIndex(idOfUser);
            if (index != -1){
                Student student = std.searchStudent(index);
                student.addSubject(sub);
            }
            return true;
            // TODO StudentManagement.....
        }
            else if (role == "lecturer"){
            int index = lec.findLecIndex(idOfUser);
            if (index != -1){
                Lecturer lecturer = lec.searchLecturer(index);
                lecturer.addSubject(sub);
            }
            return true;
        }
        else 
            return false;
    }

    public void unassignSubj(Subject sub, String role, int idOfUser){
        if (role == "student"){
            int index = std.findStdIndex(idOfUser);
            if (index != -1){
                Student student = std.searchStudent(index);
                student.delSubject(sub);
            }
            // TODO StudentManagement.....
        }
            else if (role == "lecturer"){
            int index = lec.findLecIndex(idOfUser);
            if (index != -1){
                Lecturer lecturer = lec.searchLecturer(index);
                lecturer.delSubject(sub);
            }
        }
    } 

}


