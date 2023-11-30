package models;

public class Admin extends Person
{
    
    // constructor  uses super class constructor (Person constructor)
    public Admin(String userName, String password) {
        super(userName, password, "admin");
    }
}
