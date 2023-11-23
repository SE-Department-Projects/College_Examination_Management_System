package models;

public abstract class Person {

    // attributes
    private String userName;
    private String password;
    private final String role; // admin, user, lecturer
    private boolean isLoggedOut = false;

    // constructor
    public Person(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    // Setters

    public boolean setUserName(String userName) {

            // validation (username must be atleast 6 characters)
        if (userName.length() >= 6) {
            this.userName = userName;
            return true;
        } 
        else {
            return false;
        }
    }

    public boolean setPassword(String password) {

            // validation (password must be atleast 8 characters and has atleast 1 alphabet letter)
        if (password.length() >= 8 && password.matches(".*[a-zA-Z]+.*")) {
            this.password = password;
            return true;
        }
        else {
            return false;
        }
        
    }

    // Getters

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }

    // login method

    public boolean login(String userName, String password) {
        // TODO: implement this method later
        return true;
    }

    // logout method

    public void logout() {
        this.isLoggedOut = true;
    }



}
