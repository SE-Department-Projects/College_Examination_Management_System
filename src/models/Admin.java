package models;

public class Admin extends Person
{
    public LecturerManagement lectureManager = new LecturerManagement();

    public Admin(String userName, String password, String role) {
        super(userName, password, role);
    }
}
