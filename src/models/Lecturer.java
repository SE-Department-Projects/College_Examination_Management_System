package models;

public class Lecturer extends Person {

    static int numOfLecturer = 0;
    private final int ID;

    public Lecturer(String userName, String password) {
        super(userName, password, "lecturer");
        this.ID = ++numOfLecturer;
    }

    public int getID() {
        return ID;
    }
}

