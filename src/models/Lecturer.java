package models;

import java.util.ArrayList;

public class Lecturer extends Person {

    static int numOfLecturer = 0;
    private ArrayList<Subject> subjects;
    private final int ID;


    public Lecturer(String userName, String password) {
        super(userName, password, "lecturer");
        this.ID = ++numOfLecturer;
        this.subjects = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public boolean addSubject(Subject subject) {
        if (this.subjects.contains(subject)){
            return false;
        }
        else{
            this.subjects.add(subject);
            return true;
        }
    }

    public boolean delSubject(Subject subject) {
        if (this.subjects.contains(subject)){
            this.subjects.remove(subject);
            return true;
        }
        else{
            return false;
        }
    }
}

