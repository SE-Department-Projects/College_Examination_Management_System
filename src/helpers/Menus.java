package helpers;

public class Menus {
    static private int op;


    public static int notAuthenticatedMenu()
    {

        System.out.println("you are not Authenticated");
        System.out.println("1=> try again");
        System.out.println("0=> logout");
        System.out.print("Enter your answer: ");

        op = Functions.readPositiveORZeroInt();

        return op;
    }

    public static int mainMenu()
    {
        System.out.println("1=> Admin");
        System.out.println("2=> Lecturer");
        System.out.println("3=> Student");

        System.out.print("enter the num of the role: ");
        op = Functions.readInt();

        return op;
    }


    public static int AdminManageMenu()
    {
        System.out.println("choose only one option to manage: ");
        System.out.println("1=> manage Lecturer");
        System.out.println("2=> manage Student");
        System.out.println("3=> manage Subject");
        System.out.println("4=> see personal info");
        System.out.println("5=> Update personal info");
        System.out.println("0=> Exit");
        System.out.print("Enter your answer: ");

        op = Functions.readPositiveORZeroInt();

        return op;
    }

    public static int adminManagingLecturers ()
    {
        System.out.println("\nYou are now managing Lecturers");
        System.out.println("\n\nSelect operation");
        System.out.println("1=> Add");
        System.out.println("2=> Delete");
        System.out.println("3=> Search");
        System.out.println("4=> List");
        System.out.println("5=> Update");
        System.out.println("6=> Assign Subject");
        System.out.println("7=> Un-assign Subject");
        System.out.println("8=> Back");
        System.out.println("0=> Exit");
        System.out.print("enter operation num: ");

        op = Functions.readPositiveORZeroInt();
        return  op;
    }
public static int updateAdminUsernamePassword ()
{

    System.out.println("1=> update username");
    System.out.println("2=> update password");
    System.out.print("Enter your answer: ");
    op = Functions.readPositiveORZeroInt();

    return op;
}
    public static int studentMenu() {
        System.out.println("Choose only one option to do: ");
        System.out.println("1=> See Registered Subjects");
        System.out.println("2=> Get Exam(s)");
        System.out.println("3=> See Finished Exams");
        System.out.println("4=> See personal information");
        System.out.println("5=> Update profile");
        System.out.println("0=> Exit");
        System.out.print("Enter your answer: ");
        op = Functions.readPositiveORZeroInt();

        return op;
    }

    public static int studentFinishedExamMenu() {
        System.out.println("1=> see exam grade");
        System.out.println("2=> see corrected exam answer");
        System.out.println("3=> back");

        op = Functions.readPositiveORZeroInt();
        return op;
    }
}
