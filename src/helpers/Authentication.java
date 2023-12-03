package helpers;
import models.*;

public class Authentication {

    public static boolean adminLogin(String username, String password) // ba3den nb2a nhadel el files
    {
        if(username.equals("a") && password.equals("aa"))
        {
                return  true;
        }
        return false;
    }

    public static int lecturerLogin(String username, String password) 
    {
        for(Lecturer lecturer : LecturerManagement.getLecturersArr())
        {
            if(lecturer.getUserName().equals(username) && lecturer.getPassword().equals(password))
            {
                return  lecturer.getID();
            }
        }
        return -1;
    }

    public static boolean studentLogin(String username, String password) // ba3den nb2a nhadel el files
    {
        if(username.equals("d") && password.equals("dd"))
        {
            return  true;
        }
        return false;
    }
}
