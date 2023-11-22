package models;

public class Lecturer extends User {

    static int numOfLecturer = 0;
    private final int ID;

    public Lecturer(String userName, String password, String role) {
        super(userName, password, role);
        this.ID = ++numOfLecturer;
    }

    public int getID() {
        return ID;
    }
}

