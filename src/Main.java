import models.Admin;
import models.User;

public class Main {
    public static void main(String[] args) {

        Admin admin = new Admin("test","123","admin");

        System.out.println(admin.getUserName());
    }
}