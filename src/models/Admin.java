package models;

public class Admin extends Person
{
    public LecturerManagement lectureManager = new LecturerManagement();
    public StudentManagement studentManager = new StudentManagement();

    public SubjectManagement subjectManager = new SubjectManagement();
    
    // constructor  uses super class constructor (Person constructor)
    public Admin(String userName, String password) {
        super(userName, password, "admin");
    }
}
