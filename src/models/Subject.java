package models;

public class Subject {
    private int SubjID;
    private String name;
    private int LecturerID;

    Subject(int SubjID, String name, int LecturerID){
        this.SubjID = SubjID;
        this.name = name;
        this.LecturerID = LecturerID;
    }

    public int getSubjID() {
        return SubjID;
    }
}
