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

    public static boolean lecturerLogin(String username, String password) // ba3den nb2a nhadel el files
    {
        if(username.equals("lusername") && password.equals("lpassword"))
        {
                return  true;
        }
        return false;
    }

    public static boolean studentLogin(String username, String password) // ba3den nb2a nhadel el files
    {
        if(username.equals("susername") && password.equals("spassword"))
        {
            return  true;
        }
        return false;
    }
}
