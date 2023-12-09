package models;


import java.util.ArrayList;
public class Admin extends Person
{
    private static ArrayList<Person> allUsers = new ArrayList<Person>();
    
    // constructor  uses super class constructor (Person constructor)
    public Admin(String userName, String password,String email,String phone) {
        super(userName, password, "admin",email,phone);
    }

    // add user to the arraylist of allUsers
    public static void FillUsers(Person user) {

        for (Student student : StudentManagement.getStudentArray()) {
            allUsers.add(student);
        }
        for (Lecturer lecturer : LecturerManagement.getLecturersArr()) {
            allUsers.add(lecturer);
        }
    }

    public static ArrayList<Person> getAllUsers() {
        return allUsers;
    }

    public static void emptyAllUsers() {
        allUsers.clear();
    }


    @Override
    public String toString() {
        return "Admin{" +
        " userName='" + this.getUserName() + '\'' +
        ", password='" + this.getPassword() + '\'' +
        ", email='" + this.getEmailToString() + '\'' +
        ", phone='" + this.getPhoneToString() + '\'' +
        '}';
    }
}
