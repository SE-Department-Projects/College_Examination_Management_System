package models;

public class Admin extends Person
{
    
    // constructor  uses super class constructor (Person constructor)
    public Admin(String userName, String password,String email,String phone) {
        super(userName, password, "admin",email,phone);
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
