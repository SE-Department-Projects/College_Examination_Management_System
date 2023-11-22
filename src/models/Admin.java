package models;

public class Admin extends User
{
    public LecturerManagement lectureManager = new LecturerManagement();

    public Admin(String userName, String password, String role) {
        super(userName, password, role);
    }
}
