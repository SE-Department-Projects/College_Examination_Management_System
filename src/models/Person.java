package models;

public abstract class Person {

    // attributes
    private String userName;
    private String password;
    private String email;
    private String phone;
    private final String role; // admin, user, lecturer
    private boolean isLoggedOut = false;

    // constructor
    public Person(String userName, String password, String role,String email,String phone) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    // Setters

    public boolean setUserName(String userName) {
        //TODO validation for change username (no two users can have the same username)
            this.userName = userName;
            return true;
    }

    public boolean setPassword(String password) {

        //TODO validation for change password 
            // validation (password must be atleast 8 characters and has atleast 1 alphabet letter)
        // if (password.length() >= 8 && password.matches(".*[a-zA-Z]+.*")) {
            this.password = password;
            return true;
        // }
        // else {
        //     return false;
        // }
        
    }

    public boolean setEmail(String email) {
        this.email = email;
        return true;
    }

    public boolean setPhone(String phone) {
        this.phone = phone;
        return true;
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

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmailToString(){
        if (this.email.equals("@")) {
            return "Not Set";
        }
        else {
            return this.email;
        }
    }

    public String getPhoneToString(){
        if (this.phone.equals("0")) {
            return "Not Set";
        }
        else {
            return this.phone;
        }
    }

    // login method

    public boolean login(String userName, String password) {
        return this.userName.equals(userName) && this.password.equals(password);
    }

    // logout method

    public void logout() {
        this.isLoggedOut = true;
    }



}
