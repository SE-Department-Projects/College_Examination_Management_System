package helpers;

public class Menus {
    static private int op;

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
