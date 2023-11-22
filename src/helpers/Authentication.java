package helpers;

public class Authentication {

    public static boolean adminLogin(String username, String password) // ba3den nb2a nhadel el files
    {
        if(username.equals("username") && password.equals("password"))
        {
                return  true;
        }
        return false;
    }
}
