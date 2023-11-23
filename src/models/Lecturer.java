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

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public void delSubject(Subject subject) {
        this.subjects.remove(subject);
    }
}

