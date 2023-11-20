package models;

public class User {

    // attributes
    private String userName;
    private String password;
    private String role; // admin, user, lecturer

    // constructor
    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    // Setters

    public void setUserName(String userName) {
        this.userName = userName;
        // TODO: make validation
    }

    public void setPassword(String password) {
        this.password = password;
        // TODO: make validation
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
        System.out.println("You have logged out successfully");
    }



}
