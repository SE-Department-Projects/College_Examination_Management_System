package helpers;

public class Menus {
    static private int op;


    public static int notAuthenticatedMenu() {

        System.out.println("you are not Authenticated");
        System.out.println("1=> try again");
        System.out.println("0=> logout");
        System.out.print("Enter your answer: ");

        op = Functions.readPositiveORZeroInt();

        return op;
    }

    public static int mainMenu() {
        System.out.println("1=> Admin");
        System.out.println("2=> Lecturer");
        System.out.println("3=> Student");
        System.out.println("0=> Exit");

        System.out.print("enter the num of the role: ");
        op = Functions.readInt();

        return op;
    }


    public static int AdminManageMenu() {
        System.out.println("choose only one option to manage: ");
        System.out.println("1=> manage Lecturer");
        System.out.println("2=> manage Student");
        System.out.println("3=> manage Subject");
        System.out.println("4=> See personal info");
        System.out.println("5=> Update personal info");
        System.out.println("6=> List all Users");
        System.out.println("0=> Exit");
        System.out.print("Enter your answer: ");

        op = Functions.readPositiveORZeroInt();

        return op;
    }

    public static int adminManagingLecturers() {
        System.out.println("\nYou are now managing Lecturers");
        System.out.println("\nSelect operation");
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
        return op;
    }

    public static int adminManageStudents() {
        System.out.println("\nYou are now managing Students");
        System.out.println("\nSelect operation");
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
        return op;
    }

    public static int adminManageSubjects() {
        System.out.println("\nYou are now managing Subjects");
        System.out.println("\nSelect operation");
        System.out.println("1=> Add");
        System.out.println("2=> Delete");
        System.out.println("3=> Search");
        System.out.println("4=> List");
        System.out.println("5=> Update");
        System.out.println("6=> Back");
        System.out.println("0=> Exit");
        System.out.print("enter operation num: ");

        op = Functions.readPositiveORZeroInt();
        return op;
    }

    public static int adminUpdateUsersInfo() {
        System.out.println("1=> update username");
        System.out.println("2=> update password");
        System.out.println("0=> back");
        System.out.print("enter operation num: ");
        op = Functions.readPositiveORZeroInt();
        return op;
    }

    public static int updatePersonalInfo() {

        System.out.println("1=> update Username");
        System.out.println("2=> update Password");
        System.out.println("3=> Update Email");
        System.out.println("4=> Update Phone");
        System.out.println("0=> Back");
        System.out.print("Enter your answer: ");
        op = Functions.readPositiveORZeroInt();

        return op;
    }

    public static int lecturerManageMenu() {
        System.out.println("choose only one option to manage: ");
        System.out.println("1=> Manage Exams");
        System.out.println("2=> Manage Reports");
        System.out.println("3=> Show personal Info");
        System.out.println("4=> Update personal Info");
        System.out.println("0=> exit");
        System.out.print("Enter your answer: ");
        op = Functions.readPositiveORZeroInt();

        return op;
    }

    public static int lecturerManageExamMenu() {

        System.out.println("Select operation");
        System.out.println("1=> add");
        System.out.println("2=> delete");
        System.out.println("3=> list");
        System.out.println("4=> Back");
        System.out.println("0=> exit");

        System.out.print("Enter your answer: ");
        op = Functions.readPositiveORZeroInt();

        return op;
    }

    public static int lecturerManagerReports() {
        System.out.println("1=> Generate Report");
        System.out.println("2=> View Reports");
        System.out.println("0=> Back");

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
        System.out.println("5=> Update Personal Information");
        System.out.println("0=> Exit");
        System.out.print("Enter your answer: ");
        op = Functions.readPositiveORZeroInt();

        return op;
    }

    public static int studentFinishedExamMenu() {
        System.out.println("1=> see exam grade");
        System.out.println("2=> see corrected exam answer");
        System.out.println("0=> back");
        System.out.print("Enter your answer: ");

        op = Functions.readPositiveORZeroInt();
        return op;
    }
}
