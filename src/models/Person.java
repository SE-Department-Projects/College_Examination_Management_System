package models;

public abstract class Person {

    // attributes
    private String userName;
    private String password;
    private String email;
    private String phone;
    private final String role; // admin, user, lecturer
    private static int usersCount = 0;

    // constructor
    public Person(String userName, String password, String role,String email,String phone) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
        usersCount++;
    }

    // Setters

    public boolean setUserName(String userName) {
            this.userName = userName;
            return true;
    }

    public boolean setPassword(String password) {
            this.password = password;
            return true;
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

    public static int getUsersCount() {
        return usersCount;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getRole() {
        return this.role;
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


    public String getBriefInfo() {
        return getRole()+"{" +
        " userName='" + this.getUserName() + '\'' +
        ", password='" + this.getPassword() + '\'' +
        '}';
    }

}
